package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Baltop implements CommandExecutor {
    private final EconomyManager economyManager = Betterssentials.economyManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int pageNumber = 1;
        HashMap<UUID, Integer> balances = economyManager.sortedBalances();
        List<UUID> uuids = new ArrayList<>(balances.keySet());
        List<UUID> page = new ArrayList<>();
        if (args.length == 0) {

        } else {
            pageNumber = Integer.parseInt(args[0]);
        }

        for (int i = 0; i < 5; i++) {
            if ((i + 5 * (pageNumber - 1)) >= uuids.size()) continue;
            page.add(uuids.get(i + 5 * (pageNumber - 1)));
        }

        sender.sendMessage("Page " + pageNumber + " out of " + (balances.size() / 5 + 1));
        for (UUID uuid : page) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            sender.sendMessage(player.getName() + ": " + balances.get(uuid));
        }
        return true;
    }
}
