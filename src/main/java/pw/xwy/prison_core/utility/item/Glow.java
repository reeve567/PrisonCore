package pw.xwy.prison_core.utility.item;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Glow extends Enchantment {
	
	/**
	 * insert this in main class and run in onEnable
	 * <p>
	 * private void registerGlow() {
	 * try {
	 * Field f = Enchantment.class.getDeclaredField("acceptingNew");
	 * f.setAccessible(true);
	 * f.set(null, true);
	 * } catch (Exception e) {
	 * e.printStackTrace();
	 * }
	 * try {
	 * Glow glow = new Glow(70);
	 * Enchantment.registerEnchantment(glow);
	 * } catch (IllegalArgumentException ignored) {
	 * } catch (Exception e) {
	 * e.printStackTrace();
	 * }
	 * }
	 **/
	public Glow(int id) {
		
		super(id);
	}
	
	@Override
	public boolean canEnchantItem(ItemStack itemStack) {
		
		return false;
	}
	
	@Override
	public boolean conflictsWith(Enchantment enchantment) {
		
		return false;
	}
	
	@Override
	public EnchantmentTarget getItemTarget() {
		
		return null;
	}
	
	@Override
	public int getMaxLevel() {
		
		return 0;
	}
	
	@Override
	public String getName() {
		
		return null;
	}
	
	@Override
	public int getStartLevel() {
		
		return 0;
	}
	
}