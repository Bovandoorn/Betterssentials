package me.snowman.betterssentials.managers;

import me.snowman.betterssentials.Betterssentials;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class EconomyManager {
    public static Economy econ = null;
    private final EconomyImplementer economyImplementer = Betterssentials.economyImplementer;
    private final FileManager fileManager = Betterssentials.fileManager;
    private final PluginManager pluginManager = Betterssentials.pluginManager;

    public Map<UUID, Double> balances = new HashMap<>();

    public boolean setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public void setupBalancesPlayers() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (economyImplementer.playerBank.get(players.getUniqueId()) == null) {
//                playerBank.put(players.getUniqueId(), fileManager.getPlayer(players.getUniqueId()).getDouble("values.balance"));
                economyImplementer.depositPlayer(players, fileManager.getPlayer(players.getUniqueId()).getDouble("values.balance") - economyImplementer.getBalance(players));
            }
        }

    }

    public HashMap sortedBalances() {
        File folderPlayerData = new File(pluginManager.getPlugin().getDataFolder(), "data" + File.separator + "player data" + File.separator);

        for (File file : folderPlayerData.listFiles()) {
            UUID uuid = UUID.fromString(file.getName().replace(".yml", ""));
            balances.put(uuid, fileManager.getPlayer(uuid).getDouble("values.balance"));
        }
        for (UUID uuid : economyImplementer.playerBank.keySet()) {
            balances.put(uuid, economyImplementer.playerBank.get(uuid));
        }
        return balances.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

}
