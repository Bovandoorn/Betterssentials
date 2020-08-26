package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import me.snowman.betterssentials.managers.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final VanishManager vanishManager = Betterssentials.vanishManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + messageManager.getMessage("NoConsole"));
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            if (vanishManager.isVanished(player)) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.showPlayer(Betterssentials.getPlugin(Betterssentials.class), player);
                }
                vanishManager.removeVanish(player);
                player.sendMessage(prefix + messageManager.getMessage("Unvanished").replace("%player%", player.getDisplayName()));
            } else {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.hidePlayer(Betterssentials.getPlugin(Betterssentials.class), player);
                }
                vanishManager.setVanish(player);
                player.sendMessage(prefix + messageManager.getMessage("Vanished").replace("%player%", player.getDisplayName()));
            }
        }
        return true;
    }
}
