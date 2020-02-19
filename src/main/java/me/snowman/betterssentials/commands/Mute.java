package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import me.snowman.betterssentials.managers.MuteManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mute implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final MuteManager muteManager = Betterssentials.muteManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (args.length == 0) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player> [time s/m/h]"));
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
            return true;
        }
        if (args.length == 1) {
            muteManager.setMute(player, 0);
            sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("Muted"), player));
            player.sendMessage(prefix + messageManager.getMessage("Gotmuted"));
            return true;
        }
        if (args.length == 2) {
            long time = System.currentTimeMillis() / 1000;
            if (args[1].contains("s") || args[1].contains("sec") || args[1].contains("second") || args[1].contains("seconds")) {
                if (messageManager.parseInt(args[1].replace("s", "")) != null || messageManager.parseInt(args[1].replace("sec", "")) != null || messageManager.parseInt(args[1].replace("second", "")) != null || messageManager.parseInt(args[1].replace("seconds", "")) != null) {
                    muteManager.setMute(player, time + messageManager.parseInt(args[1].replace("s", "").replace("sec", "").replace("second", "").replace("seconds", "")));
                    sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("Muted"), player));
                    player.sendMessage(prefix + messageManager.getMessage("Gotmuted"));
                    return true;
                }
            } else if (args[1].contains("m") || args[1].contains("min") || args[1].contains("minute") || args[1].contains("minutes")) {
                if (messageManager.parseInt(args[1].replace("m", "")) != null || messageManager.parseInt(args[1].replace("min", "")) != null || messageManager.parseInt(args[1].replace("minute", "")) != null || messageManager.parseInt(args[1].replace("minutes", "")) != null) {
                    muteManager.setMute(player, time + messageManager.parseInt(args[1].replace("m", "").replace("min", "").replace("minute", "").replace("minutes", "")) * 60);
                    sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("Muted"), player));
                    player.sendMessage(prefix + messageManager.getMessage("Gotmuted"));
                    return true;
                }
            } else if (args[1].contains("h") || args[1].contains("hour") || args[1].contains("hours")) {
                if (messageManager.parseInt(args[1].replace("h", "")) != null || messageManager.parseInt(args[1].replace("hour", "")) != null || messageManager.parseInt(args[1].replace("hours", "")) != null) {
                    muteManager.setMute(player, time + messageManager.parseInt(args[1].replace("h", "").replace("hour", "").replace("hours", "")) * 60 * 60);
                    sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("Muted"), player));
                    player.sendMessage(prefix + messageManager.getMessage("Gotmuted"));
                    return true;
                }
            } else {
                sender.sendMessage(prefix + messageManager.getMessage("NumberNotValid"));
                return true;
            }
        }
        return true;
    }
}
