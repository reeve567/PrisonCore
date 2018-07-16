////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.utilities;
// made by reeve
// on 7:12 PM

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Glow extends Enchantment {
	
	
	public Glow(int id) {
		
		super(id);
	}
	
	@Override
	public String getName() {
		
		return "Glow";
	}
	
	@Override
	public int getMaxLevel() {
		
		return 1;
	}
	
	@Override
	public int getStartLevel() {
		
		return 1;
	}
	
	@Override
	public EnchantmentTarget getItemTarget() {
		
		return EnchantmentTarget.ALL;
	}
	
	@Override
	public boolean conflictsWith(Enchantment enchantment) {
		
		return false;
	}
	
	@Override
	public boolean canEnchantItem(ItemStack itemStack) {
		
		return true;
	}
	
	
}
