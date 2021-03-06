package me.snowman.betterssentials.managers;

import me.snowman.betterssentials.Betterssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileManager {
    private final Betterssentials plugin = Betterssentials.pluginManager.getPlugin();

    private File folderPlayerData = new File(plugin.getDataFolder(), "data" + File.separator + "player data" + File.separator);
    private File folderData = new File(plugin.getDataFolder(), "data" + File.separator);
    private File messagesFile, playerFile;
    private FileConfiguration messagesCfg, playerCfg;

    public void setupMessages() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        messagesFile = new File(plugin.getDataFolder(), "messages-en.yml");
        if (!messagesFile.exists()) {
            plugin.saveResource("messages-en.yml", true);
            messagesCfg = YamlConfiguration.loadConfiguration(messagesFile);
            Bukkit.getServer().getConsoleSender().sendMessage(Betterssentials.messageManager.getPrefix() + Betterssentials.messageManager.color("&bMessages file created successfully."));
        }
        if (messagesCfg == null) {
            messagesCfg = YamlConfiguration.loadConfiguration(messagesFile);
        }
    }

    public FileConfiguration getMessages() {
        return messagesCfg;
    }

    public void saveMessages() {
        try {
            messagesCfg.save(messagesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadMessages() {
        messagesFile = new File(plugin.getDataFolder(), "messages-en.yml");
        messagesCfg = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public void setupConfig() {
        Betterssentials.pluginManager.getPlugin().saveDefaultConfig();
    }

    public FileConfiguration getConfig() {
        return Betterssentials.pluginManager.getPlugin().getConfig();
    }

    public void saveConfig() {
        Betterssentials.pluginManager.getPlugin().saveConfig();
    }

    public void reloadConfig() {
        Betterssentials.pluginManager.getPlugin().reloadConfig();
    }

    public void setupPlayer(UUID uuid) {
        String name = Bukkit.getPlayer(uuid).getName();
        playerFile = new File(plugin.getDataFolder(), "data" + File.separator + "player data" + File.separator + uuid + ".yml");
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        if (!folderData.exists()) {
            folderData.mkdir();
        }
        if (!folderPlayerData.exists()) {
            folderPlayerData.mkdir();
        }
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Playerfile for &c" + name + " &6created."));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        playerCfg = YamlConfiguration.loadConfiguration(playerFile);
    }

    public FileConfiguration getPlayer(UUID uuid) {
        if (playerFile == null || !playerFile.getName().equalsIgnoreCase(uuid + ".yml")) {
            playerFile = new File(plugin.getDataFolder(), "data" + File.separator + "player data" + File.separator + uuid + ".yml");
            playerCfg = YamlConfiguration.loadConfiguration(playerFile);
        }
        return playerCfg;
    }

    public void savePlayer() {
        try {
            playerCfg.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
