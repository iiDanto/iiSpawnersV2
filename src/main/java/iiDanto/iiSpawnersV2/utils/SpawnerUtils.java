package iiDanto.iiSpawnersV2.utils;

import iiDanto.iiSpawnersV2.IiSpawnersV2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.sql.SQLException;

public class SpawnerUtils {
    private final IiSpawnersV2 plugin;
    private final NamespacedKey key;

    public SpawnerUtils(IiSpawnersV2 plugin) {
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "type");
    }

    public void giveSpawner(Player p, String type, Integer amount) {
        ItemStack spawner = new ItemStack(Material.SPAWNER);
        ItemMeta spawnermeta = spawner.getItemMeta();
        spawnermeta.addEnchant(Enchantment.MENDING, 1, true);
        switch (type) {
            case "pig":
                spawnermeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "PIG" + ChatColor.WHITE + "" + ChatColor.BOLD + "SPAWNER");
                spawnermeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "pig");
                break;
            case "cow":
                spawnermeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "COW" + ChatColor.WHITE + "" + ChatColor.BOLD + "SPAWNER");
                spawnermeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "cow");
                break;
            case "zombie":
                spawnermeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "ZOMBIE" + ChatColor.WHITE + "" + ChatColor.BOLD + "SPAWNER");
                spawnermeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "zombie");
                break;
            case "skeleton":
                spawnermeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "SKELETON" + ChatColor.WHITE + "" + ChatColor.BOLD + "SPAWNER");
                spawnermeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "skeleton");
                break;
            case "creeper":
                spawnermeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "CREEPER" + ChatColor.WHITE + "" + ChatColor.BOLD + "SPAWNER");
                spawnermeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "creeper");
                break;
            case "enderman":
                spawnermeta.setDisplayName(ChatColor.BLACK + "" + ChatColor.BOLD + "ENDERMAN" + ChatColor.WHITE + "" + ChatColor.BOLD + "SPAWNER");
                spawnermeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "enderman");
                break;
        }
        spawner.setItemMeta(spawnermeta);
        for (int i = 0; i < amount; i++) {
            p.getInventory().addItem(spawner);
        }
    }
}
   