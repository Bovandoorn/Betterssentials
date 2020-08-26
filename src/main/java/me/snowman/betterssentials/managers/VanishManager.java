package me.snowman.betterssentials.managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishManager {

    private List<UUID> vanishList = new ArrayList<>();

    public boolean isVanished(Player player) {
        return vanishList.contains(player.getUniqueId());
    }

    public void setVanish(Player player) {
        vanishList.add(player.getUniqueId());
    }

    public void removeVanish(Player player) {
        vanishList.remove(player.getUniqueId());
    }
}
