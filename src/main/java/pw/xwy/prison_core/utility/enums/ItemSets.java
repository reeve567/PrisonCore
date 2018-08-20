////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.utility.enums;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ItemSets {
	SWORD("Sword", Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD),
	AXE("Axe", Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE),
	BOW("Bow", Material.BOW),
	PICK("Pickaxe", Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE),
	HELM("Helmet", Material.LEATHER_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.GOLD_HELMET, Material.DIAMOND_HELMET),
	CHEST("Chestplate", Material.LEATHER_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.DIAMOND_CHESTPLATE),
	LEGGINGS("Leggings", Material.LEATHER_LEGGINGS, Material.IRON_LEGGINGS, Material.GOLD_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_LEGGINGS),
	BOOTS("Boots", Material.LEATHER_BOOTS, Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.CHAINMAIL_BOOTS, Material.DIAMOND_BOOTS),
	TOOLS("Tools", SWORD, AXE, BOW, PICK),
	ARMOR("Armor", HELM, CHEST, LEGGINGS, BOOTS),
	SWORDAXE("Sword & Axe", SWORD, AXE),
	EVERYTHING("Tools & Armor", SWORD, AXE, BOW, PICK, HELM, CHEST, LEGGINGS, BOOTS);
	
	private final List<Material> set = new ArrayList<Material>();
	private final String name;
	private List<ItemSets> sets = null;
	private int amount = 1;
	
	ItemSets(String name, ItemSets... sets) {
		for (ItemSets itemset : sets) {
			set.addAll(itemset.getSet());
		}
		this.sets = Arrays.asList(sets);
		amount = sets.length;
		this.name = name;
	}
	
	public List<Material> getSet() {
		return set;
	}
	
	ItemSets(String name, Material... a) {
		sets = Arrays.asList(this);
		set.addAll(Arrays.asList(a));
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean setContains(Material a) {
		return set.contains(a);
	}
	
	public int getAmount() {
		return amount;
	}
	
	public List<ItemSets> getSets() {
		return sets;
	}
	
}
