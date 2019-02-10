package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;
import pw.xwy.prison_core.utility.item.CustomItem;

public class CustomEnchant {

	private String name;
	private ItemSets sets;
	private Rarity rarity;
	private String description;
	private ItemStack displayItem;

	public CustomEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem) {
		this(name, sets, rarity, description, displayItem, 0);
	}

	public CustomEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability) {
		this.name = name;
		this.sets = sets;
		this.rarity = rarity;
		this.description = description;
		this.displayItem = new CustomItem(displayItem).setDurability(durability);
	}

	public ItemSets getSets() {
		return sets;
	}

	public void setSets(ItemSets sets) {
		this.sets = sets;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemStack getDisplayItem() {
		return displayItem;
	}

	public void setDisplayItem(ItemStack displayItem) {
		this.displayItem = displayItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
