package me.snowman.betterssentials.managers;

import java.util.ArrayList;
import java.util.UUID;

public class EnderChestManager {
    private ArrayList<UUID> usingEc = new ArrayList<>();

    public void addUsingEc(UUID uuid) {
        usingEc.add(uuid);
    }

    public ArrayList getUsingEc() {
        return usingEc;
    }

    public void removedUsingEc(UUID uuid) {
        usingEc.remove(uuid);
    }

    public boolean isUsingEc(UUID uuid) {
        return usingEc.contains(uuid);
    }
}
