package pw.xwy.prison_core.custom_enchants;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomVanillaEnchant;
import pw.xwy.prison_core.utility.enums.ItemSets;
import pw.xwy.prison_core.utility.enums.Messages;
import pw.xwy.prison_core.utility.enums.Rarity;

public class Fortune5 extends CustomVanillaEnchant {
	public Fortune5(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, boolean... disable) {
		super(name, sets, rarity, description, displayItem, disable);
	}

	public Fortune5(String name, ItemSets sets, Rarity rarity, String description, Material displayItem, int durability, boolean... disable) {
		super(name, sets, rarity, description, displayItem, durability, disable);
	}

	@Override
	public void apply(ItemStack itemInteractedWith, Player player) {
		if (itemInteractedWith.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
			if (itemInteractedWith.getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) == 5)
				return;
		}
		itemInteractedWith.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
		itemInteractedWith.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
		player.setItemOnCursor(null);
		player.sendMessage(Messages.itemSuccess.get());

	}
}
