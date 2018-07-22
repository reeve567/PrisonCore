package pw.xwy.prison_core;

import pw.xwy.prison_core.utility.Rank;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {
	public static double startingMoney;
	public static String moneySymbol;
	private static HashMap<UUID, PlayerData> playerDataMap = new HashMap<>();
	
	public static void addPlayerData(UUID uuid, PlayerData data) {
		System.out.println("Player data added - " + uuid.toString());
		playerDataMap.put(uuid, data);
	}
	
	public static HashMap<UUID, PlayerData> getPlayerData() {
		return playerDataMap;
	}
	
	public static PlayerData getPlayerData(UUID uuid) {
		return playerDataMap.getOrDefault(uuid, new PlayerData(uuid, startingMoney, Rank.A, 0));
	}
}
