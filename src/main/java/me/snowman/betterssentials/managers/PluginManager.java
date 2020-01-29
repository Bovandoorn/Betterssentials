package me.snowman.betterssentials.managers;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.commands.*;
import me.snowman.betterssentials.events.AfkEvent;
import me.snowman.betterssentials.events.BalanceEvent;
import me.snowman.betterssentials.events.BanEvent;
import me.snowman.betterssentials.events.FirstJoinEvent;
import org.bukkit.entity.Player;

public class PluginManager {

    public Betterssentials getPlugin() {
        return Betterssentials.getPlugin(Betterssentials.class);
    }

    public void loadCommands() {
        getPlugin().getCommand("afk").setExecutor(new Afk());
        getPlugin().getCommand("back").setExecutor(new Back());
        getPlugin().getCommand("balance").setExecutor(new Balance());
        getPlugin().getCommand("ban").setExecutor(new Ban());
        getPlugin().getCommand("broadcast").setExecutor(new Broadcast());
        getPlugin().getCommand("betterssentials").setExecutor(new BetterssentialsCommand());
        getPlugin().getCommand("clearchat").setExecutor(new ClearChat());
        getPlugin().getCommand("clearinventory").setExecutor(new ClearInventory());
        getPlugin().getCommand("unban").setExecutor(new Unban());
    }

    public void loadEvents() {
        getPlugin().getServer().getPluginManager().registerEvents(new AfkEvent(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new BalanceEvent(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new FirstJoinEvent(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new BanEvent(), getPlugin());
    }

    public boolean checkPerm(Player player, String perm) {
        if (player.hasPermission("betterssentials." + perm) || player.hasPermission("essentials." + perm) || player.hasPermission("*") || player.isOp()) {
            return true;
        } else {
            player.sendMessage("No perm");
            return false;
        }
    }
}
