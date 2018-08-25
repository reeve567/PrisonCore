package pw.xwy.prison_core.utility.player;

import pw.xwy.prison_core.utility.enums.Rank;
import pw.xwy.prison_core.utility.misc_managers.RanksManager;
import pw.xwy.prison_core.utility.ReadableNumbers;
import pw.xwy.prison_core.utility.enums.Tag;

public class XPlayerData {
	private double balance;
	private Rank rank;
	private int prestige;
	private Tag activeTag = null;
	private boolean tagToggle = true;
	
	public XPlayerData(double balance, Rank rank, int prestige) {
		this.balance = balance;
		this.rank = rank;
		this.prestige = prestige;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public void prestige() {
		addBalance(-getRankupPriceMultiplied());
		setPrestige(getPrestige() + 1);
		setRank(Rank.A);
	}
	
	public int getPrestige() {
		return prestige;
	}
	
	public void addBalance(double balance) {
		this.balance += balance;
	}
	
	public double getRankupPriceMultiplied() {
		return getRankupPrice() * getRankupMultiplier();
	}
	
	public double getRankupPrice() {
		return rank.getCostToRankup();
	}
	
	public double getRankupMultiplier() {
		return RanksManager.getCostMult(getPrestige());
	}
	
	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}
	
	public double getSellMultuplier() {
		return RanksManager.getSellPriceMult(prestige);
	}
	
	public int percentProgress() {
		if (getBalance() == 0) {
			return 0;
		} else if (getBalance() >= getRankupPriceMultiplied()) {
			return 100;
		} else {
			return (int) ((getBalance() / getRankupPriceMultiplied()) * 100);
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getBalanceReadable() {
		return ReadableNumbers.coolFormat(getBalance(), 0);
	}
	
	public void rankup() {
		int i = rank.ordinal();
		addBalance(-getRankupPriceMultiplied());
		setRank(Rank.values()[i + 1]);
	}
	
	public boolean canPrestige() {
		return rank == Rank.Z && canRankup();
	}
	
	public boolean canRankup() {
		return getBalance() >= getRankupPriceMultiplied();
	}
	
	public Tag getActiveTag() {
		return activeTag;
	}
	
	public void setActiveTag(Tag activeTag) {
		this.activeTag = activeTag;
	}
	
	public boolean isTagToggle() {
		return tagToggle;
	}
	
	public void setTagToggle(boolean tagToggle) {
		this.tagToggle = tagToggle;
	}
}
