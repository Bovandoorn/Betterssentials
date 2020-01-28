package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.FileManager;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

public class Ban implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final FileManager fileManager = Betterssentials.fileManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(messageManager.getPrefix() + messageManager.color("&cUsage: /ban <player> [reason]"));
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(messageManager.getPrefix() + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            UUID uuid = player.getUniqueId();
            player.kickPlayer(messageManager.playerPlaceholder(messageManager.getMessage("DefaultBan"), player));
            fileManager.getPlayer(uuid).set("values.banned", true);
            fileManager.getPlayer(uuid).set("values.ban reason", messageManager.getMessage("DefaultBan"));
            fileManager.savePlayer();
            return true;
        } else {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(messageManager.getPrefix() + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            UUID uuid = player.getUniqueId();
            final String[] reason = Arrays.stream(args).skip(1).toArray(String[]::new);
            player.kickPlayer(messageManager.getMessage("YouBanned") + " " + String.join(" ", reason));
            fileManager.getPlayer(uuid).set("values.banned", true);
            fileManager.getPlayer(uuid).set("values.ban reason", messageManager.getMessage("YouBanned") + " " + String.join(" ", reason));
            fileManager.savePlayer();
            return true;
        }
    }
}
