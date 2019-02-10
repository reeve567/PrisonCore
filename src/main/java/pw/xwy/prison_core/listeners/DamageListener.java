package pw.xwy.prison_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import pw.xwy.prison_core.PrisonCore;
import pw.xwy.prison_core.RealName;
import pw.xwy.prison_core.commands.EventCommand;
import pw.xwy.prison_core.utility.CustomEnviromentalDamageEnchant;
import pw.xwy.prison_core.utility.Rect3D;
import pw.xwy.prison_core.utility.enums.Messages;

import java.util.HashMap;
import java.util.UUID;

public class DamageListener implements Listener {

	public static HashMap<UUID, Integer> combatTag = new HashMap<>();
	public static Player summoner = null;
	static boolean playerMadeExplosion = false;
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

			if (e.getEntity() instanceof Player) {
				Player player = (Player) e.getEntity();
				ItemStack legs = player.getInventory().getLeggings();
				ItemStack chest = player.getInventory().getChestplate();
				ItemStack boots = player.getInventory().getBoots();
				ItemStack helm = player.getInventory().getHelmet();
				
				/*if (armorCheck(chest, CEnchant.DETONATE.getName())) {
					int r = EnchantDrop.getRandomNumberFrom(1, 100);
					if (r > 75) {
						explodee = (Player) e.getEntity();
						playerMadeExplosion = true;
						e.getEntity().getLocation().getWorld().createExplosion(e.getEntity().getLocation(), 3, false);
					}
				}*/
				if (armorCheck(legs, RealName.STARVEDI.getEnchant().getName())) {
					if (e.getDamager() instanceof Player && ((Player) e.getDamager()).getFoodLevel() > 0) {
						int num = EnchantDrop.getRandomNumberFrom(1, 100);
						if (num <= 15) {
							((Player) e.getDamager()).setFoodLevel(((Player) e.getDamager()).getFoodLevel() - 1);
						}
					}
				} else if (armorCheck(legs, RealName.STARVEDII.getEnchant().getName())) {
					if (e.getDamager() instanceof Player && ((Player) e.getDamager()).getFoodLevel() > 0) {
						int num = EnchantDrop.getRandomNumberFrom(1, 100);
						if (num <= 25) {
							((Player) e.getDamager()).setFoodLevel(((Player) e.getDamager()).getFoodLevel() - 1);
						}
					}
				} else if (armorCheck(legs, RealName.STARVEDIII.getEnchant().getName())) {
					if (e.getDamager() instanceof Player && ((Player) e.getDamager()).getFoodLevel() > 0) {
						int num = EnchantDrop.getRandomNumberFrom(1, 100);
						if (num <= 35) {
							((Player) e.getDamager()).setFoodLevel(((Player) e.getDamager()).getFoodLevel() - 1);
						}
					}
				}
				if (armorCheck(legs, RealName.ANTIKNOCKBACKI.getEnchant().getName())) {
					Bukkit.getScheduler().runTaskLater(PrisonCore.getInstance(), () -> {

						Vector kb = e.getDamager().getLocation().toVector().subtract(e.getEntity().getLocation().toVector()).multiply(-0.75);
						e.getEntity().setVelocity(kb);
					}, 1);
				} else if (armorCheck(legs, RealName.ANTIKNOCKBACKII.getEnchant().getName())) {
					Bukkit.getScheduler().runTaskLater(PrisonCore.getInstance(), () -> {

						Vector kb = e.getDamager().getLocation().toVector().subtract(e.getEntity().getLocation().toVector()).multiply(-0.5);
						e.getEntity().setVelocity(kb);
					}, 1);
				} else if (armorCheck(legs, RealName.ANTIKNOCKBACKIII.getEnchant().getName())) {

					Bukkit.getScheduler().runTaskLater(PrisonCore.getInstance(), () -> {

						Vector kb = e.getDamager().getLocation().toVector().subtract(e.getEntity().getLocation().toVector()).multiply(-0.25);
						e.getEntity().setVelocity(kb);
					}, 1);
				}

				if (armorCheck(helm, RealName.MOLTEN.getEnchant().getName())) {
					moltenFunc(e.getDamager(), e.getEntity());
				} else if (armorCheck(chest, RealName.MOLTEN.getEnchant().getName())) {
					moltenFunc(e.getDamager(), e.getEntity());
				} else if (armorCheck(legs, RealName.MOLTEN.getEnchant().getName())) {
					moltenFunc(e.getDamager(), e.getEntity());
				} else if (armorCheck(boots, RealName.MOLTEN.getEnchant().getName())) {
					moltenFunc(e.getDamager(), e.getEntity());
				}
			}
			if (e.getDamager() instanceof Arrow) {
				if (((Arrow) e.getDamager()).getShooter() instanceof Player) {
					Player p = (Player) ((Arrow) e.getDamager()).getShooter();
					Arrow arrow = (Arrow) e.getDamager();
					if (arrow.hasMetadata("Freezer")) {
						int num = EnchantDrop.getRandomNumberFrom(1, 100);
						if (num <= 20) {
							if (e.getEntity() instanceof Player) {
								Player p1 = (Player) e.getEntity();
								p1.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 3));
								p.sendMessage(Messages.frozen.get());
							}
						}
					}
					if (arrow.hasMetadata("Voltage")) {
						int num = EnchantDrop.getRandomNumberFrom(1, 100);
						if (num <= 20) {
							e.getEntity().getLocation().getWorld().strikeLightning(e.getEntity().getLocation());
							p.sendMessage(Messages.smited.get());
						}
					}
					if (arrow.hasMetadata("RPG")) {
						int num = EnchantDrop.getRandomNumberFrom(1, 100);
						if (num <= 15) {
							playerMadeExplosion = true;
							e.getEntity().getLocation().getWorld().createExplosion(e.getEntity().getLocation(), 5);
						}
					}
					if (arrow.hasMetadata("Explosive")) {
						int num = EnchantDrop.getRandomNumberFrom(1, 100);
						if (num <= 70) {
							e.setDamage(e.getDamage() * 2);
						}
					}
					if (arrow.hasMetadata("Poisonous")) {
						int num = EnchantDrop.getRandomNumberFrom(1, 100);
						if (num <= 50) {
							if (e.getEntity() instanceof Player) {
								((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
								p.sendMessage(Messages.poisoned.get());
							}
						}
					}
				}
			}

			if (e.getDamager() instanceof Player) {
				Player player = (Player) e.getDamager();
				if (player.getItemInHand().hasItemMeta()) {
					if (player.getItemInHand().getItemMeta().hasLore()) {
						for (String s : player.getItemInHand().getItemMeta().getLore()) {
							if (s.equalsIgnoreCase(RealName.VAMPIRE.getEnchant().getName())) {
								double d = player.getHealth() + (e.getDamage() / 2);
								if (d > player.getMaxHealth()) d = player.getMaxHealth();
								player.setHealth(d);
							} else if (s.equalsIgnoreCase(RealName.STORMCALLER.getEnchant().getName()) || s.equalsIgnoreCase(RealName.THOR.getEnchant().getName())) {
								int r = EnchantDrop.getRandomNumberFrom(1, 100);
								if (r <= 10) {
									player.getLocation().getWorld().strikeLightning(e.getEntity().getLocation());
									summoner = player;
								}
							} else if (s.equalsIgnoreCase(RealName.EXPTHIEF.getEnchant().getName())) {
								int r = EnchantDrop.getRandomNumberFrom(1, 100);
								if (r <= 85) {
									if (e.getEntity() instanceof Player) {
										if (!(((Player) e.getEntity()).getInventory().getBoots() != null &&
												((Player) e.getEntity()).getInventory().getBoots().hasItemMeta() &&
												((Player) e.getEntity()).getInventory().getBoots().getItemMeta().hasLore() &&
												((Player) e.getEntity()).getInventory().getBoots().getItemMeta().getLore()
														.contains(RealName.EXPPROTECTOR.getEnchant().getName()))) {
											((Player) e.getEntity()).setExp(((Player) e.getEntity()).getExp() * 98);
											((Player) e.getDamager()).setExp(((Player) e.getDamager()).getExp() + ((Player) e.getEntity()).getExp() * 2);
										}
									}
								}
							} else if (s.equalsIgnoreCase(RealName.CHARGE.getEnchant().getName())) {
								int r = EnchantDrop.getRandomNumberFrom(1, 100);
								if (r > 85) {
									if (player.isSprinting()) {
										e.setDamage(e.getDamage() * 1.2);
									}
								}
							} /*else if (s.equalsIgnoreCase(CEnchant.DEMONSBLADE.getName())) {
								e.setDamage(e.getDamage() * 1.05);
							}*/ else if (s.equalsIgnoreCase(RealName.WITHERI.getEnchant().getName())) {
								int r = EnchantDrop.getRandomNumberFrom(1, 100);
								if (r > 85) {
									if (e.getEntity() instanceof Player) {
										((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0));
									}
								}
							} else if (s.equalsIgnoreCase(RealName.WITHERII.getEnchant().getName())) {
								int r = EnchantDrop.getRandomNumberFrom(1, 100);
								if (r > 85) {
									if (e.getEntity() instanceof Player) {
										((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 0));
									}
								}
							} else if (s.equalsIgnoreCase(RealName.FROZENBLADE.getEnchant().getName())) {
								int r = EnchantDrop.getRandomNumberFrom(1, 100);
								if (r > 85) {
									if (e.getEntity() instanceof Player) {
										((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
									}
								}
							} else if (s.equalsIgnoreCase(RealName.DIZZY.getEnchant().getName())) {
								if (e.getEntity() instanceof Player) {
									((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 0));
								}
							} else if (s.equalsIgnoreCase(RealName.HOSTILEDAMAGE.getEnchant().getName())) {
								if (e.getEntity() instanceof Monster) {
									e.setDamage(e.getDamage() * 2);
								}
							} else if (s.equalsIgnoreCase(RealName.PARALYZE.getEnchant().getName())) {
								if (e.getEntity() instanceof Player) {

									int r = EnchantDrop.getRandomNumberFrom(1, 100);
									if (r > 80) {
										((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
									}
								}
							} else if (s.equalsIgnoreCase(RealName.BATTLEROAR.getEnchant().getName())) {
								if (e.getEntity() instanceof Player) {
									int g = EnchantDrop.getRandomNumberFrom(1, 100);
									if (g <= 20) {
										int r = EnchantDrop.getRandomNumberFrom(1, 100);
										if (r <= 50) {
											if (((Player) e.getEntity()).hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
												((Player) e.getEntity()).removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
											((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 0));
										} else {
											if (((Player) e.getEntity()).hasPotionEffect(PotionEffectType.WITHER))
												((Player) e.getEntity()).removePotionEffect(PotionEffectType.WITHER);
											((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 0));
										}
									}
								}
							} else if (s.equalsIgnoreCase(RealName.MINERI.getEnchant().getName())) {

							} else if (s.equalsIgnoreCase(RealName.MINERII.getEnchant().getName())) {

							}
						}
					}
				}
			}
		}
	}

	private void moltenFunc(Entity damager, Entity person) {

		if (damager instanceof Arrow) {
			Arrow arrow = (Arrow) damager;
			if (arrow.getShooter() instanceof Skeleton) {
				Skeleton sk = (Skeleton) arrow.getShooter();
				sk.setFireTicks(100);

			} else if (arrow.getShooter() instanceof Player) {
				Player pl = (Player) arrow.getShooter();
				pl.setFireTicks(100);
			} else
				((Player) person).sendMessage(String.valueOf(((Arrow) damager).getShooter()));
		} else
			damager.setFireTicks(100);
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
