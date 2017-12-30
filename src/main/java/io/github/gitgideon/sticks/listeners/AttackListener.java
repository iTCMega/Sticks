package io.github.gitgideon.sticks.listeners;

import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class AttackListener implements Listener {

    private final ItemStack stick;
    private final HashMap<UUID, Creature> pairs = new HashMap<>();

    public AttackListener(ItemStack stick) {
        this.stick = stick;
    }

    @EventHandler
    public void onRightClickFirst(PlayerInteractAtEntityEvent e) {
        if (!e.getPlayer().hasPermission("sticks.use.attackstick")) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (pairs.containsKey(e.getPlayer().getUniqueId())) return;
        if (!(e.getRightClicked() instanceof Monster)) return;
        pairs.put(e.getPlayer().getUniqueId(), (Creature) e.getRightClicked());
    }

    @EventHandler
    public void onRightClickSecond(PlayerInteractAtEntityEvent e) {
        if (!e.getPlayer().hasPermission("sticks.use.attackstick")) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().isSimilar(stick)) return;
        if (!pairs.containsKey(e.getPlayer().getUniqueId())) return;
        if (pairs.get(e.getPlayer().getUniqueId()) == e.getRightClicked()) return;
        if (!(e.getRightClicked() instanceof LivingEntity)) return;
        pairs.get(e.getPlayer().getUniqueId()).setTarget((LivingEntity) e.getRightClicked());
        pairs.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (pairs.containsKey(e.getPlayer().getUniqueId()))
            pairs.remove(e.getPlayer().getUniqueId());
    }



}
