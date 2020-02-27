package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Killall implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messageManager.getPrefix();
        int entities = 0;
        if (args.length == 0) {
            sender.sendMessage(prefix + messageManager.color("&cUsage: /" + label + " <all/items/mobs/players>"));
            return true;
        }
        switch (args[0]) {
            case "players":
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (sender instanceof Player) {
                        if (sender.getName().equalsIgnoreCase(player.getName())) continue;
                    }
                    player.setHealth(0);
                    entities++;
                }
                break;
            case "mobs":
                for (World world : Bukkit.getWorlds()) {
                    for (LivingEntity entity : Bukkit.getWorld(world.getName()).getLivingEntities()) {
                        if (entity instanceof Player) continue;
                        entity.setHealth(0);
                        entities++;
                    }
                }
                break;
            case "items":
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : Bukkit.getWorld(world.getName()).getEntities()) {
                        if (!(entity instanceof Item)) continue;
                        Item item = (Item) entity;
                        item.remove();
                        entities++;
                    }
                }
                break;
            case "all":
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : Bukkit.getWorld(world.getName()).getEntities()) {
                        if (!(entity instanceof Item)) continue;
                        Item item = (Item) entity;
                        item.remove();
                        entities++;
                    }

                    for (LivingEntity entity : Bukkit.getWorld(world.getName()).getLivingEntities()) {
                        if (entity instanceof Player) continue;
                        entity.setHealth(0);
                        entities++;
                    }
                }
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (sender instanceof Player) {
                        if (sender.getName().equalsIgnoreCase(player.getName())) continue;
                    }
                    player.setHealth(0);
                    entities++;
                }
                break;
        }
        sender.sendMessage(prefix + messageManager.getMessage("Killall").replace("%entities%", String.valueOf(entities)));
        return true;
    }
}
