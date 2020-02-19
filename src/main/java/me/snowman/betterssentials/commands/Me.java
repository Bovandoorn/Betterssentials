package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Me implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(messageManager.color("&cUsage: /" + label + " <description>"));
            return true;
        }
        String message = messageManager.getMessage("Me").replace("%message%", String.join(" ", args));
        if (sender instanceof Player) {
            message = message.replace("%player%", ((Player) sender).getDisplayName());
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.sendMessage(message);
            }
        } else {
            System.out.println(message);
            message = message.replace("%player%", "@");
            System.out.println(message);
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.sendMessage(message);
            }
            sender.sendMessage(message);
        }
        return true;
    }
}
