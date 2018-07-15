package pw.xwy.prison_core.utility;

public enum ERank {
	A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, Free;
	
	int costToRankup;
	String chatPrefix;
	
	ERank() {
		costToRankup = 1;
		chatPrefix = "§7[" + this.name() + "§7] ";
	}
	
	public int getCostToRankup() {
		if (this == Free) {
			return -1;
		}
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
}
