package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.FileManager;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BetterssentialsCommand implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final FileManager fileManager = Betterssentials.fileManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final String prefix = messageManager.getPrefix();
        if (args.length == 0) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <reload>"));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            fileManager.reloadConfig();
            fileManager.reloadMessages();
            sender.sendMessage(prefix + messageManager.getMessage("ConfigReload"));
            return true;
        }
        sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <reload>"));
        return true;
    }
}
