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
        int amount;
        if (player == null) {
            sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
            return true;
        }
        if (material == null) {
            sender.sendMessage(prefix + messageManager.getMessage("ItemNotValid").replace("%item%", args[1]));
            return true;
        }
        if (args.length < 3) {
            amount = 1;
        } else {
            if (messageManager.parseInt(args[2]) == null) {
                sender.sendMessage(prefix + messageManager.getMessage("NumberNotValid"));
                return true;
            }
            amount = messageManager.parseInt(args[2]);
        }
        ItemStack item = new ItemStack(material, amount);
        player.getInventory().addItem(item);
        sender.sendMessage(prefix + messageManager.playerPlaceholder(messageManager.getMessage("GiveOthers"), player).replace("%item%", args[1]).replace("%amount%", String.valueOf(amount)));
        return true;
    }
}
