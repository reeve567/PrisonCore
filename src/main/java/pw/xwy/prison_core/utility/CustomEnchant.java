package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.config.Config;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Rarity;
import pw.xwy.prison_core.utility.item.CustomItem;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomEnchant {

	private String name;
	private String realName;
	private String commandLabel;
	private ItemSets sets;
	private Rarity rarity;
	private List<String> description;
	private ItemStack displayItem;
	private boolean enabled = true;

	public CustomEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		this(name, sets, rarity, description, displayItem, 0);
		if (disable.length > 0) enabled = false;
	}

	public CustomEnchant(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		if (disable.length > 0) enabled = false;
		this.name = rarity.getColor() + name;
		this.realName = name.replaceAll(" ", "").replaceAll("&7", "").replaceAll("'", "").toUpperCase();
		this.commandLabel = name.replaceAll(" ", "").replaceAll("&7", "").replaceAll("'", "");
		this.sets = sets;
		this.rarity = rarity;
		this.description = conv(description);
		this.displayItem = new CustomItem(displayItem).setDurability(durability);
	}

	public void saveDefault(Config config) {
		String base = "CustomEnchants." + realName + ".";
		config.set(base + "name", name);
		config.set(base + "rarity", rarity.toString());
		config.set(base + "commandLabel", commandLabel);
		config.set(base + "description", description);
		config.set(base + "enabled", true);
	}

	public void setCustomStuff(Config config) {
		String base = "CustomEnchants." + realName + ".";
		this.name = config.getString(base + "name");
		this.rarity = Rarity.valueOf(config.getString(base + "rarity"));
		this.commandLabel = config.getString(base + "commandLabel");
		this.description = config.getStringList(base + "description");
		this.enabled = config.getBoolean(base + "enabled");
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

	public boolean containsSet(ItemSets set) {
		return sets.getSets().contains(set);
	}

	public ItemSets getSets() {
		return sets;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public List<String> getDescription() {
		return description;
	}

	public ItemStack getDisplayItem() {
		return displayItem;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getRealName() {
		return realName;
	}

	public String getCommandLabel() {
		return commandLabel;
	}

	public boolean checkSets(Material material) {
		return sets.setContains(material);
	}
}
