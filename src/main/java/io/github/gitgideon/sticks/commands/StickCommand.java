package io.github.gitgideon.sticks.commands;

import io.github.gitgideon.sticks.Sticks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class StickCommand implements CommandExecutor {

    private HashMap<String, String> messages;
    private String name;
    private ItemStack stick;

    public StickCommand(Sticks plugin, String name) {
        this.messages = plugin.getMessages();
        this.name = name;
        this.stick = plugin.getSticks().get(name);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(messages.get("not_a_player"));
            return true;
        }

        if (!sender.hasPermission("sticks.commands." + name)) {
            sender.sendMessage(messages.get("no_permission"));
            return true;
        }

        ((Player) sender).getInventory().addItem(stick);
        sender.sendMessage(messages.get("success").replace("STICK", name));
        return true;
    }
}
