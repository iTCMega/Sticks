package io.github.gitgideon.sticks.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WitchListener implements Listener {

    private final ItemStack stick;

    public WitchListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onFlyClick(PlayerInteractEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.witchstick")) return;
        e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(4));
    }

}
