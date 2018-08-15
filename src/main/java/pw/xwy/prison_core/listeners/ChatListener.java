package pw.xwy.prison_core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pw.xwy.prison_core.Permissions;
import pw.xwy.prison_core.utility.PlayerManager;
import pw.xwy.prison_core.utility.XPlayer;

public class ChatListener implements Listener {
	
	public static final String prefix = "§a§lBilly §8§l»» §7";
	public static final String[] groupsList = {"guppy", "mudkip", "shark", "whale", "jellyfish", "lionfish"};
	public static final String[] extraGroups = {"helper", "moderator", "admin", "owner", "developer"};
	private static final String[] groupsListTitle = {"§3Guppy", "§6Mud§bKip", "§1Shark", "§9§lWhale", "§d§lJellyfish", "§4§lLion§6§lFish"};
	private static final String[] extraGroupsTitle = {"§eHelper", "§5Mod", "§cAdmin", "§6Owner", "§4Dev"};
	public static boolean stopped = false;
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		XPlayer data = PlayerManager.getXPlayer(e.getPlayer());
		
		String firstTitle = "";
		String secondTitle = "";
		
		for (int i = 0; i < groupsList.length; i++) {
			if (data.hasGroup(groupsList[i])) {
				secondTitle = "§8[" + groupsListTitle[i] + "§8] ";
			}
		}
		
		for (int i = 0; i < extraGroups.length; i++) {
			if (data.hasGroup(extraGroups[i])) {
				firstTitle = "§8[" + extraGroupsTitle[i] + "§8] ";
			}
		}
		
		if (stopped) {
			if (!e.getPlayer().hasPermission(Permissions.CHATSTOP_COMMAND.toString())) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cYou cannot chat while chat is still stopped.");
			} else {
				e.setFormat(firstTitle + secondTitle + "§6" + e.getPlayer().getName() + " §8§l» §7" + e.getMessage());
			}
			return;
		}
		if (data.hasGroup(extraGroups[4])) {
			e.setFormat(firstTitle + "§6" + e.getPlayer().getName() + " §8§l» §7" + e.getMessage());
			return;
		}
		
		e.setFormat(firstTitle + secondTitle + "§8[§c" + data.getData().getPrestige() + "§8] " + ChatColor.translateAlternateColorCodes('&', data.getData().getRank().getChatPrefix()) + " §6" + e.getPlayer().getName() + " §8§l» §7" + e.getMessage());
	}
	
	
}
