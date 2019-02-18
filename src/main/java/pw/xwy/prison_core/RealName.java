package pw.xwy.prison_core;

import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.CustomEnchantManager;

public enum RealName {

	ANTIKNOCKBACKI, ANTIKNOCKBACKII, ANTIKNOCKBACKIII,
	ARMORDESTRUCTOR,
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
	GRAPPLINGBOW,
	GUARDIAN,
	GUARDIANSBLOCK,
	HEARTBOOST,
	HOSTILEDAMAGE,
	LUMBERJACK,
	MEDICINE,
	MINERI, MINERII,
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
	//Still need to make class for these enchants and some others
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
	FURNACE,
	EXPLOSIVEARROW,
	RPG,

	SMELTING,

	GLOWING,
	SCUBADIVER,
	VALOR,

	MOBSLAYERI,
	MOBSLAYERII,
	MOBSLAYERIII,
	ANIMALCOOKER,




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
