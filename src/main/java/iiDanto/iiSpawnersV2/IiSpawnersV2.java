package iiDanto.iiSpawnersV2;

import iiDanto.iiSpawnersV2.commands.GiveSpawnerCommand;
import iiDanto.iiSpawnersV2.db.SpawnerDatabase;
import iiDanto.iiSpawnersV2.gui.SpawnerGUI;
import iiDanto.iiSpawnersV2.listeners.PlayerListener;
import iiDanto.iiSpawnersV2.utils.SpawnerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.Timer;

public final class IiSpawnersV2 extends JavaPlugin {

    private SpawnerDatabase spawnerDatabase;
    private SpawnerUtils SpawnerUtils;
    private SpawnerGUI spawnerGUI;

    @Override
    public void onEnable() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            spawnerDatabase = new SpawnerDatabase(getDataFolder().getAbsolutePath() + "/iispawners.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            getLogger().warning("Failed to connect to the database!" + ex.getMessage());
            Bukkit.getPluginManager().disablePlugin(this);
        }
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerListener(spawnerDatabase, SpawnerUtils, this, spawnerGUI), this);
        getCommand("givespawner").setExecutor(new GiveSpawnerCommand());
    }

        @Override
        public void onDisable () {
            try {
                spawnerDatabase.closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        // UPDATE
    }

