package pw.xwy.prison_core.utility.player;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {
	
	private static HashMap<UUID, XPlayer> playerHashMap = new HashMap<>();
	private static HashMap<String, UUID> nameToUUID = new HashMap<>();
	
	public static void addPermission(Player player, String s) {
		getXPlayer(player).addPermission(s);
	}
	
	public static XPlayer getXPlayer(Player player) {
		return getXPlayer(player.getUniqueId());
	}
	
	public static XPlayer getXPlayer(UUID player) {
		
		XPlayer tempPlayer = playerHashMap.get(player);
		if (tempPlayer == null) {
			tempPlayer = new XPlayer(player);
			playerHashMap.put(player, tempPlayer);
		}
		return tempPlayer;
	}
	
	public static void removePermission(Player player, String s) {
		getXPlayer(player).removePermission(s);
	}
	
	public static boolean isUniquePlayer(Player player) {
		return getXPlayer(player).isFirstJoin();
	}
	
	public static UUID getPlayer(String name) {
		return nameToUUID.get(name);
	}
	
	public static void updatePlayer(String name, UUID uuid) {
		nameToUUID.put(name, uuid);
	}
	
	public static ArrayList<String> getPlayerStrings() {
		ArrayList<String> temp = new ArrayList<>();
		for (String s : nameToUUID.keySet()) {
			temp.add(s + ":" + nameToUUID.get(s));
		}
		return temp;
	}
	
	public static void unloadPlayer(UUID id) {
		if (playerHashMap.containsKey(id)) {
			playerHashMap.get(id).save();
			playerHashMap.remove(id);
		}
	}
	
	public static boolean hasGroup(Player player, String group) {
		return getXPlayer(player).hasGroup(group);
	}
	
	public static void addGroup(Player player, String group) {
		getXPlayer(player).addGroup(group);
	}
	
	public static void savePlayerData() {
		for (UUID id : playerHashMap.keySet()) {
			playerHashMap.get(id).save();
		}
	}
}
