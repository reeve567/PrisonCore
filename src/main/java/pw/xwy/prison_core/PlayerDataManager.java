package pw.xwy.prison_core;

import pw.xwy.prison_core.utility.Config;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {
	
	public Config balances;
	public Config ranks;
	private HashMap<UUID, Integer> balancesMap = new HashMap<>();
	private HashMap<UUID, String> ranksMap = new HashMap<>();
	
	public PlayerDataManager() {
		balances = new Config(PrisonCore.getInstance().getDataFolder(), "playerBalances");
		ranks = new Config(PrisonCore.getInstance().getDataFolder(), "playerRanks");
		loadConfig();
	}
	
	private void loadConfig() {
	
	}
}
