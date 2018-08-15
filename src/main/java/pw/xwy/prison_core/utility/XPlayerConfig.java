package pw.xwy.prison_core.utility;

import org.bukkit.permissions.PermissionAttachment;
import pw.xwy.prison_core.Permissions;
import pw.xwy.prison_core.PrisonCore;

import java.io.File;
import java.util.*;

public class XPlayerConfig extends Config {
	public static final ArrayList<String> defaults = new ArrayList<>(Arrays.asList("plots.use", "plots.info", "plots.claim"
			, "plots.auto", "plots.home", "plots.clear", "plots.delete", "plots.list", "plots.list.mine"
			, "plots.list.shared", "plots.set", "plots.visit", "plots.visit.owned", "plots.visit.shared"
			, "plots.set.flag", "plots.flag.add", "plots.flag.remove", "plots.flag.list", "plots.flag.info"
			, "plots.flag", "plots.confirm", "plots.toggle", "plots.toggle.titles", "plots.set.biome", "plots.set.home"
			, "plots.denied", "plots.remove", "plots.untrust", "plots.undeny", "plots.kick", "plots.done", "plots.save"
			, "plots.continue"));
	public static final ArrayList<String> guppyPerms = new ArrayList<>(Arrays.asList(Permissions.FEED_COMMAND.toString(),
			Permissions.ENDERCHEST_COMMAND.toString(), Permissions.HAT_COMMAND.toString(), Permissions.WORKBENCH_COMMAND.toString(), "kits.guppy"));
	public static final ArrayList<String> mudkipPerms = new ArrayList<>(Arrays.asList());
	
	public static final String[] groupsList = {"guppy", "mudkip", "shark", "whale", "jellyfish", "lionfish"};
	public static final String[] extraGroups = {"helper", "moderator", "admin", "owner", "developer"};
	public static double startingMoney;
	public static String moneySymbol;
	private final int ver = 1;
	public boolean youtuber;
	public boolean twitcher;
	private boolean firstJoin = false;
	private XPlayerData data;
	private boolean chatSpy;
	private HashMap<String, Date> lastUsed = new HashMap<>();
	private HashSet<String> groups = new HashSet<>();
	
	public XPlayerConfig(UUID name, XPlayer player) {
		super(new File(PrisonCore.getInstance().getDataFolder(), "Players"), name.toString());
		setDefaults();
		data = new XPlayerData(name, getDouble("general-data.balance"), Rank.valueOf(getString("prison-data.rank")), getInt("prison-data.prestige"));
		if (!getString("prison-data.active-tag").equalsIgnoreCase("unset"))
			data.setActiveTag(Tag.valueOf(getString("prison-data.active-tag")));
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
		ArrayList<String> permissions = (ArrayList<String>) getStringList("permissions");
		for (String s : permissions) {
			player.addPermission(s);
		}
		for (String s : defaults) {
			player.addPermission(s);
		}
		ArrayList<String> groups = (ArrayList<String>) getStringList("groups");
		for (String s : groups) {
			addRank(s);
		}
	}
	
	public void setDefaults() {
		if (getVersion() != ver) {
			//unique player
			firstJoin = true;
			setVersion();
			set("prison-data.rank", Rank.A.toString());
			set("prison-data.prestige", 0);
			set("prison-data.tags", new ArrayList<>());
			set("prison-data.active-tag", "unset");
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
	
	public void addRank(String rank) {
		rank = rank.toLowerCase();
		groups.add(rank);
	}
	
	public int getVersion() {
		return getInt("ver");
	}
	
	public void setVersion() {
		set("ver", ver);
	}
	
	public XPlayerData getData() {
		return data;
	}
	
	public boolean isFirstJoin() {
		return firstJoin;
	}
	
	public Date getLastUsed(String s) {
		return lastUsed.get(s);
	}
	
	public void setLastUsed(String s) {
		lastUsed.put(s, new Date());
	}
	
	public void removeRank(String rank) {
		rank = rank.toLowerCase();
		groups.remove(rank);
	}
	
	public boolean hasGroup(String s) {
		HashSet<String> groupsListTemp = new HashSet<>();
		s = s.toLowerCase();
		
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
	
	public void saveData(PermissionAttachment attachmentInfo, boolean chatSpy) {
		set("prison-data.rank", data.getRank().toString());
		set("prison-data.prestige", data.getPrestige());
		set("general-data.balance", data.getBalance());
		set("prison-data.tags", data.getTagStrings());
		set("prison-data.active-tag", data.getActiveTag() != null ? data.getActiveTag().toString() : "unset");
		ArrayList<String> permissions = new ArrayList<>();
		for (String s : attachmentInfo.getPermissions().keySet()) {
			if (attachmentInfo.getPermissions().get(s) && !defaults.contains(s)) {
				permissions.add(s);
			}
		}
		set("permissions", permissions);
		set("groups", groups.toArray(new String[0]));
		set("general-data.chat-spy", chatSpy);
		saveConfig();
	}
	
	public boolean isChatSpy() {
		return chatSpy;
	}
}
