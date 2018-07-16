////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.custom_enchants.commands.CECommandHandler;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.enums.Messages;
import pw.xwy.prison_core.custom_enchants.listeners.CEListenerHandler;
import pw.xwy.prison_core.custom_enchants.menus.*;
import pw.xwy.prison_core.custom_enchants.schedules.*;
import pw.xwy.prison_core.custom_enchants.soulcrates.*;
import pw.xwy.prison_core.custom_enchants.utilities.ConfigCheck;
import pw.xwy.prison_core.custom_enchants.utilities.Glow;
import pw.xwy.prison_core.custom_enchants.utilities.MessagesFunctions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Logger;

public class CustomEnchantsHandler {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	public static int ceCount;
	private static CustomEnchantsHandler customEnchants;
	private CECommandHandler commandHandler;
	
	public static CustomEnchantsHandler getCustomEnchants() {
		return customEnchants;
	}
	
	public void onEnable() {
		registerGlow();
		
		new MessagesFunctions(PrisonCore.getInstance());
		CEListenerHandler listnerHandler = new CEListenerHandler(PrisonCore.getInstance());
		commandHandler = new CECommandHandler();
		commandHandler.Init();
		customEnchants = this;
		listnerHandler.Init();
		loadCrates();
		startTasks();
		loadMenus();
		YamlConfiguration config = new YamlConfiguration();
		File f = new File(PrisonCore.getInstance().getDataFolder(), "custom-enchants.yml");
		boolean newF = false;
		ConfigCheck configCheck = new ConfigCheck(PrisonCore.getInstance());
		if (configCheck.Init()) {
			PrisonCore.getInstance().getDataFolder().mkdirs();
			if (!f.exists()) {
				try {
					f.createNewFile();
					newF = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				config.load(f);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			System.out.println("setup completed");
		}
		int ce = 0;
		
		int sword = 0;
		int axe = 0;
		int pick = 0;
		int bow = 0;
		int helm = 0;
		int chest = 0;
		int leggings = 0;
		int boots = 0;
		
		for (CEnchant c : CEnchant.values()) {
			if (newF) {
				c.customEnchant.saveDefault(config);
			} else {
				c.getCustomStuff(config);
			}
			
			if (c.isEnabled() && c.getAmount() > 0) {
				ce += c.getAmount();
				if (c.checkSets(Material.DIAMOND_SWORD)) sword++;
				if (c.checkSets(Material.DIAMOND_AXE)) axe++;
				if (c.checkSets(Material.DIAMOND_PICKAXE)) pick++;
				if (c.checkSets(Material.BOW)) bow++;
				if (c.checkSets(Material.DIAMOND_HELMET)) helm++;
				if (c.checkSets(Material.DIAMOND_CHESTPLATE)) chest++;
				if (c.checkSets(Material.DIAMOND_LEGGINGS)) leggings++;
				if (c.checkSets(Material.DIAMOND_BOOTS)) boots++;
			}
		}
		try {
			config.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (Bukkit.getOnlinePlayers().length > 0) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.hasPermission("Xwy.ce.notify")) {
					p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "«" + ChatColor.STRIKETHROUGH + "------------------------------" + ChatColor.GRAY + "" + ChatColor.BOLD + "»");
					p.sendMessage(Messages.mainPre.get() + "§6CustomEnchants" + ChatColor.GRAY + " have been loaded with " + ce + " enchants.");
					p.sendMessage(Messages.mainPre.get() + "§c" + sword + " sword");
					p.sendMessage(Messages.mainPre.get() + "§c" + bow + " bow");
					p.sendMessage(Messages.mainPre.get() + "§c" + axe + " axe");
					p.sendMessage(Messages.mainPre.get() + "§c" + pick + " pickaxe");
					p.sendMessage(Messages.mainPre.get() + "§c" + helm + " helmet");
					p.sendMessage(Messages.mainPre.get() + "§c" + chest + " chestplate");
					p.sendMessage(Messages.mainPre.get() + "§c" + leggings + " legging");
					p.sendMessage(Messages.mainPre.get() + "§c" + boots + " boot");
					p.sendMessage(Messages.mainPre.get() + "§c" + (ceCount = sword + bow + axe + pick + helm + chest + leggings + boots) + " total");
					p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "«" + ChatColor.STRIKETHROUGH + "------------------------------" + ChatColor.GRAY + "" + ChatColor.BOLD + "»");
				}
			}
		}
		
		Bukkit.getPluginManager().registerEvents(new ConversionMenu(), PrisonCore.getInstance());
	}
	
	private void registerGlow() {
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Glow glow = new Glow(999);
			Enchantment.registerEnchantment(glow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadCrates() {
		new HydroSC();
		new MysticalSC();
		new RareSC();
		new UncommonSC();
		new CommonSC();
	}
	
	private void startTasks() {
		if (CEnchant.SCUBADIVER.isEnabled()) new WaterBreathing(PrisonCore.getInstance());
		if (CEnchant.GLOWING.isEnabled()) new NightVision(PrisonCore.getInstance());
		if (CEnchant.HEARTBOOST.isEnabled()) new HeartCheck(PrisonCore.getInstance());
		if (CEnchant.VALOR.isEnabled()) new ValorCheck(PrisonCore.getInstance());
		if (CEnchant.FLASH.isEnabled()) new FlashCheck(PrisonCore.getInstance());
		if (CEnchant.MOONGRAVITY.isEnabled()) new JumpBoost(PrisonCore.getInstance());
		if (CEnchant.WINDSSPEEDI.isEnabled() || CEnchant.WINDSSPEEDII.isEnabled()) new Speed(PrisonCore.getInstance());
	}
	
	private void loadMenus() {
		new AxeMenu();
		new BootsMenu();
		new BowMenu();
		new ChestMenu();
		new HelmMenu();
		new LeggingsMenu();
		new PickMenu();
		new SwordMenu();
	}
	
}
