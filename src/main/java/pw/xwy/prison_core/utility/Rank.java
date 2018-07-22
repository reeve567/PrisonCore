package pw.xwy.prison_core.utility;

public enum Rank {
	A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;
	
	int costToRankup;
	String chatPrefix;
	
	Rank() {
		costToRankup = 1;
		chatPrefix = "§7[" + this.name() + "§7]";
	}
	
	public int getCostToRankup() {
		return costToRankup;
	}
	
	public String getChatPrefix() {
		return chatPrefix;
	}
	
	public void setRankupCost(int cost) {
		costToRankup = cost;
	}
	
	public void setRankPrefix(String prefix) {
		chatPrefix = prefix;
	}
	
	public boolean isLowerOrEqual(Rank one, Rank two) {
		return one.ordinal() <= two.ordinal();
	}
	
	public boolean isHigherOrEqual(Rank one, Rank two) {
		return one.ordinal() >= two.ordinal();
	}
	
	public boolean isLower(Rank one, Rank two) {
		return one.ordinal() < two.ordinal();
	}
	
	public boolean isHigher(Rank one, Rank two) {
		return one.ordinal() > two.ordinal();
	}
}
