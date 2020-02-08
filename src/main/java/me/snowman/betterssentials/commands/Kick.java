package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Kick implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (args.length == 0) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player> [reason]"));
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            player.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
            return true;
        }
        if (args.length == 1) {
            player.kickPlayer(messageManager.getMessage("DefaultKick"));
            return true;
        }
        final String[] reason = Arrays.stream(args).skip(1).toArray(String[]::new);
        player.kickPlayer(String.join(" ", reason));
        return true;
    }
}
