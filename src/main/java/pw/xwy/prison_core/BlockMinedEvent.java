package pw.xwy.prison_core;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pw.xwy.prison_core.utility.Mine;

public class BlockMinedEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Block block;
	private Player player;
	private Mine mine;
	private boolean isOriginal;
	
	public BlockMinedEvent(Block block, Player player, Mine mine, boolean isOriginal) {
		this.block = block;
		this.player = player;
		this.mine = mine;
		this.isOriginal = isOriginal;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public static void call(Block block, Player player, Mine mine, boolean isOriginal) {
		Bukkit.getPluginManager().callEvent(new BlockMinedEvent(block, player, mine, isOriginal));
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public boolean isOriginal() {
		return isOriginal;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Block getBlock() {
		return block;
	}
}
