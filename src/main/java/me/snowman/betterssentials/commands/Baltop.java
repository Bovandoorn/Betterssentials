package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.EconomyManager;
import me.snowman.betterssentials.managers.FileManager;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class Baltop implements CommandExecutor {
    private final EconomyManager economyManager = Betterssentials.economyManager;
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final FileManager fileManager = Betterssentials.fileManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int pageNumber;
        HashMap<UUID, Integer> balances = economyManager.sortedBalances();
        List<UUID> uuids = new ArrayList<>(balances.keySet());
        List<UUID> page = new ArrayList<>();
        page.removeAll(page);
        if (args.length == 0) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(args[0]);
        }

        for (int i = 0; i < 5; i++) {
            if ((i + 5 * (pageNumber - 1)) >= uuids.size()) continue;
            page.add(uuids.get(i + 5 * (pageNumber - 1)));
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        Date date = new Date();
        String formattedDate = dateFormat.format(date);
        sender.sendMessage(messageManager.getMessage("Baltopheader").replace("%date%", formattedDate).replace("%currentpage%", String.valueOf(pageNumber)).replace("%pages%", String.valueOf(balances.size() / 5 + 1)));
        for (int i = 0; i < page.size(); i++) {
            String ranking = String.valueOf((i + 1) + 5 * (pageNumber - 1));
            OfflinePlayer player = Bukkit.getOfflinePlayer(page.get(i));
            sender.sendMessage(messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("Baltopplayers").replace("%ranking%", ranking), player)));
        }
        if (pageNumber + 1 <= balances.size() / 5 + 1) {
            sender.sendMessage(messageManager.getMessage("Baltopfooter").replace("%nextpage%", String.valueOf(pageNumber + 1)));
        }
        return true;
    }
}
