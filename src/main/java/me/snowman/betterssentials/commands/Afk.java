package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.AfkManager;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Afk implements CommandExecutor {
    private final AfkManager afkManager = Betterssentials.afkManager;
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final String prefix = messageManager.getPrefix();
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
                return true;
            }
            Player player = (Player) sender;
            if (!afkManager.getAfkList().contains(player.getUniqueId())) {
                afkManager.getAfkList().add(player.getUniqueId());
                afkManager.sendAfk(player);
                return true;
            } else {
                afkManager.getAfkList().remove(player.getUniqueId());
                afkManager.sendAfk(player);
                return true;
            }
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            if (!afkManager.getAfkList().contains(player.getUniqueId())) {
                afkManager.getAfkList().add(player.getUniqueId());
                afkManager.sendAfk(player);
                return true;
            } else {
                afkManager.getAfkList().remove(player.getUniqueId());
                afkManager.sendAfk(player);
                return true;
            }
        }
        sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " [player]"));
        return true;
    }
}
