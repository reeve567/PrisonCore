package pw.xwy.prison_core.commands;

import org.bukkit.entity.Player;
import pw.xwy.prison_core.ChatListener;

public class FeedCommand {
	
	public static void run(Player player) {
		player.setFoodLevel(20);
		player.sendMessage(ChatListener.prefix + "You have been fed");
	}
	
}
