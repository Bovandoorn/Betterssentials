package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (label.equalsIgnoreCase("gms")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
                    return true;
                }
                Player player = (Player) sender;
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
                return true;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            player.setGameMode(GameMode.SURVIVAL);
            sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
            return true;
        }
        if (label.equalsIgnoreCase("gmc")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
                    return true;
                }
                Player player = (Player) sender;
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
                return true;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            player.setGameMode(GameMode.CREATIVE);
            sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
            return true;
        }
        if (label.equalsIgnoreCase("gma")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
                    return true;
                }
                Player player = (Player) sender;
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
                return true;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            player.setGameMode(GameMode.ADVENTURE);
            sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
            return true;
        }
        if (label.equalsIgnoreCase("gmsp")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
                    return true;
                }
                Player player = (Player) sender;
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
                return true;
            }
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            player.setGameMode(GameMode.SPECTATOR);
            sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <gamemode/survival/adventure/spectator> [player]"));
            return true;
        }
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <gamemode/survival/adventure/spectator> <player>"));
                return true;
            }
            Player player = (Player) sender;
            switch (args[0]) {
                case "creative":
                case "1":
                    player.setGameMode(GameMode.CREATIVE);
                    break;
                case "survival":
                case "0":
                    player.setGameMode(GameMode.SURVIVAL);
                    break;
                case "adventure":
                case "2":
                    player.setGameMode(GameMode.ADVENTURE);
                    break;
                case "spectator":
                case "3":
                    player.setGameMode(GameMode.SPECTATOR);
                    break;
            }
            player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
        }
        if (args.length == 2) {
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            switch (args[0]) {
                case "creative":
                case "1":
                    player.setGameMode(GameMode.CREATIVE);
                    break;
                case "survival":
                case "0":
                    player.setGameMode(GameMode.SURVIVAL);
                    break;
                case "adventure":
                case "2":
                    player.setGameMode(GameMode.ADVENTURE);
                    break;
                case "spectator":
                case "3":
                    player.setGameMode(GameMode.SPECTATOR);
                    break;
            }
            sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GmSet"), player));
            return true;
        }
        return true;
    }
}
