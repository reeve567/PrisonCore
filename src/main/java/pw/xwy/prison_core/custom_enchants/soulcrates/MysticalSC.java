////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.soulcrates;
// made by reeve
// on 1:05 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.enums.Rarity;
import pw.xwy.prison_core.custom_enchants.enums.Souls;

public class MysticalSC extends Crate {
	
	public MysticalSC() {
		super(Bukkit.createInventory(null, 27, Rarity.MYSTICAL.getLabel()));
		for (CEnchant ces : CEnchant.values()) {
			if (ces.getRarity().equals(Rarity.MYSTICAL)) {
				prizes.add(new Prize(ces.getName(), new ItemStack(Material.BOOK)));
			}
		}
		prizes.add(new Prize(ChatColor.GREEN + "$" + ChatColor.DARK_GREEN + "20000", new ItemStack(Material.DOUBLE_PLANT, 20)));
		prizes.add(new Prize(ChatColor.GREEN + "$" + ChatColor.DARK_GREEN + "17500", new ItemStack(Material.DOUBLE_PLANT, 17)));
		prizes.add(new Prize(Souls.RARE.getItem().getItemMeta().getDisplayName(), Souls.RARE.getItem()));
		prizes.add(new Prize(Souls.UNCOMMON.getItem().getItemMeta().getDisplayName(), Souls.UNCOMMON.getItem()));
		prizes.add(new Prize(Souls.MYSTICAL.getItem().getItemMeta().getDisplayName(), Souls.MYSTICAL.getItem()));
		prizes.add(new Prize(ChatColor.GREEN + "22 Creeper spawn eggs", new ItemStack(Material.MONSTER_EGG, 22, (short) 50)));
		prizes.add(new Prize(ChatColor.YELLOW + "64 Bottle o Enchanting", new ItemStack(Material.EXP_BOTTLE, 64)));
		prizes.add(new Prize(ChatColor.GRAY + "10 Hoppers", new ItemStack(Material.HOPPER, 5)));
		prizes.add(new Prize(ChatColor.BLUE + "Kraken", new ItemStack(Material.INK_SACK, 1, (short) 4)));
		prizes.add(new Prize(ChatColor.DARK_PURPLE + "Mystical Package", new ItemStack(Material.NETHER_STAR)));
		
		for (int i = 0; i < 27; i++) {
			baseInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
		}
	}
	
}
