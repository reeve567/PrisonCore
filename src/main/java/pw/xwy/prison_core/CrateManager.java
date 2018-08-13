package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.ConfigurationHandler;
import pw.xwy.prison_core.utility.CustomItem;
import pw.xwy.prison_core.utility.ReadableNumbers;
import pw.xwy.prison_core.utility.Tag;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CrateManager {
	
	private static Location location = null;
	
	public CrateManager(String location) {
		if (!location.equalsIgnoreCase("unset")) {
			String world = location.substring(0, location.indexOf(';'));
			location = location.replaceFirst(world + ";", "");
			int x = Integer.parseInt(location.substring(0, location.indexOf(';')));
			location = location.replaceFirst(x + ";", "");
			int y = Integer.parseInt(location.substring(0, location.indexOf(';')));
			location = location.replaceFirst(y + ";", "");
			int z = Integer.parseInt(location);
			
			CrateManager.location = new Location(Bukkit.getWorld(world), x, y, z);
		}
	}
	
	public static boolean isCrate(Block block) {
		if (location != null) {
			return block.getLocation().equals(location);
		}
		return false;
	}
	
	public static boolean setCrate(Player player) {
		if (player.getTargetBlock(null, 5).getType() == Material.ENDER_CHEST) {
			location = player.getTargetBlock(null, 5).getLocation();
			player.sendMessage("set crate");
			return true;
		}
		return false;
	}
	
	public static String crateString() {
		if (location == null) return "unset";
		return location.getWorld().getName() + ";" + location.getBlockX() + ";" + location.getBlockY() + ";" + location.getBlockZ();
	}
	
	public static ItemStack getKey(CrateType type) {
		return new CustomItem(Material.TRIPWIRE_HOOK).setName(type.name + " §fKey");
	}
	
	public enum CrateType {
		VOTE("§aVote"), RARE("§dRare"), ELITE("§4Elite"), TAG("§eTag");
		
		private String name;
		
		CrateType(String s) {
			name = s;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public enum VotePrizes {
		MONEY_10000(60, new MoneyPrize(10000, true)),
		MONEY_100000(20, new MoneyPrize(100000, true)),
		MONEY_500000(10, new MoneyPrize(500000, true)),
		GOD_APPLES(3, new ItemPrize("§65 Golden Apples", new CustomItem(Material.GOLDEN_APPLE).setDurability(1).setCustomAmount(5), 20000)),
		TAG_KEY(5, new ItemPrize("§eTag §fKey", getKey(CrateType.TAG), 10000)),
		GUPPY_RANK(1, new RankPrize("§3Guppy §6Rank", "guppy", 100000)),
		GOD_TOOLS_KIT(1, new PermissionPrize("§bGod Tools §6Kit", "kits.god-tools", 100000));
		private int chance;
		private Prize<?> prize;
		
		VotePrizes(int chance, Prize<?> prize) {
			this.chance = chance;
			this.prize = prize;
		}
		
		public int getChance() {
			return chance;
		}
		
		
		public Prize<?> getPrize() {
			return prize;
		}
	}
	
	public enum RarePrizes {
		PLOT(25, new PlotPrize("§2Plot", 10, 1_000_000)),
		GUPPY_RANK(10, new RankPrize("§3Guppy §6Rank", "guppy", 1_250_000)),
		EXP_MINE(5, new PermissionPrize("§aEXP §6Mine", "mines.exp", 2_000_000)),
		GOD_TOOLS_KIT(15, new PermissionPrize("§bGod Tools §6Kit", "kits.god-tools", 500_000)),
		GOD_AXE_KIT(6, new PermissionPrize("§bGod Axe §6Kit", "kits.god-axe", 2_000_000)),
		PVP_KIT(7, new PermissionPrize("§bPvP §6Kit", "kits.pvp", 2_000_000)),
		MUDKIP_RANK(7, new RankPrize("§6Mu§bKip §6Rank", "mudkip", 2_000_000)),
		MONEY_1000000(15, new MoneyPrize(1000000, true)),
		MONEY_10000000(10, new MoneyPrize(10000000, true));
		private int chance;
		private Prize<?> prize;
		
		RarePrizes(int chance, Prize<?> prize) {
			this.chance = chance;
			this.prize = prize;
		}
		
		public int getChance() {
			return chance;
		}
		
		
		public Prize<?> getPrize() {
			return prize;
		}
	}
	
	public enum ElitePrizes {
		HIGHROLLER(20, new TagPrize("§6§lHigh-Roller §6Tag", Tag.HIGHROLLER, 80_000_000)),
		WHALE(20, new RankPrize("§9Whale §6Rank", "whale", 100_000_000)),
		EXP_MINE(15, new PermissionPrize("§aEXP §6Mine", "mines.exp", 50_000_000)),
		SHARK(18, new RankPrize("§1Shark §6Rank", "shark", 75_000_000)),
		MONEY_100000000(11, new MoneyPrize(100_000_000, true)),
		JELLYFISH(9, new RankPrize("§d§lJellyfish §6Rank", "jellyfish", 150_000_000)),
		EMERALD_MINE(7, new PermissionPrize("§a§lEmerald Mine", "mines.emerald", 160_000_000));
		private int chance;
		private Prize<?> prize;
		
		ElitePrizes(int chance, Prize<?> prize) {
			this.chance = chance;
			this.prize = prize;
		}
		
		public int getChance() {
			return chance;
		}
		
		public Prize<?> getPrize() {
			return prize;
		}
	}
	
	public enum TagPrizes {
		GAMBLER(new TagPrize("§aGambler", Tag.GAMBLER, 20_000)),
		HACKER(new TagPrize("§4Hacker", Tag.HACKER, 20_000)),
		MUDKIP(new TagPrize("§6Mud§bKip", Tag.MUDKIP, 20_000)),
		NERD(new TagPrize("§3N3rd", Tag.NERD, 20_000)),
		PVP(new TagPrize("§4PvP", Tag.PVP, 20_000)),
		MUDKIPPER(new TagPrize("§b§lMudkipper", Tag.MUDKIPPER, 20_000)),
		SALTY(new TagPrize("§fSalty", Tag.SALTY, 20_000)),
		NEWB(new TagPrize("§eNewb", Tag.NEWB, 20_000)),
		MLG(new TagPrize("§7#§9§lM§f§lL§c§lG", Tag.MLG, 20_000)),
		DED(new TagPrize("§7§oded", Tag.DED, 20_000));
		private Prize<Tag> prize;
		
		TagPrizes(Prize<Tag> prize) {
			this.prize = prize;
		}
		
		public Prize<Tag> getPrize() {
			return prize;
		}
	}
	
	public abstract static class Prize<T> {
		private T value;
		private int backupReward = -1;
		private String name;
		
		public Prize(String name, T value) {
			this.name = name;
			this.value = value;
		}
		
		public Prize(String name, T value, int backupReward) {
			this.name = name;
			this.value = value;
			this.backupReward = backupReward;
		}
		
		public T getValue() {
			return value;
		}
		
		public int getBackupReward() {
			return backupReward;
		}
		
		public abstract void givePrize(Player player);
		
		public String getName() {
			return name;
		}
		
		public String getMessage() {
			return "§7You have won " + name + "§7!";
		}
	}
	
	static class MoneyPrize extends Prize<Integer> {
		boolean original;
		
		MoneyPrize(Integer value, boolean original) {
			super("§2$§7" + ReadableNumbers.coolFormat(value, 0), value);
			this.original = original;
		}
		
		@Override
		public void givePrize(Player player) {
			if (original) {
				player.sendMessage(getMessage());
			} else {
				player.sendMessage(getBackupMessage());
			}
			ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData().addBalance(getValue());
		}
		
		public String getBackupMessage() {
			return "§7You have won " + getName() + "§7 since you already had that.";
		}
	}
	
	static class ItemPrize extends Prize<ItemStack> {
		
		public ItemPrize(String name, ItemStack customItem, int i) {
			super(name, customItem, i);
		}
		
		@Override
		public void givePrize(Player player) {
			player.sendMessage(getMessage());
			if (player.getInventory().firstEmpty() == -1) {
				MoneyPrize prize = new MoneyPrize(getBackupReward(), false);
				prize.givePrize(player);
			} else {
				player.getInventory().addItem(getValue());
				player.updateInventory();
			}
		}
	}
	
	static class PermissionPrize extends Prize<String> {
		public PermissionPrize(String name, String s, int i) {
			super(name, s, i);
		}
		
		@Override
		public void givePrize(Player player) {
			player.sendMessage(getMessage());
			if (player.hasPermission(getValue())) {
				MoneyPrize prize = new MoneyPrize(getBackupReward(), false);
				prize.givePrize(player);
			} else {
				PermissionsEx.getUser(player).addTimedPermission(getValue(), null, Integer.MAX_VALUE);
			}
		}
	}
	
	static class RankPrize extends Prize<String> {
		public RankPrize(String name, String value, int i) {
			super(name, value, i);
		}
		
		@Override
		public void givePrize(Player player) {
			player.sendMessage(getMessage());
			if (PermissionsEx.getUser(player).inGroup(getValue())) {
				MoneyPrize prize = new MoneyPrize(getBackupReward(), false);
				prize.givePrize(player);
			} else {
				PermissionsEx.getUser(player).addGroup(getValue(), null, Long.MAX_VALUE);
			}
		}
	}
	
	static class TagPrize extends Prize<Tag> {
		public TagPrize(String name, Tag value, int backupReward) {
			super(name, value, backupReward);
		}
		
		@Override
		public void givePrize(Player player) {
			player.sendMessage(getMessage());
			if (ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData().getTags().contains(getValue())) {
				MoneyPrize prize = new MoneyPrize(getBackupReward(), false);
				prize.givePrize(player);
			} else {
				ConfigurationHandler.playerConfigs.get(player.getUniqueId()).getData().getTags().add(getValue());
			}
		}
	}
	
	static class PlotPrize extends Prize<Integer> {
		public PlotPrize(String name, Integer value, int max) {
			super(name, value, max);
		}
		
		@Override
		public void givePrize(Player player) {
			player.sendMessage(getMessage());
			boolean found = false;
			for (int i = 1; i <= getValue(); i++)
				if (!player.hasPermission("plots.plot." + i)) {
					PermissionsEx.getUser(player).addTimedPermission("plots.plot." + i, null, Integer.MAX_VALUE);
					PermissionsEx.getUser(player).save();
					found = true;
					break;
				}
			if (!found) {
				MoneyPrize prize = new MoneyPrize(getBackupReward(), false);
				prize.givePrize(player);
			}
		}
	}
}
