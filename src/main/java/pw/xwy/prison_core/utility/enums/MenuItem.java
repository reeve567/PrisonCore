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
	//UNSTABLEI("This enchant decreases the speed at which armor durability decreases.", Material.DIAMOND),
	//UNSTABLEII("This enchant decreases the speed at which armor durability decreases.", Material.DIAMOND),
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
	MOONGRAVITY("This enchant gives the wearer JumpBoost I infinitely.", Material.GOLDEN_CARROT),
	//LEADERSHIP("When faction members are near you, your damage is increased.", Material.CHAINMAIL_HELMET),
	//HARDENED("This enchant increases the amount of blocks you++can mine without repairing.", Material.WOOD_PICKAXE),
	//MAGNET("Mined blocks go straight to your inventory.", Material.HOPPER),
	SMELTING("When you destroy a block with this, you++get the molten products of that block.", Material.GOLD_INGOT),
	FORTUNEV("This enchant brings great fortune to many.", Material.DIAMOND_PICKAXE),


	EFFICIENCYVI("Makes your pickaxe mine faster", Material.IRON_PICKAXE),
	EFFICIENCYVII("Makes your pickaxe mine faster", Material.IRON_PICKAXE),
	EFFICIENCYVIII("Makes your pickaxe mine faster", Material.DIAMOND_PICKAXE),
	EFFICIENCYIX("Makes your pickaxe mine faster", Material.DIAMOND_PICKAXE),
	EFFICIENCYX("Makes your pickaxe mine faster", Material.GOLD_PICKAXE)
	;

	private final Material type;
	private byte durability = 0;
	
	MenuItem(String desc, Material type) {
		this.type = type;
	}

	MenuItem(String desc, Material type, int dur) {
		durability = (byte) dur;
		this.type = type;
	}
	
	public byte getDurability() {
		return durability;
	}
	
	public Material getType() {
		return type;
	}
}
