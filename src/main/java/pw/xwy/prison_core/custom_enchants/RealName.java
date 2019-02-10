package pw.xwy.prison_core.custom_enchants;

import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.CustomEnchantManager;

public enum RealName {
	ARMORDESTRUCTOR,
	BURNSHIELD,
	CURSED,
	DECAPITATE,
	DEMONSWEAKNESSI,
	DEMONSWEAKNESSII,
	FLASH,
	LUMBERJACK,
	REKT,
	SELFHEALER,
	SMOKESCREENI,
	SMOKESCREENII,
	SMOKESCREENIII,
	SOULBOUND,
	THOR,
	XWY,

	//TODO: Move to correct place
	//Just put these here to get rid of compiler errors, runtime errors will occur still
	//Still need to make class for these enchants and some others
	EXPLOSIVEPICKI,
	EXPLOSIVEPICKII,
	EXPLOSIVEPICKIII,

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

	MEDICINE,

	GUARDIAN,

	GUARDIANSBLOCK,

	REINFORCED,

	GRAPPLINGBOW,

	;

	RealName() {

	}

	public CustomEnchant getEnchant() {
		return CustomEnchantManager.getInstance().getEnchantsByRealName().get(this.toString());
	}

}
