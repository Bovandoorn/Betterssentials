package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {
    private final FileManager fileManager = Betterssentials.fileManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int lines = fileManager.getConfig().getInt("Clear-Chat-Lines");
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            for (int i = 0; i < lines; i++) {
                player.sendMessage(" ");
            }
        }
        return false;
    }
}
