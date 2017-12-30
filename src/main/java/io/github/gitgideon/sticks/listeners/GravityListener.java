package io.github.gitgideon.sticks.listeners;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class GravityListener implements Listener {

    private final ItemStack stick;

    public GravityListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.gravitystick")) return;
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;
        e.getRightClicked().setGravity(!e.getRightClicked().hasGravity());
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.gravitystick")) return;
        if (e.getAction() != Action.LEFT_CLICK_AIR && e.getAction() != Action.LEFT_CLICK_BLOCK) return;
        e.getPlayer().setGravity(!e.getPlayer().hasGravity());
    }

}
