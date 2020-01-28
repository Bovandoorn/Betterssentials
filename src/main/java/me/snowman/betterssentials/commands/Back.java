package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Back implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) ;
        Player player = (Player) sender;
        Betterssentials.pluginManager.checkPerm(player, "back");
        return true;
    }
}
