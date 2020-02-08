package me.snowman.betterssentials.events;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.InvseeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InvseeEvent implements Listener {
    private final InvseeManager invseeManager = Betterssentials.invseeManager;

    @EventHandler
    public void onInvsee(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Player target = null;
        if (!invseeManager.isUsingInvsee(player.getUniqueId())) {
            return;
        }
        if (!player.hasPermission("betterssentials.invsee.modify")) {
            event.setCancelled(true);
        }
        if (player.getOpenInventory().getTopInventory().getHolder() instanceof Player) {
            target = (Player) player.getOpenInventory().getTopInventory().getHolder();
        }
        for (int i = 0; i < 36; i++) {
            target.getInventory().setItem(i, player.getOpenInventory().getTopInventory().getItem(i));
        }
    }

    @EventHandler
    public void onCloseInvsee(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Player target = null;
        if (!invseeManager.isUsingInvsee(player.getUniqueId())) {
            return;
        }
        if (player.getOpenInventory().getTopInventory().getHolder() instanceof Player) {
            target = (Player) player.getOpenInventory().getTopInventory().getHolder();
        }
        for (int i = 0; i < 36; i++) {
            target.getInventory().setItem(i, player.getOpenInventory().getTopInventory().getItem(i));
        }
        invseeManager.removeUsingInvsee(player.getUniqueId());
    }
}
