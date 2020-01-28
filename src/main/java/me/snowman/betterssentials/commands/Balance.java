package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.EconomyImplementer;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Balance implements CommandExecutor {
    private final EconomyImplementer economyImplementer = Betterssentials.economyImplementer;
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(messageManager.getPrefix() + messageManager.color("&cUsage: /balance <player>"));
                return true;
            }
            Player player = (Player) sender;
            player.sendMessage(messageManager.getPrefix() +
                    messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("BalancePlayer"), player)));
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(messageManager.getPrefix() + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            sender.sendMessage(messageManager.getPrefix() +
                    messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("BalancePlayer"), player)));
            return true;
        }
        sender.sendMessage(messageManager.getPrefix() + messageManager.color("&cUsage: /balance [player]"));
        return true;
    }
}
