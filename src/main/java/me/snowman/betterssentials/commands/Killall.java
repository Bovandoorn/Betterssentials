package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Killall implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        int entities = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (sender instanceof Player) {
                if (sender.getName().equalsIgnoreCase(player.getName())) continue;
            }
            player.setHealth(0);
            entities++;
        }
        sender.sendMessage(prefix + messageManager.getMessage("Killall").replace("%entities%", String.valueOf(entities)));
        return true;
    }
}
