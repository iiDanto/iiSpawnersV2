package iiDanto.iiSpawnersV2.gui;

import iiDanto.iiSpawnersV2.db.SpawnerDatabase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class SpawnerGUI {
    private final SpawnerDatabase db;

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
        p.openInventory(gui);
    }
}
