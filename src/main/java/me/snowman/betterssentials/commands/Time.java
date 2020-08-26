package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Time implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();

        if (args.length == 0) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <day/night/4000>"));
            return true;
        }
        if (!(sender instanceof Player)) {
            if (args.length == 1) {
                sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <day/night/4000> <world>"));
                return true;
            }
            World world = Bukkit.getWorld(args[1]);
            switch (args[0]) {
                case "day":
                    world.setTime(1000);
                    sender.sendMessage(prefix + messageManager.getMessage("TimeSet").replace("%time%", "day").replace("%world%", world.getName()));
                    break;
                case "night":
                    world.setTime(13000);
                    sender.sendMessage(prefix + messageManager.getMessage("TimeSet").replace("%time%", "night").replace("%world%", world.getName()));
                default:
                    if (messageManager.parseInt(args[0]) == null) {
                        sender.sendMessage(prefix + messageManager.getMessage("NumberNotValid"));
                        return true;
                    }
                    world.setTime(Long.parseLong(args[1]));
                    sender.sendMessage(prefix + messageManager.getMessage("TimeSet").replace("%time%", args[1]).replace("%world%", world.getName()));
            }
            return true;
        }
        Player player = (Player) sender;
        World world;
        if (args.length == 1) {
            world = player.getWorld();
        } else {
            world = Bukkit.getWorld(args[1]);
        }
        switch (args[0]) {
            case "day":
                world.setTime(1000);
                player.sendMessage(prefix + messageManager.getMessage("TimeSet").replace("%time%", "day").replace("%world%", world.getName()));
                break;
            case "night":
                world.setTime(13000);
                player.sendMessage(prefix + messageManager.getMessage("TimeSet").replace("%time%", "night").replace("%world%", world.getName()));
                break;
            default:
                if (messageManager.parseInt(args[0]) == null) {
                    sender.sendMessage(prefix + messageManager.getMessage("NumberNotValid"));
                    return true;
                }
                world.setTime(Long.parseLong(args[0]));
                player.sendMessage(prefix + messageManager.getMessage("TimeSet").replace("%time%", args[0]).replace("%world%", world.getName()));
        }
        return true;
    }
}
