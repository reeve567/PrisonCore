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

import org.bukkit.Bukkit;
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
import org.bukkit.util.Vector;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.utility.ce.MessagesFunctions;
import pw.xwy.prison_core.utility.enums.Messages;

import java.util.ArrayList;
import java.util.List;

public class BowListener implements Listener {

	static List<String> freezerlist = new ArrayList<>();
	static List<String> smiteList = new ArrayList<>();
	static List<String> poisonList = new ArrayList<>();
	static List<String> furnaceList = new ArrayList<>();
	static List<String> explosiveList = new ArrayList<>();
	static List<String> rpgList = new ArrayList<>();
	private static List<String> cantShoot = new ArrayList<>();
	private static List<String> cantFire = new ArrayList<>();
	private List<String> fired = new ArrayList<>();

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
									if (s.contains("GrapplingBow")) {
										hasGrapple = true;
									}
									if (s.contains("Rifle")) {
										hasRifle = true;
									}
									if (s.contains("ShotgunBow")) {
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
					if (e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasLore() && e.getItem().getItemMeta().getLore().contains(CEnchant.RIFLE.getName())) {

						if (e.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Mode: Rifle") ||
								(e.getPlayer().getInventory().getBoots() != null && e.getPlayer().getInventory().getBoots().hasItemMeta() && e.getPlayer
										().getInventory().getBoots().getItemMeta().hasLore() && e.getPlayer().getInventory().getBoots().getItemMeta().getLore().contains(CEnchant.XWY.getName()))) {
							if (e.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Mode: Rifle")) {
								if (e.getPlayer().getInventory().contains(Material.ARROW)) {

									if (!cantFire.contains(e.getPlayer().getName())) {
										cantFire.add(e.getPlayer().getName());
										e.setCancelled(true);
										e.getPlayer().getItemInHand().setDurability((short) (e.getPlayer().getItemInHand().getDurability() + 1));
										e.getPlayer().getInventory().removeItem(new ItemStack(Material.ARROW, 1));
										Arrow arrow = e.getPlayer().launchProjectile(Arrow.class);
										arrow.setBounce(false);
										Vector v = e.getPlayer().getLocation().getDirection().multiply(3);
										arrow.setVelocity(v);
										e.getPlayer().updateInventory();

										Bukkit.getScheduler().runTaskLater(PrisonCore.getInstance(), () -> cantFire.remove(e.getPlayer().getName()), 100L);

									} else {
										e.setCancelled(true);
										e.getPlayer().sendMessage(Messages.prefix.get() + ChatColor.GRAY + "You may only do this every 5 seconds.");
									}
								} else {
									e.getPlayer().sendMessage(Messages.noAmmo.get());
								}
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
					if (e.getBow().getItemMeta().getLore().contains(CEnchant.SHOTGUN.getName())) {
						ItemStack i = e.getBow();
						if (i.getItemMeta().getLore().contains(ChatColor.GRAY + "Mode: Default")) {
							if (((Player) e.getEntity()).getInventory().contains(Material.ARROW, 4)) {

								e.getProjectile().remove();
								((Player) e.getEntity()).getInventory().addItem(new ItemStack(Material.ARROW, 1));

								e.getBow().setDurability((short) (e.getBow().getDurability() - 1));
								for (int r = 1; r <= 4; r++) {
									((Player) e.getEntity()).getInventory().removeItem(new ItemStack(Material.ARROW, 1));
									Arrow arrow = e.getEntity().launchProjectile(Arrow.class);
									arrow.setBounce(false);
									Vector v = e.getEntity().getLocation().getDirection();
									v.add(new Vector(Vec(), 0, Vec()));
									arrow.setVelocity(v);
									((Player) e.getEntity()).updateInventory();
								}
							} else {
								e.setCancelled(true);
								e.getEntity().sendMessage(Messages.noAmmo.get());
								((Player) e.getEntity()).updateInventory();
							}
						}
					}
				}
			}
		}
	}

	private float Vec() {

		float spread = (float) .2;
		return -spread + (float) (Math.random() * ((spread + spread)));
	}

	@EventHandler
	public void onFire(ProjectileLaunchEvent e) {

		if (e.getEntity().getShooter() instanceof Player) {
			if (e.getEntity() instanceof Arrow) {
				final Player p = (Player) e.getEntity().getShooter();
				ItemStack i = p.getItemInHand();
				if (i.hasItemMeta()) {
					if (i.getItemMeta().hasLore()) {
						for (String s : i.getItemMeta().getLore()) {
							if (s.equalsIgnoreCase(ChatColor.GRAY + "Mode: Grappling")) {
								for (String k : i.getItemMeta().getLore()) {
									if (k.equalsIgnoreCase(CEnchant.GRAPPLINGBOW.getName())) {

										ItemStack boots = p.getInventory().getBoots();
										if (boots != null && boots.getItemMeta().getLore().contains(CEnchant.XWY.getName())) {
											fired.add(p.getName());
										} else {
											if (!cantShoot.contains(p.getName())) {
												fired.add(p.getName());
												cantShoot.add(p.getName());
												Bukkit.getScheduler().runTaskLater(PrisonCore.getInstance(), () -> cantShoot.remove(p.getName()), 300);
											} else {
												p.sendMessage(Messages.fifsecCooldown.get());
												e.setCancelled(true);
												p.getInventory().addItem(new ItemStack(Material.ARROW));
											}
										}
									}
								}
							} else if (s.equalsIgnoreCase(CEnchant.FROZENARROW.getName())) {
								if (!freezerlist.contains(p.getName())) {
									freezerlist.add(p.getName());
								}
							} else if (s.equalsIgnoreCase(CEnchant.VOLTAGE.getName())) {
								if (!smiteList.contains(p.getName())) {
									smiteList.add(p.getName());
									DamageListener.summoner = p;
								}
							} else if (s.equalsIgnoreCase(CEnchant.POISONOUSARROW.getName())) {
								if (!poisonList.contains(p.getName())) {
									poisonList.add(p.getName());
								}
							} else if (s.equalsIgnoreCase(CEnchant.FURNACE.getName())) {
								if (!furnaceList.contains(p.getName())) {
									furnaceList.add(p.getName());
								}
							} else if (s.equalsIgnoreCase(CEnchant.EXPLOSIVEARROW.getName())) {
								if (!explosiveList.contains(p.getName())) {
									explosiveList.add(p.getName());
								}
							} else if (s.equalsIgnoreCase(CEnchant.RPG.getName())) {
								if (!rpgList.contains(p.getName())) {
									rpgList.add(p.getName());
								}
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
				Player p = (Player) a.getShooter();

				if (fired != null) {
					int i = 0;
					while (i < fired.size()) {
						String s = fired.get(i);
						if (s.equalsIgnoreCase(p.getName())) {
							//vector force and whatnot
							Location aLoc = a.getLocation();
							Location pLoc = p.getLocation();

							Double x = aLoc.getX() - pLoc.getX();
							Double y = aLoc.getY() - pLoc.getY();
							Double z = aLoc.getZ() - pLoc.getZ();

							Vector v = new Vector();

							v.setX(x / 3);
							v.setY(y / 9);
							v.setZ(z / 3);

							p.setVelocity(v);

							p.sendMessage(Messages.launched.get());

							fired.remove(p.getName());
						}
						i++;
					}

				}

				if (rpgList != null) {
					if (BowListener.rpgList.contains(p.getName())) {
						BowListener.rpgList.remove(p.getName());
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

}
