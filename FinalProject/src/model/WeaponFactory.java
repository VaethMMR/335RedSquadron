package model;

import java.util.Random;
import java.io.Serializable;

public class WeaponFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5255085810997770068L;
	private Weapon weapon;

	public WeaponFactory() {
	}

	/**
	 * This method builds a weapon with different effects based on its name
	 * 
	 * @param name
	 *            The name of the weapon
	 * @param level
	 *            The level of the weapon
	 * @return weapon
	 * 			  returns the created weapon
	 */
	public Weapon makeWeapon(String name, int level) {
		if (level == -1) {
			Random generator = new Random();
			level = generator.nextInt(3) + 1;
		}
		if (name == "Sword") {
			weapon = new Weapon(name, level);
			weapon.setRange(1);
			weapon.setMight(8 + level);
			weapon.setAccuracy(90 + level);
			weapon.setCritical(10 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(2);
			return weapon;
		}

		if (name == "Axe") {
			weapon = new Weapon(name, level);
			weapon.setRange(1);
			weapon.setMight(10 + level);
			weapon.setAccuracy(75 + level);
			weapon.setCritical(5 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(2);
			return weapon;
		}

		if (name == "Bow") {
			weapon = new Weapon(name, level);
			weapon.setRange(2);
			weapon.setMight(1 + level);
			weapon.setAccuracy(95 + level);
			weapon.setCritical(20 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(0);
			return weapon;
		}

		if (name == "Staff") {
			weapon = new Weapon(name, level);
			weapon.setRange(2);
			weapon.setMight(0);
			weapon.setAccuracy(100 + level);
			weapon.setCritical(0 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(10 + level);
			weapon.setWeight(0);
			return weapon;
		}

		if (name == "Light Magic") {
			weapon = new Weapon(name, level);
			weapon.setRange(2);
			weapon.setMight(0);
			weapon.setAccuracy(90 + level);
			weapon.setCritical(5 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(2 + level);
			weapon.setWeight(0);
			return weapon;
		}

		if (name == "Anima Magic") {
			weapon = new Weapon(name, level);
			weapon.setRange(2);
			weapon.setMight(0);
			weapon.setAccuracy(80 + level);
			weapon.setCritical(5);
			weapon.setCost(5 * level);
			weapon.setMagic(5 + level);
			weapon.setWeight(0);
			return weapon;
		}

		if (name == "Dark Magic") {
			weapon = new Weapon(name, level);
			weapon.setRange(2);
			weapon.setMight(0);
			weapon.setAccuracy(80 + level);
			weapon.setCritical(15 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(10 + level);
			weapon.setWeight(5);
			return weapon;
		}
		if (name == "Lance") {
			weapon = new Weapon(name, level);
			weapon.setRange(1);
			weapon.setMight(9 + level);
			weapon.setAccuracy(85 + level);
			weapon.setCritical(5 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(5);
			return weapon;
		}
		if (name == "Knife") {
			weapon = new Weapon(name, level);
			weapon.setRange(1);
			weapon.setMight(2 + level);
			weapon.setAccuracy(100 + level);
			weapon.setCritical(15 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(1);
			return weapon;
		}

		else {
			return null;
		}

	}

}
