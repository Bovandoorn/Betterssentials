package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventory implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.getInventory().clear();
                player.sendMessage(messageManager.getPrefix() + messageManager.playerPlaceholder(messageManager.getMessage("ClearInv"), player));
                return true;
            }
            sender.sendMessage(messageManager.getPrefix() + messageManager.color("&cUsage: /" + label + " <player>"));
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            player.getInventory().clear();
            sender.sendMessage(messageManager.getPrefix() + messageManager.playerPlaceholder(messageManager.getMessage("ClearInv"), player));
            return true;
        }
        sender.sendMessage(messageManager.getPrefix() + messageManager.color("&cUsage: /" + label + " <player>"));
        return true;
    }
}
