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
    public static VanishManager vanishManager;
    public static TeleportManager teleportManager;

    public void onEnable() {
        loadManagers();
        pluginManager.loadEvents();
        pluginManager.loadCommands();
        loadEconomy();
        loadChat();
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
        vanishManager = new VanishManager();
        teleportManager = new TeleportManager();
    }

    public void loadEconomy() {
        if (getConfig().getBoolean("Enable-Economy")) {
            economyManager.setupEconomy();
            vaultHook.hook();
            economyManager.setupBalancesPlayers();
        } else {
            getServer().getConsoleSender().sendMessage(messageManager.getPrefix() + messageManager.color("&6Economy is currently &4disabled&6!"));
        }
    }

    public void loadChat() {
        if (getConfig().getBoolean("Enable-Chat")) {
            chatManager.setupChat();
        } else {
            getServer().getConsoleSender().sendMessage(messageManager.getPrefix() + messageManager.color("&6Chat manager is currently &4disabled&6!"));
        }
    }


}
