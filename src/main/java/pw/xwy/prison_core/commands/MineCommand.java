package pw.xwy.prison_core.commands;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.ERank;
import pw.xwy.prison_core.utility.Mine;
import pw.xwy.prison_core.utility.MineHandler;

public class MineCommand {
	
	public static void run(Player player, String[] args) {
		
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("setArea")) {
				if (args.length == 2) {
					if (ERank.valueOf(args[1]) != ERank.Free) {
						MineHandler.mines.get(ERank.valueOf(args[1])).setRectangle(MineHandler.locationOneHashMap.getOrDefault(player.getUniqueId(), null), MineHandler.locationTwoHashMap.getOrDefault(player.getUniqueId(), null));
						player.sendMessage("set area");
					}
				}
			}
			if (args[0].equalsIgnoreCase("setBlock")) {
				if (args.length == 3) {
					if (ERank.valueOf(args[1].toUpperCase()) != ERank.Free) {
						if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
							Mine mine = MineHandler.mines.get(ERank.valueOf(args[1].toUpperCase()));
							if (mine.canSet(Integer.parseInt(args[2]))) {
								mine.materials.put(player.getItemInHand().getType(), Integer.valueOf(args[2]));
								player.sendMessage("set, total : " + mine.total());
								
								
							} else {
								player.sendMessage("cant set " + mine.airCheck() + " space left");
							}
							
						}
					}
				}
			}
			if (args[0].equalsIgnoreCase("wand")) {
				player.getInventory().addItem(MineHandler.mineSelector);
			}
			if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("clean")) {
				if (args.length == 2) {
					if (ERank.valueOf(args[1].toUpperCase()) != ERank.Free) {
						Mine mine = MineHandler.mines.get(ERank.valueOf(args[1].toUpperCase()));
						mine.clean();
						player.sendMessage("Mine cleaned");
					}
				}
			}
			if (args[0].equalsIgnoreCase("npc")) {
				NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "Xwy");
				npc.spawn(player.getLocation());
				npc.setName("§6Sell All");
			}
		}
		
		
	}
	
}
