package io.github.gitgideon.sticks.listeners;

import io.github.gitgideon.sticks.Sticks;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class TeleportListener implements Listener {

    private final ItemStack stick;
    private final int range;

    public TeleportListener(ItemStack stick, Sticks plugin) {
        this.stick = stick;
        range = plugin.getConfig().getInt("sticks.teleport.range");
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (!e.getPlayer().hasPermission("sticks.use.teleportstick")) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        Entity entity = e.getRightClicked();
        int x = ThreadLocalRandom.current().nextInt(range);
        int y = ThreadLocalRandom.current().nextInt(range);
        entity.teleport(entity.getWorld().getHighestBlockAt(x, y).getLocation());
    }
}
