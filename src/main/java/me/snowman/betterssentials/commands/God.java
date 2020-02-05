package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God implements CommandExecutor {
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
            if (player.isInvulnerable()) {
                player.setInvulnerable(false);
                player.sendMessage(prefix + messageManager.getMessage("GodMeDisabled"));
            } else {
                player.setInvulnerable(true);
                player.sendMessage(prefix + messageManager.getMessage("GodMeEnabled"));
            }
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            if (player.isInvulnerable()) {
                player.setInvulnerable(false);
                sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GodOthersDisabled"), player));
            } else {
                player.setInvulnerable(true);
                sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GodOthersEnabled"), player));
            }
        }
        return true;
    }
}
