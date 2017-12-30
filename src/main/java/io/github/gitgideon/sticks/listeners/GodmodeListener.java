package io.github.gitgideon.sticks.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class GodmodeListener implements Listener {

    private final ItemStack stick;

    public GodmodeListener(ItemStack stick) {
        this.stick = stick;
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (!e.getEntity().hasPermission("sticks.use.godmodestick")) return;
        if (!((Player) e.getEntity()).getInventory().getItemInMainHand().isSimilar(stick)) return;
        e.setCancelled(true);
    }

}
