package me.snowman.betterssentials.managers;

import java.util.ArrayList;
import java.util.UUID;

public class InvseeManager {
    private ArrayList<UUID> usingInvsee = new ArrayList<>();

    public void addUsingInvsee(UUID uuid) {
        usingInvsee.add(uuid);
    }

    public void removeUsingInvsee(UUID uuid) {
        usingInvsee.remove(uuid);
    }

    public boolean isUsingInvsee(UUID uuid) {
        return usingInvsee.contains(uuid);
    }

}
