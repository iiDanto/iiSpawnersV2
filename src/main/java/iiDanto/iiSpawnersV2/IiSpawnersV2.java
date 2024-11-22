package iiDanto.iiSpawnersV2;

import iiDanto.iiSpawnersV2.db.SpawnerDatabase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class IiSpawnersV2 extends JavaPlugin {

    private SpawnerDatabase spawnerDatabase;

    @Override
    public void onEnable() {
        try {
            if (!getDataFolder().exists()){
                getDataFolder().mkdirs();
            }
            spawnerDatabase = new SpawnerDatabase(getDataFolder().getAbsolutePath() + "/iispawners.db");
        }catch (SQLException ex){
            ex.printStackTrace();
            getLogger().warning("Failed to connect to the database!" + ex.getMessage());
            Bukkit.getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {
        try{
            spawnerDatabase.closeConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
