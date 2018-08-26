package pw.xwy.prison_core.utility.enums;

public enum ExtraRank {
	GUPPY, MUDKIP, SHARK, WHALE, JELLYFISH, EXP_MINE, RESOURCES1, RESOURCES2;
	
	@Override
	public String toString() {
		return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
	}
}
