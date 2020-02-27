package me.snowman.betterssentials.managers;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.commands.*;
import me.snowman.betterssentials.events.*;

public class PluginManager {

    public Betterssentials getPlugin() {
        return Betterssentials.getPlugin(Betterssentials.class);
    }

    public void loadCommands() {
        getPlugin().getCommand("afk").setExecutor(new Afk());
        getPlugin().getCommand("back").setExecutor(new Back());
        getPlugin().getCommand("balance").setExecutor(new Balance());
        getPlugin().getCommand("baltop").setExecutor(new Baltop());
        getPlugin().getCommand("ban").setExecutor(new Ban());
        getPlugin().getCommand("broadcast").setExecutor(new Broadcast());
        getPlugin().getCommand("betterssentials").setExecutor(new BetterssentialsCommand());
        getPlugin().getCommand("clearchat").setExecutor(new ClearChat());
        getPlugin().getCommand("clearinventory").setExecutor(new ClearInventory());
        getPlugin().getCommand("economy").setExecutor(new Economy());
        getPlugin().getCommand("enderchest").setExecutor(new EnderChest());
        getPlugin().getCommand("experience").setExecutor(new Experience());
        getPlugin().getCommand("feed").setExecutor(new Feed());
        getPlugin().getCommand("fly").setExecutor(new Fly());
        getPlugin().getCommand("gamemode").setExecutor(new Gamemode());
        getPlugin().getCommand("gms").setExecutor(new Gamemode());
        getPlugin().getCommand("gmc").setExecutor(new Gamemode());
        getPlugin().getCommand("gma").setExecutor(new Gamemode());
        getPlugin().getCommand("gmsp").setExecutor(new Gamemode());
        getPlugin().getCommand("give").setExecutor(new Give());
        getPlugin().getCommand("god").setExecutor(new God());
        getPlugin().getCommand("hat").setExecutor(new Hat());
        getPlugin().getCommand("heal").setExecutor(new Heal());
        getPlugin().getCommand("help").setExecutor(new Help());
        getPlugin().getCommand("invsee").setExecutor(new Invsee());
        getPlugin().getCommand("kick").setExecutor(new Kick());
        getPlugin().getCommand("kickall").setExecutor(new Kickall());
        getPlugin().getCommand("kill").setExecutor(new Kill());
        getPlugin().getCommand("killall").setExecutor(new Killall());
        getPlugin().getCommand("list").setExecutor(new List());
        getPlugin().getCommand("me").setExecutor(new Me());
        getPlugin().getCommand("mute").setExecutor(new Mute());
        getPlugin().getCommand("nick").setExecutor(new Nick());
        getPlugin().getCommand("pay").setExecutor(new Pay());
        getPlugin().getCommand("realname").setExecutor(new Realname());
        getPlugin().getCommand("repair").setExecutor(new Repair());
        getPlugin().getCommand("skull").setExecutor(new Skull());
        getPlugin().getCommand("unban").setExecutor(new Unban());
        getPlugin().getCommand("unmute").setExecutor(new Unmute());
    }

    public void loadEvents() {
        getPlugin().getServer().getPluginManager().registerEvents(new AfkEvent(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new BalanceEvent(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new FirstJoinEvent(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new BanEvent(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new EnderChestEvent(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new InvseeEvent(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new MuteEvent(), getPlugin());
    }
}
