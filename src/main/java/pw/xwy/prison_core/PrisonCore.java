package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.commands.*;
import pw.xwy.prison_core.listeners.*;
import pw.xwy.prison_core.listeners.gui.DonorShopGUI;
import pw.xwy.prison_core.listeners.gui.KitGUI;
import pw.xwy.prison_core.listeners.gui.TagGUI;
import pw.xwy.prison_core.listeners.gui.WarpGUI;
import pw.xwy.prison_core.listeners.gui.ce.MainMenu;
import pw.xwy.prison_core.tasks.CombatTagTask;
import pw.xwy.prison_core.tasks.HologramAnimationTask;
import pw.xwy.prison_core.utility.ce.CustomEnchantsManager;
import pw.xwy.prison_core.utility.config.ConfigurationManager;
import pw.xwy.prison_core.utility.integration.DiscordIntegration;
import pw.xwy.prison_core.utility.integration.TelegramIntegration;
import pw.xwy.prison_core.utility.mine.MineManager;
import pw.xwy.prison_core.utility.misc_managers.HologramsManager;
import pw.xwy.prison_core.utility.scoreboard.ScoreboardsManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PrisonCore extends JavaPlugin {
	
	public static boolean discordIntegration = false;
	public static boolean telegramIntegration = false;
	private static Logger logger;
	private static PrisonCore instance;
	private ConfigurationManager configurationManager;
	
	public static void log(String message, int level) {
		if (level >= 1 && discordIntegration) {
			DiscordIntegration.messages.add(message);
		}
		if (level == 2 && telegramIntegration) {
			TelegramIntegration.messages.add(message);
		}
	}
	
	public static void log(String message) {
		logger.log(Level.INFO, message);
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
		new HologramsManager();
		configurationManager = new ConfigurationManager(this);
		loadManagers();
		stageTwoListeners();
		loadCommands();
		loadTasks();
	}
	
	private void stageOneListeners() {
		registerEvents(new MineManager());
	}
	
	private void loadManagers() {
		new ScoreboardsManager();
		new CustomEnchantsManager().onEnable();
		
	}
	
	private void stageTwoListeners() {
		registerEvents(
				new JoinListener(),
				new CrateListener(),
				new DrugsListener(),
				new DonorShopGUI(null),
				new KitGUI(null),
				new WarpGUI(null, 0),
				new MainMenu(null),
				new TagGUI(null),
				new LeaveListener(),
				new BlockListener(),
				new ChatListener(),
				new VoucherListener(),
				new NPCListener(),
				new VoteListener(),
				new DamageListener(),
				new EnchantDrop(),
				new FeedListener(),
				new DeathListener(),
				new BowListener(),
				new RespawnListener(),
				new SignListener(),
				new HologramsManager(),
				new NoFlyListener()
		);
		
	}
	
	private void loadCommands() {
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
		new PermissionsCommand();
		new FlyCommand();
		new TagCommand();
		new WarpCommand();
		new ToggleCommand();
		new CustomEnchantsCommand();
		new EventCommand();
		new VoteCommand();
	}
	
	private void loadTasks() {
		new HologramAnimationTask().runTaskTimer(this, 5, 5);
		new CombatTagTask().runTaskTimer(this, 5, 20);
	}
	
	private static void registerEvents(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, instance);
	}
	
	private void registerEvents(Listener... listeners) {
		for (Listener l : listeners) {
			PrisonCore.registerEvents(l);
		}
	}
	
	@Override
	public void onDisable() {
		configurationManager.onDisable();
		HologramsManager.disable();
	}
	
	
}
