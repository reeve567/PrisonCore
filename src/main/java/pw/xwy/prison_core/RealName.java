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
	EXPLOSIVEPICKI, EXPLOSIVEPICKII, EXPLOSIVEPICKIII,
	EXPPROTECTOR,
	EXPTHIEF,
	FLASH,
	FROZENBLADE,
	FURNACE,
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
	SELFHEALER,
	SMOKESCREENI, SMOKESCREENII, SMOKESCREENIII,
	SOULBOUND,
	STARVEDI, STARVEDII, STARVEDIII,
	STORMCALLER,
	SUPERSPEED,
	THOR,
	VAMPIRE,
	WINDSSPEEDI, WINDSSPEEDII,
	WITHERI, WITHERII,
	XWY,

	//TODO: Move to correct place
	//Just put these here to get rid of compiler errors, runtime errors will occur still
	//Still need to make classes for these enchants

	FORTUNEV,

	EFFICIENCYVI,
	EFFICIENCYVII,
	EFFICIENCYVIII,
	EFFICIENCYIX,
	EFFICIENCYX,

	SHOTGUN,
	RIFLE,
	FROZENARROW,
	VOLTAGE,
	POISONOUSARROW,

	EXPLOSIVEARROW,
	RPG,

	SMELTING,

	GLOWING,
	SCUBADIVER,
	VALOR,







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
