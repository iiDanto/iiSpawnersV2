package iiDanto.iiSpawnersV2.listeners;

import iiDanto.iiSpawnersV2.db.SpawnerDatabase;
import iiDanto.iiSpawnersV2.utils.SpawnerUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

import java.sql.SQLException;

public class PlayerListener implements Listener {
    private final SpawnerDatabase db;
    private final SpawnerUtils su;

    public PlayerListener(SpawnerDatabase db, SpawnerUtils su) {
        this.db = db;
        this.su = su;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) throws SQLException {
        if (e.getBlock().equals(Material.SPAWNER)){
            if (e.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().has(su.key)){
                String type = e.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().get(su.key, PersistentDataType.STRING);
                db.addSpawner(e.getBlock().getLocation(), type);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) throws SQLException{
        if (db.isSpawner(e.getBlock().getLocation())){
            if (db.getStack(e.getBlock().getLocation()) > 64){
                // TODO: Remove 64 From the stack if the player is sneaking, if not remove one.
            }
        }
    }

    public void updateAllSpawners() throws SQLException {
        while (true) {
            // TODO: Update Spawner money and exp using the values provided in the config.yml
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
