package pw.xwy.prison_core.utility;

import pw.xwy.prison_core.PlayerDataManager;
import pw.xwy.prison_core.PrisonCore;

import java.util.ArrayList;
import java.util.Arrays;

import static pw.xwy.prison_core.PrisonCore.discordIntegration;

public class ConfigurationHandler {
	
	private static Config mainConfiguration;
	private static Config mineConfiguration;
	private static Config rankPricesConfiguration;
	
	public ConfigurationHandler(PrisonCore core) {
		mainConfiguration = new Config(core.getDataFolder(), "config");
		rankPricesConfiguration = new Config(core.getDataFolder(), "ranks");
		mineConfiguration = new Config(core.getDataFolder(), "mines");
		loadConfig();
	}
	
	private void loadConfig() {
		loadMain();
		loadRankPrices();
		new PlayerDataManager();
		new MineManager();
		loadMines();
	}
	
	private void loadMain() {
		if (mainConfiguration.getInt("ver") != 1) {
			mainConfiguration.set("ver", 1);
			mainConfiguration.setComment("Discord-Integration", "This is for logging everything to do with the plugin to discord, if enabled.", "The webhook url you get from being an admin on a discord channel and right clicking on the text channel.", "Server identifier isn't anything special, it's purely cosmetic.");
			mainConfiguration.set("Discord-Integration.Enabled", false);
			mainConfiguration.set("Discord-Integration.Webhook-URL", "discord webhook url here");
			mainConfiguration.saveConfig();
		}
		
		discordIntegration = mainConfiguration.getBoolean("Discord-Integration.Enabled");
		if (discordIntegration) {
			new DiscordIntegration("System", mainConfiguration.getString("Discord-Integration.Webhook-URL"), mainConfiguration.getString("Discord-Integration.Server-Identifier")).runTaskTimer(PrisonCore.getInstance(), 0, 5);
		}
	}
	
	private void loadRankPrices() {
		if (rankPricesConfiguration.getInt("ver") != 1) {
			rankPricesConfiguration.set("ver", 1);
			for (String s : RanksManager.rankNames) {
				rankPricesConfiguration.set(s + ".Rankup-Cost", 1);
				rankPricesConfiguration.set(s + ".Chat-Prefix", " &7[&6" + s + "] ");
				rankPricesConfiguration.set(s + ".Shop", new ArrayList<String>());
			}
			rankPricesConfiguration.saveConfig();
		}
		
		for (String s : RanksManager.rankNames) {
			ERank.valueOf(s).setRankPrefix(rankPricesConfiguration.getString(s + ".Chat-Prefix"));
			ERank.valueOf(s).setRankupCost(rankPricesConfiguration.getInt(s + ".Rankup-Cost"));
		}
	}
	
	private void loadMines() {
		if (mineConfiguration.getInt("ver") != 1) {
			//set defaults
			mineConfiguration.set("ver", 1);
			saveMines();
		}
		for (ERank rank : ERank.values()) {
			if (rank != ERank.Free) {
				Mine mine = MineManager.mines.get(rank);
				mine.setComposition(mineConfiguration.getString(rank.toString() + ".composition"));
				mine.setRectangle(mineConfiguration.getStringList(rank.toString() + ".location").toArray(new String[0]));
				mine.setProgressSign(mineConfiguration.getStringList(rank.toString() + ".progressSign").toArray(new String[0]));
			}
		}
	}
	
	public void saveMines() {
		for (ERank rank : ERank.values()) {
			if (rank != ERank.Free) {
				Mine mine = MineManager.mines.get(rank);
				mineConfiguration.set(rank.toString() + ".location", Arrays.asList(mine.areaStrings()));
				mineConfiguration.set(rank.toString() + ".composition", mine.compositionString());
				mineConfiguration.set(rank.toString() + ".progressSign", Arrays.asList(mine.getProgressSignStrings()));
			}
		}
		mineConfiguration.saveConfig();
	}
	
	public static Config getMainConfiguration() {
		return mainConfiguration;
	}
	
	public static Config getRankPricesConfiguration() {
		return rankPricesConfiguration;
	}
	
	public static Config getMineConfiguration() {
		return mineConfiguration;
	}
	
	public void onDisable() {
		saveMines();
		
	}
}
