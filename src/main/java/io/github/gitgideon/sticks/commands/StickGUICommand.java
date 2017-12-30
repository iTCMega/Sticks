package io.github.gitgideon.sticks.commands;

import io.github.gitgideon.sticks.Sticks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class StickGUICommand implements CommandExecutor, Listener {

    private final HashMap<String, ItemStack> sticks;
    private final HashMap<String, String> messages;
    private final Sticks plugin;
    private final String inventoryTitle;

    public StickGUICommand(Sticks plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.sticks = plugin.getSticks();
        this.messages = plugin.getMessages();
        this.plugin = plugin;
        this.inventoryTitle = messages.get("inventory_title");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You can only run this command as player!");
            return true;
        }

        if (!sender.hasPermission("commands.gui")) {
            sender.sendMessage(messages.get("no_permission"));
            return true;
        }
        Player p = (Player) sender;
        Inventory inventory = plugin.getServer().createInventory(p, 36, inventoryTitle);

        for(Map.Entry<String, ItemStack> entry : sticks.entrySet()) {
            String key = entry.getKey();
            ItemStack stick = entry.getValue();
            if (p.hasPermission("sticks.gui." + key + "stick")) inventory.addItem(stick);
        }

        if (inventory.firstEmpty() == 0) {
            sender.sendMessage(ChatColor.RED + "You don't have any sticks!");
            return true;
        }
        p.openInventory(inventory);
        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;
        if (!e.getClickedInventory().getName().equals(inventoryTitle) && !e.getInventory().getName().equals(inventoryTitle))
            return;
        e.setCancelled(true);
        if (!e.getClickedInventory().getName().equals(inventoryTitle)) return;
        if (e.getClickedInventory().getItem(e.getSlot()) == null) return;
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getClickedInventory().getItem(e.getSlot());
        for (ItemStack stick : sticks.values()) {
            if (item.isSimilar(stick)) p.getInventory().addItem(item);
        }
    }
}

