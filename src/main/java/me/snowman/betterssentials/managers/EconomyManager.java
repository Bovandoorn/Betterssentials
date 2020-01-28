package me.snowman.betterssentials.managers;

import me.snowman.betterssentials.Betterssentials;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyManager {
    public static Economy econ = null;
    private final EconomyImplementer economyImplementer = Betterssentials.economyImplementer;
    private final FileManager fileManager = Betterssentials.fileManager;

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

}
