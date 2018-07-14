package pw.xwy.prison_core;

import org.bukkit.entity.Player;

public class Feed {
	
	public static void run(Player player) {
		player.setFoodLevel(20);
		player.sendMessage(ChatListener.prefix + "Billy has fed you");
	}
	
}
