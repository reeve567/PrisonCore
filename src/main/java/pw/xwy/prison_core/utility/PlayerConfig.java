package pw.xwy.prison_core.utility;

import pw.xwy.prison_core.PlayerData;
import pw.xwy.prison_core.PrisonCore;

import java.io.File;
import java.util.UUID;

public class PlayerConfig extends Config {
	public static double startingMoney;
	public static String moneySymbol;
	private final int ver = 1;
	private boolean firstJoin = false;
	private PlayerData data;
	
	public PlayerConfig(UUID name) {
		super(new File(PrisonCore.getInstance().getDataFolder(), "Players"), name.toString());
		setDefaults();
		data = new PlayerData(name, getDouble("general-data.balance"), Rank.valueOf(getString("prison-data.rank")), getInt("prison-data.prestige"));
		
	}
	
	public void setDefaults() {
		if (getVersion() != ver) {
			//unique player
			firstJoin = true;
			set("prison-data.rank", Rank.A.toString());
			set("prison-data.prestige", 0);
			set("general-data.balance", startingMoney);
			saveConfig();
		}
	}
	
	public int getVersion() {
		return getInt("ver");
	}
	
	public PlayerData getData() {
		return data;
	}
	
	public boolean isFirstJoin() {
		return firstJoin;
	}
	
	public void setVersion() {
		set("ver", ver);
	}
	
	public void saveData() {
		set("prison-data.rank", data.getRank());
		set("prison-data.prestige", data.getPrestige());
		set("general-data.balance", data.getBalance());
		saveConfig();
	}
}
