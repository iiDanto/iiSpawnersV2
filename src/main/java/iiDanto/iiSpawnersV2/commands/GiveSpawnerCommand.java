package iiDanto.iiSpawnersV2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiveSpawnerCommand implements CommandExecutor {
    String[] spawnertypes = {"pig", "cow", "zombie", "skeleton", "creeper", "enderman"};
    List<String> spawnerlist = Arrays.asList(spawnertypes);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length > 3){
           sender.sendRichMessage("<#ff0000>Invalid Arguments!");
           return true;
        }
        switch (args.length){
            case 1:
                String type = args[0];
                if (spawnerlist.contains(type)){

                }
                break;
        }
        return true;
    }
}
