package pw.xwy.prison_core.utility.enums;

public enum Tag {
	HIGHROLLER("§6§lHigh-Roller"),
	XWY("§7#§6§lXwy"),
	GAMBLER("§aGambler"),
	HACKER("§4Hacker"),
	MUDKIP("§6Mud§bKip"),
	NERD("§3N3rd"),
	PVP("§4PvP"),
	MUDKIPPER("§b§lMudkipper"),
	SALTY("§fSalty"),
	NEWB("§eNewb"),
	MLG("§7#§9§lM§f§lL§c§lG"),
	MAXED("§7#§cM§ea§ax§be§5d"),
	DED("§7§oded"),
	;
	private String display;
	
	Tag(String display) {
		this.display = display;
	}
	
	public String getDisplay() {
		return display;
	}
}
