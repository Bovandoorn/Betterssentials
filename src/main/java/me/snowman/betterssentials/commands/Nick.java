package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (args.length == 0) {
            sender.sendMessage(messageManager.color("&cUsage: /" + label + " [player] <nickname/off>"));
            return true;
        }

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + messageManager.getMessage("NoConsole"));
                return true;
            }
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("off")) {
                player.sendMessage(prefix + messageManager.getMessage("RemovedNick"));
                return true;
            }
            player.setDisplayName(messageManager.color(args[0]));
            player.sendMessage(prefix + messageManager.getMessage("SetNick").replace("%nick%", messageManager.color(args[0])));
            return true;
        }

        if (args.length == 2) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            player.setDisplayName(messageManager.color(args[1]));
            player.sendMessage(prefix + messageManager.getMessage("SetNick").replace("%nick%", messageManager.color(args[1])));
            sender.sendMessage(prefix + messageManager.getMessage("SetSNick").replace("%player%", player.getName()).replace("%nick%", messageManager.color(args[1])));
            return true;
        }
        return true;
    }
}
