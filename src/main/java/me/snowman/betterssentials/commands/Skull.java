package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Skull implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + messageManager.getMessage("NoConsole"));
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <name>"));
            return true;
        }
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(args[0]);
        itemStack.setItemMeta(skullMeta);
        player.getInventory().addItem(itemStack);
        player.sendMessage(prefix + messageManager.getMessage("SkullGiven").replace("%player%", args[0]));
        return true;
    }
}
