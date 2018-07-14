package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String message = e.getMessage();
		String prefix = "§a§lBilly §8§l»»";
		if (message.contains("hello") && message.contains("billy")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(prefix + "Hello");
		} else {
			Bukkit.broadcastMessage("§7[Noob] " + e.getPlayer().getName() + " » " + e.getMessage());
		}
	}
	
	public String convert(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
}
