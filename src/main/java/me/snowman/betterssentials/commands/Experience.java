package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Experience implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (args.length == 0) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player> [show/set/give] [amount|L<level>]"));
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
            return true;
        }
        if (args.length == 1) {
            sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("ShowXp"), player));
            return true;
        }
        if (args.length == 2 && args[1].equalsIgnoreCase("show")) {
            sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("ShowXp"), player));
            return true;
        }
        if (args.length == 3) {
            if (messageManager.parseInt(args[2].replace("L", "")) == null) {
                sender.sendMessage(prefix + messageManager.getMessage("NumberNotValid"));
                return true;
            }

            switch (args[1]) {
                case "set":
                    if (args[2].contains("L") && messageManager.parseInt(args[2].replace("L", "")) != null) {
                        player.setLevel(messageManager.parseInt(args[2].replace("L", "")));
                        player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("LevelsSet"), player));
                        return true;
                    }
                    player.setTotalExperience(0);
                    player.setLevel(0);
                    player.setExp(0);
                    player.giveExp(Integer.parseInt(args[2]) - player.getTotalExperience());
                    player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("XpSet"), player));
                    return true;
                case "give":
                    if (args[2].contains("L") && messageManager.parseInt(args[2].replace("L", "")) != null) {
                        player.setLevel(player.getLevel() + messageManager.parseInt(args[2].replace("L", "")));
                        player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("LevelsAdded"), player).replace("%givenlevel%", args[2].replace("L", "")));
                        return true;
                    }
                    player.giveExp(messageManager.parseInt(args[2]));
                    player.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("XpAdded"), player));
                    return true;
                default:
                    sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player> [show/set/give] [amount|L<level>]"));
                    return true;
            }
        }
        sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player> [show/set/give] [amount|L<level>]"));
        return true;
    }
}
