package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	
	public static final String prefix = "§a§lBilly §8§l»» §f";
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		String message = e.getMessage();
		if (message.contains("hello") && message.contains("billy")) {
			e.getPlayer().sendMessage(prefix + "Hello Im Billy your personal assistant and prostitute. Ask any questions that start with a what and address me somewhere in the question and i will answer");
		} else if (message.contains("what") && message.contains("billy")) {
			e.getPlayer().sendMessage(prefix + "A dolphin can have up to 8 orgasms in 10 mins");
		} else {
			PlayerData data = PlayerDataManager.getPlayerData(e.getPlayer().getUniqueId());
			Bukkit.broadcastMessage("§8[§c" + data.getPrestige() + "§8] " + ChatColor.translateAlternateColorCodes('&',data.getRank().getChatPrefix()) + " §6" + e.getPlayer().getName() + " §8§l» §7" + e.getMessage());
		}
	}
	
	public String convert(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
}
