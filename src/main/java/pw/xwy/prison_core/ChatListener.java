package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	
	public static final String prefix = "§a§lBilly §8§l»»";
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String message = e.getMessage();
		if (message.contains("hello") && message.contains("billy")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(prefix + "Hello Im Billy your personal assistant and prostitute. Ask any questions that start with a what and address me somewhere in the question and i will answer");
		}
		else if (message.contains("what") && message.contains("billy")){
			e.setCancelled(true);
			e.getPlayer().sendMessage(prefix + "A dolphin can have up to 8 orgasms in 10 mins" );
		}
		else {
			Bukkit.broadcastMessage("§7[Noob] " + e.getPlayer().getName() + " » " + e.getMessage());
		}
	}
	
	public String convert(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
}
