package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.EconomyImplementer;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pay implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final EconomyImplementer economyImplementer = Betterssentials.economyImplementer;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + messageManager.getMessage("NoConsole"));
            return true;
        }
        Player player = (Player) sender;
        if (args.length < 2) {
            player.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player> <amount>"));
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
            return true;
        }
        if (messageManager.parseInt(args[1]) == null) {
            player.sendMessage(prefix + messageManager.getMessage("NumberNotValid"));
            return true;
        }
        int amount = messageManager.parseInt(args[1]);
        Double playerBalance = economyImplementer.getBalance(player.getName());
        if (playerBalance < amount) {
            player.sendMessage(prefix + messageManager.getMessage("NotEnoughMoney"));
            return true;
        }
        if (target.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage(prefix + messageManager.getMessage("PayYourself"));
            return true;
        }
        economyImplementer.withdrawPlayer(player.getName(), amount);
        economyImplementer.depositPlayer(target.getName(), amount);
        player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.vaultPlaceholder(messageManager.getMessage("GaveMoney")), target));
        target.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.vaultPlaceholder(messageManager.getMessage("RecievedMoney")), player));
        return true;
    }
}
