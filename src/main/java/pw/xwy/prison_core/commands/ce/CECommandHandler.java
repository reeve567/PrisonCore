////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.commands.ce;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.commands.ce.admin.CeGive;
import pw.xwy.prison_core.commands.ce.admin.CeOrganize;
import pw.xwy.prison_core.commands.ce.player.CeMenu;
import pw.xwy.prison_core.commands.ce.player.Conv;
import pw.xwy.prison_core.utility.enums.ChangeLog;

public class CECommandHandler implements CommandExecutor {
	
	private CeMenu ceMenu;
	private CeGive ceGive;
	private CeOrganize ceOrganize;
	
	public CECommandHandler() {
		Bukkit.getServer().getPluginCommand("ce").setExecutor(this);
		Bukkit.getServer().getPluginCommand("conv").setExecutor(this);
		Init();
	}
	
	public void Init() {
		ceMenu = new CeMenu();
		ceGive = new CeGive();
		ceOrganize = new CeOrganize();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (command.getLabel().equalsIgnoreCase("ce")) {
			if (args.length > 0) {
				if (argCheck(args[0])) {
					ceGive.run(sender, args);
				} else if (args[0].equalsIgnoreCase("version")) {
					sender.sendMessage(ChangeLog.getStrings().toArray(new String[0]));
				} else if (args[0].equalsIgnoreCase("organize")) {
					ceOrganize.run(sender);
				} else if (args[0].equalsIgnoreCase("enchant")) {
					if (sender.hasPermission("xwy.ce.admin")) {
						if (sender instanceof Player) {
							ItemStack stack = ((Player) sender).getItemInHand();
							stack.addUnsafeEnchantment(Enchantment.getByName(args[1]), Integer.parseInt(args[2]));
							((Player) sender).setItemInHand(stack);
							((Player) sender).updateInventory();
						}
						
					}
					
					
				}
			} else ceMenu.run(sender);
		} else if (command.getLabel().equalsIgnoreCase("conv")) {
			Conv.run((Player) sender);
		}
		return true;
	}
	
	private boolean argCheck(String sent) {
		return "give".equalsIgnoreCase(sent);
	}
}
