package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.commands.FeedCommand;
import pw.xwy.prison_core.commands.MineCommand;
import pw.xwy.prison_core.utility.ConfigurationHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PrisonCore extends JavaPlugin {
	
	public static boolean discordIntegration = false;
	private static Logger logger;
	private static PrisonCore instance;
	private ConfigurationHandler configurationHandler;
	
	public static void log(String message) {
		logger.log(Level.INFO, message);
		//add discord message stuff
	}
	
	public static PrisonCore getInstance() {
		return instance;
	}
	
	public static void registerEvents(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, instance);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getLabel().equalsIgnoreCase("feed")) {
			FeedCommand.run((Player) sender);
			return true;
		} else if (command.getLabel().equalsIgnoreCase("mine")) {
			MineCommand.run((Player) sender, args);
			return true;
		}
		return false;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		logger = Bukkit.getLogger();
		configurationHandler = new ConfigurationHandler(this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
	}
	
	@Override
	public void onDisable() {
		configurationHandler.onDisable();
	}
	
	
}
