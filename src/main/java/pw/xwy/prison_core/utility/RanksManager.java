package pw.xwy.prison_core.utility;

import java.util.ArrayList;

public class RanksManager {
	
	public static final String[] rankNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Free"};
	private static ArrayList<Rank> ranks = new ArrayList<>();
	
	public RanksManager() {
	
	}
	
	public static void addRank(Rank rank) {
		ranks.add(rank);
	}
}
