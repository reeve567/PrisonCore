package pw.xwy.prison_core.utility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerSkull extends CustomItem {
	
	public PlayerSkull(Player p) {
		super(Material.SKULL_ITEM);
		setDurability(3);
		SkullMeta meta = (SkullMeta) getItemMeta();
		meta.setOwner(p.getName());
		setItemMeta(meta);
	}
	
}