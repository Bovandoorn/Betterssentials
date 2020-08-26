package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Sudo implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();

        if (args.length == 0 || args.length == 1) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player> <command/c:message>"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        String chatCheck = args[1].substring(0, 2);

        if (target == null) {
            sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
            return true;
        }
        final String[] argument = Arrays.stream(args).skip(1).toArray(String[]::new);

        if (chatCheck.equalsIgnoreCase("c:")) {
            argument[0] = argument[0].replace("c:", "");
            target.chat(String.join(" ", argument));
            return true;
        }
        target.performCommand(String.join(" ", argument));
        return true;
    }
}
