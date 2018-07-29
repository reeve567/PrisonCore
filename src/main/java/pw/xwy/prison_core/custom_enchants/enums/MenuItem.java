////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.enums;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum MenuItem {
	DEMONSWEAKNESSI("Has a chance to give the opponent++weakness for 2 seconds.", Material.EYE_OF_ENDER),
	DEMONSWEAKNESSII("Has a chance to give the opponent++weakness for 4 seconds.", Material.EYE_OF_ENDER),
	SMOKESCREENI("Has a chance to give the opponent++blindness for 2 seconds.", Material.COAL, 1),
	SMOKESCREENII("Has a chance to give the opponent++blindness for 4 seconds.", Material.COAL, 1),
	SMOKESCREENIII("Has a chance to give the opponent++blindness for 5 seconds.", Material.COAL, 1),
	CURSED("Has a chance to give the opponent++Mining Fatigue for 8 seconds.", Material.WOOD_PICKAXE),
	SOULBOUND("Dying with this enchant on an item++has a chance of keeping it on respawn.", Material.GOLDEN_APPLE, 1),
	REKT("Has a chance to deal double damage.", Material.DIAMOND_PICKAXE),
	DECAPITATE("This enchant ensures you salvage++an enemy player's head.", Material.SKULL_ITEM, 3),
	LUMBERJACK("Mining the bottom log in a tree++will drop all the wood.", Material.LOG),
	ARMORDESTRUCTOR("Damages the opponents armor more than normal.", Material.LEATHER_CHESTPLATE),
	THOR("Has a chance to smite the opponent when you hit them.", Material.GOLD_AXE),
	SELFHEALER("This enchant will give you 4++seconds of regeneration when you get below 3 hearts.", Material.RED_MUSHROOM),
	FLASH("This enchant gives you haste if++you have the enchant on all pieces of armor.", Material.GOLD_PICKAXE),
	VALOR("This enchant gives you resistance if++you have the enchant on all pieces of armor.", Material.BEACON),
	AUTOFEEDER("This enchant prevents you from losing hunger.", Material.COOKIE),
	UNSTABLEI("This enchant decreases the speed at which armor durability decreases.", Material.DIAMOND),
	UNSTABLEII("This enchant decreases the speed at which armor durability decreases.", Material.DIAMOND),
	GUARDIAN("This enchant makes the wearer immune to wither.", Material.YELLOW_FLOWER),
	MEDICINE("This enchant makes the wearer immune to poison.", Material.BROWN_MUSHROOM),
	HEARTBOOST("This enchant adds 4 hearts to the player's max health.", Material.REDSTONE_BLOCK),
	MOLTEN("This enchant will set an attacker on fire if they hit the wearer.", Material.LAVA_BUCKET),
	BURNSHEILD("This enchant allows the wearer to swim in lava and fire.", Material.POTION, 8259),
	GLOWING("This enchant gives the wearer infinite night vision.", Material.GLOWSTONE),
	SCUBADIVER("This enchant gives the wearer infinite water breathing.", Material.RAW_FISH),
	ANTIKNOCKBACKI("This enchantment reduces the amount of knockback you take.", Material.FIREWORK_CHARGE),
	ANTIKNOCKBACKII("This enchantment reduces the amount of knockback you take.", Material.FIREWORK_CHARGE),
	ANTIKNOCKBACKIII("This enchantment reduces the amount of knockback you take.", Material.FIREWORK_CHARGE),
	EXPPROTECTOR("This enchantment prevents your EXP from being stolen.", Material.EXP_BOTTLE),
	REINFORCED("This enchant will give you 4 seconds of++resistance when you get below 3 hearts.", Material.GOLDEN_APPLE),
	//DETONATE("This enchant has a chance of exploding, damaging everything nearby except the wearer.", Material.TNT),
	STARVEDI("This enchant has a chance to make your attacker hungry.", Material.RAW_FISH, 3),
	STARVEDII("This enchant has a chance to make your attacker hungry.", Material.RAW_FISH, 3),
	STARVEDIII("This enchant has a chance to make your attacker hungry.", Material.RAW_FISH, 3),
	BATTLEROAR("This enchant either gives the wearer strength when hit, or wither.", Material.REDSTONE_TORCH_ON),
	SHOTGUN("Shooting an arrow with this enchant++launches 4 projectiles while on the++correct mode.", Material.ARROW),
	GRAPPLINGBOW("Arrows will launch you towards where they++land, if on the correct mode.", Material.WEB),
	RIFLE("With this enchant, all you have to do++is right click to shoot an arrow, if++on the correct mode.", Material.FIREWORK),
	EXPLOSIVEARROW("This enchant gives your arrows a chance to++do double damage.", Material.GOLD_INGOT),
	RPG("This enchant gives your arrows a chance to++explode on impact.", Material.TNT),
	FROZENARROW("This enchant gives your arrows a chance to++give the player it hit slowness.", Material.ICE),
	VOLTAGE("This enchant gives your arrows a chance to++smite the player it hit.", Material.BLAZE_POWDER),
	POISONOUSARROW("This enchant gives you arrows a chance to++poison the player it hit.", Material.SPIDER_EYE),
	FURNACE("This enchant allows your arrows to cook the++meat of animals that it kills.", Material.GRILLED_PORK),
	MOONGRAVITY("This enchant gives the wearer JumpBoost I infinitely.", Material.GOLDEN_CARROT),
	SUPERSPEED("When you hit to one and a half hearts or++below, you get a speed boost++for 3 seconds.", Material.GOLD_BOOTS),
	//LEADERSHIP("When faction members are near you, your damage is increased.", Material.CHAINMAIL_HELMET),
	WINDSSPEEDI("This enchant gives the wearer Speed I infinitely.", Material.SUGAR),
	WINDSSPEEDII("This enchant gives the wearer Speed II infinitely.", Material.SUGAR),
	HARDENED("This enchant increases the amount of blocks you++can mine without repairing.", Material.WOOD_PICKAXE),
	MAGNET("Mined blocks go straight to your inventory.", Material.HOPPER),
	SMELTING("When you destroy a block with this, you++get the molten products of that block.", Material.GOLD_INGOT),
	FORTUNEV("This enchant brings great fortune to many.", Material.DIAMOND_PICKAXE),
	MINERI("This doubles the attack damage of a pickaxe", Material.GOLD_PICKAXE),
	MINERII("This triples the attack damage of a pickaxe", Material.DIAMOND_AXE),
	EXPLOSIVEPICK("This enchant allows your pickaxe to++mine in a 3x3x3 area.", Material.FIREBALL),
	VAMPIRE("Hitting an enemy with this enchant++on your sword will heal you for half++the damage you do.", Material.PUMPKIN),
	WITHERI("Hitting an enemy with this enchant++has a chance to wither the opponent++for 2 seconds.", Material.SKULL_ITEM, 1),
	WITHERII("Hitting an enemy with this enchant++has a chance to wither the opponent++for 4 seconds.", Material.SKULL_ITEM, 1),
	CHARGE("Hitting an enemy while sprinting++increases your damage.", Material.GLOWSTONE_DUST),
	DIZZY("Hitting an enemy with this enchant++inflicts 3 seconds of nausea.", Material.JACK_O_LANTERN),
	GUARDIANSBLOCK("Taking damage while blocking with++this enchant reduces damage by 50%", Material.GOLD_SWORD),
	EXPTHIEF("When you hit someone with this,++it has a chance of stealing their EXP.", Material.EXP_BOTTLE),
	FROZENBLADE("This enchantment has a chance to give++the opponent slowness for 5 seconds.", Material.ICE),
	//DEMONSBLADE("This enchant increases your damage++during combos.", Material.REDSTONE),
	MOBSLAYERI("Killing mobs with this enchantment++yields a higher amount of XP.", Material.SPIDER_EYE),
	MOBSLAYERII("Killing mobs with this enchantment++yields a higher amount of XP.", Material.SPIDER_EYE),
	MOBSLAYERIII("Killing mobs with this enchantment++yields a higher amount of XP.", Material.SPIDER_EYE),
	HOSTILEDAMAGE("This enchantment doubles damage to++hostile mobs.", Material.SKULL_ITEM, 2),
	STORMCALLER("Hitting an enemy has a chance to++smite them with lightning.", Material.NETHER_STAR),
	PARALYZE("Hitting a mob has a chance to slow++it's movement speed.", Material.ROTTEN_FLESH),
	ANIMALCOOKER("Killing mobs will cook their meat automatically.", Material.COOKED_BEEF);
	
	private final CEnchant customEnchant;
	private final List<String> desc;
	private final Material type;
	private byte durability = 0;
	
	MenuItem(String desc, Material type) {
		customEnchant = CEnchant.valueOf(toString());
		this.desc = conv(desc);
		this.type = type;
		customEnchant.customEnchant.setDescription(getDesc());
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
	
	public List<String> getDesc() {
		return desc;
	}
	
	MenuItem(String desc, Material type, int dur) {
		customEnchant = CEnchant.valueOf(toString());
		durability = (byte) dur;
		this.desc = conv(desc);
		this.type = type;
		customEnchant.customEnchant.setDescription(this.desc);
	}
	
	public CEnchant getCustomEnchant() {
		return customEnchant;
	}
	
	public byte getDurability() {
		return durability;
	}
	
	public Material getType() {
		return type;
	}
}
