package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomVanillaEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Efficiency6 extends CustomVanillaEnchant {
	public Efficiency6(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public Efficiency6(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void apply(ItemStack itemInteractedWith, Player player) {
		if (itemInteractedWith.getItemMeta().hasEnchant(Enchantment.DIG_SPEED)) {
			if (itemInteractedWith.getItemMeta().getEnchantLevel(Enchantment.DIG_SPEED) >= 6)
				return;
		}
		itemInteractedWith.removeEnchantment(Enchantment.DIG_SPEED);
		itemInteractedWith.addUnsafeEnchantment(Enchantment.DIG_SPEED, 6);
		player.sendMessage(Messages.itemSuccess.get());
		player.setItemOnCursor(null);
	}

}
