////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.listeners;
// made by reeve
// on 8:18 PM

import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import pw.xwy.prison_core.custom_enchants.soulcrates.*;
import pw.xwy.prison_core.custom_enchants.utilities.CratesUtility;

public class GorixClick implements Listener {
	
	static int r;
	static int j;
	private JavaPlugin main;
	
	GorixClick(JavaPlugin main) {
		this.main = main;
	}
	
	/*@EventHandler
	public void onClick(NPCRightClickEvent e) {
		
		if (e.getNPC().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&4&lRubix"))) {
			if (e.getClicker().getItemInHand().hasItemMeta() && e.getClicker().getItemInHand().getItemMeta().hasDisplayName()) {
				for (Souls s : Souls.values()) {
					if (e.getClicker().getItemInHand().getItemMeta().getDisplayName().equals(s.getItem().getItemMeta().getDisplayName())) {
						
						CrateOpen crateOpen = new CrateOpen(main);
						
						if (s.getName().equals(Souls.COMMON.getName())) {
							
							if (e.getClicker().getInventory().firstEmpty() == -1) {
								e.getClicker().sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Your inventory is full!");
								return;
							}
							
							int r = EnchantDrop.getRandomNumberFrom(0, CommonSC.get().getPrizes().size() - 1);
							
							if (e.getClicker().getItemInHand().getAmount() > 1) {
								e.getClicker().getItemInHand().setAmount(e.getClicker().getItemInHand().getAmount() - 1);
							} else {
								e.getClicker().setItemInHand(null);
							}
							
							Inventory inv = openCommon();
							e.getClicker().openInventory(inv);
							crateOpen.openCrate(e.getClicker(), inv, Rarity.COMMON, r + j);
							
						} else if (s.getName().equals(Souls.UNCOMMON.getName())) {
							if (e.getClicker().getInventory().firstEmpty() == -1) {
								e.getClicker().sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Your inventory is full!");
								return;
							}
							
							int r = EnchantDrop.getRandomNumberFrom(0, UncommonSC.get().getPrizes().size() - 1);
							
							if (e.getClicker().getItemInHand().getAmount() > 1) {
								e.getClicker().getItemInHand().setAmount(e.getClicker().getItemInHand().getAmount() - 1);
							} else {
								e.getClicker().setItemInHand(null);
							}
							
							Inventory inv = openUncommon();
							e.getClicker().openInventory(inv);
							crateOpen.openCrate(e.getClicker(), inv, Rarity.UNCOMMON, r + j);
							
						} else if (s.getName().equals(Souls.RARE.getName())) {
							if (e.getClicker().getInventory().firstEmpty() == -1) {
								e.getClicker().sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Your inventory is full!");
								return;
							}
							
							int r = EnchantDrop.getRandomNumberFrom(0, RareSC.get().getPrizes().size() - 1);
							
							if (e.getClicker().getItemInHand().getAmount() > 1) {
								e.getClicker().getItemInHand().setAmount(e.getClicker().getItemInHand().getAmount() - 1);
							} else {
								e.getClicker().setItemInHand(null);
							}
							
							Inventory inv = openRare();
							e.getClicker().openInventory(inv);
							crateOpen.openCrate(e.getClicker(), inv, Rarity.RARE, r + j);
							
						} else if (s.getName().equals(Souls.MYSTICAL.getName())) {
							if (e.getClicker().getInventory().firstEmpty() == -1) {
								e.getClicker().sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Your inventory is full!");
								return;
							}
							
							int r = EnchantDrop.getRandomNumberFrom(0, MysticalSC.get().getPrizes().size() - 1);
							
							if (e.getClicker().getItemInHand().getAmount() > 1) {
								e.getClicker().getItemInHand().setAmount(e.getClicker().getItemInHand().getAmount() - 1);
							} else {
								e.getClicker().setItemInHand(null);
							}
							
							Inventory inv = openMystical();
							e.getClicker().openInventory(inv);
							crateOpen.openCrate(e.getClicker(), inv, Rarity.MYSTICAL, r + j);
							
						} else if (s.getName().equals(Souls.HYDRO.getName())) {
							if (e.getClicker().getInventory().firstEmpty() == -1) {
								e.getClicker().sendMessage(Messages.prefix.get() + ChatColor.GRAY + "Your inventory is full!");
								return;
							}
							
							int r = EnchantDrop.getRandomNumberFrom(0, HydroSC.get().getPrizes().size() - 1);
							
							if (e.getClicker().getItemInHand().getAmount() > 1) {
								e.getClicker().getItemInHand().setAmount(e.getClicker().getItemInHand().getAmount() - 1);
							} else {
								e.getClicker().setItemInHand(null);
							}
							
							Inventory inv = openHydro();
							e.getClicker().openInventory(inv);
							crateOpen.openCrate(e.getClicker(), inv, Rarity.HYDRO, r + j);
							
						}
						return;
						
					}
				}
				e.getClicker().sendMessage(Messages.noSoul.get());
			} else {
				e.getClicker().sendMessage(Messages.noSoul.get());
			}
		}
	}*/
	
	static Inventory openCommon() {
		Inventory inv = CommonSC.get().getBaseInventory();
		r = EnchantDrop.getRandomNumberFrom(0, CommonSC.get().getPrizes().size() - 1);
		j = 0;
		
		int i = 9;
		
		while (i < 18) {
			if (r + j < CommonSC.get().getPrizes().size() + 1) {
				CratesUtility.putPrize(inv, i, r + j, CommonSC.get().getPrizes());
				j++;
			} else {
				j = 0;
				r = 1;
				CratesUtility.putPrize(inv, i, 1, CommonSC.get().getPrizes());
			}
			i++;
		}
		
		return inv;
	}
	
	static Inventory openUncommon() {
		Inventory inv = UncommonSC.get().getBaseInventory();
		r = EnchantDrop.getRandomNumberFrom(0, UncommonSC.get().getPrizes().size() - 1);
		j = 0;
		
		int i = 9;
		
		while (i < 18) {
			if (r + j < UncommonSC.get().getPrizes().size() + 1) {
				CratesUtility.putPrize(inv, i, r + j, UncommonSC.get().getPrizes());
				j++;
			} else {
				j = 0;
				r = 1;
				CratesUtility.putPrize(inv, i, 1, UncommonSC.get().getPrizes());
			}
			i++;
		}
		
		return inv;
	}
	
	static Inventory openRare() {
		Inventory inv = RareSC.get().getBaseInventory();
		r = EnchantDrop.getRandomNumberFrom(0, RareSC.get().getPrizes().size() - 1);
		j = 0;
		
		int i = 9;
		
		while (i < 18) {
			if (r + j < RareSC.get().getPrizes().size() + 1) {
				CratesUtility.putPrize(inv, i, r + j, RareSC.get().getPrizes());
				j++;
			} else {
				j = 0;
				r = 1;
				CratesUtility.putPrize(inv, i, 1, RareSC.get().getPrizes());
			}
			i++;
		}
		
		return inv;
	}
	
	static Inventory openMystical() {
		Inventory inv = MysticalSC.get().getBaseInventory();
		r = EnchantDrop.getRandomNumberFrom(0, MysticalSC.get().getPrizes().size() - 1);
		j = 0;
		
		int i = 9;
		
		while (i < 18) {
			if (r + j < MysticalSC.get().getPrizes().size() + 1) {
				CratesUtility.putPrize(inv, i, r + j, MysticalSC.get().getPrizes());
				j++;
			} else {
				j = 0;
				r = 1;
				CratesUtility.putPrize(inv, i, 1, MysticalSC.get().getPrizes());
			}
			i++;
		}
		
		return inv;
	}
	
	static Inventory openHydro() {
		Inventory inv = HydroSC.get().getBaseInventory();
		r = EnchantDrop.getRandomNumberFrom(0, HydroSC.get().getPrizes().size() - 1);
		j = 0;
		
		int i = 9;
		
		while (i < 18) {
			if (r + j < HydroSC.get().getPrizes().size() + 1) {
				CratesUtility.putPrize(inv, i, r + j, HydroSC.get().getPrizes());
				j++;
			} else {
				j = 0;
				r = 1;
				CratesUtility.putPrize(inv, i, 1, HydroSC.get().getPrizes());
			}
			i++;
		}
		
		return inv;
	}
}
