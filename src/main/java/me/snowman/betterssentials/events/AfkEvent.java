package me.snowman.betterssentials.events;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.AfkManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AfkEvent implements Listener {
    private final AfkManager afkManager = Betterssentials.afkManager;

    @EventHandler
    public void onAfk(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!afkManager.getAfkList().contains(player.getUniqueId()))
            afkManager.getAfkList().remove(player.getUniqueId());
        if (afkManager.getAfkList().contains(player.getUniqueId()) && afkManager.getAfkTimer().get(player.getUniqueId()) == 0) {
            afkManager.getAfkList().remove(player.getUniqueId());
            afkManager.sendAfk(player);
        }
        if (!afkManager.getAfkTimer().containsKey(player.getUniqueId()))
            afkManager.makeAfk(player);
        afkManager.getAfkTimer().put(player.getUniqueId(), 0);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (afkManager.getAfkTimer().containsKey(player.getUniqueId())) {
            afkManager.getAfkTimer().remove(player.getUniqueId());
            afkManager.getAfkList().remove(player.getUniqueId());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!afkManager.getAfkTimer().containsKey(player.getUniqueId()))
            afkManager.makeAfk(player);
        afkManager.getAfkTimer().put(player.getUniqueId(), 0);
    }
}
