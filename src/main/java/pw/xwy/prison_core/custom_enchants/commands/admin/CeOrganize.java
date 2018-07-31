package pw.xwy.prison_core.custom_enchants.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import pw.xwy.prison_core.custom_enchants.enums.CEnchant;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class CeOrganize {
	
	public void run(CommandSender sender) {
		if (sender.hasPermission("ce.admin") || sender.getName().equalsIgnoreCase("Xwy")) {
			if (!(sender instanceof Player)) {
				return;
			}
			Player player = (Player) sender;
			ItemMeta meta = player.getItemInHand().getItemMeta();
			List<String> lore = meta.getLore();
			
			List<String> common = new ArrayList<>();
			List<String> uncommon = new ArrayList<>();
			List<String> rare = new ArrayList<>();
			List<String> mystical = new ArrayList<>();
			List<String> legendary = new ArrayList<>();
			List<String> admin = new ArrayList<>();
			List<String> other = new ArrayList<>();
			
			for (String s : lore) {
				boolean found = false;
				for (CEnchant ce : CEnchant.values()) {
					if (ce.getName().equalsIgnoreCase(s)) {
						found = true;
						switch (ce.getRarity()) {
							case ADMIN:
								admin.add(s);
								break;
							case COMMON:
								common.add(s);
								break;
							case UNCOMMON:
								uncommon.add(s);
								break;
							case RARE:
								rare.add(s);
								break;
							case MYSTICAL:
								mystical.add(s);
								break;
							case HYDRO:
								legendary.add(s);
								break;
						}
						break;
					}
				}
				if (!found) {
					other.add(s);
				}
			}
			sortStrings(common);
			sortStrings(uncommon);
			sortStrings(rare);
			sortStrings(mystical);
			sortStrings(legendary);
			sortStrings(admin);
			
			ArrayList<String> lores = new ArrayList<>();
			lores.addAll(common);
			lores.addAll(uncommon);
			lores.addAll(rare);
			lores.addAll(mystical);
			lores.addAll(legendary);
			lores.addAll(admin);
			lores.addAll(other);
			
			meta.setLore(lores);
			player.getItemInHand().setItemMeta(meta);
		}
	}
	
	
	public void sortStrings(List<String> strings) {
		strings.sort(Collator.getInstance());
	}
	
}
