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
import java.util.List;

public enum MenuItem {


	VALOR("This enchant gives you resistance if++you have the enchant on all pieces of armor.", Material.BEACON),
	AUTOFEEDER("This enchant prevents you from losing hunger.", Material.COOKIE),
	//UNSTABLEI("This enchant decreases the speed at which armor durability decreases.", Material.DIAMOND),
	//UNSTABLEII("This enchant decreases the speed at which armor durability decreases.", Material.DIAMOND),
	HEARTBOOST("This enchant adds 4 hearts to the player's max health.", Material.REDSTONE_BLOCK),
	GLOWING("This enchant gives the wearer infinite night vision.", Material.GLOWSTONE),
	SCUBADIVER("This enchant gives the wearer infinite water breathing.", Material.RAW_FISH),

	//DETONATE("This enchant has a chance of exploding, damaging everything nearby except the wearer.", Material.TNT),
	SHOTGUN("Shooting an arrow with this enchant++launches 4 projectiles while on the++correct mode.", Material.ARROW),

	RIFLE("With this enchant, all you have to do++is right click to shoot an arrow, if++on the correct mode.", Material.FIREWORK),
	EXPLOSIVEARROW("This enchant gives your arrows a chance to++do double damage.", Material.GOLD_INGOT),
	RPG("This enchant gives your arrows a chance to++explode on impact.", Material.TNT),
	FROZENARROW("This enchant gives your arrows a chance to++give the player it hit slowness.", Material.ICE),
	VOLTAGE("This enchant gives your arrows a chance to++smite the player it hit.", Material.BLAZE_POWDER),
	POISONOUSARROW("This enchant gives you arrows a chance to++poison the player it hit.", Material.SPIDER_EYE),
	FURNACE("This enchant allows your arrows to cook the++meat of animals that it kills.", Material.GRILLED_PORK),
	MOONGRAVITY("This enchant gives the wearer JumpBoost I infinitely.", Material.GOLDEN_CARROT),
	//LEADERSHIP("When faction members are near you, your damage is increased.", Material.CHAINMAIL_HELMET),
	WINDSSPEEDI("This enchant gives the wearer Speed I infinitely.", Material.SUGAR),
	WINDSSPEEDII("This enchant gives the wearer Speed II infinitely.", Material.SUGAR),
	//HARDENED("This enchant increases the amount of blocks you++can mine without repairing.", Material.WOOD_PICKAXE),
	//MAGNET("Mined blocks go straight to your inventory.", Material.HOPPER),
	SMELTING("When you destroy a block with this, you++get the molten products of that block.", Material.GOLD_INGOT),
	FORTUNEV("This enchant brings great fortune to many.", Material.DIAMOND_PICKAXE),
	MOBSLAYERI("Killing mobs with this enchantment++yields a higher amount of XP.", Material.SPIDER_EYE),
	MOBSLAYERII("Killing mobs with this enchantment++yields a higher amount of XP.", Material.SPIDER_EYE),
	MOBSLAYERIII("Killing mobs with this enchantment++yields a higher amount of XP.", Material.SPIDER_EYE),
	HOSTILEDAMAGE("This enchantment doubles damage to++hostile mobs.", Material.SKULL_ITEM, 2),
	ANIMALCOOKER("Killing mobs will cook their meat automatically.", Material.COOKED_BEEF),


	EFFICIENCYVI("Makes your pickaxe mine faster", Material.IRON_PICKAXE),
	EFFICIENCYVII("Makes your pickaxe mine faster", Material.IRON_PICKAXE),
	EFFICIENCYVIII("Makes your pickaxe mine faster", Material.DIAMOND_PICKAXE),
	EFFICIENCYIX("Makes your pickaxe mine faster", Material.DIAMOND_PICKAXE),
	EFFICIENCYX("Makes your pickaxe mine faster", Material.GOLD_PICKAXE)
	;
	
	private final List<String> desc;
	private final Material type;
	private byte durability = 0;
	
	MenuItem(String desc, Material type) {
		this.desc = conv(desc);
		this.type = type;
	}
	
	private ArrayList<String> conv(String desc) {
		ArrayList<String> de = new ArrayList<>();
		while (desc.contains("++")) {
			
			de.add("&b" + desc.substring(0, desc.indexOf("++")));
			desc = desc.replace(desc.substring(0, desc.indexOf("++") + 2), "");
		}
		de.add("&b" + desc);
		return de;
	}
	
	MenuItem(String desc, Material type, int dur) {
		durability = (byte) dur;
		this.desc = conv(desc);
		this.type = type;
	}
	
	public byte getDurability() {
		return durability;
	}
	
	public Material getType() {
		return type;
	}
}
