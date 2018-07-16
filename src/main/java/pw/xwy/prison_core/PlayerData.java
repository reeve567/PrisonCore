package pw.xwy.prison_core;

import pw.xwy.prison_core.utility.ERank;

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
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void addBalance(double balance) {
		this.balance += balance;
	}
	
	public ERank getRank() {
		return rank;
	}
	
	public void setRank(ERank rank) {
		this.rank = rank;
	}
	
	public int getPrestige() {
		return prestige;
	}
	
	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}
}
