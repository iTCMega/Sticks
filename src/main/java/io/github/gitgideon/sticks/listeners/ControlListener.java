package io.github.gitgideon.sticks.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ControlListener implements Listener {

    private final ItemStack stick;
    private final double onGround = -0.0784000015258789;

    public ControlListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.controlstick")) return;
        if (!e.getPlayer().isInsideVehicle()) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Entity vehicle = e.getPlayer().getVehicle();
        vehicle.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.5).setY(vehicle.getVelocity().getY()));
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.controlstick")) return;
        if (!e.getPlayer().isInsideVehicle()) return;
        if (e.getAction() != Action.LEFT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_AIR) return;
        Entity vehicle = e.getPlayer().getVehicle();
        if (vehicle.getVelocity().getY() != onGround) return;
        vehicle.setVelocity(vehicle.getVelocity().setY(vehicle.getVelocity().getY() + 0.8));
    }

}
