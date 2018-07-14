package pw.xwy.prison_core.utility;

public class Rank {
	
	public final String name;
	public final String chatprefix;
	public final int costToRankup;
	
	public Rank(String name, String chatprefix, int costToRankup) {
		this.name = name;
		this.chatprefix = chatprefix;
		this.costToRankup = costToRankup;
	}
	
	public boolean isFree() {
		return name.equalsIgnoreCase("Free");
	}
}
