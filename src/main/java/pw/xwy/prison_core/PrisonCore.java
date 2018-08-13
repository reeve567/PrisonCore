package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.commands.*;
import pw.xwy.prison_core.custom_enchants.CustomEnchantsHandler;
import pw.xwy.prison_core.listeners.*;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.DiscordIntegration;
import pw.xwy.prison_core.utility.MineManager;
import pw.xwy.prison_core.utility.TelegramIntegration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PrisonCore extends JavaPlugin {
	
	public static boolean discordIntegration = false;
	public static boolean telegramIntegration = false;
	private static Logger logger;
	private static PrisonCore instance;
	private ConfigurationHandler configurationHandler;
	
	public static void log(String message) {
		logger.log(Level.INFO, message);
		//add discord message stuff
		if (discordIntegration) {
			DiscordIntegration.messages.add(message);
		}
		if (telegramIntegration) {
			TelegramIntegration.messages.add(message);
		}
	}
	
	public static PrisonCore getInstance() {
		return instance;
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
		} else if (command.getLabel().equalsIgnoreCase("ds")) {
			DonorShopCommand.run((Player) sender);
		} else if (command.getLabel().equalsIgnoreCase("kit")) {
			KitCommand.run((Player) sender, args);
		}
		return false;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		logger = Bukkit.getLogger();
		stageOneListeners();
		configurationHandler = new ConfigurationHandler(this);
		stageTwoListeners();
		new CustomEnchantsHandler().onEnable();
		loadCommands();
	}
	
	private void stageOneListeners() {
		registerEvents(new MineManager());
	}
	
	private void stageTwoListeners() {
		registerEvents(new JoinListener());
		registerEvents(new ChatListener());
		registerEvents(new DrugsListener());
		registerEvents(new DonorShopGUI(null));
		registerEvents(new KitGUI(null));
		registerEvents(new LeaveListener());
		registerEvents(new BlockListener());
		registerEvents(new VoucherListener());
		registerEvents(new SignListener());
		registerEvents(new CrateListener());
		
	}
	
	public void loadCommands() {
		new RankupCommand();
		new SetCommand();
		new SpawnCommand();
		new ChatstopCommand();
		new ChatclearCommand();
		new WorkbenchCommand();
		new SellAllCommand();
		new AutosellCommand();
		new EnderchestCommand();
		new HatCommand();
		new KeyCommand();
	}
	
	public static void registerEvents(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, instance);
	}
	
	@Override
	public void onDisable() {
		configurationHandler.onDisable();
	}
	
	
}
