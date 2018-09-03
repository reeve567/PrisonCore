package pw.xwy.prison_core.listeners.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.enums.ExtraRank;
import pw.xwy.prison_core.utility.enums.NormalWarps;
import pw.xwy.prison_core.utility.enums.Rank;
import pw.xwy.prison_core.utility.item.CustomItem;
import pw.xwy.prison_core.utility.mine.MineManager;
import pw.xwy.prison_core.utility.player.PlayerManager;
import pw.xwy.prison_core.utility.player.XPlayer;

public class WarpGUI implements Listener {
	
	public static final int MAIN_MENU = 1;
	public static final int DONOR_WARPS = 2;
	public static final int MINE_WARPS = 3;
	public Inventory inventory;
	
	public WarpGUI(Player pl, int menu) {
		if (pl != null) {
			//ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 15);
			ItemStack backgroup = new CustomItem(Material.STAINED_GLASS_PANE).setDurability(15).setName(" ");
			if (menu == MAIN_MENU) {
				inventory = Bukkit.createInventory(null, 54, "§6Warps");
				//Background
				setBackground(inventory, backgroup);
				//Lores
				String warp = "§7Click here to teleport.";
				String category = "§7Click to see warps in this category.";
				//Items
				ItemStack spawn = new CustomItem(Material.BED).setName("§6Spawn").setLore(warp);
				ItemStack plots = new CustomItem(Material.GRASS).setName("§6Plots").setLore(warp);
				ItemStack furnaces = new CustomItem(Material.FURNACE).setName("§6Furnaces").setLore(warp);
				ItemStack crates = new CustomItem(Material.ENDER_CHEST).setName("§6Crates").setLore(warp);
				ItemStack resources = new CustomItem(Material.WOOD).setName("§6Resources").setLore(warp);
				ItemStack pvp = new CustomItem(Material.IRON_SWORD).setName("§6PvP").setLore(warp);
				ItemStack info = new CustomItem(Material.SIGN).setName("§6Info").setLore(warp);
				
				ItemStack mines = new CustomItem(Material.IRON_PICKAXE).setName("§6Mine Warps").setLore(category);
				ItemStack donorWarps = new CustomItem(Material.NETHER_STAR).setName("§6Donor Warps").setLore(category);
				
				//Setting Slots
				
				inventory.setItem(12, furnaces);
				inventory.setItem(14, plots);
				inventory.setItem(20, crates);
				inventory.setItem(22, spawn);
				inventory.setItem(24, pvp);
				inventory.setItem(29, resources);
				inventory.setItem(33, info);
				inventory.setItem(38, mines);
				inventory.setItem(42, donorWarps);
			} else if (menu == DONOR_WARPS) {
				XPlayer player = PlayerManager.getXPlayer(pl);
				
				inventory = Bukkit.createInventory(null, 27, "§6Donor Warps");
				//Background
				setBackground(inventory, backgroup);
				//Lores
				String no_permission = "§7You do not have permission to go here.";
				String not_in_group = "§7You are not in the required group to go here.";
				String warp = "§7Click here to teleport.";
				//Items
				ItemStack xpMine = new CustomItem(Material.EXP_BOTTLE).setName("§6EXP Mine").setLore(pl.hasPermission("xwy.mine.exp-mine") ? warp : no_permission);
				
				ItemStack mudkip = new CustomItem(Material.WOOL).setName("§6Mudkip Mine").setDurability(1).setLore(player.hasGroup("mudkip") ? warp : not_in_group);
				
				ItemStack shark = new CustomItem(Material.WOOL).setName("§6Shark Mine").setDurability(3).setLore(player.hasGroup("shark") ? warp : not_in_group);
				
				ItemStack whale = new CustomItem(Material.WOOL).setName("§6Whale Mine").setDurability(11).setLore(player.hasGroup("whale") ? warp : not_in_group);
				
				ItemStack jellyfish = new CustomItem(Material.WOOL).setName("§6Jellyfish Mine").setDurability(2).setLore(player.hasGroup("jellyfish") ? warp : not_in_group);
				//Setting Slots
				inventory.setItem(10, xpMine);
				
				inventory.setItem(13, mudkip);
				inventory.setItem(14, shark);
				inventory.setItem(15, whale);
				inventory.setItem(16, jellyfish);
			} else if (menu == MINE_WARPS) {
				XPlayer player = PlayerManager.getXPlayer(pl);
				
				inventory = Bukkit.createInventory(null, 54, "§6Mine Warps");
				//Background
				setBackground(inventory, backgroup);
				//Items
				ItemStack a = getMineStack(player, Rank.A, Material.STONE);
				ItemStack b = getMineStack(player, Rank.B, Material.COBBLESTONE);
				ItemStack c = getMineStack(player, Rank.C, Material.COAL_ORE);
				ItemStack d = getMineStack(player, Rank.D, Material.COAL);
				ItemStack e = getMineStack(player, Rank.E, Material.COAL_BLOCK);
				ItemStack f = getMineStack(player, Rank.F, Material.IRON_ORE);
				ItemStack g = getMineStack(player, Rank.G, Material.IRON_INGOT);
				ItemStack h = getMineStack(player, Rank.H, Material.IRON_BLOCK);
				ItemStack i = getMineStack(player, Rank.I, Material.GOLD_ORE);
				ItemStack j = getMineStack(player, Rank.J, Material.GOLD_INGOT);
				ItemStack k = getMineStack(player, Rank.K, Material.GOLD_BLOCK);
				ItemStack l = getMineStack(player, Rank.L, Material.LAPIS_ORE);
				ItemStack m = getMineStack(player, Rank.M, Material.INK_SACK, 4);
				ItemStack n = getMineStack(player, Rank.N, Material.LAPIS_BLOCK);
				ItemStack o = getMineStack(player, Rank.O, Material.MOSSY_COBBLESTONE);
				ItemStack p = getMineStack(player, Rank.P, Material.REDSTONE_ORE);
				ItemStack q = getMineStack(player, Rank.Q, Material.REDSTONE);
				ItemStack r = getMineStack(player, Rank.R, Material.REDSTONE_BLOCK);
				ItemStack s = getMineStack(player, Rank.S, Material.PRISMARINE);
				ItemStack t = getMineStack(player, Rank.T, Material.OBSIDIAN);
				ItemStack u = getMineStack(player, Rank.U, Material.DIAMOND_ORE);
				ItemStack v = getMineStack(player, Rank.V, Material.DIAMOND);
				ItemStack w = getMineStack(player, Rank.W, Material.DIAMOND_BLOCK);
				ItemStack x = getMineStack(player, Rank.X, Material.EMERALD_ORE);
				ItemStack y = getMineStack(player, Rank.Y, Material.EMERALD);
				ItemStack z = getMineStack(player, Rank.Z, Material.EMERALD_BLOCK);
				
				//Setting Slots
				
				inventory.setItem(10, a);
				inventory.setItem(11, b);
				inventory.setItem(12, c);
				inventory.setItem(13, d);
				inventory.setItem(14, e);
				inventory.setItem(15, f);
				inventory.setItem(16, g);
				
				inventory.setItem(19, h);
				inventory.setItem(20, i);
				inventory.setItem(21, j);
				inventory.setItem(22, k);
				inventory.setItem(23, l);
				inventory.setItem(24, m);
				inventory.setItem(25, n);
				
				inventory.setItem(28, o);
				inventory.setItem(29, p);
				inventory.setItem(30, q);
				inventory.setItem(31, r);
				inventory.setItem(32, s);
				inventory.setItem(33, t);
				inventory.setItem(34, u);
				
				inventory.setItem(38, v);
				inventory.setItem(39, w);
				inventory.setItem(40, x);
				inventory.setItem(41, y);
				inventory.setItem(42, z);
				
			}
			
		}
	}
	
	public static Inventory setBackground(Inventory inventory, ItemStack stack) {
		for (int i = 0; i < inventory.getSize(); i++)
			inventory.setItem(i, stack);
		return inventory;
	}
	
	private ItemStack getMineStack(XPlayer player, Rank rank, Material material) {
		return getMineStack(player, rank, material, 0);
	}
	
	private ItemStack getMineStack(XPlayer player, Rank rank, Material material, int durability) {
		String rank_up = "§7You need to rank up.";
		String warp = "§7Click here to teleport.";
		return new CustomItem(material).setName("§6" + rank.toString() + " Mine").setDurability(durability).setLore(player.getRank().ordinal() >= rank.ordinal() ? warp : rank_up);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§6Warps")) {
			e.setCancelled(true);
			int i = e.getRawSlot();
			if (i < 54) {
				NormalWarps warp = null;
				int j = 0;
				switch (i) {
					case 12:
						warp = NormalWarps.FURNACES;
						break;
					case 14:
						warp = NormalWarps.PLOTS;
						break;
					case 20:
						warp = NormalWarps.CRATES;
						break;
					case 22:
						warp = NormalWarps.SPAWN;
						break;
					case 24:
						warp = NormalWarps.PVP;
						break;
					case 29:
						warp = NormalWarps.RESOURCES;
						break;
					case 33:
						warp = NormalWarps.INFO;
						break;
					case 38:
						j = MINE_WARPS;
						break;
					case 42:
						j = DONOR_WARPS;
						break;
				}
				if (warp != null) {
					if (warp.getLocation() != null)
						e.getWhoClicked().teleport(warp.getLocation());
					else
						e.getWhoClicked().sendMessage("§cWarp not set!");
				} else if (j != 0) {
					e.getWhoClicked().openInventory(new WarpGUI((Player) e.getWhoClicked(), j).inventory);
				}
			}
		} else if (e.getInventory().getTitle().equalsIgnoreCase("§6Donor Warps")) {
			e.setCancelled(true);
			int i = e.getRawSlot();
			if (i < 27) {
				ExtraRank rank = null;
				XPlayer player = PlayerManager.getXPlayer((Player) e.getWhoClicked());
				switch (i) {
					case 10:
						if (e.getWhoClicked().hasPermission("xwy.mine.exp-mine"))
							rank = ExtraRank.EXP_MINE;
						break;
					case 13:
						if (player.hasGroup("mudkip"))
							rank = ExtraRank.MUDKIP;
						break;
					case 14:
						if (player.hasGroup("shark"))
							rank = ExtraRank.SHARK;
						break;
					case 15:
						if (player.hasGroup("whale"))
							rank = ExtraRank.WHALE;
						break;
					case 16:
						if (player.hasGroup("jellyfish"))
							rank = ExtraRank.JELLYFISH;
						break;
				}
				if (rank != null) {
					if (MineManager.extraMines.get(rank).getWarp() != null)
						e.getWhoClicked().teleport(MineManager.extraMines.get(rank).getWarp());
					else
						e.getWhoClicked().sendMessage("§cWarp not set!");
				}
			}
			
		} else if (e.getInventory().getTitle().equalsIgnoreCase("§6Mine Warps")) {
			e.setCancelled(true);
			int i = e.getRawSlot();
			if (i < 54) {
				Rank r = null;
				
				switch (i) {
					case 10:
						r = Rank.A;
						break;
					case 11:
						r = Rank.B;
						break;
					case 12:
						r = Rank.C;
						break;
					case 13:
						r = Rank.D;
						break;
					case 14:
						r = Rank.E;
						break;
					case 15:
						r = Rank.F;
						break;
					case 16:
						r = Rank.G;
						break;
					case 19:
						r = Rank.H;
						break;
					case 20:
						r = Rank.I;
						break;
					case 21:
						r = Rank.J;
						break;
					case 22:
						r = Rank.K;
						break;
					case 23:
						r = Rank.L;
						break;
					case 24:
						r = Rank.M;
						break;
					case 25:
						r = Rank.N;
						break;
					case 28:
						r = Rank.O;
						break;
					case 29:
						r = Rank.P;
						break;
					case 30:
						r = Rank.Q;
						break;
					case 31:
						r = Rank.R;
						break;
					case 32:
						r = Rank.S;
						break;
					case 33:
						r = Rank.T;
						break;
					case 34:
						r = Rank.U;
						break;
					case 38:
						r = Rank.V;
						break;
					case 39:
						r = Rank.W;
						break;
					case 40:
						r = Rank.X;
						break;
					case 41:
						r = Rank.Y;
						break;
					case 42:
						r = Rank.Z;
						break;
				}
				if (r != null) {
					XPlayer player = PlayerManager.getXPlayer((Player) e.getWhoClicked());
					if (player.getRank().ordinal() >= r.ordinal()) {
						if (MineManager.mines.get(r).getWarp() != null)
							e.getWhoClicked().teleport(MineManager.mines.get(r).getWarp());
					}
				}
				
			}
			
		}
	}
	
}
