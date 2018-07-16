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

public enum Messages {
	prefix("§6§lSystem " + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "»» "),
	senderIsConsole(prefix.get() + ChatColor.RED + "You must be a player to do this!"),
	itemBroke(prefix.get() + ChatColor.GRAY + "Whoops, looks like your item broke.  Better luck next time!"),
	itemSuccess(prefix.get() + ChatColor.GRAY + ChatColor.GRAY + "Binding the enchantment to this item was successful!"),
	itemUnsuccessful(prefix.get() + ChatColor.GRAY + "Binding the enchant to this item was unsuccessful, and the book was lost."),
	rektMessage(prefix.get() + ChatColor.GRAY + "Double damage!"),
	cursed(prefix.get() + ChatColor.GRAY + "Your opponent was cursed!"),
	launched(prefix.get() + ChatColor.GRAY + "weeeee"),
	frozen(prefix.get() + ChatColor.GRAY + "Your target was hit with a chill."),
	smited(prefix.get() + ChatColor.GRAY + "Your target was smitten"),
	fifsecCooldown(Messages.prefix.get() + ChatColor.GRAY + "You can only shoot this every 15 seconds!"),
	fullInventory(prefix.get() + ChatColor.RED + "Your inventory is full!"),
	noAmmo(Messages.prefix.get() + ChatColor.GRAY + "Not enough ammo."),
	poisoned(Messages.prefix.get() + ChatColor.GRAY + "Your target was poisoned."),
	noSoul(Messages.prefix.get() + ChatColor.GRAY + "This is not a key, bring me a key and I will grant you a reward."),
	mainPre(ChatColor.GRAY + "   " + ChatColor.BOLD + "»  ");
	
	private final String value;
	
	Messages(String s) {
		value = s;
	}
	
	public String get() {
		return value;
	}
}
