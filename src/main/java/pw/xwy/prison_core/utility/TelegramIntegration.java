package pw.xwy.prison_core.utility;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Date;

public class TelegramIntegration extends BukkitRunnable {
	
	public static final ArrayList<String> messages = new ArrayList<>();
	private static TelegramBot bot;
	private static String info = "[INFO] ";
	private static String date;
	private static String chatID;
	
	public TelegramIntegration(String botToken, String chatID) {
		bot = new TelegramBot(botToken);
		TelegramIntegration.chatID = chatID;
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
		SendMessage message1 = new SendMessage(chatID, info + date + " " + message);
		bot.execute(message1);
		message1 = null;
	}
	
}
