package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if(args.length == 0){
            if(sender instanceof Player){
                sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <0-10> [fly/walk]"));
            }else{
                sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <0-10> <player> [fly/walk]"));
            }
            return true;
        }
        if(messageManager.parseInt(args[0]) == null){
            sender.sendMessage(prefix + messageManager.getMessage("NumberNotValid"));
            return true;
        }

        if(messageManager.parseInt(args[0]) > 10 || messageManager.parseInt(args[0]) < 0){
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <0-10> [fly/walk] [player]"));
            return true;
        }

        if(args.length == 1){
            if(!(sender instanceof Player)){
                sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <0-10> <player> [fly/walk]"));
                return true;
            }
            Player player = (Player) sender;
            if(player.isFlying()){
                player.setFlySpeed((float) messageManager.parseInt(args[0]) / 10);
                player.sendMessage(prefix + messageManager.getMessage("Speed").replace("%flywalk%", "fly").replace("%speed%", args[0]));
            }else{
                player.setWalkSpeed((float) messageManager.parseInt(args[0]) / 10);
                player.sendMessage(prefix + messageManager.getMessage("Speed").replace("%flywalk%", "walk").replace("%speed%", args[0]));
            }
            return true;
        }

        if(args.length == 2){
            if(!(sender instanceof Player)){
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                    return true;
                }
                if(target.isFlying()){
                    target.setFlySpeed((float) messageManager.parseInt(args[0]) / 10);
                    target.sendMessage(prefix + messageManager.getMessage("SpeedSet").replace("%flywalk%", "fly").replace("%speed%", args[0]).replace("%player%", sender.getName()));
                }else{
                    target.setWalkSpeed((float) messageManager.parseInt(args[0]) / 10);
                    target.sendMessage(prefix + messageManager.getMessage("SpeedSet").replace("%flywalk%", "walk").replace("%speed%", args[0]).replace("%player%", sender.getName()));
                }
                return true;
            }
            Player player = (Player) sender;
            switch (args[1]){
                case "fly":
                    player.setFlySpeed((float) messageManager.parseInt(args[0]) / 10);
                    player.sendMessage(prefix + messageManager.getMessage("Speed").replace("%flywalk%", "fly").replace("%speed%", args[0]));
                    break;
                case "walk":
                    player.setWalkSpeed((float) messageManager.parseInt(args[0]) / 10);
                    player.sendMessage(prefix + messageManager.getMessage("Speed").replace("%flywalk%", "walk").replace("%speed%", args[0]));
                    break;
                default:
                    player.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <0-10> [fly/walk]"));
                    break;
            }
        }

        if(args.length == 3){
            Player target = Bukkit.getPlayer(args[2]);
            if (target == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            switch (args[1]){
                case "fly":
                    target.setFlySpeed((float) messageManager.parseInt(args[0]) / 10);
                    target.sendMessage(prefix + messageManager.getMessage("SpeedSet").replace("%flywalk%", "fly").replace("%speed%", args[0]).replace("%player%", sender.getName()));
                    break;
                case "walk":
                    target.setWalkSpeed((float) messageManager.parseInt(args[0]) / 10);
                    target.sendMessage(prefix + messageManager.getMessage("SpeedSet").replace("%flywalk%", "walk").replace("%speed%", args[0]).replace("%player%", sender.getName()));
                    break;
                default:
                    sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <0-10> [fly/walk] [player]"));
                    break;
            }
        }
        return true;
    }
}
