package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import me.snowman.betterssentials.managers.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Back implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final TeleportManager teleportManager = Betterssentials.teleportManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + messageManager.getMessage("NoConsole"));
            return true;
        }
        Player player = (Player) sender;

        if (teleportManager.getBackLocation(player) == null) {
            player.sendMessage(prefix + messageManager.getMessage("NoTp"));
            return true;
        }

        player.teleport(teleportManager.getBackLocation(player));
        player.sendMessage(prefix + messageManager.getMessage("Teleporting"));
        return true;
    }
}
