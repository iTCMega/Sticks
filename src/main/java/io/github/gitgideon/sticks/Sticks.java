package io.github.gitgideon.sticks;

import io.github.gitgideon.sticks.commands.StickCommand;
import io.github.gitgideon.sticks.commands.StickGUICommand;
import io.github.gitgideon.sticks.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public final class Sticks extends JavaPlugin {

    private final HashMap<String, String> messages = new HashMap<>();
    private final HashMap<String, ItemStack> sticks = new HashMap<>();

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        setupMessages();
        setupSticks();
        setupCommands();
        setupListeners();
        initializeRecipes();
    }

    private void setupCommands() {
        getConfig().getConfigurationSection("sticks").getKeys(false)
                .forEach(key -> getCommand(key + "stick").setExecutor(new StickCommand(this, key)));
        getCommand("sticks").setExecutor(new StickGUICommand(this));
    }

    private void setupMessages() {
        getConfig().getConfigurationSection("messages").getKeys(false).forEach(key ->
                messages.put(key, ChatColor.translateAlternateColorCodes('&',
                                getConfig().getString("messages." + key))));
    }

    private void setupSticks() {
        getConfig().getConfigurationSection("sticks").getKeys(false).forEach(key -> {
           ItemStack stick = new ItemStack(Material.STICK);
           ItemMeta meta = stick.getItemMeta();
           meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                   getConfig().getString("sticks." + key + ".name")));
           stick.setItemMeta(meta);
           sticks.put(key, stick);
        });
    }

    private void setupListeners() {
        PluginManager pm = getServer().getPluginManager();
        if (getConfig().getBoolean("sticks.attack.enabled.usage"))
            pm.registerEvents(new AttackListener(sticks.get("attack")), this);
        if (getConfig().getBoolean("sticks.control.enabled.usage"))
            pm.registerEvents(new ControlListener(sticks.get("control")), this);
        if (getConfig().getBoolean("sticks.godmode.enabled.usage"))
            pm.registerEvents(new GodmodeListener(sticks.get("godmode")), this);
        if (getConfig().getBoolean("sticks.gravity.enabled.usage"))
            pm.registerEvents(new GravityListener(sticks.get("gravity")), this);
        if (getConfig().getBoolean("sticks.growup.enabled.usage"))
            pm.registerEvents(new GrowupListener(sticks.get("growup")), this);
        if (getConfig().getBoolean("sticks.kill.enabled.usage"))
            pm.registerEvents(new KillListener(sticks.get("kill")), this);
        if (getConfig().getBoolean("sticks.mount.enabled.usage"))
            pm.registerEvents(new MountListener(sticks.get("mount")), this);
        if (getConfig().getBoolean("sticks.pickup.enabled.usage"))
            pm.registerEvents(new PickupListener(sticks.get("pickup")), this);
        if (getConfig().getBoolean("sticks.slime.enabled.usage"))
            pm.registerEvents(new SlimeListener(sticks.get("slime")), this);
        if (getConfig().getBoolean("sticks.witch.enabled.usage"))
            pm.registerEvents(new WitchListener(sticks.get("witch")), this);
        if (getConfig().getBoolean("sticks.jump.enabled.usage"))
            pm.registerEvents(new JumpListener(sticks.get("jump")), this);
    }

    private void initializeRecipes() {
        getConfig().getConfigurationSection("recipes").getKeys(false).forEach(key -> {
            if (getConfig().getBoolean("sticks." + key + ".enabled.recipe"))
                createRecipe(key, sticks.get(key));
        });
    }

    private void createRecipe(String name, ItemStack result) {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, name), result);
        recipe.shape("ABC", "DEF", "GHI");
        List<String> list = getConfig().getStringList("recipes." + name);
        try {
            recipe.setIngredient('A', Material.matchMaterial(list.get(0)));
            recipe.setIngredient('B', Material.matchMaterial(list.get(1)));
            recipe.setIngredient('C', Material.matchMaterial(list.get(2)));
            recipe.setIngredient('D', Material.matchMaterial(list.get(3)));
            recipe.setIngredient('E', Material.matchMaterial(list.get(4)));
            recipe.setIngredient('F', Material.matchMaterial(list.get(5)));
            recipe.setIngredient('G', Material.matchMaterial(list.get(6)));
            recipe.setIngredient('H', Material.matchMaterial(list.get(7)));
            recipe.setIngredient('I', Material.matchMaterial(list.get(8)));
            getServer().addRecipe(recipe);
        } catch (Exception e) {
            getLogger().info("ATTENTION! You misspelled or typed in a non-existing material in the config, so the " +
                    "recipe for the " + name + " couldn't be enabled!");
        }
    }

    public HashMap<String, ItemStack> getSticks() {
        return sticks;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

}
