package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.FileManager;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class Unban implements CommandExecutor {
    private final FileManager fileManager = Betterssentials.fileManager;
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            UUID uuid = player.getUniqueId();
            if (fileManager.getPlayer(uuid).getBoolean("values.banned")) {
                fileManager.getPlayer(uuid).set("values.banned", false);
                fileManager.savePlayer();
                sender.sendMessage(messageManager.getPrefix() + messageManager.getMessage("Unbanned").replace("%target%", player.getName()).replace("%player%", sender.getName()));
            } else {
                sender.sendMessage(messageManager.getPrefix() + messageManager.playerPlaceholder(messageManager.getMessage("NotBanned"), player));
                return true;
            }
            return true;
        }

        sender.sendMessage(messageManager.getPrefix() + messageManager.color("&cUsage: /unban <player>"));
        return true;
    }
}
