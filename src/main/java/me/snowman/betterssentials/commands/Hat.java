package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hat implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
            return true;
        }
        Player player = (Player) sender;
        if (player.getInventory().getHelmet() != null) {
            player.getInventory().addItem(player.getInventory().getHelmet());
            player.getInventory().setHelmet(null);
            player.sendMessage(prefix + messageManager.getMessage("RetrievedHat"));
            return true;
        }
        if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            player.sendMessage(prefix + messageManager.getMessage("HatInHand"));
            return true;
        }
        player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
        player.getInventory().setItemInMainHand(null);
        player.sendMessage(prefix + messageManager.getMessage("EnjoyHat"));
        return true;
    }
}
