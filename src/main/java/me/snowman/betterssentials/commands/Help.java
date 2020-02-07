package me.snowman.betterssentials.commands;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Help implements CommandExecutor {
    private final MessageManager messageManager = Betterssentials.messageManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int pageNumber;
        List<String> helpList = new ArrayList<>();
        if (args.length == 0) {
            pageNumber = 1;
            for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
                helpList.add(plugin.getName());
            }

            for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
                helpList.addAll(plugin.getDescription().getCommands().keySet());
            }

            sender.sendMessage(messageManager.getMessage("Helpheader").replace("%currentpage%", String.valueOf(pageNumber)).replace("%pages%", String.valueOf(helpList.size() / 9 + 1)));
            for (int i = 0; i < 9; i++) {
                if (i >= helpList.size()) {
                    sender.sendMessage(messageManager.getMessage("Helpfooter").replace("%nextpage%", String.valueOf(helpList.size() / 9 + 1)));
                    return true;
                }
                if (Bukkit.getServer().getPluginManager().getPlugin(helpList.get(i)) != null) {
                    sender.sendMessage(messageManager.getMessage("Helpplugins").replace("%plugin%", Bukkit.getServer().getPluginManager().getPlugin(helpList.get(i)).getName()));
                } else {
                    String commandName = helpList.get(i);
                    String commandDescription;
                    if (Bukkit.getServer().getPluginCommand(helpList.get(i)) == null) {
                        commandDescription = "";
                    } else {
                        commandDescription = Bukkit.getServer().getPluginCommand(helpList.get(i)).getDescription();
                    }
                    sender.sendMessage(messageManager.getMessage("Helpcommands").replace("%command%", commandName).replace("%description%", commandDescription));
                }
            }
            if (pageNumber + 1 <= helpList.size() / 9 + 1) {
                sender.sendMessage(messageManager.getMessage("Helpfooter").replace("%nextpage%", String.valueOf(pageNumber + 1)));
            }
        }
        if (args.length == 1 && messageManager.parseInt(args[0]) != null) {
            pageNumber = messageManager.parseInt(args[0]);
            for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
                helpList.add(plugin.getName());
            }

            for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
                helpList.addAll(plugin.getDescription().getCommands().keySet());
            }
            if (pageNumber > helpList.size() / 9 + 1) {
                sender.sendMessage(messageManager.getMessage("Helpheader").replace("%currentpage%", String.valueOf(pageNumber)).replace("%pages%", String.valueOf(helpList.size() / 9 + 1)));
                sender.sendMessage(messageManager.getMessage("Helpfooter").replace("%nextpage%", String.valueOf(helpList.size() / 9 + 1)));
                return true;
            }
            sender.sendMessage(messageManager.getMessage("Helpheader").replace("%currentpage%", String.valueOf(pageNumber)).replace("%pages%", String.valueOf(helpList.size() / 9 + 1)));
            for (int i = 0; i < 9; i++) {
                int line = i + 9 * (pageNumber - 1);
                if (line >= helpList.size()) {
                    sender.sendMessage(messageManager.getMessage("Helpfooter").replace("%nextpage%", String.valueOf(helpList.size() / 9 + 1)));
                    return true;
                }
                if (Bukkit.getServer().getPluginManager().getPlugin(helpList.get(line)) != null) {
                    sender.sendMessage(messageManager.getMessage("Helpplugins").replace("%plugin%", Bukkit.getServer().getPluginManager().getPlugin(helpList.get(line)).getName()));
                } else {
                    String commandName = helpList.get(line);
                    String commandDescription;
                    if (Bukkit.getServer().getPluginCommand(helpList.get(line)) == null) {
                        commandDescription = "";
                    } else {
                        commandDescription = Bukkit.getServer().getPluginCommand(helpList.get(line)).getDescription();
                    }
                    sender.sendMessage(messageManager.getMessage("Helpcommands").replace("%command%", commandName).replace("%description%", commandDescription));
                }
            }
            if (pageNumber + 1 <= helpList.size() / 9 + 1) {
                sender.sendMessage(messageManager.getMessage("Helpfooter").replace("%nextpage%", String.valueOf(pageNumber + 1)));
            }
            return true;
        }
        if (args.length == 1 && messageManager.parseInt(args[0]) == null) {
            pageNumber = 1;
            for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
                if (plugin.getName().equalsIgnoreCase(args[0])) {
                    helpList.addAll(plugin.getDescription().getCommands().keySet());
                    String labelCap = label.substring(0, 1).toUpperCase() + label.substring(1);
                    sender.sendMessage(messageManager.getMessage("Helpheaderplugin").replace("%command%", labelCap + " " + args[0]).replace("%currentpage%", String.valueOf(pageNumber)).replace("%pages%", String.valueOf(helpList.size() / 9 + 1)).replace("%plugin%", plugin.getName()));
                    for (int i = 0; i < 9; i++) {
                        if (i >= helpList.size()) {
                            sender.sendMessage(messageManager.getMessage("Helpfooter").replace("%nextpage%", String.valueOf(helpList.size() / 9 + 1)));
                            return true;
                        }
                        String commandName = helpList.get(i);
                        String commandDescription;
                        if (Bukkit.getServer().getPluginCommand(helpList.get(i)) == null) {
                            commandDescription = "";
                        } else {
                            commandDescription = Bukkit.getServer().getPluginCommand(helpList.get(i)).getDescription();
                        }
                        sender.sendMessage(messageManager.getMessage("Helpcommands").replace("%command%", commandName).replace("%description%", commandDescription));
                    }
                    if (pageNumber + 1 <= helpList.size() / 9 + 1) {
                        sender.sendMessage(messageManager.getMessage("Helpfooterplugin").replace("%nextpage%", String.valueOf(pageNumber + 1)).replace("%plugin%", args[0]));
                    }
                    return true;
                }
            }
        }
        if (args.length == 2 && messageManager.parseInt(args[0]) == null && messageManager.parseInt(args[1]) != null) {
            pageNumber = messageManager.parseInt(args[1]);
            for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
                if (plugin.getName().equalsIgnoreCase(args[0])) {
                    helpList.addAll(plugin.getDescription().getCommands().keySet());
                    String labelCap = label.substring(0, 1).toUpperCase() + label.substring(1);
                    sender.sendMessage(messageManager.getMessage("Helpheaderplugin").replace("%command%", labelCap + " " + args[0]).replace("%currentpage%", String.valueOf(pageNumber)).replace("%pages%", String.valueOf(helpList.size() / 9 + 1)).replace("%plugin%", plugin.getName()));
                    for (int i = 0; i < 9; i++) {
                        int line = i + 9 * (pageNumber - 1);
                        if (line >= helpList.size()) {
                            sender.sendMessage(messageManager.getMessage("Helpfooterplugin").replace("%nextpage%", String.valueOf(helpList.size() / 9 + 1)).replace("%plugin%", args[0]));
                            return true;
                        }
                        String commandName = helpList.get(line);
                        String commandDescription;
                        if (Bukkit.getServer().getPluginCommand(helpList.get(line)) == null) {
                            commandDescription = "";
                        } else {
                            commandDescription = Bukkit.getServer().getPluginCommand(helpList.get(line)).getDescription();
                        }
                        sender.sendMessage(messageManager.getMessage("Helpcommands").replace("%command%", commandName).replace("%description%", commandDescription));
                    }
                    if (pageNumber + 1 <= helpList.size() / 9 + 1) {
                        sender.sendMessage(messageManager.getMessage("Helpfooterplugin").replace("%nextpage%", String.valueOf(pageNumber + 1)).replace("%plugin%", args[0]));
                    }
                    return true;
                }
            }
        }
        return true;
    }
}