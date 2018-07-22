////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import pw.xwy.prison_core.utility.Config;

public enum CEnchant {
	THOR("&cThor", "Axe", Rarity.COMMON, ItemSets.AXE),
	STORMCALLER("&cStormCaller", "Sword", Rarity.COMMON, ItemSets.SWORD),
	REKT("&cRekt", "Axe", Rarity.RARE, ItemSets.AXE),
	DECAPITATE("&dDecapitate", "Sword & Axe", Rarity.RARE, ItemSets.SWORDAXE),
	MOBSLAYERI("&cMobSlayer I", "MobSlayerI", "Sword", Rarity.COMMON, ItemSets.SWORD),
	MOBSLAYERII("&cMobSlayer II", "MobSlayerII", "Sword", Rarity.UNCOMMON, 0, ItemSets.SWORD),
	MOBSLAYERIII("&cMobSlayer III", "MobSlayerIII", "Sword", Rarity.RARE, 0, ItemSets.SWORD),
	MOLTEN("&aMolten", "Armor", Rarity.COMMON, ItemSets.ARMOR),
	UNSTABLEI("&aUnstable I", "UnstableI", "Armor", Rarity.RARE, ItemSets.ARMOR),
	UNSTABLEII("&aUnstable II", "UnstableII", "Armor", Rarity.MYSTICAL, 0, ItemSets.ARMOR),
	SOULBOUND("&aSoulbound", "Tools & Armor", Rarity.HYDRO, ItemSets.EVERYTHING),
	FROZENBLADE("&cFrozenBlade", "Sword", Rarity.RARE, ItemSets.SWORD),
	EXPTHIEF("&cEXPThief", "Sword", Rarity.RARE, ItemSets.SWORD),
	HOSTILEDAMAGE("&cHostileDamage", "Sword", Rarity.UNCOMMON, ItemSets.SWORD),
	VAMPIRE("&cVampire", "Sword", Rarity.HYDRO, ItemSets.SWORD),
	GRAPPLINGBOW("&aGrapplingBow", "Bow", Rarity.HYDRO, ItemSets.BOW),
	CURSED("&cCursed", "Axe", Rarity.HYDRO, ItemSets.AXE),
	DIZZY("&cDizzy", "Sword", Rarity.MYSTICAL, ItemSets.SWORD),
	SMOKESCREENI("&cSmokeScreen I", "SmokeScreenI", "Sword & Axe", Rarity.MYSTICAL, ItemSets.SWORDAXE),
	SMOKESCREENII("&cSmokeScreen II", "SmokeScreenII", "Sword & Axe", Rarity.MYSTICAL, 0, ItemSets.SWORDAXE),
	SMOKESCREENIII("&cSmokeScreen III", "SmokeScreenIII", "Sword & Axe", Rarity.HYDRO, 0, ItemSets.SWORDAXE),
	POISONOUSARROW("&cPoisonousArrow", "Bow", Rarity.UNCOMMON, ItemSets.BOW),
	ANIMALCOOKER("&cAnimalCooker", "Sword", Rarity.COMMON, ItemSets.SWORD),
	FURNACE("&cFurnace", "Bow", Rarity.COMMON, ItemSets.BOW),
	SCUBADIVER("&aScubaDiver", "Helmet", Rarity.COMMON, ItemSets.HELM),
	GLOWING("&aGlowing", "Helmet", Rarity.COMMON, ItemSets.HELM),
	HEARTBOOST("&aHeartBoost", "Helmet", Rarity.HYDRO, ItemSets.HELM),
	VALOR("&cValor", "Armor", Rarity.HYDRO, ItemSets.ARMOR),
	FLASH("&aFlash", "Armor", Rarity.HYDRO, ItemSets.ARMOR),
	SMELTING("&aSmelting", "Pickaxe", Rarity.COMMON, ItemSets.PICK),
	HARDENED("&aHardened", "Pickaxe", Rarity.COMMON, ItemSets.PICK),
	FORTUNEV("&7Fortune V", "FortuneV", "Pickaxe", Rarity.MYSTICAL, ItemSets.PICK),
	MAGNET("&aMagnet", "Pickaxe", Rarity.RARE, ItemSets.PICK),
	EXPLOSIVEPICK("&aExplosivePick", "Pickaxe", Rarity.HYDRO, ItemSets.PICK),
	//DETONATE("&cDetonate", "Chestplate", Rarity.MYSTICAL, ItemSets.CHEST),
	DEMONSWEAKNESSI("&cDemon's Weakness I", "DemonsWeaknessI", "Sword & Axe", Rarity.HYDRO, ItemSets.SWORDAXE),
	DEMONSWEAKNESSII("&cDemon's Weakness II", "DemonsWeaknessII", "Sword & Axe", Rarity.HYDRO, 0, ItemSets.SWORDAXE),
	PARALYZE("&cParalyze", "Sword", Rarity.COMMON, ItemSets.SWORD),
	WITHERI("&cWither I", "WitherI", "Sword", Rarity.HYDRO, ItemSets.SWORD),
	WITHERII("&cWither II", "WitherII", "Sword", Rarity.HYDRO, 0, ItemSets.SWORD),
	BATTLEROAR("&cBattleRoar", "Chestplate", Rarity.UNCOMMON, ItemSets.CHEST),
	BURNSHEILD("&aBurn-Shield", "Armor", Rarity.COMMON, ItemSets.ARMOR),
	MEDICINE("&aMedicine", "Helmet", Rarity.HYDRO, ItemSets.HELM),
	GUARDIAN("&aGuardian", "Helmet", Rarity.HYDRO, ItemSets.HELM),
	AUTOFEEDER("&aAutoFeeder", "Leggings", Rarity.MYSTICAL, ItemSets.LEGGINGS),
	VOLTAGE("&cVoltage", "Bow", Rarity.UNCOMMON, ItemSets.BOW),
	FROZENARROW("&cFrozenArrow", "Bow", Rarity.UNCOMMON, ItemSets.BOW),
	SHOTGUN("&cShotgunBow", "Bow", Rarity.HYDRO, ItemSets.BOW),
	EXPPROTECTOR("&aEXPProtector", "Boots", Rarity.RARE, ItemSets.LEGGINGS),
	SELFHEALER("&aSelfHealer", "Boots", Rarity.HYDRO, ItemSets.LEGGINGS),
	CHARGE("&cCharge", "Sword", Rarity.HYDRO, ItemSets.SWORD),
	//DEMONSBLADE("&cDemon's Blade", "DemonsBlade", "Sword", Rarity.RARE, ItemSets.SWORD),
	GUARDIANSBLOCK("&cGuardian's Block", "GuardiansBlock", "Sword", Rarity.MYSTICAL, ItemSets.SWORD),
	ARMORDESTRUCTOR("&cArmor Destructor", "ArmorDestructor", "Axe", Rarity.RARE, ItemSets.AXE),
	RIFLE("&cRifleBow", "Bow", Rarity.HYDRO, ItemSets.BOW),
	EXPLOSIVEARROW("&cExplosiveArrow", "Bow", Rarity.MYSTICAL, ItemSets.BOW),
	RPG("&cRPG", "Bow", Rarity.MYSTICAL, ItemSets.BOW),
	REINFORCED("&aReinforced", "Chestplate", Rarity.HYDRO, ItemSets.CHEST),
	//LEADERSHIP("&cLeadership", "Boots", Rarity.HYDRO, ItemSets.BOOTS),
	MOONGRAVITY("&aMoonGravity", "Boots", Rarity.RARE, ItemSets.BOOTS),
	WINDSSPEEDI("&aWind's Speed I", "WindsSpeedI", "Boots", Rarity.COMMON, ItemSets.BOOTS),
	WINDSSPEEDII("&aWind's Speed II", "WindsSpeedII", "Boots", Rarity.RARE, 0, ItemSets.BOOTS),
	SUPERSPEED("&aSuperSpeed", "Boots", Rarity.HYDRO, ItemSets.BOOTS),
	STARVEDI("&cStarved I", "StarvedI", "Chestplate", Rarity.RARE, ItemSets.CHEST),
	STARVEDII("&cStarved II", "StarvedII", "Chestplate", Rarity.RARE, 0, ItemSets.CHEST),
	STARVEDIII("&cStarved III", "StarvedIII", "Chestplate", Rarity.MYSTICAL, 0, ItemSets.CHEST),
	ANTIKNOCKBACKI("&cAntiKnockback I", "AntiKnockbackI", "Leggings", Rarity.UNCOMMON, ItemSets.LEGGINGS),
	ANTIKNOCKBACKII("&cAntiKnockback II", "AntiKnockbackII", "Leggings", Rarity.RARE, 0, ItemSets.LEGGINGS),
	ANTIKNOCKBACKIII("&cAntiKnockback III", "AntiKnockbackIII", "Leggings", Rarity.MYSTICAL, 0, ItemSets.LEGGINGS),
	LUMBERJACK("&aLumberjack", "Axe", Rarity.RARE, ItemSets.AXE),
	MINERI("&cMiner I", "MinerI", "Pickaxe", Rarity.MYSTICAL, ItemSets.PICK),
	MINERII("&cMiner II", "MinerII", "Pickaxe", Rarity.MYSTICAL, 0, ItemSets.PICK),
	
	XWY("&6God", "Xwy", "Boots", Rarity.ADMIN, 0, ItemSets.BOOTS),;
	
	public CustomEnchant customEnchant;
	private boolean enabled = true;
	
	CEnchant(String inGameName, String commandLabel, String loreLbl, Rarity rarity, ItemSets a) {
		customEnchant = new CustomEnchant(this, inGameName, commandLabel, rarity, a);
	}
	
	CEnchant(String inGameName, String commandLabel, String loreLbl, Rarity rarity, int am, ItemSets a) {
		customEnchant = new CustomEnchant(this, inGameName, commandLabel, rarity, a, am);
	}
	
	CEnchant(String inGameName, String loreLbl, Rarity rarity, ItemSets a) {
		customEnchant = new CustomEnchant(this, inGameName, rarity, a);
	}
	
	public boolean checkSets(Material m) {
		return customEnchant.getSets().setContains(m);
	}
	
	public boolean containsSet(ItemSets set) {
		return customEnchant.getSets().getSets().contains(set);
	}
	
	public int getAmount() {
		return customEnchant.getAmount();
	}
	
	public String getLabel() {
		
		return customEnchant.getCommandLabel();
	}
	
	public String getLoreLbl() {
		
		return conv(customEnchant.getLoreLabel());
	}
	
	private String conv(String name) {
		return ChatColor.translateAlternateColorCodes('&', name);
	}
	
	public String getName() {
		
		return conv(customEnchant.getName());
	}
	
	public Rarity getRarity() {
		
		return customEnchant.getRarity();
	}
	
	public void getCustomStuff(Config config) {
		customEnchant.setCustomStuff(config);
	}
	
	public void disable() {
		enabled = false;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
}
