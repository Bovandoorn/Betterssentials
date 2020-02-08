package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Kickall implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String reason;
        if (args.length == 0) {
            reason = messageManager.getMessage("DefaultKick");
        }
        reason = String.join(" ", Arrays.stream(args).skip(1).toArray(String[]::new));
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (sender instanceof Player) {
                if (sender.getName().equalsIgnoreCase(player.getName())) continue;
            }
            player.kickPlayer(reason);
        }
        return true;
    }
}
