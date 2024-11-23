package iiDanto.iiSpawnersV2.listeners;

import iiDanto.iiSpawnersV2.db.SpawnerDatabase;
import iiDanto.iiSpawnersV2.utils.SpawnerUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerListener implements Listener {
    private final SpawnerDatabase db;
    private final SpawnerUtils su;

    public PlayerListener(SpawnerDatabase db, SpawnerUtils su) {
        this.db = db;
        this.su = su;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if (e.getBlock().equals(Material.SPAWNER)){
            if (e.getPlayer().getItemInHand().getItemMeta().getPersistentDataContainer().has(su.key)){
                // TODO: Add the spawner to the database
            }
        }
    }
}
