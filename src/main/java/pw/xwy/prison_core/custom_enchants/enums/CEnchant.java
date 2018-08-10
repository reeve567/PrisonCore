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
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.Config;

public enum CEnchant {
	THOR("Thor", Rarity.COMMON, ItemSets.AXE),
	STORMCALLER("StormCaller", Rarity.COMMON, ItemSets.SWORD),
	REKT("Rekt", Rarity.RARE, ItemSets.AXE),
	DECAPITATE("Decapitate", Rarity.RARE, ItemSets.SWORDAXE),
	MOBSLAYERI("MobSlayer I", "MobSlayerI", Rarity.COMMON, ItemSets.SWORD),
	MOBSLAYERII("MobSlayer II", "MobSlayerII", Rarity.UNCOMMON, 0, ItemSets.SWORD),
	MOBSLAYERIII("MobSlayer III", "MobSlayerIII", Rarity.RARE, 0, ItemSets.SWORD),
	MOLTEN("Molten", Rarity.COMMON, ItemSets.ARMOR),
	UNSTABLEI("Unstable I", "UnstableI", Rarity.RARE, ItemSets.ARMOR),
	UNSTABLEII("Unstable II", "UnstableII", Rarity.MYSTICAL, 0, ItemSets.ARMOR),
	SOULBOUND("Soulbound", Rarity.HYDRO, ItemSets.EVERYTHING),
	FROZENBLADE("Frozen Blade", Rarity.RARE, ItemSets.SWORD),
	EXPTHIEF("EXP Thief", Rarity.RARE, ItemSets.SWORD),
	HOSTILEDAMAGE("Hostile Damage", Rarity.UNCOMMON, ItemSets.SWORD),
	VAMPIRE("Vampire", Rarity.HYDRO, ItemSets.SWORD),
	GRAPPLINGBOW("Grappling Bow", Rarity.HYDRO, ItemSets.BOW),
	CURSED("Cursed", Rarity.HYDRO, ItemSets.AXE),
	DIZZY("Dizzy", Rarity.MYSTICAL, ItemSets.SWORD),
	SMOKESCREENI("Smoke Screen I", "SmokeScreenI", Rarity.MYSTICAL, ItemSets.SWORDAXE),
	SMOKESCREENII("Smoke Screen II", "SmokeScreenII", Rarity.MYSTICAL, 0, ItemSets.SWORDAXE),
	SMOKESCREENIII("Smoke Screen III", "SmokeScreenIII", Rarity.HYDRO, 0, ItemSets.SWORDAXE),
	POISONOUSARROW("PoisonousArrow", Rarity.UNCOMMON, ItemSets.BOW),
	ANIMALCOOKER("Animal Cooker", Rarity.COMMON, ItemSets.SWORD),
	FURNACE("Furnace", Rarity.COMMON, ItemSets.BOW),
	SCUBADIVER("Scuba Diver", Rarity.COMMON, ItemSets.HELM),
	GLOWING("Glowing", Rarity.COMMON, ItemSets.HELM),
	HEARTBOOST("Heart Boost", Rarity.HYDRO, ItemSets.HELM),
	VALOR("Valor", Rarity.HYDRO, ItemSets.ARMOR),
	FLASH("Flash", Rarity.HYDRO, ItemSets.ARMOR),
	SMELTING("Smelting", Rarity.COMMON, ItemSets.PICK),
	HARDENED("Hardened", Rarity.COMMON, ItemSets.PICK),
	FORTUNEV("&7Fortune V", "FortuneV", Rarity.MYSTICAL, ItemSets.PICK),
	//MAGNET("Magnet", Rarity.RARE, ItemSets.PICK),
	EXPLOSIVEPICKI("Explosive Pick I","ExplosivePickI", Rarity.HYDRO, ItemSets.PICK),
	EXPLOSIVEPICKII("Explosive Pick II","ExplosivePickII", Rarity.HYDRO,0, ItemSets.PICK),
	EXPLOSIVEPICKIII("Explosive Pick III","ExplosivePickIII", Rarity.HYDRO,0, ItemSets.PICK),
	
	//DETONATE("Detonate", "Chestplate", Rarity.MYSTICAL, ItemSets.CHEST),
	DEMONSWEAKNESSI("Demon's Weakness I", "DemonsWeaknessI", Rarity.HYDRO, ItemSets.SWORDAXE),
	DEMONSWEAKNESSII("Demon's Weakness II", "DemonsWeaknessII", Rarity.HYDRO, 0, ItemSets.SWORDAXE),
	PARALYZE("Paralyze", Rarity.COMMON, ItemSets.SWORD),
	WITHERI("Wither I", "WitherI", Rarity.HYDRO, ItemSets.SWORD),
	WITHERII("Wither II", "WitherII", Rarity.HYDRO, 0, ItemSets.SWORD),
	BATTLEROAR("BattleRoar", Rarity.UNCOMMON, ItemSets.CHEST),
	BURNSHEILD("Burn-Shield", Rarity.COMMON, ItemSets.ARMOR),
	MEDICINE("Medicine", Rarity.HYDRO, ItemSets.HELM),
	GUARDIAN("Guardian", Rarity.HYDRO, ItemSets.HELM),
	AUTOFEEDER("AutoFeeder", Rarity.MYSTICAL, ItemSets.LEGGINGS),
	VOLTAGE("Voltage", Rarity.UNCOMMON, ItemSets.BOW),
	FROZENARROW("FrozenArrow", Rarity.UNCOMMON, ItemSets.BOW),
	SHOTGUN("ShotgunBow", Rarity.HYDRO, ItemSets.BOW),
	EXPPROTECTOR("EXPProtector", Rarity.RARE, ItemSets.LEGGINGS),
	SELFHEALER("SelfHealer", Rarity.HYDRO, ItemSets.LEGGINGS),
	CHARGE("Charge", Rarity.HYDRO, ItemSets.SWORD),
	//DEMONSBLADE("Demon's Blade", "DemonsBlade", "Sword", Rarity.RARE, ItemSets.SWORD),
	GUARDIANSBLOCK("Guardian's Block", "GuardiansBlock", Rarity.MYSTICAL, ItemSets.SWORD),
	ARMORDESTRUCTOR("Armor Destructor", "ArmorDestructor", Rarity.RARE, ItemSets.AXE),
	RIFLE("RifleBow", Rarity.HYDRO, ItemSets.BOW),
	EXPLOSIVEARROW("ExplosiveArrow", Rarity.MYSTICAL, ItemSets.BOW),
	RPG("RPG", Rarity.MYSTICAL, ItemSets.BOW),
	REINFORCED("Reinforced", Rarity.HYDRO, ItemSets.CHEST),
	//LEADERSHIP("Leadership", "Boots", Rarity.HYDRO, ItemSets.BOOTS),
	MOONGRAVITY("MoonGravity", Rarity.RARE, ItemSets.BOOTS),
	WINDSSPEEDI("Wind's Speed I", "WindsSpeedI", Rarity.COMMON, ItemSets.BOOTS),
	WINDSSPEEDII("Wind's Speed II", "WindsSpeedII", Rarity.RARE, 0, ItemSets.BOOTS),
	SUPERSPEED("SuperSpeed", Rarity.HYDRO, ItemSets.BOOTS),
	STARVEDI("Starved I", "StarvedI", Rarity.RARE, ItemSets.CHEST),
	STARVEDII("Starved II", "StarvedII", Rarity.RARE, 0, ItemSets.CHEST),
	STARVEDIII("Starved III", "StarvedIII", Rarity.MYSTICAL, 0, ItemSets.CHEST),
	ANTIKNOCKBACKI("AntiKnockback I", "AntiKnockbackI", Rarity.UNCOMMON, ItemSets.LEGGINGS),
	ANTIKNOCKBACKII("AntiKnockback II", "AntiKnockbackII", Rarity.RARE, 0, ItemSets.LEGGINGS),
	ANTIKNOCKBACKIII("AntiKnockback III", "AntiKnockbackIII", Rarity.MYSTICAL, 0, ItemSets.LEGGINGS),
	LUMBERJACK("Lumberjack", Rarity.RARE, ItemSets.AXE),
	MINERI("Miner I", "MinerI", Rarity.MYSTICAL, ItemSets.PICK),
	MINERII("Miner II", "MinerII", Rarity.MYSTICAL, 0, ItemSets.PICK),
	
	XWY("God", "Xwy", Rarity.ADMIN, 0, ItemSets.BOOTS),;
	
	public CustomEnchant customEnchant;
	private boolean enabled = true;
	
	CEnchant(String inGameName, String commandLabel, Rarity rarity, ItemSets a) {
		customEnchant = new CustomEnchant(this, inGameName, commandLabel, rarity, a);
	}
	
	CEnchant(String inGameName, String commandLabel, Rarity rarity, int am, ItemSets a) {
		customEnchant = new CustomEnchant(this, inGameName, commandLabel, rarity, a, am);
	}
	
	CEnchant(String inGameName, Rarity rarity, ItemSets a) {
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
	
	public static boolean hasEnchant(ItemStack stack, CEnchant enchant) {
		return stack != null && stack.hasItemMeta() && stack.getItemMeta().hasLore() && stack.getItemMeta().getLore().contains(enchant.getName());
	}
	
}
