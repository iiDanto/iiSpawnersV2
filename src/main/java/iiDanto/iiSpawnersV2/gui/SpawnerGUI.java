package iiDanto.iiSpawnersV2.gui;

import iiDanto.iiSpawnersV2.db.SpawnerDatabase;
import iiDanto.iiSpawnersV2.utils.MathUtils;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import net.md_5.bungee.api.ChatColor;

import java.sql.SQLException;
import java.util.ArrayList;

public class SpawnerGUI {
    private final SpawnerDatabase db;
    private NamespacedKey nsk;

    public SpawnerGUI(SpawnerDatabase db) {
        this.db = db;
    }

    public void openSpawnerGUI(Player p, Location location) throws SQLException{
        String type = db.getType(location);
        int stack = db.getStack(location);
        int money = db.getMoney(location);
        int exp = db.getEXP(location);
        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GRAY + "" + ChatColor.BOLD + "%amtx %t Spawner(s)".replace("%amt", Integer.toString(stack).replace("%t", type)));
        ItemStack bg = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        for (int i = 0; i < 27; i++){
            gui.setItem(i, bg);
        }
        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        ItemMeta goldmeta = gold.getItemMeta();
        goldmeta.getPersistentDataContainer().set(nsk, PersistentDataType.STRING, MathUtils.locationToString(location));
        goldmeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "COLLECT MONEY");
        ArrayList<String> lore = new ArrayList();
        double fillPercent = db.getProcentFilled(location);
        lore.add(ChatColor.GOLD + String.valueOf(fillPercent) + "%" + org.bukkit.ChatColor.GRAY + " filled");
        goldmeta.setLore(lore);
        gold.setItemMeta(goldmeta);
        gui.setItem(11, gold);
        Material head = Material.getMaterial(type.toUpperCase() + "_HEAD");
        ItemStack headitem;
        if (head != null) {
            headitem = new ItemStack(Material.valueOf(type.toUpperCase() + "_HEAD"));
        } else {
            headitem = new ItemStack(Material.SPAWNER);
        }
        ChatColor colour = ChatColor.of("#2df505");
        ItemMeta headmeta = headitem.getItemMeta();
        headmeta.setDisplayName(colour + "" + ChatColor.BOLD + "%amt %type Spawner(s)".replace("%amt", Integer.toString(stack).replace("%type", type)));
        headitem.setItemMeta(headmeta);
        gui.setItem(13, headitem);
        p.openInventory(gui);
    }
}
