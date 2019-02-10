package pw.xwy.prison_core.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.utility.item.CustomItem;
import pw.xwy.prison_core.utility.misc_managers.CrateManager;

import java.util.Random;

public class CrateListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.ENDER_CHEST) {
				if (CrateManager.isCrate(e.getClickedBlock())) {
					e.setCancelled(true);
					ItemStack stack = e.getItem();
					if (stack != null && stack.hasItemMeta() && stack.getItemMeta().hasDisplayName() && stack.getType() == Material.TRIPWIRE_HOOK) {
						String name = stack.getItemMeta().getDisplayName();
						Random r = new Random();
						int i = r.nextInt(100);
						int total = 0;


						if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
							if (stack.getAmount() == 1) {
								e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
							} else {
								e.getPlayer().setItemInHand(new CustomItem(stack).setCustomAmount(stack.getAmount() - 1));
							}
						}
						if (name.contains(CrateManager.CrateType.VOTE.getName())) {
							for (CrateManager.VotePrizes prize : CrateManager.VotePrizes.values()) {
								total += prize.getChance();
								if (i < total) {
									prize.getPrize().givePrize(e.getPlayer());
									break;
								}
							}

							return;
						} else if (name.contains(CrateManager.CrateType.RARE.getName())) {
							for (CrateManager.RarePrizes prize : CrateManager.RarePrizes.values()) {
								total += prize.getChance();
								if (i < total) {
									prize.getPrize().givePrize(e.getPlayer());
									break;
								}
							}
							return;
						} else if (name.contains(CrateManager.CrateType.ELITE.getName())) {
							for (CrateManager.ElitePrizes prize : CrateManager.ElitePrizes.values()) {
								total += prize.getChance();
								if (i < total) {
									prize.getPrize().givePrize(e.getPlayer());
									break;
								}
							}
							return;
						} else if (name.contains(CrateManager.CrateType.TAG.getName())) {
							i = r.nextInt(CrateManager.TagPrizes.values().length);
							CrateManager.TagPrizes.values()[i].getPrize().givePrize(e.getPlayer());
							return;
						}
					}
					//TODO: use a key! message change
					e.getPlayer().sendMessage("§cYou need a key in your hand to use this!");

				}
			}
		}
	}
}
