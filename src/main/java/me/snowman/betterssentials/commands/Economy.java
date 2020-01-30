package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.EconomyImplementer;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Economy implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final EconomyImplementer economyImplementer = Betterssentials.economyImplementer;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (args.length < 2) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <give/take/set/reset> <player> <amount>"));
            return true;
        }
        String action = args[0];
        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
            return true;
        }
        if (action.equalsIgnoreCase("reset")) {
            economyImplementer.withdrawPlayer(player, economyImplementer.getBalance(player));
            sender.sendMessage(messageManager.getPrefix() + messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("EconomyResetP"), player)));
            player.sendMessage(messageManager.getPrefix() + messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("EconomyResetT"), player)));
            return true;
        }
        Integer amount = messageManager.parseInt(args[2]);
        if (amount == null) {
            sender.sendMessage(prefix + messageManager.getMessage("NumberNotValid"));
            return true;
        }

        switch (action) {
            case "give":
                economyImplementer.depositPlayer(player, amount);
                sender.sendMessage(messageManager.getPrefix() + messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("EconomyGiveP"), player)).replace("%balance%", String.valueOf(amount)));
                player.sendMessage(messageManager.getPrefix() + messageManager.vaultPlaceholder(messageManager.getMessage("EconomyGiveT").replace("%balance%", String.valueOf(amount))));
                break;
            case "take":
                economyImplementer.withdrawPlayer(player, amount);
                sender.sendMessage(messageManager.getPrefix() + messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("EconomyTakeP"), player)).replace("%balance%", String.valueOf(amount)));
                player.sendMessage(messageManager.getPrefix() + messageManager.vaultPlaceholder(messageManager.getMessage("EconomyTakeT").replace("%balance%", String.valueOf(amount))));
                break;
            case "set":
                economyImplementer.withdrawPlayer(player, economyImplementer.getBalance(player));
                economyImplementer.depositPlayer(player, amount);
                sender.sendMessage(messageManager.getPrefix() + messageManager.vaultPlaceholder(messageManager.playerPlaceholder(messageManager.getMessage("EconomySetP"), player)).replace("%balance%", String.valueOf(amount)));
                player.sendMessage(messageManager.getPrefix() + messageManager.vaultPlaceholder(messageManager.getMessage("EconomySetT").replace("%balance%", String.valueOf(amount))));
                break;
            default:
                sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <give/take/set/reset> <player> <amount>"));
                break;
        }
        return true;
    }
}
