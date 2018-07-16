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
// on 7:44 PM

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;
import pw.xwy.prison_core.custom_enchants.enums.Rarity;
import pw.xwy.prison_core.custom_enchants.enums.Souls;

public class UncommonSC extends Crate {
	
	public UncommonSC() {
		super(Bukkit.createInventory(null, 27, Rarity.UNCOMMON.getLabel()));
		for (CEnchant ces : CEnchant.values()) {
			if (ces.getRarity().equals(Rarity.UNCOMMON)) {
				prizes.add(new Prize(ces.getName(), new ItemStack(Material.BOOK)));
			}
		}
		prizes.add(new Prize(resources.getString("10000"), new ItemStack(Material.DOUBLE_PLANT, 10)));
		prizes.add(new Prize(resources.getString("7500"), new ItemStack(Material.DOUBLE_PLANT, 7)));
		prizes.add(new Prize(Souls.RARE.getItem().getItemMeta().getDisplayName(), Souls.RARE.getItem()));
		prizes.add(new Prize(Souls.UNCOMMON.getItem().getItemMeta().getDisplayName(), Souls.UNCOMMON.getItem()));
		prizes.add(new Prize(resources.getString("OcelotSpawner"), new ItemStack(Material.MOB_SPAWNER, 1)));
		prizes.add(new Prize(resources.getString("PigSpawner"), new ItemStack(Material.MOB_SPAWNER, 1)));
		prizes.add(new Prize(resources.getString("10Creepers"), new ItemStack(Material.MONSTER_EGG, 10, (short) 50)));
		prizes.add(new Prize(resources.getString("32EXP"), new ItemStack(Material.EXP_BOTTLE, 32)));
		prizes.add(new Prize(resources.getString("2Hopper"), new ItemStack(Material.HOPPER, 2)));
		prizes.add(new Prize(resources.getString("64TNT&5Dispensers"), new ItemStack(Material.TNT, 64)));
		prizes.add(new Prize(resources.getString("OrePackage"), new ItemStack(Material.EMERALD_ORE, 32)));
		prizes.add(new Prize(resources.getString("Shark"), new ItemStack(Material.INK_SACK, 1, (short) 4)));
		
		for (int i = 0; i < 27; i++) {
			baseInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
		}
	}
	
}
