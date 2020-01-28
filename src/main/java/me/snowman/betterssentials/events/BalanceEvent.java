package me.snowman.betterssentials.events;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.EconomyImplementer;
import me.snowman.betterssentials.managers.EconomyManager;
import me.snowman.betterssentials.managers.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class BalanceEvent implements Listener {
    private final EconomyImplementer economyImplementer = Betterssentials.economyImplementer;
    private final EconomyManager economyManager = Betterssentials.economyManager;
    private final FileManager fileManager = Betterssentials.fileManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        UUID uuid = player.getUniqueId();
        Double balance = fileManager.getPlayer(uuid).getDouble("values.balance");
        economyImplementer.depositPlayer(name, balance);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        UUID uuid = player.getUniqueId();
        Double balance = economyImplementer.getBalance(name);
        fileManager.getPlayer(uuid).set("values.balance", balance);
        fileManager.savePlayer();
        economyImplementer.playerBank.remove(uuid);
    }
}
