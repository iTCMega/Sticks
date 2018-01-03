package io.github.gitgideon.sticks.listeners;

import org.bukkit.entity.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class GrowupListener implements Listener {

    private final ItemStack stick;

    public GrowupListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.growupstick")) return;
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (!(e.getRightClicked() instanceof Ageable)) return;
        Ageable entity = (Ageable) e.getRightClicked();
        if (entity.isAdult())
            entity.setBaby();
        else
            entity.setAdult();
    }

}
