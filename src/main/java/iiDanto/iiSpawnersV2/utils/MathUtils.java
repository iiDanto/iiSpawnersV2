package iiDanto.iiSpawnersV2.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class MathUtils {
    public static String locationToString(Location location){
        return location.getWorld().getName()+":"+location.getBlockX()+":"+location.getBlockY()+":"+location.getBlockZ();
        // returns: world:100:60:10
    }

    public static Location str2loc(String str){
        String str2loc[]=str.split("\\:");
        Location loc = new Location(Bukkit.getWorld(str2loc[0]),0,0,0);
        loc.setX(Double.parseDouble(str2loc[1]));
        loc.setY(Double.parseDouble(str2loc[2]));
        loc.setZ(Double.parseDouble(str2loc[3]));
        return loc;
    }
}
