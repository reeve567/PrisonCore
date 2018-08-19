package pw.xwy.prison_core.utility;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

public class XPlayer extends CraftPlayer {
	
	private XPlayerConfig config;
	private boolean chatSpy = false;
	//TODO MOVE TO XPLAYERCONFIG cv
	
	public XPlayer(UUID player) {
		this(Bukkit.getPlayer(player));
	}
	
	public XPlayer(Player player) {
		super((CraftServer) Bukkit.getServer(), ((CraftPlayer) player).getHandle());
		config = new XPlayerConfig(player.getUniqueId());
		
		initChatspy();
	}
	
	public void initChatspy() {
		chatSpy = config.isChatSpy();
	}
	
	public XPlayerData getData() {
		return config.getData();
	}
	
	public void save() {
		config.saveData(chatSpy);
	}
	
	public boolean isFirstJoin() {
		return config.isFirstJoin();
	}
	
	public void addPermission(String s) {
		config.addPermission(s);
	}
	
	public void removePermission(String s) {
		config.removePermission(s);
	}
	
	public boolean hasGroup(String s) {
		return config.hasGroup(s);
	}
	
	public void addGroup(String s) {
		config.addRank(s);
	}
	
	public void removeGroup(String s) {
		config.removeRank(s);
	}
	
	public void setLastUsed(String s) {
		config.setLastUsed(s);
	}
	
	public Date getLastUsed(String s) {
		return config.getLastUsed(s);
	}
	
}
