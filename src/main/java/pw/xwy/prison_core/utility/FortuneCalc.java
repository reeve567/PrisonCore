////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/24/18 6:47 PM                             /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (greys#0001) unless otherwise noted.           /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

package pw.xwy.prison_core.utility;

import org.bukkit.Material;

import java.util.Random;


public class FortuneCalc {
	
	/**
	 * Official drop calculator by: minecraft.gamepedia.com/Enchanting
	 *
	 * @param fortuneLevel level of enchant
	 * @param typeOfBlock  the type of block we are breaking
	 * @param returnValue  what we use to multiple by
	 * @return the number of blocks dropped.
	 */
	
	public static int numDroppedFromFortune(int fortuneLevel, Material typeOfBlock, int returnValue) {
		if (!(fortuneLevel > 3)) {
			if (typeOfBlock == Material.REDSTONE || typeOfBlock == Material.GOLD_BLOCK || typeOfBlock == Material.IRON_BLOCK || typeOfBlock == Material.DIAMOND_BLOCK || typeOfBlock == Material.EMERALD_BLOCK || typeOfBlock == Material.REDSTONE_ORE || typeOfBlock == Material.REDSTONE_BLOCK || typeOfBlock == Material.CARROT || typeOfBlock == Material.MELON || typeOfBlock == Material.MELON_BLOCK || typeOfBlock == Material.NETHER_WARTS || typeOfBlock == Material.POTATO || typeOfBlock == Material.SEEDS || typeOfBlock == Material.LONG_GRASS) {
				
				if (typeOfBlock == Material.REDSTONE) {
					for (int i = 0; i < fortuneLevel; i++) {
						returnValue++;
						if (returnValue == 4)
							break;
					}
				} else if (typeOfBlock == Material.MELON || typeOfBlock == Material.MELON_BLOCK) {
					for (int i = 0; i < fortuneLevel; i++) {
						returnValue++;
						if (returnValue == 9)
							break;
					}
				} else if (typeOfBlock == Material.LONG_GRASS) {
					for (int i = 0; i < fortuneLevel; i++)
						returnValue = returnValue + 2;
				} else {
					for (int i = 0; i < fortuneLevel; i++)
						returnValue++;
				}
			} else {
				Random rand = new Random();
				int result = rand.nextInt(100);
				if (fortuneLevel == 1) {
					int chance = 33;
					if (chance > result) {
						return returnValue * 2;
					}
				} else if (fortuneLevel == 2) {
					int chance = 50;
					if (chance > result) {
						if (result < 25) {
							returnValue = returnValue * 3;
						} else {
							returnValue = returnValue * 2;
						}
					}
				} else {
					int chance = 60;
					if (chance > result) {
						if (result <= 20) {
							returnValue = returnValue * 4;
						} else if (result > 20 && result <= 40) {
							returnValue = returnValue * 3;
						} else {
							returnValue = returnValue * 2;
						}
					}
				}
			}
			return returnValue;
		} else {
			Random ran = new Random();
			int j = ran.nextInt(fortuneLevel);
			
			if (j < 0)
				j = 0;
			
			return j + 1;
		}
	}
	
	/**
	 * Gravel to flint calculator found at: minecraft.gamepedia.com/Enchanting
	 *
	 * @param fortuneLevel
	 * @return gravel/flint
	 */
	public static Material specialGravelDrop(int fortuneLevel) {
		Material returnV = null;
		int baseGravelRate = 10;
		Random rand = new Random();
		int result = rand.nextInt(100);
		if (fortuneLevel == 1) {
			if (result < (baseGravelRate + 14)) {
				returnV = Material.FLINT;
			} else {
				returnV = Material.GRAVEL;
			}
		} else if (fortuneLevel == 2) {
			if (result < (baseGravelRate + 25)) {
				returnV = Material.FLINT;
			} else {
				returnV = Material.GRAVEL;
			}
		} else {
			returnV = Material.FLINT;
		}
		return returnV;
	}
}