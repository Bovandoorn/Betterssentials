package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Repair implements CommandExecutor {
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
            ItemStack item = player.getInventory().getItemInMainHand();
            Damageable damageable = (Damageable) item.getItemMeta();
            if (damageable == null || damageable.getDamage() == 0) {
                player.sendMessage(prefix + messageManager.getMessage("NoItemRepair"));
                return true;
            }
            damageable.setDamage(0);
            item.setItemMeta((ItemMeta) damageable);
            player.sendMessage(prefix + messageManager.getMessage("Repaired"));
            return true;
        }
        switch (args[0]) {
            case "hand":
                ItemStack item = player.getInventory().getItemInMainHand();
                Damageable damageable = (Damageable) item.getItemMeta();
                if (damageable == null || damageable.getDamage() == 0) {
                    player.sendMessage(prefix + messageManager.getMessage("NoItemRepair"));
                    return true;
                }
                damageable.setDamage(0);
                item.setItemMeta((ItemMeta) damageable);
                player.sendMessage(prefix + messageManager.getMessage("Repaired"));
                break;
            case "all":
                List<String> repairedItems = new ArrayList<>();
                for (ItemStack items : player.getInventory().getContents()) {
                    if (items == null) continue;
                    Damageable damageable1 = (Damageable) items.getItemMeta();
                    if (damageable1 == null || damageable1.getDamage() == 0) continue;
                    damageable1.setDamage(0);
                    items.setItemMeta((ItemMeta) damageable1);
                    repairedItems.add(items.getType().name().toLowerCase().replace("_", " "));
                }
                if (repairedItems.size() == 0) {
                    player.sendMessage(prefix + messageManager.getMessage("NoRepairAll"));
                    break;
                }
                String itemsMessage = String.join(", ", repairedItems);
                player.sendMessage(prefix + messageManager.getMessage("RepairedAll").replace("%items%", itemsMessage));
                break;
        }
        return true;
    }
}
