package iiDanto.iiSpawnersV2.listeners;

import iiDanto.iiSpawnersV2.db.SpawnerDatabase;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerListener implements Listener {
    private final SpawnerDatabase db;

    public PlayerListener(SpawnerDatabase db) {
        this.db = db;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        // TODO: Check if it's a spawner, if so add the spawner to the database.
    }
}
