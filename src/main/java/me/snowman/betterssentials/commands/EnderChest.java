package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.EnderChestManager;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EnderChest implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final EnderChestManager enderChestManager = Betterssentials.enderChestManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
            return true;
        }
        Player player = (Player) sender;
        Inventory ec = null;
        if (args.length == 0) {
            ec = Bukkit.createInventory(player, 27, "Ender Chest");
            for (int i = 0; i < player.getEnderChest().getSize(); i++) {
                ItemStack item = player.getEnderChest().getItem(i);
                if (item == null) continue;
                ec.setItem(i, item);
            }
        }
        if (args.length > 0) {
            Player target = Bukkit.getPlayer(args[0]);
            ec = Bukkit.createInventory(target, 27, "Ender Chest");
            if (target == null) {
                sender.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            for (int i = 0; i < target.getEnderChest().getSize(); i++) {
                ItemStack item = target.getEnderChest().getItem(i);
                if (item == null) continue;
                ec.setItem(i, item);
            }
        }
        player.openInventory(ec);
        enderChestManager.addUsingEc(player.getUniqueId());
        return true;
    }
}
