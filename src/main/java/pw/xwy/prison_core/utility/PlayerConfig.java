package pw.xwy.prison_core.utility;

import pw.xwy.prison_core.PrisonCore;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class PlayerConfig extends Config {
	public static double startingMoney;
	public static String moneySymbol;
	private final int ver = 1;
	private boolean firstJoin = false;
	private PlayerData data;
	private HashMap<String, Date> lastUsed = new HashMap<>();
	
	
	public PlayerConfig(UUID name) {
		super(new File(PrisonCore.getInstance().getDataFolder(), "Players"), name.toString());
		setDefaults();
		data = new PlayerData(name, getDouble("general-data.balance"), Rank.valueOf(getString("prison-data.rank")), getInt("prison-data.prestige"));
		if (!getString("prison-data.active-tag").equalsIgnoreCase("unset")) data.setActiveTag(Tag.valueOf(getString("prison-data.active-tag")));
		lastUsed.put("god-tools", new Date(getLong("cool-downs.god-tools")));
		lastUsed.put("god-axe", new Date(getLong("cool-downs.god-axe")));
		lastUsed.put("pvp", new Date(getLong("cool-downs.pvp")));
		lastUsed.put("guppy", new Date(getLong("cool-downs.guppy")));
		lastUsed.put("mudkip", new Date(getLong("cool-downs.mudkip")));
		lastUsed.put("shark", new Date(getLong("cool-downs.shark")));
		lastUsed.put("whale", new Date(getLong("cool-downs.whale")));
		lastUsed.put("jellyfish", new Date(getLong("cool-downs.jellyfish")));
		
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
			set("cool-downs.god-tools", 0L);
			set("cool-downs.god-axe", 0L);
			set("cool-downs.pvp", 0L);
			set("cool-downs.guppy", 0L);
			set("cool-downs.mudkip", 0L);
			set("cool-downs.shark", 0L);
			set("cool-downs.whale", 0L);
			set("cool-downs.jellyfish", 0L);
			saveConfig();
		}
	}
	
	public int getVersion() {
		return getInt("ver");
	}
	
	public void setVersion() {
		set("ver", ver);
	}
	
	public PlayerData getData() {
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
	
	public void saveData() {
		set("prison-data.rank", data.getRank().toString());
		set("prison-data.prestige", data.getPrestige());
		set("general-data.balance", data.getBalance());
		set("prison-data.tags", data.getTagStrings());
		set("prison-data.active-tag", data.getActiveTag() != null ? data.getActiveTag().toString() : "unset");
		saveConfig();
	}
}
