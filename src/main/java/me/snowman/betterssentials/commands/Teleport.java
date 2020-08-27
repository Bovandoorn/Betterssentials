package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import me.snowman.betterssentials.managers.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final TeleportManager teleportManager = Betterssentials.teleportManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();

        if (!(sender instanceof Player) && args.length < 2) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player1> <player2>"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player1> [player2]"));
            return true;
        }
        Player target1 = Bukkit.getPlayer(args[0]);

        if (target1 == null) {
            sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
        }
        if (args.length == 1) {

            return true;
        }
        Player target2 = Bukkit.getPlayer(args[1]);
        if (!(sender instanceof Player)) {
            if (target2 == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            return true;
        }

        return true;
    }
}
