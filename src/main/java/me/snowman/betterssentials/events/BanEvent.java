package me.snowman.betterssentials.events;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.UUID;

public class BanEvent implements Listener {
    private final FileManager fileManager = Betterssentials.fileManager;

    @EventHandler
    public void onBan(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (fileManager.getPlayer(uuid).getBoolean("values.banned")) {
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            event.setKickMessage(fileManager.getPlayer(uuid).getString("values.ban reason"));
        }
    }
}
