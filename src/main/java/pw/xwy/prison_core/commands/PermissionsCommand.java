package pw.xwy.prison_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.PlayerManager;
import pw.xwy.prison_core.utility.XPlayer;
import pw.xwy.prison_core.utility.XPlayerConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissionsCommand implements CommandExecutor, TabCompleter {
	public PermissionsCommand() {
		Bukkit.getPluginCommand("permissions").setExecutor(this);
		Bukkit.getPluginCommand("permissions").setTabCompleter(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("invalid use -- too lazy to add help message currently");
		} else {
			if (args[0].equalsIgnoreCase("groupadd") || args[0].equalsIgnoreCase("gadd")) {
				Player p = Bukkit.getPlayer(args[1]);
				if (p != null) {
					XPlayer player = PlayerManager.getXPlayer(Bukkit.getPlayer(args[1]));
					if (!player.hasGroup(args[2])) {
						player.addGroup(args[2]);
						return true;
					} else {
						sender.sendMessage("Player already in group");
					}
				} else {
					sender.sendMessage("Player not found");
				}
			} else if (args[0].equalsIgnoreCase("groupremove") || args[0].equalsIgnoreCase("gremove")) {
				Player p = Bukkit.getPlayer(args[1]);
				if (p != null) {
					XPlayer player = PlayerManager.getXPlayer(Bukkit.getPlayer(args[1]));
					if (player.hasGroup(args[2])) {
						player.removeGroup(args[2]);
						return true;
					} else {
						sender.sendMessage("Player not in group");
					}
				} else {
					sender.sendMessage("Player not found");
				}
			} else if (args[0].equalsIgnoreCase("permissionremove") || args[0].equalsIgnoreCase("premove")) {
				Player p = Bukkit.getPlayer(args[1]);
				if (p != null) {
					XPlayer player = PlayerManager.getXPlayer(Bukkit.getPlayer(args[1]));
					if (p.hasPermission(args[2])) {
						player.removePermission(args[2]);
						return true;
					} else {
						sender.sendMessage("Player does not have that permission");
					}
				} else {
					sender.sendMessage("Player not found");
				}
			} else if (args[0].equalsIgnoreCase("permissionadd") || args[0].equalsIgnoreCase("padd")) {
				Player p = Bukkit.getPlayer(args[1]);
				if (p != null) {
					XPlayer player = PlayerManager.getXPlayer(Bukkit.getPlayer(args[1]));
					if (!p.hasPermission(args[2])) {
						player.addPermission(args[2]);
						return true;
					} else {
						sender.sendMessage("Player already has that permission");
					}
				} else {
					sender.sendMessage("Player not found");
				}
			} else if (args[0].equalsIgnoreCase("check")) {
				Player p = Bukkit.getPlayer(args[1]);
				if (p != null) {
					sender.sendMessage(String.valueOf(p.hasPermission(args[2])));
					return true;
				} else {
					sender.sendMessage("Player not found");
				}
				
			}
			
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
		if (args.length == 1) {
			return new ArrayList<>(Arrays.asList("groupadd", "groupremove", "permissionadd", "permissionremove", "gadd", "gremove", "padd", "premove", "check"));
		} else if (args.length == 2) {
			ArrayList<String> players = new ArrayList<>();
			for (Player p : Bukkit.getOnlinePlayers()) {
				players.add(p.getName());
			}
			return players;
		} else if (args.length == 3 && args[0].startsWith("g")) {
			ArrayList<String> groups = new ArrayList<>(Arrays.asList(XPlayerConfig.groupsList));
			groups.addAll(Arrays.asList(XPlayerConfig.extraGroups));
			return groups;
		}
		return null;
	}
}
