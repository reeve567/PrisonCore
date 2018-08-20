package pw.xwy.prison_core.utility.misc_managers;

import java.util.HashMap;

public class RanksManager {
	
	public static final String[] rankNames = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private static final HashMap<Integer, Prestige> prestiges = new HashMap<>();
	public static int maxPrestige;
	public static boolean usePreviousPrestigeMultipliers;
	
	public RanksManager() {
	
	}
	
	public static void addPrestige(int prestige, double costMult, double sellPriceMult) {
		prestiges.put(prestige, new Prestige(costMult, sellPriceMult));
	}
	
	public static double getCostMult(int i) {
		if (usePreviousPrestigeMultipliers) {
			double temp = 1;
			for (int j = 1; j < maxPrestige; j++) {
				temp *= prestiges.get(j).getCostMult();
			}
			return temp;
		}
		return prestiges.getOrDefault(i, new Prestige(1, 1)).getCostMult();
	}
	
	public static double getSellPriceMult(int i) {
		return prestiges.getOrDefault(i, new Prestige(1, 1)).getSellPriceMult();
	}
	
	private static class Prestige {
		
		private double costMult;
		private double sellPriceMult;
		
		private Prestige(double costMult, double sellPriceMult) {
			this.costMult = costMult;
			this.sellPriceMult = sellPriceMult;
		}
		
		public double getSellPriceMult() {
			return sellPriceMult;
		}
		
		public double getCostMult() {
			return costMult;
		}
		
	}
	
}
