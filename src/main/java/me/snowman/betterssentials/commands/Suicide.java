package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Suicide implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + messageManager.getMessage("NoConsole"));
            return true;
        }

        Player player = (Player) sender;
        player.setHealth(0);
        player.sendMessage(prefix + messageManager.getMessage("Suicide1"));
        Bukkit.broadcastMessage(prefix + messageManager.getMessage("Suicide2").replace("%player%", player.getDisplayName()));
        return true;
    }
}
