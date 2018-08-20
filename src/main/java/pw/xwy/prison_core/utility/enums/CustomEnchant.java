////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 6/17/18 12:11 PM                            /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.utility.enums;

import pw.xwy.prison_core.utility.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomEnchant {
	
	public static List<CEnchant> hydro = new ArrayList<>();
	public static List<CEnchant> mystical = new ArrayList<>();
	public static List<CEnchant> rare = new ArrayList<>();
	public static List<CEnchant> uncommon = new ArrayList<>();
	public static List<CEnchant> common = new ArrayList<>();
	private CEnchant enchant;
	private String realName;
	private String name;
	private ItemSets sets;
	private String loreLabel;
	private String commandLabel;
	private List<String> description;
	private Rarity rarity;
	private int amount;
	
	public CustomEnchant(CEnchant enchant, String name, Rarity rarity, ItemSets itemSets) {
		realName = enchant.toString();
		this.enchant = enchant;
		this.name = name;
		this.sets = itemSets;
		this.rarity = rarity;
		this.commandLabel = name;
		this.loreLabel = itemSets.getName();
		this.amount = itemSets.getAmount();
	}
	
	public CustomEnchant(CEnchant enchant, String name, String commandLabel, Rarity rarity, ItemSets itemSets) {
		realName = enchant.toString();
		this.enchant = enchant;
		this.name = name;
		this.sets = itemSets;
		this.rarity = rarity;
		this.commandLabel = commandLabel;
		this.loreLabel = itemSets.getName();
		this.amount = itemSets.getAmount();
	}
	
	public CustomEnchant(CEnchant enchant, String name, String commandLabel, Rarity rarity, ItemSets itemSets, int amount) {
		realName = enchant.toString();
		this.enchant = enchant;
		this.name = name;
		this.sets = itemSets;
		this.rarity = rarity;
		this.commandLabel = commandLabel;
		this.loreLabel = itemSets.getName();
		this.amount = amount;
	}
	
	public static CEnchant getRandomEnchant(Rarity rarity) {
		switch (rarity) {
			case HYDRO:
				return hydro.get(new Random().nextInt(hydro.size()));
			case MYSTICAL:
				return mystical.get(new Random().nextInt(mystical.size()));
			case RARE:
				return rare.get(new Random().nextInt(rare.size()));
			case UNCOMMON:
				return uncommon.get(new Random().nextInt(uncommon.size()));
			case COMMON:
				return common.get(new Random().nextInt(common.size()));
			case ADMIN:
				return CEnchant.XWY;
		}
		return null;
	}
	
	public String getLoreLabel() {
		return loreLabel;
	}
	
	
	public String getCommandLabel() {
		return commandLabel;
	}
	
	
	public int getAmount() {
		return amount;
	}
	
	public ItemSets getSets() {
		return sets;
	}
	
	public void saveDefault(Config config) {
		String base = "CustomEnchants." + realName + ".";
		config.set(base + "name", name);
		config.set(base + "rarity", rarity.toString());
		config.set(base + "commandLabel", commandLabel);
		config.set(base + "description", description);
		config.set(base + "enabled", true);
		addToList(enchant);
	}
	
	private void addToList(CEnchant enchant) {
		if (enchant.isEnabled()) {
			if (getRarity() == Rarity.COMMON) {
				common.add(enchant);
			} else if (getRarity() == Rarity.UNCOMMON) {
				uncommon.add(enchant);
			} else if (getRarity() == Rarity.RARE) {
				rare.add(enchant);
			} else if (getRarity() == Rarity.MYSTICAL) {
				mystical.add(enchant);
			} else if (getRarity() == Rarity.HYDRO) {
				hydro.add(enchant);
			}
		}
	}
	
	public Rarity getRarity() {
		return rarity;
	}
	
	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}
	
	public String getName() {
		return rarity.getColor() + name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCustomStuff(Config config) {
		String base = "CustomEnchants." + realName + ".";
		name = config.getString(base + "name");
		rarity = Rarity.valueOf(config.getString(base + "rarity"));
		commandLabel = config.getString(base + "commandLabel");
		description = config.getStringList(base + "description");
		boolean enabled = config.getBoolean(base + "enabled");
		if (!enabled) enchant.disable();
		addToList(enchant);
	}
	
	public List<String> getDescription() {
		return description;
	}
	
	public void setDescription(List<String> strings) {
		description = strings;
	}
	
}
