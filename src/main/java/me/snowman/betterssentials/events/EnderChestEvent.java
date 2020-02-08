package me.snowman.betterssentials.events;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.EnderChestManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EnderChestEvent implements Listener {
    private final EnderChestManager enderChestManager = Betterssentials.enderChestManager;

    @EventHandler
    public void onEc(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!enderChestManager.isUsingEc(player.getUniqueId())) {
            return;
        }
        if (!player.hasPermission("betterssentials.enderchest.modify")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCloseEc(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Player target = null;
        if (!enderChestManager.isUsingEc(player.getUniqueId())) {
            return;
        }
        if (player.getOpenInventory().getTopInventory().getHolder() instanceof Player) {
            target = (Player) player.getOpenInventory().getTopInventory().getHolder();
        }
        for (int i = 0; i < player.getEnderChest().getSize(); i++) {
            target.getEnderChest().setItem(i, player.getOpenInventory().getTopInventory().getItem(i));
        }
        enderChestManager.removeUsingEc(player.getUniqueId());
    }
}
