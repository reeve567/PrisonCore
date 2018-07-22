package pw.xwy.prison_core;

import pw.xwy.prison_core.utility.ERank;
import pw.xwy.prison_core.utility.RanksManager;
import pw.xwy.prison_core.utility.ReadableNumbers;

import java.util.UUID;

public class PlayerData {
	private UUID uuid;
	private double balance;
	private ERank rank;
	private int prestige;
	
	public PlayerData(UUID uuid, double balance, ERank rank, int prestige) {
		this.uuid = uuid;
		this.balance = balance;
		this.rank = rank;
		this.prestige = prestige;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public ERank getRank() {
		return rank;
	}
	
	public void setRank(ERank rank) {
		this.rank = rank;
	}
	
	public void prestige() {
		setPrestige(getPrestige() + 1);
		setRank(ERank.A);
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
		setRank(ERank.values()[i + 1]);
		addBalance(-getRankupPriceMultiplied());
	}
	
	public boolean canPrestige() {
		return rank == ERank.Z && canRankup();
	}
	
	public boolean canRankup() {
		return getBalance() >= getRankupPriceMultiplied();
	}
	
}
