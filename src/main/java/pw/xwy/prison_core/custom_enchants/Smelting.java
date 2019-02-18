package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.FortuneCalc;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;
import pw.xwy.prison_core.utility.item.CustomItem;

import java.util.ArrayList;

public class Smelting extends CustomEnchant {
	public Smelting(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public Smelting(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	public ArrayList<ItemStack> event(Block b, boolean fortune, int lvl, byte data) {
		ArrayList<ItemStack> drops = new ArrayList<>();
		if (b.getType().equals(Material.GOLD_ORE) || b.getType().equals(Material.IRON_ORE) || b.getType().equals(Material.COBBLESTONE)) {
			drops.add(smelting(b.getType(), getAmount(fortune, lvl, b.getType())));
		} else {
			if (b.getType() == Material.LAPIS_ORE) data = 4;

			for (ItemStack j : b.getDrops()) {
				drops.add(new CustomItem(new ItemStack(j.getType(), getAmount(fortune, lvl, b.getType()), data)).setDurability((int) data));
			}
		}
		return drops;
	}

	private ItemStack smelting(Material type, int amount) {

		if (type.equals(Material.GOLD_ORE)) {
			return new ItemStack(Material.GOLD_INGOT, amount);
		} else if (type.equals(Material.IRON_ORE)) {
			return new ItemStack(Material.IRON_INGOT, amount);
		} else if (type.equals(Material.COBBLESTONE)) {
			return new ItemStack(Material.STONE);
		}
		return new ItemStack(Material.AIR);
	}

	private int getAmount(boolean fortune, int forLevel, Material type) {
		if (fortune) return FortuneCalc.numDroppedFromFortune(forLevel, type, 1);
		return 1;
	}
}
