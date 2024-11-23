package iiDanto.iiSpawnersV2.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class SpawnerUtils {
    public void giveSpawner(Player p, String type, Integer amount) throws SQLException{
        ItemStack spawner = new ItemStack(Material.SPAWNER);
        ItemMeta spawnermeta = spawner.getItemMeta();
        MiniMessage miniMessage = MiniMessage.miniMessage();
        spawnermeta.addEnchant(Enchantment.MENDING, 1, true);
        switch (type){
            case "pig":
                TextComponent textComponent = Component.text("<color:#e342f5><bold>PIG <color:#FFFFFF>SPAWNER");
                spawnermeta.setDisplayName(textComponent.content());
                break;
            case "cow":
                TextComponent textComponent2 = Component.text("<color:#FFFFFF><bold>COW <color:#FFFFFF>SPAWNER");
                spawnermeta.setDisplayName(textComponent2.content());
                break;
            case "zombie":
                TextComponent textComponent3 = Component.text("<color:#42f542><bold>ZOMBIE <color:#FFFFFF>SPAWNER");
                spawnermeta.setDisplayName(textComponent3.content());
        }
    }
}
