package pw.xwy.prison_core;

import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.CustomEnchantManager;

public enum RealName {
	ARMORDESTRUCTOR,
	BURNSHIELD,
	CURSED,
	DECAPITATE,
	DEMONSWEAKNESSI,
	DEMONSWEAKNESSII,
	EXPLOSIVEPICKI,
	EXPLOSIVEPICKII,
	EXPLOSIVEPICKIII,
	FLASH,
	GRAPPLINGBOW,
	GUARDIAN,
	GUARDIANSBLOCK,
	LUMBERJACK,
	MEDICINE,
	REINFORCED,
	REKT,
	SELFHEALER,
	SMOKESCREENI,
	SMOKESCREENII,
	SMOKESCREENIII,
	SOULBOUND,
	SUPERSPEED,
	THOR,
	XWY,

	//TODO: Move to correct place
	//Just put these here to get rid of compiler errors, runtime errors will occur still
	//Still need to make class for these enchants and some others


	FORTUNEV,

	EFFICIENCYI,
	EFFICIENCYII,
	EFFICIENCYIII,
	EFFICIENCYIV,
	EFFICIENCYV,
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
	FURNACE,
	EXPLOSIVEARROW,
	RPG,

	STARVEDI,
	STARVEDII,
	STARVEDIII,



	ANTIKNOCKBACKI,
	ANTIKNOCKBACKII,
	ANTIKNOCKBACKIII,

	MOLTEN,

	VAMPIRE,

	STORMCALLER,

	EXPTHIEF,

	EXPPROTECTOR,

	CHARGE,

	WITHERI,
	WITHERII,
	FROZENBLADE,

	DIZZY,

	HOSTILEDAMAGE,

	PARALYZE,

	BATTLEROAR,

	MINERI,
	MINERII,

	SMELTING

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
