package io.github.gitgideon.sticks.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class JumpListener implements Listener {

    private final ItemStack stick;
    private final double onGround = -0.0784000015258789;

    public JumpListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (!e.getPlayer().hasPermission("sticks.use.jumpstick")) return;
        if (e.getPlayer().getVelocity().getY() != onGround) return;
        e.getPlayer().setVelocity(e.getPlayer().getVelocity().add(new Vector(0, 1, 0)));
    }
}
