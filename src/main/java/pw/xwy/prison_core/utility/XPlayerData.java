package pw.xwy.prison_core.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class XPlayerData {
	private UUID uuid;
	private double balance;
	private Rank rank;
	private int prestige;
	private ArrayList<Tag> tags = new ArrayList<>();
	private Tag activeTag = null;
	
	public XPlayerData(UUID uuid, double balance, Rank rank, int prestige) {
		this.uuid = uuid;
		this.balance = balance;
		this.rank = rank;
		this.prestige = prestige;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public void prestige() {
		setPrestige(getPrestige() + 1);
		setRank(Rank.A);
		addBalance(-getRankupPriceMultiplied());
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
		setRank(Rank.values()[i + 1]);
		addBalance(-getRankupPriceMultiplied());
	}
	
	public boolean canPrestige() {
		return rank == Rank.Z && canRankup();
	}
	
	public boolean canRankup() {
		return getBalance() >= getRankupPriceMultiplied();
	}
	
	public ArrayList<Tag> getTags() {
		return tags;
	}
	
	public Tag getActiveTag() {
		return activeTag;
	}
	
	public void setActiveTag(Tag activeTag) {
		this.activeTag = activeTag;
	}
	
	public List<String> getTagStrings() {
		ArrayList<String> strings = new ArrayList<>();
		for (Tag tag : tags) {
			strings.add(tag.toString());
		}
		return strings;
	}
}
