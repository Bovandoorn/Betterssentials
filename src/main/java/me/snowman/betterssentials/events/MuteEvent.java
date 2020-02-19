package me.snowman.betterssentials.events;

import me.snowman.betterssentials.Betterssentials;
import me.snowman.betterssentials.managers.MessageManager;
import me.snowman.betterssentials.managers.MuteManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.concurrent.TimeUnit;

public class MuteEvent implements Listener {
    private final MessageManager messageManager = Betterssentials.messageManager;
    private final MuteManager muteManager = Betterssentials.muteManager;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (muteManager.getMutes().get(player.getUniqueId()) == null) return;
        if (muteManager.getMutes().get(player.getUniqueId()) == 0) {
            player.sendMessage(messageManager.getPrefix() + messageManager.getMessage("MutePermanent"));
            event.setCancelled(true);
            return;
        }
        if (muteManager.checkMute(player)) {
            long timeMuted = muteManager.getMutes().get(player.getUniqueId()) - System.currentTimeMillis() / 1000;
            long hours = TimeUnit.SECONDS.toHours(timeMuted);
            timeMuted -= hours * 60 * 60;
            long minutes = TimeUnit.SECONDS.toMinutes(timeMuted);
            timeMuted -= minutes * 60;
            long seconds = TimeUnit.SECONDS.toSeconds(timeMuted);
            String timePlaceholder = hours + " hours " + minutes + " minutes " + seconds + " seconds";
            player.sendMessage(messageManager.getPrefix() + messageManager.getMessage("BeenMuted").replace("%time%", timePlaceholder));
            event.setCancelled(true);
        }
    }
}
