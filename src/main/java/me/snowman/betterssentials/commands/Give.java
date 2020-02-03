package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Give implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (args.length < 2) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player> <item> [amount]"));
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        Material material = Material.matchMaterial(args[1].toUpperCase());
        if (player == null) {
            sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
            return true;
        }
        if (material == null) {
            sender.sendMessage(prefix + messageManager.getMessage("ItemNotValid").replace("%item%", args[1]));
            return true;
        }
        if (messageManager.parseInt(args[2]) == null) {
            sender.sendMessage(prefix + messageManager.getMessage("NumberNotValid"));
            return true;
        }
        ItemStack item = new ItemStack(material, messageManager.parseInt(args[2]));
        player.getInventory().addItem(item);
        sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GiveOthers"), player).replace("%item%", args[1]).replace("%amount%", args[2]));
        return true;
    }
}
