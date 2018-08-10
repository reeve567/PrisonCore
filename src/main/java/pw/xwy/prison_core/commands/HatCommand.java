package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {
	
	public HatCommand() {
		Bukkit.getPluginCommand("hat").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getLabel().equalsIgnoreCase("hat")) {
			if (!(commandSender instanceof Player)) {
				return false;
			}
			
			if (((Player) commandSender).getItemInHand() != null) {
				ItemStack helm = ((Player) commandSender).getInventory().getHelmet();
				ItemStack hand = ((Player) commandSender).getInventory().getItemInHand();
				
				((Player) commandSender).getInventory().setHelmet(hand);
				((Player) commandSender).setItemInHand(helm);
			}
			
		}
		return false;
	}
}
