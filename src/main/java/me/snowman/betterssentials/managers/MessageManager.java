package me.snowman.betterssentials.managers;

import me.snowman.betterssentials.Betterssentials;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class MessageManager {
    private final EconomyImplementer economyImplementer = Betterssentials.economyImplementer;

    public String getPrefix() {
        return getMessage("Prefix");
    }

    public String getMessage(String message) {
        return color(Betterssentials.fileManager.getMessages().getString(message));
    }

    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public List<String> color(List<String> list) {
        return list.stream().map(this::color).collect(Collectors.toList());
    }

    public Integer parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String playerPlaceholder(String string, Player player) {
        string = string.replace("%player%", player.getName());
        string = string.replace("%money%", String.valueOf(economyImplementer.getBalance(player.getName())));
        return string;
    }

    public String playerPlaceholder(String string, OfflinePlayer player) {
        string = string.replace("%player%", player.getName());
        string = string.replace("%money%", String.valueOf(economyImplementer.getBalance(player)));
        return string;
    }

    public String vaultPlaceholder(String string) {
        string = string.replace("%money_sign%", economyImplementer.currencyNameSingular());
        return string;
    }
}
