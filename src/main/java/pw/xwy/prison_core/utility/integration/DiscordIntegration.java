package pw.xwy.prison_core.utility.integration;

import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Date;

public class DiscordIntegration extends BukkitRunnable {
	
	public static final ArrayList<String> messages = new ArrayList<>();
	private static final String icon = "https://camo.githubusercontent.com/a4e336d9971686ae5698d2a39011e8e499a82475/68747470733a2f2f6a6172692e6c6f6c2f5a6e4e305451654d6d692e706e67";
	private static String title;
	private static TemmieWebhook temmie;
	private static String info = "[INFO] ";
	private static String name;
	private static String date;
	
	public DiscordIntegration(String botTitle, String webhook, String serverName) {
		messages.add("Started DiscordIntegration");
		title = botTitle;
		name = serverName;
		temmie = new TemmieWebhook(webhook);
	}
	
	@Override
	public void run() {
		if (messages.size() > 0) {
			instantBroadcast(messages.remove(0));
		}
	}
	
	public static void instantBroadcast(String message) {
		Date dateS = new Date();
		date = "[" + dateS.getHours() + ":" + dateS.getMinutes() + ":" + dateS.getSeconds() + "]";
		DiscordMessage message1 = new DiscordMessage(title, info + date + " [" + name + "] " + message, icon);
		temmie.sendMessage(message1);
		message1 = null;
	}
	
}
