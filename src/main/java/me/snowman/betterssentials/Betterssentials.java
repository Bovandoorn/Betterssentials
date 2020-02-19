package me.snowman.betterssentials;

import me.snowman.betterssentials.managers.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Betterssentials extends JavaPlugin {
    public static PluginManager pluginManager;
    public static MessageManager messageManager;
    public static FileManager fileManager;
    public static AfkManager afkManager;
    public static EconomyImplementer economyImplementer;
    public static EconomyManager economyManager;
    private static VaultHook vaultHook;
    public static EnderChestManager enderChestManager;
    public static InvseeManager invseeManager;
    public static ChatManager chatManager;
    public static MuteManager muteManager;

    public void onEnable() {
        loadManagers();
        pluginManager.loadEvents();
        pluginManager.loadCommands();
        economyManager.setupEconomy();
        chatManager.setupChat();
        vaultHook.hook();
        economyManager.setupBalancesPlayers();
        fileManager.setupMessages();
        fileManager.setupConfig();
    }

    public void onDisable() {
        vaultHook.unhook();
    }

    public void loadManagers() {
        pluginManager = new PluginManager();
        fileManager = new FileManager();
        economyImplementer = new EconomyImplementer();
        vaultHook = new VaultHook();
        economyManager = new EconomyManager();
        messageManager = new MessageManager();
        afkManager = new AfkManager();
        enderChestManager = new EnderChestManager();
        invseeManager = new InvseeManager();
        chatManager = new ChatManager();
        muteManager = new MuteManager();
    }


}
