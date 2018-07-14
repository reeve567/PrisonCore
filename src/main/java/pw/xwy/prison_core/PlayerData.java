package pw.xwy.prison_core;

import pw.xwy.prison_core.utility.Config;
import pw.xwy.prison_core.utility.ERank;

import java.util.UUID;

public class PlayerData {
	private int balance = 0;
	private ERank rank;
	private Config config;
	
	public PlayerData(UUID uuid) {
		config = new Config(PrisonCore.getInstance().getDataFolder(), uuid.toString());
	}
	
	private void saveConfig() {
		config.saveConfig();
	}
	
}
