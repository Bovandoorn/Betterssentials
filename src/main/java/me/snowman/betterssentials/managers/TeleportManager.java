package me.snowman.betterssentials.managers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportManager {

    private Map<UUID, Location> backMap = new HashMap<>();

    public void addBackLocation(Player player) {
        backMap.put(player.getUniqueId(), player.getLocation());
    }

    public Location getBackLocation(Player player) {
        if (backMap.containsKey(player.getUniqueId())) return player.getLocation();
        return null;
    }
}
