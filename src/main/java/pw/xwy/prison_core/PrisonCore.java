package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.commands.BalanceCommand;
import pw.xwy.prison_core.commands.FeedCommand;
import pw.xwy.prison_core.commands.MineCommand;
import pw.xwy.prison_core.custom_enchants.CustomEnchantsHandler;
import pw.xwy.prison_core.custom_enchants.menus.MainMenu;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.MineManager;

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
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getLabel().equalsIgnoreCase("feed")) {
			FeedCommand.run((Player) sender);
			return true;
		} else if (command.getLabel().equalsIgnoreCase("mine")) {
			MineCommand.run((Player) sender, args);
			return true;
		} else if (command.getLabel().equalsIgnoreCase("bal")) {
			BalanceCommand.run((Player) sender, args);
		}
		return false;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		logger = Bukkit.getLogger();
		registerEvents(new MineManager());
		new JoinListener();
		configurationHandler = new ConfigurationHandler(this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
		registerEvents(new Drugslistener());
		registerEvents(new DonorShopGUI(null));
		
		new CustomEnchantsHandler().onEnable();
		
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "<-------------------------------------->");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§c" + PrisonCore.getInstance().getDescription().getName() + " Enabled.");
		Bukkit.getConsoleSender().sendMessage("§c" + PrisonCore.getInstance().getDescription().getName() + " made by Xwy.");
		Bukkit.getConsoleSender().sendMessage("§cCurrent Version: " + ChatColor.GRAY + PrisonCore.getInstance().getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "<-------------------------------------->");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		
	}
	
	public static void registerEvents(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, instance);
	}
	
	public static PrisonCore getInstance() {
		return instance;
	}
	
	@Override
	public void onDisable() {
		configurationHandler.onDisable();
		
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "<-------------------------------------->");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§c" + getDescription().getName() + " Disabled.");
		Bukkit.getConsoleSender().sendMessage("§c" + getDescription().getName() + " made by Xwy.");
		Bukkit.getConsoleSender().sendMessage("§cCurrent Version: " + ChatColor.GRAY + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "<-------------------------------------->");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		
	}
	
	
}
