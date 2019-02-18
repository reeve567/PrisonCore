package pw.xwy.prison_core;

import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.CustomEnchantManager;

public enum RealName {

	ANIMALCOOKER,
	ANTIKNOCKBACKI, ANTIKNOCKBACKII, ANTIKNOCKBACKIII,
	ARMORDESTRUCTOR,
	AUTOFEEDER,
	BATTLEROAR,
	BURNSHIELD,
	CHARGE,
	CURSED,
	DECAPITATE,
	DEMONSWEAKNESSI, DEMONSWEAKNESSII,
	DIZZY,
	EFFICIENCYVI, EFFICIENCYVII, EFFICIENCYVIII, EFFICIENCYIX, EFFICIENCYX,
	EXPLOSIVEPICKI, EXPLOSIVEPICKII, EXPLOSIVEPICKIII,
	EXPPROTECTOR,
	EXPTHIEF,
	FLASH,
	FORTUNEV,
	FROZENBLADE,
	FURNACE,
	GLOWING,
	GRAPPLINGBOW,
	GUARDIAN,
	GUARDIANSBLOCK,
	HEARTBOOST,
	HOSTILEDAMAGE,
	LUMBERJACK,
	MEDICINE,
	MINERI, MINERII,
	MOBSLAYERI, MOBSLAYERII, MOBSLAYERIII,
	MOLTEN,
	PARALYZE,
	REINFORCED,
	REKT,
	RIFLE,
	SCUBADIVER,
	SELFHEALER,
	SHOTGUN,
	SMELTING,
	SMOKESCREENI, SMOKESCREENII, SMOKESCREENIII,
	SOULBOUND,
	STARVEDI, STARVEDII, STARVEDIII,
	STORMCALLER,
	SUPERSPEED,
	THOR,
	VALOR,
	VAMPIRE,
	VOLTAGE,
	WINDSSPEEDI, WINDSSPEEDII,
	WITHERI, WITHERII,
	XWY,

	//TODO: Move to correct place
	//Just put these here to get rid of compiler errors, runtime errors will occur still
	//Still need to make classes for these enchants







	FROZENARROW,

	POISONOUSARROW,

	EXPLOSIVEARROW,
	RPG,













	;

	public static boolean hasEnchant(ItemStack itemInHand, RealName ench) {
		if (itemInHand != null && itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasLore()) {
			for (String s : itemInHand.getItemMeta().getLore()) {
				if (ench.getEnchant().getName().equalsIgnoreCase(s))
					return true;
			}
		}
		return false;
	}

	public CustomEnchant getEnchant() {
		return CustomEnchantManager.getInstance().getEnchantsByRealName().get(this.toString());
	}

}
