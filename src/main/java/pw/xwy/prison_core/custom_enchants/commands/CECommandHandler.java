////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.custom_enchants.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.custom_enchants.commands.admin.CeGive;
import pw.xwy.prison_core.custom_enchants.commands.player.CeMenu;
import pw.xwy.prison_core.custom_enchants.commands.player.Conv;
import pw.xwy.prison_core.custom_enchants.enums.ChangeLog;

public class CECommandHandler implements CommandExecutor {
	
	private CeMenu ceMenu;
	private CeGive ceGive;
	public CECommandHandler() {
		Bukkit.getServer().getPluginCommand("ce").setExecutor(this);
	}
	
	public void Init() {
		ceMenu = new CeMenu();
		ceGive = new CeGive();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (command.getLabel().equalsIgnoreCase("ce")) {
			if (args.length > 0) {
				if (argCheck(args[0])) {
					ceGive.run(sender, args);
				} else if (args[0].equalsIgnoreCase("version")) {
					sender.sendMessage(ChangeLog.getStrings().toArray(new String[0]));
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
