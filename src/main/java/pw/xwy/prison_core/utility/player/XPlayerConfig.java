package pw.xwy.prison_core.utility.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.ReadableNumbers;
import pw.xwy.prison_core.utility.config.Config;
import pw.xwy.prison_core.utility.enums.Permissions;
import pw.xwy.prison_core.utility.enums.Rank;
import pw.xwy.prison_core.utility.enums.Tag;
import pw.xwy.prison_core.utility.misc_managers.RanksManager;

import java.io.File;
import java.time.Instant;
import java.util.*;

public class XPlayerConfig extends Config {
	public static final String[] groupsList = {"guppy", "mudkip", "shark", "whale", "jellyfish"};
	private static final double[] multiplier = {0.1, 0.2, 0.3, 0.4, 0.6};
	public static final int[] pvs = {0, 0, 2, 10, 20};
	public static final boolean[] colorChat = {false, false, true, true, true};
	public static final String[] extraGroups = {"helper", "moderator", "admin", "owner", "developer"};
	private static final ArrayList<String> defaults = new ArrayList<>(Arrays.asList("plots.use", "plots.trust", "plots.info", "plots.claim"
			, "plots.auto", "plots.home", "plots.clear", "plots.delete", "plots.list", "plots.list.mine"
			, "plots.list.shared", "plots.set", "plots.visit", "plots.visit.owned", "plots.visit.shared", "plots.visit.other"
			, "plots.set.flag", "plots.flag.add", "plots.flag.remove", "plots.flag.list", "plots.flag.info"
			, "plots.flag", "plots.confirm", "plots.toggle", "plots.toggle.titles", "plots.set.biome", "plots.set.home"
			, "plots.denied", "plots.remove", "plots.untrust", "plots.undeny", "plots.kick", "plots.done", "plots.save"
			, "plots.continue"));
	private static final ArrayList<String> guppyPerms = new ArrayList<>(Arrays.asList(Permissions.FEED_COMMAND.toString(), Permissions.ENDERCHEST_COMMAND.toString(), Permissions.HAT_COMMAND.toString(), Permissions.WORKBENCH_COMMAND.toString(), "kits.guppy"));
	private static final ArrayList<String> mudkipPerms = new ArrayList<>(Arrays.asList(Permissions.FEED_COMMAND.toString(), Permissions.ENDERCHEST_COMMAND.toString(), Permissions.HAT_COMMAND.toString(), Permissions.WORKBENCH_COMMAND.toString(), "kits.mudkip", "xwy.mine.mudkip"));
	private static final ArrayList<String> sharkPerms = new ArrayList<>(Arrays.asList(Permissions.FEED_COMMAND.toString(), Permissions.ENDERCHEST_COMMAND.toString(), Permissions.HAT_COMMAND.toString(), Permissions.WORKBENCH_COMMAND.toString(), "kits.shark", "xwy.mine.shark", "xwy.perks.commands.sellall"));
	private static final ArrayList<String> whalePerms = new ArrayList<>(Arrays.asList(Permissions.FEED_COMMAND.toString(), Permissions.ENDERCHEST_COMMAND.toString(), Permissions.HAT_COMMAND.toString(), Permissions.WORKBENCH_COMMAND.toString(), "kits.whale", "xwy.mine.whale", "xwy.perks.commands.sellall"));
	private static final ArrayList<String> jellyfishPerms = new ArrayList<>(Arrays.asList(Permissions.FEED_COMMAND.toString(), Permissions.ENDERCHEST_COMMAND.toString(), Permissions.HAT_COMMAND.toString(), Permissions.WORKBENCH_COMMAND.toString(), "kits.jellyfish", "xwy.perks.commands.fly", "xwy.mine.jellyfish", "xwy.perks.commands.autosell", "xwy.perks.commands.sellall"));
	public static double startingMoney;
	public static String moneySymbol;
	private final int ver = 1;
	private boolean youtuber;
	private boolean twitcher;
	private HashSet<String> customPermissions = new HashSet<>();
	private boolean firstJoin = false;
	private boolean chatSpy;
	private HashMap<String, Date> lastUsed = new HashMap<>();
	private HashSet<String> groups = new HashSet<>();
	private PermissionAttachment attachmentInfo = null;
	private double balance;
	private Rank rank;
	private int prestige;
	private Tag activeTag = null;
	private boolean tagToggle = true;
	
	public XPlayerConfig(UUID name) {
		super(new File(PrisonCore.getInstance().getDataFolder(), "Players"), name.toString());
		Player player = Bukkit.getPlayer(name);
		if (player != null) {
			attachmentInfo = player.addAttachment(PrisonCore.getInstance());
		}
		setDefaults();
		balance = getDouble("general-data.balance");
		rank = Rank.valueOf(getString("prison-data.rank"));
		prestige = getInt("prison-data.prestige");
		
		if (!getString("prison-data.active-tag").equalsIgnoreCase("unset"))
			setActiveTag(Tag.valueOf(getString("prison-data.active-tag")));
		setTagToggle(getBoolean("prison-data.tag-toggle"));
		lastUsed.put("god-tools", new Date(getLong("cool-downs.god-tools")));
		lastUsed.put("god-axe", new Date(getLong("cool-downs.god-axe")));
		lastUsed.put("pvp", new Date(getLong("cool-downs.pvp")));
		lastUsed.put("guppy", new Date(getLong("cool-downs.guppy")));
		lastUsed.put("mudkip", new Date(getLong("cool-downs.mudkip")));
		lastUsed.put("shark", new Date(getLong("cool-downs.shark")));
		lastUsed.put("whale", new Date(getLong("cool-downs.whale")));
		lastUsed.put("jellyfish", new Date(getLong("cool-downs.jellyfish")));
		youtuber = getBoolean("youtuber");
		twitcher = getBoolean("twitcher");
		chatSpy = getBoolean("general-data.chat-spy");
		
		if (attachmentInfo != null) {
			ArrayList<String> permissions = (ArrayList<String>) getStringList("permissions");
			for (String s : permissions) {
				addPermission(s);
			}
			for (String s : defaults) {
				attachmentInfo.setPermission(s, true);
			}
			ArrayList<String> groups = (ArrayList<String>) getStringList("groups");
			for (String s : groups) {
				addRank(s);
			}
		}
	}
	
	private void setDefaults() {
		if (getVersion() != ver) {
			//unique player
			firstJoin = true;
			setVersion();
			set("prison-data.rank", Rank.A.toString());
			set("prison-data.prestige", 0);
			set("prison-data.active-tag", "unset");
			set("prison-data.tag-toggle", true);
			set("general-data.balance", startingMoney);
			set("general-data.chat-spy", false);
			set("cool-downs.god-tools", 0L);
			set("cool-downs.god-axe", 0L);
			set("cool-downs.pvp", 0L);
			set("cool-downs.guppy", 0L);
			set("cool-downs.mudkip", 0L);
			set("cool-downs.shark", 0L);
			set("cool-downs.whale", 0L);
			set("cool-downs.jellyfish", 0L);
			set("permissions", new ArrayList<>());
			set("groups", new ArrayList<>());
			set("youtuber", false);
			set("twitcher", false);
			saveConfig();
		}
	}
	
	public void addPermission(String s) {
		attachmentInfo.setPermission(s, true);
		customPermissions.add(s);
	}
	
	public void addRank(String rank) {
		rank = rank.toLowerCase();
		groups.add(rank);
		ArrayList<String> perms = getPermissionsList(rank);
		if (perms != null) {
			for (String s : perms) {
				attachmentInfo.setPermission(s, true);
			}
		}
	}
	
	private int getVersion() {
		return getInt("ver");
	}
	
	private void setVersion() {
		set("ver", ver);
	}
	
	private ArrayList<String> getPermissionsList(String s) {
		switch (s) {
			case "guppy":
				return guppyPerms;
			case "mudkip":
				return mudkipPerms;
			case "shark":
				return sharkPerms;
			case "whale":
				return whalePerms;
			case "jellyfish":
				return jellyfishPerms;
		}
		return null;
	}
	
	public void setYouber(boolean bool) {
		youtuber = bool;
	}
	
	public boolean isYoutuber() {
		return youtuber;
	}
	
	public boolean isFirstJoin() {
		return firstJoin;
	}
	
	public Date getLastUsed(String s) {
		return lastUsed.get(s);
	}
	
	public void setLastUsed(String s) {
		lastUsed.put(s, Date.from(Instant.now()));
	}
	
	public void removeRank(String rank) {
		rank = rank.toLowerCase();
		groups.remove(rank);
		ArrayList<String> perms = getPermissionsList(rank);
		if (perms != null) {
			for (String s : perms) {
				attachmentInfo.unsetPermission(s);
			}
		}
	}
	
	public void removePermission(String s) {
		attachmentInfo.unsetPermission(s);
	}
	
	public void saveData() {
		set("prison-data.rank", getRank().toString());
		set("prison-data.prestige", getPrestige());
		set("general-data.balance", getBalance());
		set("prison-data.active-tag", getActiveTag() != null ? getActiveTag().toString() : "unset");
		set("prison-data.tag-toggle", isTagToggle());
		
		for (String s : lastUsed.keySet()) {
			set("cool-downs." + s, lastUsed.get(s).getTime());
		}
		if (attachmentInfo != null) {
			ArrayList<String> permissions = new ArrayList<>();
			for (String s : customPermissions) {
				if (attachmentInfo.getPermissions().get(s) && !defaults.contains(s)) {
					permissions.add(s);
				}
			}
			set("permissions", permissions);
			attachmentInfo.remove();
		}
		ArrayList<String> groups = new ArrayList<>(this.groups);
		set("groups", groups);
		set("general-data.chat-spy", chatSpy);
		saveConfig();
	}
	
	public double getSellMultuplier() {
		return RanksManager.getSellPriceMult(prestige) + groupSellMultiplier();
	}
	
	private double groupSellMultiplier() {
		for (String s : groups) {
			if (getGroupMultiplier(s) != 0) {
				return getGroupMultiplier(s);
			}
		}
		return 0;
	}
	
	public static double getGroupMultiplier(String s) {
		switch (s.toLowerCase()) {
			case "guppy":
				return multiplier[0];
			case "mudkip":
				return multiplier[1];
			case "shark":
				return multiplier[2];
			case "whale":
				return multiplier[3];
			case "jellyfish":
				return multiplier[4];
		}
		return 0;
	}
	
	public boolean hasGroup(String s) {
		HashSet<String> groupsListTemp = new HashSet<>();
		s = s.toLowerCase();
		
		if (youtuber) {
			groupsListTemp.addAll(Arrays.asList(groupsList));
		}
		
		for (String st : groups) {
			int k = -1;
			for (int i = 0; i < groupsList.length; i++) {
				if (groupsList[i].equalsIgnoreCase(st)) {
					k = i;
				}
			}
			groupsListTemp.addAll(Arrays.asList(groupsList).subList(0, k + 1));
			k = -1;
			for (int i = 0; i < extraGroups.length; i++) {
				if (extraGroups[i].equalsIgnoreCase(st)) {
					k = i;
				}
			}
			groupsListTemp.addAll(Arrays.asList(extraGroups).subList(0, k + 1));
		}
		
		return groupsListTemp.contains(s);
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public void prestige() {
		addBalance(-getRankupPriceMultiplied());
		setPrestige(getPrestige() + 1);
		setRank(Rank.A);
	}
	
	public int getPrestige() {
		return prestige;
	}
	
	public void addBalance(double balance) {
		this.balance += balance;
	}
	
	public double getRankupPriceMultiplied() {
		return getRankupPrice() * getRankupMultiplier();
	}
	
	public double getRankupPrice() {
		return rank.getCostToRankup();
	}
	
	public double getRankupMultiplier() {
		return RanksManager.getCostMult(getPrestige());
	}
	
	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}
	
	
	public int percentProgress() {
		if (getBalance() == 0) {
			return 0;
		} else if (getBalance() >= getRankupPriceMultiplied()) {
			return 100;
		} else {
			return (int) ((getBalance() / getRankupPriceMultiplied()) * 100);
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getBalanceReadable() {
		return ReadableNumbers.coolFormat(getBalance(), 0);
	}
	
	public void rankup() {
		int i = rank.ordinal();
		addBalance(-getRankupPriceMultiplied());
		setRank(Rank.values()[i + 1]);
	}
	
	public boolean canPrestige() {
		return rank == Rank.Z && canRankup();
	}
	
	public boolean canRankup() {
		return getBalance() >= getRankupPriceMultiplied();
	}
	
	public Tag getActiveTag() {
		return activeTag;
	}
	
	public void setActiveTag(Tag activeTag) {
		this.activeTag = activeTag;
	}
	
	public boolean isTagToggle() {
		return tagToggle;
	}
	
	public void setTagToggle(boolean tagToggle) {
		this.tagToggle = tagToggle;
	}
	
	public boolean isChatSpy() {
		return chatSpy;
	}
	
	public boolean isTwitcher() {
		return twitcher;
	}
	
	public void setTwitcher(boolean twitcher) {
		this.twitcher = twitcher;
	}
}
