package pw.xwy.prison_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import pw.xwy.prison_core.RealName;
import pw.xwy.prison_core.commands.EventCommand;
import pw.xwy.prison_core.utility.*;

import java.util.HashMap;
import java.util.UUID;

import static pw.xwy.prison_core.custom_enchants.StormCaller.summoner;

public class DamageListener implements Listener {

	public static HashMap<UUID, Integer> combatTag = new HashMap<>();
	public static boolean playerMadeExplosion = false;
	private Player explodee = null;
	private Rect3D safeArea = new Rect3D(new Location(Bukkit.getWorld("world"), -42, 90, -328), new Location(Bukkit.getWorld("world"), -27, 76, -357));
	private Rect3D pvpArea = new Rect3D(new Location(Bukkit.getWorld("world"), -56, 40, -239), new Location(Bukkit.getWorld("world"), 80, 139, -359));

	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (EventCommand.oneVersusOne.getArea().contains(e.getEntity().getLocation())) return;
			if (pvpArea.contains(e.getEntity().getLocation()) && !safeArea.contains(e.getEntity().getLocation()))
				return;
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void EnvHit(EntityDamageEvent e) {

		//TODO: Get rid of all the extra code, dim it down to looping lists with conditions for molten and the like
		//UPDATE: Might not be possible with all the different damage causes

		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			ItemStack boots = player.getInventory().getBoots();
			if (boots != null && boots.hasItemMeta() && boots.getItemMeta().hasLore() && boots.getItemMeta().getLore().contains(RealName.XWY.getEnchant().getName())) {
				((CustomEnviromentalDamageEnchant) RealName.XWY.getEnchant()).event(e);
			}
		}

		//Lightning, should stay probably
		if (e.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING)) {
			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				if (player.equals(summoner)) {
					e.setCancelled(true);
				} else {
					e.getEntity().setFireTicks(0);
				}
			} else if (e.getEntity() instanceof Item) {
				e.setCancelled(true);
			} else {
				e.getEntity().setFireTicks(0);
			}
		} else if (e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				if (player.equals(explodee)) {
					e.setCancelled(true);
					explodee = null;
				}
			}
		} else if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				if (BowListener.shootChk(player.getName()))
					e.setCancelled(true);
			}
		} else if (e.getCause().equals(EntityDamageEvent.DamageCause.POISON)) {
			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				ItemStack helm = player.getInventory().getHelmet();
				if (helm != null && helm.hasItemMeta() && helm.getItemMeta().hasLore()) {
					for (String s : helm.getItemMeta().getLore()) {
						if (s.equalsIgnoreCase(RealName.MEDICINE.getEnchant().getName())) {
							((CustomEnviromentalDamageEnchant) RealName.MEDICINE.getEnchant()).event(e);
						}
					}
				}

			}
		} else if (e.getCause().equals(EntityDamageEvent.DamageCause.WITHER)) {
			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				ItemStack helm = player.getInventory().getHelmet();
				if (helm != null && helm.hasItemMeta() && helm.getItemMeta().hasLore()) {
					for (String s : helm.getItemMeta().getLore()) {
						if (s.equalsIgnoreCase(RealName.GUARDIAN.getEnchant().getName())) {
							((CustomEnviromentalDamageEnchant) RealName.GUARDIAN.getEnchant()).event(e);
						}
					}
				}

			}
		} else if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) ||
				e.getCause().equals(EntityDamageEvent.DamageCause.LAVA) ||
				e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				ItemStack legs = player.getInventory().getLeggings();
				ItemStack chest = player.getInventory().getChestplate();
				ItemStack boots = player.getInventory().getBoots();
				ItemStack helm = player.getInventory().getHelmet();
				if (armorCheck(helm, RealName.BURNSHIELD.getEnchant().getName()) &&
						armorCheck(chest, RealName.BURNSHIELD.getEnchant().getName()) &&
						armorCheck(legs, RealName.BURNSHIELD.getEnchant().getName()) &&
						armorCheck(boots, RealName.BURNSHIELD.getEnchant().getName()))
					((CustomEnviromentalDamageEnchant) RealName.BURNSHIELD.getEnchant()).event(e);
			}
		}
	}

	private boolean armorCheck(ItemStack a, String ench) {

		if (a != null) {
			if (a.hasItemMeta()) {
				if (a.getItemMeta().hasLore()) {
					for (String s : a.getItemMeta().getLore()) {
						if (s.equalsIgnoreCase(ench)) return true;
					}
				}
			}
		}
		return false;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void combatTag(EntityDamageEvent e) {
		if (!e.isCancelled() && e.getEntity() instanceof Player) {
			combatTag.put(e.getEntity().getUniqueId(), 0);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void Hit(final EntityDamageByEntityEvent e) {

		if (!e.isCancelled()) {
			//TODO: Sort out which enchants need player damager and which don't

			if (e.getDamager() instanceof Player) {
				Player player = (Player) e.getDamager();
				if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore()) {
					for (String s : player.getItemInHand().getItemMeta().getLore()) {
						if (CustomEnchantManager.getInstance().getEnchantsByLore().containsKey(s)) {
							CustomEnchant enchant = CustomEnchantManager.getInstance().getEnchantsByLore().get(s);
							if (enchant instanceof CustomDamageEnchant) {
								CustomDamageEnchant damageEnchant = (CustomDamageEnchant) enchant;

								damageEnchant.event(e);
							}
						}
					}
				}
			}

			if (e.getEntity() instanceof Player) {

				//TODO: Run enchants like Molten, Antiknockback here

				/*if (armorCheck(chest, CEnchant.DETONATE.getName())) {
					int r = EnchantDrop.getRandomNumberFrom(1, 100);
					if (r > 75) {
						explodee = (Player) e.getEntity();
						playerMadeExplosion = true;
						e.getEntity().getLocation().getWorld().createExplosion(e.getEntity().getLocation(), 3, false);
					}
				}*/
			}



			((CustomBowEnchant) RealName.FROZENARROW.getEnchant()).event(e);
			((CustomBowEnchant) RealName.VOLTAGE.getEnchant()).event(e);
			((CustomBowEnchant) RealName.RPG.getEnchant()).event(e);
			((CustomBowEnchant) RealName.EXTRADAMAGEARROW.getEnchant()).event(e);
			((CustomBowEnchant) RealName.POISONOUSARROW.getEnchant()).event(e);


		}
	}

	@EventHandler
	public void lightStrikeFire(BlockIgniteEvent e) {

		if (e.getCause().equals(BlockIgniteEvent.IgniteCause.LIGHTNING)) {
			e.setCancelled(true);
		}

	}

	@EventHandler
	public void onFire(EntityCombustEvent e) {

		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			ItemStack legs = player.getInventory().getLeggings();
			ItemStack chest = player.getInventory().getChestplate();
			ItemStack boots = player.getInventory().getBoots();
			ItemStack helm = player.getInventory().getHelmet();
			if (armorCheck(helm, RealName.BURNSHIELD.getEnchant().getName()) &&
					armorCheck(chest, RealName.BURNSHIELD.getEnchant().getName()) &&
					armorCheck(legs, RealName.BURNSHIELD.getEnchant().getName()) &&
					armorCheck(boots, RealName.BURNSHIELD.getEnchant().getName())) {
				e.setDuration(0);
				e.setCancelled(true);
			}
		}
	}


}
