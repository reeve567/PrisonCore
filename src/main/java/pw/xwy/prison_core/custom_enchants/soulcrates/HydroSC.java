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
// on 1:28 PM

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.enums.Rarity;
import pw.xwy.prison_core.custom_enchants.enums.Souls;

public class HydroSC extends Crate {
	
	public HydroSC() {
		super(Bukkit.createInventory(null, 27, Rarity.HYDRO.getLabel()));
		for (CEnchant ces : CEnchant.values()) {
			if (ces.getRarity().equals(Rarity.HYDRO)) {
				prizes.add(new Prize(ces.getName(), new ItemStack(Material.BOOK)));
			}
		}
		prizes.add(new Prize(ChatColor.GREEN + "$" + ChatColor.DARK_GREEN + "25000", new ItemStack(Material.DOUBLE_PLANT, 25)));
		prizes.add(new Prize(ChatColor.GREEN + "$" + ChatColor.DARK_GREEN + "50000", new ItemStack(Material.DOUBLE_PLANT, 30)));
		prizes.add(new Prize(Souls.MYSTICAL.getItem().getItemMeta().getDisplayName(), Souls.MYSTICAL.getItem()));
		prizes.add(new Prize(Rarity.COMMON.name() + ChatColor.GRAY + ", " + Rarity.UNCOMMON.name() + ChatColor.GRAY + " and " + Rarity.RARE.name() + ChatColor.GRAY + " Souls", new ItemStack(Souls.MYSTICAL.getItem())));
		prizes.add(new Prize(ChatColor.BLUE + "Orca", new ItemStack(Material.INK_SACK, 1, (short) 4)));
		prizes.add(new Prize(ChatColor.DARK_PURPLE + "Blaze Spawner", new ItemStack(Material.MOB_SPAWNER)));
		
		for (int i = 0; i < 27; i++) {
			baseInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
		}
	}
	
}
