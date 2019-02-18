////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.listeners;
// made by reeve
// on 11:49 AM

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.RealName;
import pw.xwy.prison_core.custom_enchants.Rifle;
import pw.xwy.prison_core.utility.CustomBowBlockEnchant;
import pw.xwy.prison_core.utility.CustomBowDeathEnchant;
import pw.xwy.prison_core.utility.CustomBowEnchant;
import pw.xwy.prison_core.utility.CustomBowShootEnchant;
import pw.xwy.prison_core.utility.ce.MessagesFunctions;

import java.util.ArrayList;
import java.util.List;

public class BowListener implements Listener {

	public static List<String> cantShoot = new ArrayList<>();
	public static List<String> cantFire = new ArrayList<>();

	static boolean shootChk(String player) {

		return cantShoot.contains(player);
	}

	@EventHandler
	public void click(final PlayerInteractEvent e) {

		if (e.getItem() != null) {
			if (e.getItem().getType().equals(Material.BOW)) {
				if (e.getAction().equals(Action.LEFT_CLICK_AIR)) {
					if (e.getItem().hasItemMeta()) {
						if (e.getItem().getItemMeta().hasLore()) {

							boolean hasGrapple = false;
							boolean hasShotgun = false;
							boolean hasRifle = false;

							for (String s : e.getItem().getItemMeta().getLore()) {
								if (!s.contains("Mode")) {
									if (s.contains("Grappling")) {
										hasGrapple = true;
									}
									if (s.contains("Rifle")) {
										hasRifle = true;
									}
									if (s.contains("Shotgun")) {
										hasShotgun = true;
									}
								}
							}

							for (String s : e.getItem().getItemMeta().getLore()) {
								if (s.contains("Mode")) {
									List<String> l = e.getItem().getItemMeta().getLore();
									ItemMeta m = e.getItem().getItemMeta();
									String r;
									if (s.contains("Grappling")) {
										r = s.replaceAll("Grappling", nextMode(hasGrapple, hasRifle, hasShotgun, "Grappling", e.getPlayer()));
									} else if (s.contains("Rifle")) {
										r = s.replaceAll("Rifle", nextMode(hasGrapple, hasRifle, hasShotgun, "Rifle", e.getPlayer()));
									} else if (s.contains("Default")) {
										r = s.replaceAll("Default", nextMode(hasGrapple, hasRifle, hasShotgun, "Default", e.getPlayer()));
									} else {
										r = "sad";
									}
									l.remove(s);
									l.add(r);
									m.setLore(l);
									e.getItem().setItemMeta(m);
								}
							}

						}
					}
				} else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					if (e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasLore() && e.getItem().getItemMeta().getLore().contains(RealName.RIFLE.getEnchant().getName())) {

						if (e.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Mode: Rifle") ||
								(e.getPlayer().getInventory().getBoots() != null && e.getPlayer().getInventory().getBoots().hasItemMeta() && e.getPlayer
										().getInventory().getBoots().getItemMeta().hasLore() && e.getPlayer().getInventory().getBoots().getItemMeta().getLore().contains(RealName.XWY.getEnchant().getName()))) {
							if (e.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Mode: Rifle")) {
								((Rifle) RealName.RIFLE.getEnchant()).launch(e);
							} else {
								e.setCancelled(true);
								Arrow arrow = e.getPlayer().launchProjectile(Arrow.class);
								arrow.setBounce(false);
								Vector v = e.getPlayer().getLocation().getDirection().multiply(3);
								arrow.setVelocity(v);
								e.getPlayer().updateInventory();
							}
						}
					}
				}
			}
		}
	}

	private String nextMode(boolean hasGrappling, boolean hasRifle, boolean hasShotgun, String currentMode, Player p) {

		if (currentMode.equalsIgnoreCase("Default")) {
			if (hasGrappling) {
				p.sendMessage(MessagesFunctions.modeChanged("Grappling"));
				return "Grappling";
			} else if (hasRifle) {
				p.sendMessage(MessagesFunctions.modeChanged("Rifle"));
				return "Rifle";
			} else {
				p.sendMessage(MessagesFunctions.modeChanged("Default"));
				return "Default";
			}
		} else if (currentMode.equalsIgnoreCase("Grappling")) {
			if (hasRifle) {
				p.sendMessage(MessagesFunctions.modeChanged("Rifle"));
				return "Rifle";
			} else {
				p.sendMessage(MessagesFunctions.modeChanged("Default"));
				return "Default";
			}
		} else if (currentMode.equalsIgnoreCase("Rifle")) {
			p.sendMessage(MessagesFunctions.modeChanged("Default"));
			return "Default";
		} else {
			return "sad";
		}
	}

	@EventHandler
	public void shoot(EntityShootBowEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getBow().hasItemMeta()) {
				if (e.getBow().getItemMeta().hasLore()) {
					if (e.getBow().getItemMeta().getLore().contains(RealName.SHOTGUN.getEnchant().getName())) {
						((CustomBowShootEnchant) RealName.SHOTGUN.getEnchant()).event(e);
					}
				}
			}
		}
	}


	@EventHandler
	public void onFire(ProjectileLaunchEvent e) {
		//TODO: Combine with shoot event ?
		if (e.getEntity().getShooter() instanceof Player) {
			if (e.getEntity() instanceof Arrow) {
				final Player p = (Player) e.getEntity().getShooter();
				ItemStack i = p.getItemInHand();
				if (i.hasItemMeta()) {
					if (i.getItemMeta().hasLore()) {
						for (String s : i.getItemMeta().getLore()) {
							if (s.equalsIgnoreCase(ChatColor.GRAY + "Mode: Grappling")) {
								((CustomBowEnchant) RealName.GRAPPLINGBOW.getEnchant()).launch(e);
							} else if (s.equalsIgnoreCase(RealName.FROZENARROW.getEnchant().getName())) {
								((CustomBowEnchant) RealName.FROZENARROW.getEnchant()).launch(e);
							} else if (s.equalsIgnoreCase(RealName.VOLTAGE.getEnchant().getName())) {
								((CustomBowDeathEnchant) RealName.VOLTAGE.getEnchant()).launch(e);
							} else if (s.equalsIgnoreCase(RealName.POISONOUSARROW.getEnchant().getName())) {
								((CustomBowDeathEnchant) RealName.POISONOUSARROW.getEnchant()).launch(e);
							} else if (s.equalsIgnoreCase(RealName.FURNACE.getEnchant().getName())) {
								((CustomBowDeathEnchant) RealName.FURNACE.getEnchant()).launch(e);
							} else if (s.equalsIgnoreCase(RealName.EXPLOSIVEARROW.getEnchant().getName())) {
								((CustomBowDeathEnchant) RealName.EXPLOSIVEARROW.getEnchant()).launch(e);
							} else if (s.equalsIgnoreCase(RealName.RPG.getEnchant().getName())) {
								e.getEntity().setMetadata("RPG", new FixedMetadataValue(PrisonCore.getInstance(), p.getName()));
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onLand(ProjectileHitEvent e) {

		if (e.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) e.getEntity();
			if (a.getShooter() instanceof Player) {

				if (e.getEntity().hasMetadata("Grappling")) {
					//redoing

					((CustomBowBlockEnchant) RealName.GRAPPLINGBOW.getEnchant()).event(e);

					//old
					/*int i = 0;
					while (i < fired.size()) {
						String s = fired.get(i);
						if (s.equalsIgnoreCase(p.getName())) {
							//vector force and whatnot
							Location aLoc = a.getLocation();
							Location pLoc = p.getLocation();

							double x = aLoc.getX() - pLoc.getX();
							double y = aLoc.getY() - pLoc.getY();
							double z = aLoc.getZ() - pLoc.getZ();

							Vector v = new Vector();

							v.setX(x / 3);
							v.setY(y / 9);
							v.setZ(z / 3);

							p.setVelocity(v);

							p.sendMessage(Messages.launched.get());


							fired.remove(p.getName());
						}
						i++;
					}*/

				}

				if (e.getEntity().hasMetadata("RPG")) {
					int ab = EnchantDrop.getRandomNumberFrom(1, 100);
					if (ab <= 15) {
						DamageListener.playerMadeExplosion = true;
						Location l = e.getEntity().getLocation();
						e.getEntity().getLocation().getWorld().createExplosion(l.getX(), l.getY(), l.getZ(), 5, false, false);
					}
				}


			}
		}
	}

}
