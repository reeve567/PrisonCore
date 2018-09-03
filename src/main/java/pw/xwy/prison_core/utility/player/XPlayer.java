package pw.xwy.prison_core.utility.player;

import org.bukkit.entity.Player;
import pw.xwy.prison_core.utility.enums.Rank;
import pw.xwy.prison_core.utility.enums.Tag;

import java.util.Date;
import java.util.UUID;

public class XPlayer {
	
	private XPlayerConfig config;
	
	public XPlayer(UUID player) {
		config = new XPlayerConfig(player);
	}
	
	public XPlayer(Player player) {
		config = new XPlayerConfig(player.getUniqueId());
	}
	
	public void setYouber(boolean bool) {
		config.setYouber(bool);
	}
	
	public boolean isYoutuber() {
		return config.isYoutuber();
	}
	
	public void save() {
		config.saveData();
	}
	
	public double getSellMultuplier() {
		return config.getSellMultuplier();
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
	
	public void setActiveTag(Tag ded) {
		config.setActiveTag(ded);
	}
	
	public void setTagToggle(boolean b) {
		config.setTagToggle(b);
	}
	
	public Tag getActiveTag() {
		return config.getActiveTag();
	}
	
	public boolean isTagToggle() {
		return config.isTagToggle();
	}
	
	public boolean canPrestige() {
		return config.canPrestige();
	}
	
	public void prestige() {
		config.prestige();
	}
	
	public boolean canRankup() {
		return config.canRankup();
	}
	
	public void rankup() {
		config.rankup();
	}
	
	public double getBalance() {
		return config.getBalance();
	}
	
	public void addBalance(double v) {
		config.addBalance(v);
	}
	
	public Rank getRank() {
		return config.getRank();
	}
	
	public int getPrestige() {
		return config.getPrestige();
	}
	
	public void setBalance(double i) {
		config.setBalance(i);
	}
	
	public String getBalanceReadable() {
		return config.getBalanceReadable();
	}
	
	public int percentProgress() {
		return config.percentProgress();
	}
	
	public double getRankupPriceMultiplied() {
		return config.getRankupPriceMultiplied();
	}
}
