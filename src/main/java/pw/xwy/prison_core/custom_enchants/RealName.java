package pw.xwy.prison_core.custom_enchants;

import pw.xwy.prison_core.utility.CustomEnchant;
import pw.xwy.prison_core.utility.CustomEnchantManager;

public enum RealName {
	ArmorDestructor,
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
	EXPLOSIVEPICKI,
	EXPLOSIVEPICKII,
	EXPLOSIVEPICKIII;

	RealName() {

	}

	public CustomEnchant getEnchant() {
		return CustomEnchantManager.getInstance().getEnchantsByRealName().get(this.toString());
	}

}
