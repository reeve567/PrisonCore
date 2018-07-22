package pw.xwy.prison_core.utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.PlayerData;
import pw.xwy.prison_core.PlayerDataManager;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.listeners.JoinListener;
import pw.xwy.prison_core.scoreboard.ScoreboardsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import static pw.xwy.prison_core.PrisonCore.discordIntegration;

public class ConfigurationHandler {
	
	private static Config mainConfiguration;
	private static Config mineConfiguration;
	private static Config rankInfoConfiguration;
	private static Config playerBalanceData;
	private static Config playerRankData;
	private static Config playerPrestigeData;
	private static Config scoreboardConfiguration;
	private static Config prestigeConfiguration;
	
	public ConfigurationHandler(PrisonCore core) {
		mainConfiguration = new Config(core.getDataFolder(), "config");
		rankInfoConfiguration = new Config(core.getDataFolder(), "ranks");
		mineConfiguration = new Config(core.getDataFolder(), "mines");
		playerBalanceData = new Config(core.getDataFolder(), "player-balances");
		playerRankData = new Config(core.getDataFolder(), "player-ranks");
		playerPrestigeData = new Config(core.getDataFolder(), "player-prestiges");
		scoreboardConfiguration = new Config(core.getDataFolder(), "scoreboard");
		prestigeConfiguration = new Config(core.getDataFolder(), "prestiges");
		loadConfig();
	}
	
	private void loadConfig() {
		loadMain();
		loadRankPrices();
		loadPlayerData();
		loadMines();
		loadPrestiges();
		new ScoreboardsManager();
	}
	
	private void loadMain() {
		if (mainConfiguration.getInt("ver") != 1) {
			mainConfiguration.set("ver", 1);
			mainConfiguration.setComment("Discord-Integration", "This is for logging everything to do with the plugin to discord, if enabled.", "The webhook url you get from being an admin on a discord channel and right clicking on the text channel.", "Server identifier isn't anything special, it's purely cosmetic.");
			mainConfiguration.set("Discord-Integration.Enabled", false);
			mainConfiguration.set("Discord-Integration.Webhook-URL", "discord webhook url here");
			mainConfiguration.set("General.Starting-Money", 0.0);
			mainConfiguration.set("General.Money-Symbol", "$");
			mainConfiguration.set("General.Max-Prestige", 20);
			mainConfiguration.set("General.Unique-Players",0);
			mainConfiguration.saveConfig();
		}
		
		discordIntegration = mainConfiguration.getBoolean("Discord-Integration.Enabled");
		if (discordIntegration) {
			new DiscordIntegration("System", mainConfiguration.getString("Discord-Integration.Webhook-URL"), mainConfiguration.getString("Discord-Integration.Server-Identifier")).runTaskTimer(PrisonCore.getInstance(), 0, 5);
		}
		PlayerDataManager.moneySymbol = mainConfiguration.getString("General.Money-Symbol");
		PlayerDataManager.startingMoney = mainConfiguration.getDouble("General.Starting-Money");
		RanksManager.maxPrestige = mainConfiguration.getInt("General.Max-Prestige");
		JoinListener.UniquePlayers = mainConfiguration.getInt("General.Unique-Players");
	}
	
	private void loadRankPrices() {
		if (rankInfoConfiguration.getInt("ver") != 3) {
			rankInfoConfiguration.set("ver", 3);
			for (String s : RanksManager.rankNames) {
				rankInfoConfiguration.set(s + ".Rankup-Cost", 1);
				rankInfoConfiguration.set(s + ".Chat-Prefix", "§8[§6" + s + "§8]");
				rankInfoConfiguration.set(s + ".Shop", new ArrayList<String>());
			}
			rankInfoConfiguration.saveConfig();
		}
		
		for (String s : RanksManager.rankNames) {
			Rank.valueOf(s).setRankPrefix(rankInfoConfiguration.getString(s + ".Chat-Prefix"));
			Rank.valueOf(s).setRankupCost(rankInfoConfiguration.getInt(s + ".Rankup-Cost"));
		}
	}
	
	public void loadPlayerData() {
		HashMap<UUID, Double> balance = new HashMap<>();
		HashMap<UUID, Rank> ranks = new HashMap<>();
		HashMap<UUID, Integer> prestige = new HashMap<>();
		
		if (playerBalanceData.getInt("ver") != 3) {
			playerBalanceData.set("ver", 3);
			savePlayerData();
		} else {
			for (Player p : Bukkit.getOnlinePlayers()) {
				balance.put(p.getUniqueId(), playerBalanceData.getDouble("players." + p.getUniqueId(), PlayerDataManager.startingMoney));
			}
		}
		
		if (playerRankData.getInt("ver") != 7) {
			playerRankData.set("ver", 7);
			savePlayerData();
		} else {
			for (Player p : Bukkit.getOnlinePlayers()) {
				ranks.put(p.getUniqueId(), Rank.valueOf(playerRankData.getString("players." + p.getUniqueId(), "A")));
			}
		}
		
		if (playerPrestigeData.getInt("ver") != 5) {
			playerPrestigeData.set("ver", 5);
			savePlayerData();
		} else {
			for (Player p : Bukkit.getOnlinePlayers()) {
				prestige.put(p.getUniqueId(), playerPrestigeData.getInt("players." + p.getUniqueId(), 0));
			}
		}
		
		for (UUID uuid : balance.keySet()) {
			PlayerDataManager.addPlayerData(uuid, new PlayerData(uuid, balance.get(uuid), ranks.get(uuid), prestige.get(uuid)));
		}
	}
	
	private void loadMines() {
		if (mineConfiguration.getInt("ver") != 6) {
			//set defaults
			mineConfiguration.set("ver", 6);
			saveMines();
		}
		for (Rank rank : Rank.values()) {
			Mine mine = MineManager.mines.get(rank);
			mine.setComposition(mineConfiguration.getString(rank.toString() + ".composition"));
			mine.setRectangle(mineConfiguration.getStringList(rank.toString() + ".location").toArray(new String[0]));
			mine.setProgressSign(mineConfiguration.getStringList(rank.toString() + ".progressSign").toArray(new String[0]));
			mine.setWarp(mineConfiguration.getStringList(rank.toString() + ".warpLocation").toArray(new String[0]));
		}
	}
	
	private void loadPrestiges() {
		if (prestigeConfiguration.getInt("ver") != 2) {
			prestigeConfiguration.set("ver", 2);
			prestigeConfiguration.set("Multiply-Rankup-Price-Multiplier", true);
			for (int i = 1; i <= 20; i++) {
				prestigeConfiguration.set("Prestiges." + i + ".Sell-Multiplier", 1.0);
				prestigeConfiguration.set("Prestiges." + i + ".Rankup-Price-Multiplier", 1.0);
			}
			prestigeConfiguration.saveConfig();
		}
		
		for (int i = 1; i <= RanksManager.maxPrestige; i++) {
			RanksManager.addPrestige(i, prestigeConfiguration.getDouble("Prestiges." + i + ".Rankup-Price-Multiplier"), prestigeConfiguration.getDouble("Prestiges." + i + ".Sell-Multiplier"));
		}
		RanksManager.usePreviousPrestigeMultipliers = prestigeConfiguration.getBoolean("Multiply-Rankup-Price-Multiplier");
	}
	
	public void savePlayerData() {
		for (PlayerData data : PlayerDataManager.getPlayerData().values()) {
			playerBalanceData.set("players." + data.getUuid(), data.getBalance());
			playerRankData.set("players." + data.getUuid(), data.getRank().toString());
			playerPrestigeData.set("players." + data.getUuid(), data.getPrestige());
		}
		playerBalanceData.saveConfig();
		playerRankData.saveConfig();
		playerPrestigeData.saveConfig();
	}
	
	public void saveMines() {
		for (Rank rank : Rank.values()) {
			Mine mine = MineManager.mines.get(rank);
			mineConfiguration.set(rank.toString() + ".location", Arrays.asList(mine.areaStrings()));
			mineConfiguration.set(rank.toString() + ".composition", mine.compositionString());
			mineConfiguration.set(rank.toString() + ".progressSign", Arrays.asList(mine.getProgressSignStrings()));
			mineConfiguration.set(rank.toString() + ".warpLocation", Arrays.asList(mine.getWarpStrings()));
		}
		mineConfiguration.saveConfig();
	}
	
	public static Config getScoreboardConfiguration() {
		return scoreboardConfiguration;
	}
	
	public static Config getMainConfiguration() {
		return mainConfiguration;
	}
	
	public static Config getRankInfoConfiguration() {
		return rankInfoConfiguration;
	}
	
	public static Config getMineConfiguration() {
		return mineConfiguration;
	}
	
	public static Config getPlayerBalanceData() {
		return playerBalanceData;
	}
	
	public static Config getPlayerRankData() {
		return playerRankData;
	}
	
	public static Config getPlayerPrestigeData() {
		return playerPrestigeData;
	}
	
	public void onDisable() {
		saveMines();
		savePlayerData();
		mainConfiguration.set("General.Unique-Players",JoinListener.UniquePlayers);
		mainConfiguration.saveConfig();
	}
}
