package io.github.gitgideon.sticks.listeners;

import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class SlimeListener implements Listener {

    private final ItemStack stick;

    public SlimeListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!e.getPlayer().hasPermission("sticks.use.slimestick")) return;
        if (e.getRightClicked() instanceof Slime) {
            Slime slime = (Slime) e.getRightClicked();
            if (!e.getPlayer().isSneaking())
                slime.setSize(slime.getSize() + 1);
            else
            if (slime.getSize() != 1)
                slime.setSize(slime.getSize() - 1);
        } else if (e.getRightClicked() instanceof MagmaCube) {
            MagmaCube cube = (MagmaCube) e.getRightClicked();
            if (!e.getPlayer().isSneaking())
                cube.setSize(cube.getSize() + 1);
            else
            if (cube.getSize() != 1)
                cube.setSize(cube.getSize() - 1);
        }
    }

}
