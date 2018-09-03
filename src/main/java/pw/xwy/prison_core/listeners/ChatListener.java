package pw.xwy.prison_core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.enums.Permissions;
import pw.xwy.prison_core.utility.player.PlayerManager;
import pw.xwy.prison_core.utility.player.XPlayer;

public class ChatListener implements Listener {
	
	public static final String prefix = "§a§lBilly §8§l»» §7";
	public static final String[] groupsList = {"guppy", "mudkip", "shark", "whale", "jellyfish"};
	public static final String[] extraGroups = {"helper", "moderator", "admin", "owner", "developer"};
	private static final String[] groupsListTitle = {"§3Guppy", "§6Mud§bKip", "§1Shark", "§9§lWhale", "§d§lJellyfish"/*, "§4§lLion§6§lFish"*/};
	private static final String[] extraGroupsTitle = {"§eHelper", "§5Mod", "§cAdmin", "§6Owner", "§4Dev"};
	public static boolean stopped = false;
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		XPlayer player = PlayerManager.getXPlayer(e.getPlayer());
		e.setMessage(e.getMessage().replaceAll("%", "%%"));
		
		String firstTitle = "";
		String secondTitle = "";
		String tag = "";
		
		for (int i = 0; i < groupsList.length; i++) {
			if (player.hasGroup(groupsList[i])) {
				secondTitle = "§8[" + groupsListTitle[i] + "§8] ";
			}
		}
		
		for (int i = 0; i < extraGroups.length; i++) {
			if (player.hasGroup(extraGroups[i])) {
				firstTitle = "§8[" + extraGroupsTitle[i] + "§8] ";
			}
		}
		
		if (player.getActiveTag() != null && player.isTagToggle()) {
			tag = " §8[" + player.getActiveTag().getDisplay() + "§8]";
		}
		
		if (stopped) {
			if (!e.getPlayer().hasPermission(Permissions.CHATSTOP_COMMAND.toString())) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cYou cannot chat while chat is still stopped.");
			} else {
				e.setFormat(firstTitle + "§6" + e.getPlayer().getName() + " §8§l» §7" + e.getMessage());
				PrisonCore.log(e.getPlayer().getName() + " » " + e.getMessage(), 1);
			}
			return;
		}
		if (player.hasGroup(extraGroups[4]) || player.hasGroup(extraGroups[3])) {
			e.setFormat(firstTitle + "§6" + e.getPlayer().getName() + " §8§l» §7" + e.getMessage());
			PrisonCore.log(e.getPlayer().getName() + " » " + e.getMessage(), 1);
			return;
		}
		
		String youtube = player.isYoutuber() ? "§8[§cYou§fTube§8] " : secondTitle;
		
		e.setFormat(firstTitle + youtube + "§8[§c" + player.getPrestige() + "§8] " + ChatColor.translateAlternateColorCodes('&', player.getRank().getChatPrefix()) + " §6" + e.getPlayer().getName() + tag + " §8§l» §7" + e.getMessage());
		PrisonCore.log(e.getPlayer().getAddress().toString().substring(1) + " - " + e.getPlayer().getName() + " » " + e.getMessage(), 1);
	}
	
	
}
