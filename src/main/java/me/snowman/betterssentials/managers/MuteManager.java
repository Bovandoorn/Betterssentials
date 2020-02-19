package me.snowman.betterssentials.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MuteManager {

    private HashMap<UUID, Long> mutes = new HashMap<>();

    public HashMap<UUID, Long> getMutes() {
        return mutes;
    }

    public void setMute(Player player, long time) {
        mutes.put(player.getUniqueId(), time);
    }

    public boolean checkMute(Player player) {
        long time = System.currentTimeMillis() / 1000;
        if (mutes.get(player.getUniqueId()) == 0) {
            return true;
        }
        if (mutes.get(player.getUniqueId()) <= time) {
            mutes.remove(player.getUniqueId());
            return false;
        }
        return true;
    }
}
