package pw.xwy.prison_core.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import pw.xwy.prison_core.listeners.DamageListener;

import java.util.UUID;

public class CombatTagTask extends BukkitRunnable {
	@Override
	public void run() {
		for (UUID uuid : DamageListener.combatTag.keySet()) {
			if (DamageListener.combatTag.get(uuid) == 6)
				DamageListener.combatTag.remove(uuid);
			else
				DamageListener.combatTag.put(uuid, DamageListener.combatTag.get(uuid) + 1);
		}
	}
}
