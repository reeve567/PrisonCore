package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import pw.xwy.prison_core.custom_enchants.*;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CustomEnchantManager {

	private List<CustomEnchant> enchants = new ArrayList<>();
	private HashMap<Rarity, List<CustomEnchant>> enchantsByRarity = new HashMap<>();
	private List<CustomDamageEnchant> damageEnchants = new ArrayList<>();
	private List<CustomDeathEnchant> deathEnchants = new ArrayList<>();
	private List<CustomBlockEnchant> blockEnchants = new ArrayList<>();
	private List<CustomTaskEnchant> taskEnchants = new ArrayList<>();

	public CustomEnchantManager() {

		// Setup list
		for (Rarity r : Rarity.values()) {
			enchantsByRarity.put(r, new ArrayList<>());
		}

		// START ENCHANTS
		init(
				new ArmorDestructor("Armor Destructor", ItemSets.AXE, Rarity.RARE, "Damages the opponents armor more than normal.", Material.LEATHER_CHESTPLATE),
				new Cursed(),
				new Decapitate(),
				new DemonsWeakness1(),
				new DemonsWeakness2(),
				new Flash("Flash", ItemSets.ARMOR, Rarity.HYDRO, "This enchant gives you haste if++you have the enchant on all pieces of armor.", Material.GOLD_PICKAXE),
				new Lumberjack("Lumberjack", ItemSets.AXE, Rarity.RARE, "Mining the bottom log in a tree++will drop all the wood.", Material.LOG),
				new Rekt("Rekt", ItemSets.AXE, Rarity.RARE, "Has a chance to deal double damage.", Material.DIAMOND_PICKAXE),
				new SelfHealer("Self Healer", ItemSets.LEGGINGS, Rarity.MYSTICAL,"This enchant will give you 4++seconds of regeneration when you get below 3 hearts.", Material.RED_MUSHROOM),
				new SmokeScreen1("Smoke Screen I", ItemSets.SWORDAXE, Rarity.MYSTICAL, "Has a chance to give the opponent++blindness for 2 seconds.", Material.COAL, 1),
				new SmokeScreen2(),
				new SmokeScreen3(),
				new Soulbound("Soulbound", ItemSets.EVERYTHING, Rarity.HYDRO, "Dying with this enchant on an item++has a chance of keeping it on respawn.", Material.GOLDEN_APPLE, 1),
				new Thor("Thor",ItemSets.AXE,Rarity.COMMON,"Has a chance to smite the opponent when you hit them.", Material.IRON_AXE)


		);


	}

	private void init(CustomEnchant... enchants) {
		this.enchants.addAll(Arrays.asList(enchants));

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
			enchantsByRarity.put(ce.getRarity(),add);

		}


	}


}
