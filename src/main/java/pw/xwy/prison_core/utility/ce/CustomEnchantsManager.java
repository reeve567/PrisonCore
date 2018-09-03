////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.utility.ce;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.listeners.gui.ConversionMenu;
import pw.xwy.prison_core.tasks.*;
import pw.xwy.prison_core.utility.config.Config;
import pw.xwy.prison_core.utility.enums.CEnchant;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.item.Glow;

import java.lang.reflect.Field;

public class CustomEnchantsManager {
	
	public static int ceCount;
	
	public void onEnable() {
		registerGlow();
		
		new MessagesFunctions();
		Config config = new Config(PrisonCore.getInstance().getDataFolder(), "custom-enchants");
		int ce = 0;
		
		int sword = 0;
		int axe = 0;
		int pick = 0;
		int bow = 0;
		int helm = 0;
		int chest = 0;
		int leggings = 0;
		int boots = 0;
		
		boolean newf = false;
		if (config.getInt("ver") != 1) {
			newf = true;
			config.set("ver", 1);
			config.saveConfig();
		}
		for (CEnchant c : CEnchant.values()) {
			
			
			if (newf) {
				c.customEnchant.saveDefault(config);
			}
			c.getCustomStuff(config);
			
			
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
		config.saveConfig();
		
		if (Bukkit.getOnlinePlayers().size() > 0) {
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
		startTasks();
		loadMenus();
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
			if (Enchantment.isAcceptingRegistrations())
				if (Enchantment.getByName("Glow") != null) {
					Glow glow = new Glow(99);
					Enchantment.registerEnchantment(glow);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void startTasks() {
		new WaterBreathingCheck(PrisonCore.getInstance());
		new NightVisionCheck(PrisonCore.getInstance());
		new HeartCheck(PrisonCore.getInstance());
		new ValorCheck(PrisonCore.getInstance());
		new FlashCheck(PrisonCore.getInstance());
		new JumpBoostCheck(PrisonCore.getInstance());
		new SpeedCheck(PrisonCore.getInstance());
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
