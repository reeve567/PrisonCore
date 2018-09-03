package pw.xwy.prison_core.listeners;

import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pw.xwy.prison_core.listeners.gui.ce.MainMenu;
import pw.xwy.prison_core.utility.enums.ExtraRank;
import pw.xwy.prison_core.utility.enums.Rank;
import pw.xwy.prison_core.utility.player.PlayerManager;

import static pw.xwy.prison_core.utility.mine.SellUtility.sell;

public class NPCListener implements Listener {
	@EventHandler
	public void onRightClick(NPCRightClickEvent e) {
		onClick(e);
	}
	
	private void onClick(NPCClickEvent e) {
		if (e.getNPC().getName().contains("CE Menu") || e.getNPC().getName().contains("CustomEnchants")) {
			if (e.getClicker() != null) {
				e.getClicker().openInventory(new MainMenu(e.getClicker()).get());
			}
		} else if (e.getNPC().getName().startsWith("SellAll")) {
			String sRank = e.getNPC().getName().substring(7);
			npcSell(sRank, e.getClicker());
		}
	}
	
	private void npcSell(String sRank, Player clicker) {
		sRank = sRank.toUpperCase();
		try {
			Rank.valueOf(sRank);
			sell(clicker, Rank.valueOf(sRank));
		} catch (Exception ex) {
			try {
				ExtraRank.valueOf(sRank);
				sell(clicker, ExtraRank.valueOf(sRank));
			} catch (Exception ignored) {
				sell(clicker, PlayerManager.getXPlayer(clicker).getRank());
			}
		}
	}
	
	@EventHandler
	public void onLeftClick(NPCLeftClickEvent e) {
		onClick(e);
	}
	
}
