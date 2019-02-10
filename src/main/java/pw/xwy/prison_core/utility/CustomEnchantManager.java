package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import pw.xwy.prison_core.RealName;
import pw.xwy.prison_core.custom_enchants.*;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CustomEnchantManager {

	private static CustomEnchantManager instance;
	private HashMap<Rarity, List<CustomEnchant>> enchantsByRarity = new HashMap<>();
	private HashMap<String, CustomEnchant> enchantsByRealName = new HashMap<>();
	private HashMap<String, CustomEnchant> enchantsByName = new HashMap<>();
	private HashMap<String, CustomEnchant> enchantsByLore = new HashMap<>();
	private HashMap<String, CustomEnchant> enchantsByLabel = new HashMap<>();
	private List<CustomDamageEnchant> damageEnchants = new ArrayList<>();
	private List<CustomDeathEnchant> deathEnchants = new ArrayList<>();
	private List<CustomBlockEnchant> blockEnchants = new ArrayList<>();
	private List<CustomTaskEnchant> taskEnchants = new ArrayList<>();

	public CustomEnchantManager() {
		instance = this;
		// Setup list
		for (Rarity r : Rarity.values()) {
			enchantsByRarity.put(r, new ArrayList<>());
		}

		// START ENCHANTS
		init(
				new ArmorDestructor("Armor Destructor", ItemSets.AXE, Rarity.RARE, "Damages the opponents armor more than normal.", Material.LEATHER_CHESTPLATE),
				new BurnShield("Burn Shield", ItemSets.ARMOR, Rarity.COMMON, "This enchant allows the wearer++to swim in lava and fire if you have++the enchant on all pieces of armor.", Material.POTION, 8259),
				new Cursed(),
				new Decapitate(),
				new DemonsWeakness1(),
				new DemonsWeakness2(),
				new ExplosivePick1("Explosive Pick I", ItemSets.PICK, Rarity.HYDRO, "This enchant allows your pickaxe to++mine in a 3x3x3 area.", Material.FIREBALL),
				new ExplosivePick2("Explosive Pick II", ItemSets.PICK, Rarity.HYDRO, "This enchant allows your pickaxe to++mine in a 5x5x5 area.", Material.FIREBALL),
				new ExplosivePick3("Explosive Pick III", ItemSets.PICK, Rarity.HYDRO, "This enchant allows your pickaxe to++mine in a 7x7x7 area.", Material.FIREBALL),
				new GrapplingBow("Grappling Bow", ItemSets.BOW, Rarity.HYDRO, "Arrows will launch you towards where they++land, if on the correct mode.", Material.WEB),
				new Flash("Flash", ItemSets.ARMOR, Rarity.HYDRO, "This enchant gives you haste if++you have the enchant on all pieces of armor.", Material.GOLD_PICKAXE),
				new Lumberjack("Lumberjack", ItemSets.AXE, Rarity.RARE, "Mining the bottom log in a tree++will drop all the wood.", Material.LOG),
				new Medicine("Medicine", ItemSets.HELM, Rarity.MYSTICAL, "This enchant makes the wearer immune to poison.", Material.BROWN_MUSHROOM),
				new Rekt("Rekt", ItemSets.AXE, Rarity.RARE, "Has a chance to deal double damage.", Material.DIAMOND_PICKAXE),
				new SelfHealer("Self Healer", ItemSets.LEGGINGS, Rarity.MYSTICAL, "This enchant will give you 4++seconds of regeneration when you get below 3 hearts.", Material.RED_MUSHROOM),
				new SmokeScreen1("Smoke Screen I", ItemSets.SWORDAXE, Rarity.MYSTICAL, "Has a chance to give the opponent++blindness for 2 seconds.", Material.COAL, 1),
				new SmokeScreen2(),
				new SmokeScreen3(),
				new Soulbound("Soulbound", ItemSets.EVERYTHING, Rarity.HYDRO, "Dying with this enchant on an item++has a chance of keeping it on respawn.", Material.GOLDEN_APPLE, 1),
				new Thor("Thor", ItemSets.AXE, Rarity.COMMON, "Has a chance to smite the opponent when you hit them.", Material.IRON_AXE),
				new Xwy("Xwy", ItemSets.BOOTS, Rarity.ADMIN, "OOF", Material.DIAMOND_BOOTS)
		);


	}

	public static CustomEnchantManager getInstance() {
		return instance;
	}

	public HashMap<Rarity, List<CustomEnchant>> getEnchantsByRarity() {
		return enchantsByRarity;
	}

	public HashMap<String, CustomEnchant> getEnchantsByRealName() {
		return enchantsByRealName;
	}

	public HashMap<String, CustomEnchant> getEnchantsByLore() {
		return enchantsByLore;
	}

	public CustomEnchant getEnchantsByLabel(String label) {
		return enchantsByLabel.get(label);
	}

	public HashMap<String, CustomEnchant> getEnchantsByName() {
		return enchantsByName;
	}

	public List<CustomDamageEnchant> getDamageEnchants() {
		return damageEnchants;
	}

	public List<CustomDeathEnchant> getDeathEnchants() {
		return deathEnchants;
	}

	public List<CustomBlockEnchant> getBlockEnchants() {
		return blockEnchants;
	}

	public List<CustomTaskEnchant> getTaskEnchants() {
		return taskEnchants;
	}

	private void init(CustomEnchant... enchants) {
		for (CustomEnchant ce : enchants) {
			if (ce instanceof CustomDamageEnchant) {
				damageEnchants.add((CustomDamageEnchant) ce);
			} else if (ce instanceof CustomDeathEnchant) {
				deathEnchants.add((CustomDeathEnchant) ce);
			} else if (ce instanceof CustomBlockEnchant) {
				blockEnchants.add((CustomBlockEnchant) ce);
			} else if (ce instanceof CustomTaskEnchant) {
				taskEnchants.add((CustomTaskEnchant) ce);
			}

			List<CustomEnchant> add = enchantsByRarity.get(ce.getRarity());
			add.add(ce);
			enchantsByRarity.put(ce.getRarity(), add);
			enchantsByRealName.put(ce.getRealName(), ce);
			enchantsByLore.put(ce.getName(), ce);
			enchantsByLabel.put(ce.getCommandLabel(), ce);
		}


	}

	public CustomEnchant getRandomEnchant(Rarity rarity) {
		CustomEnchant customEnchant = enchantsByRarity.get(rarity).get(new Random().nextInt(enchantsByRarity.get(rarity).size()));

		while (customEnchant.getRealName().equals(RealName.EXPLOSIVEPICKII.toString()) || customEnchant.getRealName().equals(RealName.EXPLOSIVEPICKIII.toString())) {
			customEnchant = enchantsByRarity.get(rarity).get(new Random().nextInt(enchantsByRarity.get(rarity).size()));
		}

		return customEnchant;

	}


}
