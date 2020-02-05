package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.FileManager;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {
    private final FileManager fileManager = Betterssentials.fileManager;
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        int lines = fileManager.getConfig().getInt("Clear-Chat-Lines");
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.hasPermission("betterssentials.clearchat.ignore")) continue;
            for (int i = 0; i < lines; i++) {
                player.sendMessage(" ");
            }
        }
        Bukkit.broadcastMessage(prefix + messageManager.getMessage("ClearedChat").replace("%player%", sender.getName()));

        return true;
    }
}
