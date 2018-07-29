package pw.xwy.prison_core.utility;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomItem extends ItemStack {
	
	public CustomItem(Material material) {
		super(material);
	}
	
	public CustomItem(ItemStack stack) {
		super(stack);
	}
	
	public CustomItem addGlow() {
		ItemMeta meta = meta();
		meta.addEnchant(Enchantment.getByName("Glow"), 1, true);
		setMeta(meta);
		return this;
	}
	
	private ItemMeta meta() {
		return getItemMeta();
	}
	
	private void setMeta(ItemMeta meta) {
		setItemMeta(meta);
	}
	
	public CustomItem addEnchant(Enchantment enchantment, int level) {
		addUnsafeEnchantment(enchantment, level);
		return this;
	}
	
	public CustomItem setCustomAmount(int amount) {
		setAmount(amount);
		return this;
	}
	
	@Deprecated
	@Override
	public void setAmount(int amount) {
		super.setAmount(amount);
	}
	
	public CustomItem addLore(String s) {
		ItemMeta meta = meta();
		List<String> lores = new ArrayList<>();
		if (meta.hasLore()) {
			lores = meta.getLore();
		}
		if (s.split("\n").length > 1) {
			for (String st : s.split("\n")) {
				lores.add(ChatColor.translateAlternateColorCodes('&', st));
			}
		} else {
			lores.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		meta.setLore(lores);
		setMeta(meta);
		return this;
	}
	
	public CustomItem removeGlow() {
		ItemMeta meta = meta();
		meta.removeEnchant(Enchantment.getByName("Glow"));
		setMeta(meta);
		return this;
	}
	
	public CustomItem setDurability(int data) {
		setDurability((short) data);
		return this;
	}
	
	public CustomItem setLore(String... lore) {
		return setLore(Arrays.asList(lore));
	}
	
	public CustomItem setLore(List<String> lore) {
		ItemMeta meta = meta();
		List<String> lores = new ArrayList<>();
		for (String s : lore) {
			if (!s.equals("")) {
				lores.add(ChatColor.translateAlternateColorCodes('&', s));
			}
		}
		meta.setLore(lores);
		setMeta(meta);
		return this;
	}
	
	public CustomItem setLore(String s) {
		ItemMeta meta = meta();
		List<String> lores = new ArrayList<>();
		lores.add(ChatColor.translateAlternateColorCodes('&', s));
		meta.setLore(lores);
		setMeta(meta);
		return this;
	}
	
	public CustomItem setName(String s) {
		ItemMeta meta = meta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
		setMeta(meta);
		return this;
	}
	
	@Override
	public CustomItem clone() {
		return (CustomItem) super.clone();
	}
}