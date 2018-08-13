package pw.xwy.prison_core.utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pw.xwy.prison_core.CrateManager;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.scoreboard.ScoreboardsManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import static pw.xwy.prison_core.PrisonCore.discordIntegration;
import static pw.xwy.prison_core.PrisonCore.telegramIntegration;

public class ConfigurationHandler {
	
	public static HashMap<UUID, PlayerConfig> playerConfigs = new HashMap<>();
	private static Config scoreboardConfiguration;
	private Config mainConfiguration;
	private Config mineConfiguration;
	private Config rankInfoConfiguration;
	private Config prestigeConfiguration;
	private Config normalWarpsData;
	private Config miscData;
	
	public ConfigurationHandler(PrisonCore core) {
		mainConfiguration = new Config(core.getDataFolder(), "config");
		rankInfoConfiguration = new Config(core.getDataFolder(), "ranks");
		mineConfiguration = new Config(core.getDataFolder(), "mines");
		scoreboardConfiguration = new Config(core.getDataFolder(), "scoreboard");
		prestigeConfiguration = new Config(core.getDataFolder(), "prestiges");
		normalWarpsData = new Config(core.getDataFolder(), "warps-data");
		miscData = new Config(core.getDataFolder(), "misc-data");
		loadConfig();
	}
	
	private void loadConfig() {
		loadMain();
		loadRankPrices();
		loadPlayerData();
		loadMines();
		loadPrestiges();
		loadNormalWarps();
		loadMisc();
		new ScoreboardsManager();
	}
	
	private void loadMain() {
		if (mainConfiguration.getInt("ver") != 2) {
			mainConfiguration.set("ver", 2);
			mainConfiguration.setComment("Discord-Integration", "This is for logging everything to do with the plugin to discord, if enabled.", "The webhook url you get from being an admin on a discord channel and right clicking on the text channel.", "Server identifier isn't anything special, it's purely cosmetic.");
			mainConfiguration.set("Discord-Integration.Enabled", false);
			mainConfiguration.set("Discord-Integration.Webhook-URL", "discord webhook url here");
			mainConfiguration.set("Telegram-Integration.Enabled", false);
			mainConfiguration.set("Telegram-Integration.Bot-ID", "");
			mainConfiguration.set("Telegram-Integration.Chat-ID", "");
			mainConfiguration.set("General.Starting-Money", 0.0);
			mainConfiguration.set("General.Money-Symbol", "$");
			mainConfiguration.set("General.Max-Prestige", 20);
			mainConfiguration.saveConfig();
		}
		
		discordIntegration = mainConfiguration.getBoolean("Discord-Integration.Enabled");
		if (discordIntegration) {
			new DiscordIntegration("System", mainConfiguration.getString("Discord-Integration.Webhook-URL"), mainConfiguration.getString("Discord-Integration.Server-Identifier")).runTaskTimer(PrisonCore.getInstance(), 0, 5);
		}
		telegramIntegration = mainConfiguration.getBoolean("Telegram-Integration.Enabled");
		if (telegramIntegration) {
			new TelegramIntegration(mainConfiguration.getString("Telegram-Integration.Bot-ID"), mainConfiguration.getString("Telegram-Integration.Chat-ID")).runTaskTimer(PrisonCore.getInstance(), 0, 5);
			TelegramIntegration.messages.add("Integration Enabled!");
		}
		PlayerConfig.moneySymbol = mainConfiguration.getString("General.Money-Symbol");
		PlayerConfig.startingMoney = mainConfiguration.getDouble("General.Starting-Money");
		RanksManager.maxPrestige = mainConfiguration.getInt("General.Max-Prestige");
	}
	
	private void loadRankPrices() {
		if (rankInfoConfiguration.getInt("ver") != 3) {
			rankInfoConfiguration.set("ver", 3);
			for (String s : RanksManager.rankNames) {
				rankInfoConfiguration.set(s + ".Rankup-Cost", 1);
				rankInfoConfiguration.set(s + ".Chat-Prefix", "§8[§6" + s + "§8]");
			}
			rankInfoConfiguration.saveConfig();
		}
		
		for (String s : RanksManager.rankNames) {
			Rank.valueOf(s).setRankPrefix(rankInfoConfiguration.getString(s + ".Chat-Prefix"));
			Rank.valueOf(s).setRankupCost(rankInfoConfiguration.getInt(s + ".Rankup-Cost"));
		}
	}
	
	public void loadPlayerData() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			playerConfigs.put(p.getUniqueId(), new PlayerConfig(p.getUniqueId()));
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
			mine.setShop(mineConfiguration.getString(rank.toString() + ".shop"));
		}
		for (ExtraRank rank : ExtraRank.values()) {
			Mine mine = MineManager.extraMines.get(rank);
			mine.setComposition(mineConfiguration.getString(rank.toString() + ".composition"));
			mine.setRectangle(mineConfiguration.getStringList(rank.toString() + ".location").toArray(new String[0]));
			mine.setProgressSign(mineConfiguration.getStringList(rank.toString() + ".progressSign").toArray(new String[0]));
			mine.setWarp(mineConfiguration.getStringList(rank.toString() + ".warpLocation").toArray(new String[0]));
			mine.setShop(mineConfiguration.getString(rank.toString() + ".shop"));
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
	
	private void loadNormalWarps() {
		if (normalWarpsData.getInt("ver") != 1) {
			normalWarpsData.set("ver", 1);
			for (NormalWarps warp : NormalWarps.values()) {
				warp.save(normalWarpsData);
			}
			normalWarpsData.saveConfig();
		}
		for (NormalWarps warp : NormalWarps.values()) {
			warp.loadLocationFromConfig(normalWarpsData);
		}
	}
	
	private void loadMisc() {
		if (miscData.getInt("ver") != 2) {
			miscData.set("ver", 2);
			miscData.set("Locations.Crate", "unset");
			miscData.saveConfig();
		}
		new CrateManager(miscData.getString("Locations.Crate"));
	}
	
	public void saveMines() {
		for (Rank rank : Rank.values()) {
			Mine mine = MineManager.mines.get(rank);
			mineConfiguration.set(rank.toString() + ".location", Arrays.asList(mine.areaStrings()));
			mineConfiguration.set(rank.toString() + ".composition", mine.compositionString());
			mineConfiguration.set(rank.toString() + ".progressSign", Arrays.asList(mine.getProgressSignStrings()));
			mineConfiguration.set(rank.toString() + ".warpLocation", Arrays.asList(mine.getWarpStrings()));
			mineConfiguration.set(rank.toString() + ".shop", mine.shopString());
		}
		for (ExtraRank rank : ExtraRank.values()) {
			Mine mine = MineManager.extraMines.get(rank);
			mineConfiguration.set(rank.toString() + ".location", Arrays.asList(mine.areaStrings()));
			mineConfiguration.set(rank.toString() + ".composition", mine.compositionString());
			mineConfiguration.set(rank.toString() + ".progressSign", Arrays.asList(mine.getProgressSignStrings()));
			mineConfiguration.set(rank.toString() + ".warpLocation", Arrays.asList(mine.getWarpStrings()));
			mineConfiguration.set(rank.toString() + ".shop", mine.shopString());
		}
		mineConfiguration.saveConfig();
	}
	
	public static boolean isUniquePlayer(Player player) {
		return playerConfigs.get(player.getUniqueId()).isFirstJoin();
	}
	
	public static Config getScoreboardConfiguration() {
		return scoreboardConfiguration;
	}
	
	public static void unloadPlayer(UUID id) {
		playerConfigs.get(id).saveData();
		playerConfigs.remove(id);
	}
	
	public void onDisable() {
		saveNormalWarps();
		saveMines();
		savePlayerData();
		saveMiscData();
	}
	
	private void saveNormalWarps() {
		for (NormalWarps warp : NormalWarps.values()) {
			warp.save(normalWarpsData);
		}
		normalWarpsData.saveConfig();
	}
	
	private void savePlayerData() {
		for (UUID id : playerConfigs.keySet()) {
			PlayerConfig config = playerConfigs.get(id);
			config.saveData();
		}
	}
	
	private void saveMiscData() {
		miscData.set("Locations.Crate", CrateManager.crateString());
	}
	
}
