package pw.xwy.prison_core;

import org.bukkit.entity.Player;

public enum Permissions {
	PERMISSIONS_COMMAND("xwy.admin.perms"),
	KEY_COMMAND("xwy.admin.key"),
	HAT_COMMAND("xwy.perks.commands.hat"),
	ENDERCHEST_COMMAND("xwy.perks.commands.enderchest"),
	ENDERCHEST_ADMIN_COMMAND("xwy.admin.enderchest"),
	FEED_COMMAND("xwy.perks.commands.feed"),
	AUTOSELL_COMMAND("xwy.perks.commands.autosell"),
	SELLALL_COMMAND("xwy.perks.commands.sellall"),
	WORKBENCH_COMMAND("xwy.perks.commands.workbench"),
	CHATCLEAR_COMMAND("xwy.admin.chatclear"),
	CHATSTOP_COMMAND("xwy.admin.chatstop"),
	SET_COMMAND("xwy.admin.set"),
	MINE_COMMAND("xwy.admin.mine"),
	CUSTOMENCHANTS_GIVE_COMMAND("xwy.ce.admin"),
	CUSTOMENCHANTS_LOAD_MESSAGE_NOTIFY("xwy.ce.notify"),
	KITS_NO_COOLDOWNS("kits.no-cooldowns");
	private String permission;
	
	Permissions(String permission) {
		this.permission = permission;
	}
	
	
	public static void sendMessage(Player player) {
		player.sendMessage("§cYou do not have permission to do this!");
	}
	
	@Override
	public String toString() {
		return permission;
	}
}
