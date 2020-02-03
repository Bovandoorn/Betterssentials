package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
                return true;
            }
            Player player = (Player) sender;
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("FlyDisabled"), player));
                return true;
            } else {
                player.setAllowFlight(true);
                player.setFlying(true);
                player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("FlyEnabled"), player));
                return true;
            }
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("FlyDisabled"), player));
                return true;
            } else {
                player.setAllowFlight(true);
                player.setFlying(true);
                sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("FlyEnabled"), player));
                return true;
            }
        }
        return true;
    }
}
