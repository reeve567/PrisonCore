package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.utility.ConfigurationHandler;

import java.util.logging.Logger;

public class PrisonCore extends JavaPlugin {
	
	public static boolean discordIntegration = false;
	private static Logger logger;
	private static PrisonCore instance;
	private ConfigurationHandler configurationHandler = new ConfigurationHandler(this);
	
	public static void log(String message) {
	
	}
	
	public static PrisonCore getInstance() {
		return instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getLabel().equalsIgnoreCase("feed")) {
			Feed.run((Player) sender);
			return true;
		}
		return false;
	}
	
	@Override
	public void onEnable() {
		logger = Bukkit.getLogger();
		instance = this;
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
	}
	
	@Override
	public void onDisable() {
	
	}
	
}
