package me.snowman.betterssentials.managers;

import me.snowman.betterssentials.Betterssentials;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;

public class VaultHook {
    private Economy provider;

    public void hook() {
        provider = Betterssentials.economyImplementer;
        Bukkit.getServicesManager().register(Economy.class, this.provider, Betterssentials.pluginManager.getPlugin(), ServicePriority.Highest);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Vault hooked into &cBetterssentials"));

    }

    public void unhook() {
        Bukkit.getServicesManager().unregister(Economy.class, this.provider);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Vault unhooked into &cBetterssentials"));
    }
}
