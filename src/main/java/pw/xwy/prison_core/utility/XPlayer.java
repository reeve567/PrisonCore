package pw.xwy.prison_core.utility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import pw.xwy.prison_core.PrisonCore;

import java.util.Date;
import java.util.UUID;

public class XPlayer {
	
	private XPlayerConfig config;
	private boolean chatSpy = false;
	private PermissionAttachment attachmentInfo; //TODO MOVE TO XPLAYERCONFIG cv
	
	public XPlayer(UUID player) {
		this(Bukkit.getPlayer(player));
	}
	
	public XPlayer(Player player) {
		attachmentInfo = player.addAttachment(PrisonCore.getInstance());
		config = new XPlayerConfig(player.getUniqueId(), this);
		
		initChatspy();
	}
	
	public void initChatspy() {
		chatSpy = config.isChatSpy();
	}
	
	public XPlayerData getData() {
		return config.getData();
	}
	
	public void save() {
		config.saveData(attachmentInfo, chatSpy);
		attachmentInfo.remove();
	}
	
	public boolean isFirstJoin() {
		return config.isFirstJoin();
	}
	
	public void addPermission(String s) {
		attachmentInfo.setPermission(s, true);
	}
	
	public void removePermission(String s) {
		attachmentInfo.unsetPermission(s);
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
