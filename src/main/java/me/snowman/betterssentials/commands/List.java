package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class List implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> onlinePlayers = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(o -> onlinePlayers.add(o.getDisplayName()));

        String joinedPlayers = String.join(", ", onlinePlayers);
        String numberPlayers = String.valueOf(Bukkit.getOnlinePlayers().size());
        String maxPlayers = String.valueOf(Bukkit.getMaxPlayers());
        sender.sendMessage(messageManager.getMessage("List").replace("%online%", numberPlayers).replace("%max%", maxPlayers).replace("%players%", joinedPlayers));
        return true;
    }
}
