package me.snowman.betterssentials.events;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.EconomyManager;
import me.snowman.betterssentials.managers.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class FirstJoinEvent implements Listener {
    private final FileManager fileManager = Betterssentials.fileManager;
    private final EconomyManager economyManager = Betterssentials.economyManager;

    @EventHandler(priority = EventPriority.LOW)
    public void firstJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        fileManager.setupPlayer(uuid);
        if (fileManager.getPlayer(uuid).getString("name") == null || !fileManager.getPlayer(uuid).isSet("name")) {
            fileManager.getPlayer(uuid).set("name", player.getName());
            fileManager.getPlayer(uuid).set("values.balance", 0);
            fileManager.getPlayer(uuid).set("value.banned", false);
            fileManager.savePlayer();
        }
    }
}
