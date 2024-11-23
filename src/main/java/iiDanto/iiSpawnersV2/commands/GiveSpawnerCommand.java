package iiDanto.iiSpawnersV2.commands;

import iiDanto.iiSpawnersV2.utils.SpawnerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;

public class GiveSpawnerCommand implements CommandExecutor {
    String[] spawnertypes = {"pig", "cow", "zombie", "skeleton", "creeper", "enderman"};
    List<String> spawnerlist = Arrays.asList(spawnertypes);
    private SpawnerUtils spawnerUtils;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length > 3) {
            sender.sendRichMessage("<#ff0000>Invalid Arguments!");
            return true;
        }
        if (sender instanceof Player){
            Player p = (Player) sender;
            switch (args.length) {
                case 1:
                    String type = args[0];
                    if (spawnerlist.contains(type)) {
                        spawnerUtils.giveSpawner(p, type, 1);
                    } else {
                        p.sendRichMessage("<#ff0000>Invalid spawner type!");
                        p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                        return true;
                    }
                    break;
                case 2:
                    String type1 = args[1];
                    String player = args[0];
                    Player p1 = Bukkit.getPlayer(player);
                    if (p1 != null){
                        if (spawnerlist.contains(type1)){
                            spawnerUtils.giveSpawner(p1, type1, 1);
                        } else {
                            p.sendRichMessage("<#ff0000>Invalid spawner type!");
                            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                            return true;
                        }
                    } else {
                        p.sendRichMessage("<#ff0000>Invalid Player Argument!");
                        p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0f);
                    }
                    break;
                case 3:
                    String plrstr = args[0];
                    String spwnrtype = args[1];
                    Integer amtint = Integer.parseInt(args[2]);
                    Player p2 = Bukkit.getPlayer(plrstr);
                    if (p2 != null){
                        if (spawnerlist.contains(spwnrtype)){
                            spawnerUtils.giveSpawner(p2, spwnrtype, amtint);
                        } else {
                            p.sendRichMessage("<#ff0000>Invalid spawner type!");
                            p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                            return true;
                        }
                    } else {
                        p.sendRichMessage("<#ff0000>Invalid Player Argument!");
                        p.playSound(p, Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0f);
                    }
            }
        }
        return true;
    }
}