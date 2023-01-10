package net.hyren.cash.utils;

import net.hyren.cash.CashPlugin;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationParser {

  public static String to(Location location) {
    return location.getWorld().getName() + "@" + location.getX() + "@" + location.getY() + "@" + location.getZ() + "@"
        + location.getYaw() + "@" + location.getPitch();
  }

  public static Location from(String location, CashPlugin plugin) {
    String[] locationParts = location.split("@");
    World world = plugin.getServer().getWorld(locationParts[0]);
    double x = Double.parseDouble(locationParts[1]);
    double y = Double.parseDouble(locationParts[2]);
    double z = Double.parseDouble(locationParts[3]);
    float yaw = Float.parseFloat(locationParts[4]);
    float pitch = Float.parseFloat(locationParts[5]);

    return new Location(world, x, y, z, yaw, pitch);
  }
}
