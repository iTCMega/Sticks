package io.github.gitgideon.sticks.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class MountListener implements Listener {

    private final ItemStack stick;

    public MountListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (!e.getPlayer().hasPermission("sticks.use.mountstick")) return;
        if (e.getPlayer().isSneaking())
            e.getPlayer().addPassenger(e.getRightClicked());
        else
            e.getRightClicked().addPassenger(e.getPlayer());
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.mountstick.throw")) return;
        if (e.getAction() != Action.LEFT_CLICK_AIR && e.getAction() != Action.LEFT_CLICK_BLOCK) return;
        Iterator iterator = e.getPlayer().getPassengers().iterator();
        while (iterator.hasNext()) {
            e.getPlayer().removePassenger((Entity) iterator.next());
        }
    }
}
