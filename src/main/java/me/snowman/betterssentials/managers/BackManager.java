package me.snowman.betterssentials.managers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BackManager {

    public Map<UUID, Location> backLocations = new HashMap<>();

    public void setBackLocation(Player player) {
        backLocations.put(player.getUniqueId(), player.getLocation());
    }

    public Location getBackLocation(Player player) {
        return backLocations.get(player.getUniqueId());
    }
}
