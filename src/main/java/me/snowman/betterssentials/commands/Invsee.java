package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.InvseeManager;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Invsee implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final InvseeManager invseeManager = Betterssentials.invseeManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + messageManager.getMessage("NoConsole"));
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <player>"));
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(prefix + messageManager.getMessage("PlayerNotOnline"));
                return true;
            }
            Inventory inv = Bukkit.createInventory(target, 36, "Player");
            for (int i = 0; i < 36; i++) {
                ItemStack item = target.getInventory().getItem(i);
                if (item == null) continue;
                inv.setItem(i, item);
            }
            player.openInventory(inv);
            invseeManager.addUsingInvsee(player.getUniqueId());
        }
        return true;
    }
}
