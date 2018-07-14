package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.utility.ConfigurationHandler;

import java.util.logging.Logger;

public class PrisonCore extends JavaPlugin {
	
	private static Logger logger;
	public static boolean discordIntegration = false;
	private ConfigurationHandler configurationHandler = new ConfigurationHandler(this);
	private static PrisonCore instance;
	
	public static void log(String message) {
	
	}
	
	@Override
	public void onEnable() {
		logger = Bukkit.getLogger();
		instance = this;
	}
	
	@Override
	public void onDisable() {
	
	}
	
	public static PrisonCore getInstance() {
		return instance;
	}
	
}
