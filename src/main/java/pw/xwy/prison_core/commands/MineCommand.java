package pw.xwy.prison_core.commands;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.ExtraRank;
import pw.xwy.prison_core.utility.Mine;
import pw.xwy.prison_core.utility.MineManager;
import pw.xwy.prison_core.utility.Rank;

import java.util.HashSet;

public class MineCommand {
	
	public static void run(Player player, String[] args) {
		
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("setArea")) {
				if (args.length == 2) {
					Mine mine;
					String sRank = args[1].toUpperCase();
					if (MineManager.isNormalMine(sRank)) {
						mine = MineManager.mines.get(Rank.valueOf(sRank));
					} else if (MineManager.isDonorMine(sRank)) {
						mine = MineManager.extraMines.get(ExtraRank.valueOf(sRank));
					} else {
						return;
					}
					mine.setRectangle(MineManager.locationOneHashMap.getOrDefault(player.getUniqueId(), null), MineManager.locationTwoHashMap.getOrDefault(player.getUniqueId(), null));
					player.sendMessage("set area");
				}
			}
			if (args[0].equalsIgnoreCase("shop")) {
				if (args.length > 1) {
					if (args[1].equalsIgnoreCase("set")) {
						if (args.length == 4) {
							Mine mine;
							String sRank = args[2].toUpperCase();
							if (MineManager.isNormalMine(sRank)) {
								mine = MineManager.mines.get(Rank.valueOf(sRank));
							} else if (MineManager.isDonorMine(sRank)) {
								mine = MineManager.extraMines.get(ExtraRank.valueOf(sRank));
							} else {
								return;
							}
							ItemStack stack = player.getItemInHand();
							if (stack.getType() == Material.INK_SACK) {
								mine.addToShop(stack.getType(), Double.valueOf(args[3]), stack.getDurability());
							} else {
								mine.addToShop(stack.getType(), Double.valueOf(args[3]));
							}
							//send a message
						}
					} else if (args[1].equalsIgnoreCase("remove")) {
						if (args.length == 3) {
							Mine mine;
							String sRank = args[2].toUpperCase();
							if (MineManager.isNormalMine(sRank)) {
								mine = MineManager.mines.get(Rank.valueOf(sRank));
							} else if (MineManager.isDonorMine(sRank)) {
								mine = MineManager.extraMines.get(ExtraRank.valueOf(sRank));
							} else {
								return;
							}
							ItemStack stack = player.getItemInHand();
							if (stack.getType() == Material.INK_SACK) {
								mine.removeFromShop(stack.getType(), stack.getDurability());
							} else {
								mine.removeFromShop(stack.getType());
							}
							//send a message
						}
					} else if (args[1].equalsIgnoreCase("list")) {
					
					}
				}
			}
			if (args[0].equalsIgnoreCase("setBlock")) {
				if (args.length == 3) {
					if (player.getItemInHand() != null && player.getItemInHand().getType() != Material.AIR) {
						Mine mine;
						String sRank = args[1].toUpperCase();
						if (MineManager.isNormalMine(sRank)) {
							mine = MineManager.mines.get(Rank.valueOf(sRank));
						} else if (MineManager.isDonorMine(sRank)) {
							mine = MineManager.extraMines.get(ExtraRank.valueOf(sRank));
						} else {
							return;
						}
						if (mine.canSet(Integer.parseInt(args[2]), player.getItemInHand().getType(),player.getItemInHand().getDurability())) {
							mine.addMaterial(player.getItemInHand().getType(), player.getItemInHand().getDurability(), Integer.valueOf(args[2]));
							player.sendMessage("set, total : " + mine.total());
						} else {
							player.sendMessage("cant set " + mine.airCheck() + " space left");
						}
						
					}
				}
			}
			if (args[0].equalsIgnoreCase("wand")) {
				player.getInventory().addItem(MineManager.mineSelector);
			}
			if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("clean")) {
				if (args.length == 2) {
					Mine mine;
					String sRank = args[1].toUpperCase();
					if (MineManager.isNormalMine(sRank)) {
						mine = MineManager.mines.get(Rank.valueOf(sRank));
					} else if (MineManager.isDonorMine(sRank)) {
						mine = MineManager.extraMines.get(ExtraRank.valueOf(sRank));
					} else {
						return;
					}
					mine.clean();
					player.sendMessage("Mine cleaned");
				}
			}
			if (args[0].equalsIgnoreCase("setProgressSign")) {
				if (args.length == 2) {
					if (player.getTargetBlock((HashSet<Byte>) null, 5).getState() instanceof Sign) {
						Mine mine;
						String sRank = args[1].toUpperCase();
						if (MineManager.isNormalMine(sRank)) {
							mine = MineManager.mines.get(Rank.valueOf(sRank));
						} else if (MineManager.isDonorMine(sRank)) {
							mine = MineManager.extraMines.get(ExtraRank.valueOf(sRank));
						} else {
							return;
						}
						mine.setProgressSign(player.getTargetBlock((HashSet<Byte>) null, 5));
						player.sendMessage("Sign set");
					}
					
				}
			}
			if (args[0].equalsIgnoreCase("setWarp")) {
				if (args.length == 2) {
					Mine mine;
					String sRank = args[1].toUpperCase();
					if (MineManager.isNormalMine(sRank)) {
						mine = MineManager.mines.get(Rank.valueOf(sRank));
					} else if (MineManager.isDonorMine(sRank)) {
						mine = MineManager.extraMines.get(ExtraRank.valueOf(sRank));
					} else {
						return;
					}
					mine.setWarp(player.getLocation());
					player.sendMessage("Warp set");
				}
			}
		}
	}
}
