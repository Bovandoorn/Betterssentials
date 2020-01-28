package me.snowman.betterssentials.managers;

import me.snowman.betterssentials.Betterssentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AfkManager {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private ArrayList<UUID> afkList = new ArrayList<>();
    private HashMap<UUID, Integer> afkTimer = new HashMap<>();

    public ArrayList<UUID> getAfkList() {
        return this.afkList;
    }

    public HashMap<UUID, Integer> getAfkTimer() {
        return this.afkTimer;
    }

    public void sendAfk(Player p) {
        if (!this.afkList.contains(p.getUniqueId())) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage(messageManager.getPrefix() + messageManager.playerPlaceholder(messageManager.getMessage("IsNotAFK"), p));
            }
        } else {
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage(messageManager.getPrefix() + messageManager.playerPlaceholder(messageManager.getMessage("IsAFK"), p));
            }
        }
    }

    public void makeAfk(Player p) {
        (new BukkitRunnable() {
            public void run() {
                if (afkTimer.get(p.getUniqueId()) == null) {
                    cancel();
                    return;
                }
                afkTimer.put(p.getUniqueId(), afkTimer.get(p.getUniqueId()) + 1);
                if (afkTimer.get(p.getUniqueId()) == 60) {
                    afkList.add(p.getUniqueId());
                    sendAfk(p);
                }
            }
        }).runTaskTimerAsynchronously(Betterssentials.pluginManager.getPlugin(), 0L, 20L);
    }
}