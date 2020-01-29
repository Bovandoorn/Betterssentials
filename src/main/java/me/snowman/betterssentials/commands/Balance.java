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
        final String prefix = messageManager.getPrefix();
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
                return true;
            }
            Player player = (Player) sender;
            player.sendMessage(prefix +
                    messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("BalancePlayer"), player)));
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            sender.sendMessage(prefix +
                    messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("BalancePlayer"), player)));
            return true;
        }
        sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " [player]"));
        return true;
    }
}
