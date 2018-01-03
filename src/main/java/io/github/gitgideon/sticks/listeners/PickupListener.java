package io.github.gitgideon.sticks.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PickupListener implements Listener {

    private final ItemStack stick;

    public PickupListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onRightClickEntity(PlayerInteractAtEntityEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.pickupstick")) return;
        if (!(e.getRightClicked() instanceof Player)) return;
        Player p = (Player) e.getRightClicked();
        p.setCanPickupItems(!p.getCanPickupItems());
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.pickupstick")) return;
        e.getPlayer().setCanPickupItems(!e.getPlayer().getCanPickupItems());
    }
}
