package io.github.gitgideon.sticks.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class KillListener implements Listener {

    private final ItemStack stick;

    public KillListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.killstick")) return;
        if (!(e.getRightClicked() instanceof LivingEntity)) return;
        ((LivingEntity) e.getRightClicked()).setHealth(0);
    }
}
