package pw.xwy.prison_core.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.WALL_SIGN) {
			Sign sign = (Sign) e.getClickedBlock().getState().getData();
			Block chest = getBlockSignAttachedTo(e.getClickedBlock());
			if (chest != null && chest.getType() == Material.CHEST) {
			
			}
			
		}
	}
	
	public static boolean chestShop(Sign sign) {
	
	
	
	}
	
	public static Block getBlockSignAttachedTo(Block block) {
		if (block.getType().equals(Material.WALL_SIGN))
			switch (block.getData()) {
				case 2:
					return block.getRelative(BlockFace.WEST);
				case 3:
					return block.getRelative(BlockFace.EAST);
				case 4:
					return block.getRelative(BlockFace.SOUTH);
				case 5:
					return block.getRelative(BlockFace.NORTH);
			}
		return null;
	}
	
}
