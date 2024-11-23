package iiDanto.iiSpawnersV2.listeners;

import iiDanto.iiSpawnersV2.IiSpawnersV2;
import iiDanto.iiSpawnersV2.db.SpawnerDatabase;
import iiDanto.iiSpawnersV2.utils.SpawnerUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import java.sql.SQLException;
import java.util.List;

public class PlayerListener implements Listener {
    private final SpawnerDatabase db;
    private final SpawnerUtils su;
    private final IiSpawnersV2 plugin;

    public PlayerListener(SpawnerDatabase db, SpawnerUtils su, IiSpawnersV2 plugin) {
        this.db = db;
        this.su = su;
        this.plugin = plugin;
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
    public void onRightClickOnBlock(PlayerInteractEvent e) throws SQLException{
        if (e.getAction().isRightClick()){
            if (db.isSpawner(e.getClickedBlock().getLocation())){
                // TODO: Gui handling
                e.getPlayer().sendMessage("Spawner detected");
            }
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) throws SQLException{
        if (db.isSpawner(e.getBlock().getLocation())){
            e.setCancelled(true);
            int stack = db.getStack(e.getBlock().getLocation());
            String type = db.getType(e.getBlock().getLocation());
            if (e.getPlayer().isSneaking()){
                if (stack > 64){
                    db.setStack(e.getBlock().getLocation(), stack - 64);
                    e.getPlayer().sendRichMessage("<#ff0000>You broke 64x %t spawners. This stack now has %ns spawner(s).".replace("%t", type).replace("%ns", Integer.toString(stack - 64)));
                } else {
                    db.removeSpawner(e.getBlock().getLocation());
                    e.getPlayer().sendRichMessage("<#ff0000>You broke %amtx %t spawner(s). This stack now has 0 spawners".replace("%amt", Integer.toString(stack).replace("%t", type)));
                    e.getBlock().setType(Material.AIR);
                }
            } else {
                if (stack > 1){
                    db.setStack(e.getBlock().getLocation(), stack - 1);
                    e.getPlayer().sendRichMessage("<#ff0000>You broke 1x %t spawner. This stack now has %ns spawner(s)".replace("%t", type).replace("%ns", Integer.toString(stack - 1)));
                } else {
                    db.removeSpawner(e.getBlock().getLocation());
                    e.getPlayer().sendRichMessage("<#ff0000>You broke 1x %t spawner(s). This stack now has 0 spawners".replace("%t", type));
                    e.getBlock().setType(Material.AIR);
                }
            }
        }
    }

    public void updateAllSpawners() throws SQLException {
        while (true) {
            List<Location> spawnerLocations = db.getAllSpawnerLocations();
            for (Location location : spawnerLocations) {
                int exp = db.getStack(location);
                int money = db.getMoney(location);
                int stack = db.getStack(location);
                String type = db.getType(location);
                switch (type){
                    case "pig":
                        int MoneyToAdd1 = plugin.getConfig().getInt("options.money.pig");
                        int ExpToAdd1 = plugin.getConfig().getInt("options.exp.pig");
                        db.setMoney(location, (money + MoneyToAdd1 * stack));
                        db.setEXP(location, (exp + ExpToAdd1 * stack));
                    case "cow":
                        int MoneyToAdd2 = plugin.getConfig().getInt("options.money.cow");
                        int ExpToAdd2 = plugin.getConfig().getInt("options.exp.cow");
                        db.setMoney(location, (money + MoneyToAdd2 * stack));
                        db.setEXP(location, (exp + ExpToAdd2 * stack));
                    case "zombie":
                        int MoneyToAdd3 = plugin.getConfig().getInt("options.money.zombie");
                        int ExpToAdd3 = plugin.getConfig().getInt("options.exp.zombie");
                        db.setMoney(location, (money + MoneyToAdd3 * stack));
                        db.setEXP(location, (exp + ExpToAdd3 * stack));
                    case "skeleton":
                        int MoneyToAdd4 = plugin.getConfig().getInt("options.money.skeleton");
                        int ExpToAdd4 = plugin.getConfig().getInt("options.exp.skeleton");
                        db.setMoney(location, (money + MoneyToAdd4 * stack));
                        db.setEXP(location, (exp + ExpToAdd4 * stack));
                    case "creeper":
                        int MoneyToAdd5 = plugin.getConfig().getInt("options.money.creeper");
                        int ExpToAdd5 = plugin.getConfig().getInt("options.exp.creeper");
                        db.setMoney(location, (money + MoneyToAdd5 * stack));
                        db.setEXP(location, (exp + ExpToAdd5 * stack));
                    case "enderman":
                        int MoneyToAdd6 = plugin.getConfig().getInt("options.money.enderman");
                        int ExpToAdd6 = plugin.getConfig().getInt("options.exp.enderman");
                        db.setMoney(location, (money + MoneyToAdd6 * stack));
                        db.setEXP(location, (exp + ExpToAdd6 * stack));
                }
            }
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
