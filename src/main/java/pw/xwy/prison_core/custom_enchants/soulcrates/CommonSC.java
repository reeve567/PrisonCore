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
// on 5:45 PM

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.enums.Rarity;
import pw.xwy.prison_core.custom_enchants.enums.Souls;

public class CommonSC extends Crate {
	
	public CommonSC() {
		super(Bukkit.createInventory(null, 27, Rarity.COMMON.getLabel()));
		for (CEnchant ces : CEnchant.values()) {
			if (ces.getRarity().equals(Rarity.COMMON)) {
				prizes.add(new Prize(ces.getName(), new ItemStack(Material.BOOK)));
			}
		}
		prizes.add(new Prize(resources.getString("64Diamonds"), new ItemStack(Material.DIAMOND, 64)));
		prizes.add(new Prize(resources.getString("32Diamonds"), new ItemStack(Material.DIAMOND, 32)));
		prizes.add(new Prize(resources.getString("5000"), new ItemStack(Material.DOUBLE_PLANT, 5)));
		prizes.add(new Prize(resources.getString("2500"), new ItemStack(Material.DOUBLE_PLANT, 2)));
		prizes.add(new Prize(Souls.COMMON.getItem().getItemMeta().getDisplayName(), Souls.COMMON.getItem()));
		prizes.add(new Prize(Souls.UNCOMMON.getItem().getItemMeta().getDisplayName(), Souls.UNCOMMON.getItem()));
		prizes.add(new Prize(resources.getString("BatSpawner"), new ItemStack(Material.MOB_SPAWNER, 1)));
		prizes.add(new Prize(resources.getString("DiamondPackage"), new ItemStack(Material.DIAMOND_AXE, 1)));
		prizes.add(new Prize(resources.getString("5Creepers"), new ItemStack(Material.MONSTER_EGG, 5, (short) 50)));
		prizes.add(new Prize(resources.getString("64TNT"), new ItemStack(Material.TNT, 64)));
		prizes.add(new Prize(resources.getString("Shark"), new ItemStack(Material.INK_SACK, 1, (short) 4)));
		
		for (int i = 0; i < 27; i++) {
			baseInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
		}
	}
	
}
