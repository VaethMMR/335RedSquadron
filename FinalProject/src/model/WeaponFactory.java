package model;

import java.util.Random;

public class WeaponFactory {
	private Weapon weapon;

	public WeaponFactory() {
	}

	public Weapon makeWeapon(String name, int level) {
		if (level == -1) {
			Random generator = new Random();
			level = generator.nextInt(3) + 1;
		}
		if (name == "Sword") {
			weapon = new Weapon(name, level);
			weapon.setRange(1);
			weapon.setMight(2 + level);
			weapon.setAccuracy(2 + level);
			weapon.setCritical(2 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(2);
			return weapon;
		}

		if (name == "Bow") {
			weapon = new Weapon(name, level);
			weapon.setRange(4);
			weapon.setMight(1 + level);
			weapon.setAccuracy(3 + level);
			weapon.setCritical(1 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(0);
			return weapon;
		}

		if (name == "Staff") {
			weapon = new Weapon(name, level);
			weapon.setRange(2);
			weapon.setMight(0);
			weapon.setAccuracy(2 + level);
			weapon.setCritical(3 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(3 + level);
			weapon.setWeight(0);
			return weapon;
		}

		if (name == "Light Magic") {
			weapon = new Weapon(name, level);
			weapon.setRange(2);
			weapon.setMight(0);
			weapon.setAccuracy(2 + level);
			weapon.setCritical(3 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(3 + level);
			weapon.setWeight(0);
			return weapon;
		}

		if (name == "Anima Magic") {
			weapon = new Weapon(name, level);
			weapon.setRange(2);
			weapon.setMight(0);
			weapon.setAccuracy(2 + level);
			weapon.setCritical(3 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(3 + level);
			weapon.setWeight(0);
			return weapon;
		}

		if (name == "Dark Magic") {
			weapon = new Weapon(name, level);
			weapon.setRange(2);
			weapon.setMight(0);
			weapon.setAccuracy(2 + level);
			weapon.setCritical(3 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(3 + level);
			weapon.setWeight(0);
			return weapon;
		}
		if (name == "Axe"){
			weapon = new Weapon(name, level);
			weapon.setRange(1);
			weapon.setMight(3 + level);
			weapon.setAccuracy(1 + level);
			weapon.setCritical(2 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			return weapon;
		}

		if (name == "Lance") {
			weapon = new Weapon(name, level);
			weapon.setRange(1);
			weapon.setMight(3 + level);
			weapon.setAccuracy(1 + level);
			weapon.setCritical(2 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(5);
			return weapon;
		}
		
		if (name == "Knife") {
			weapon = new Weapon(name, level);
			weapon.setRange(1);
			weapon.setMight(1 + level);
			weapon.setAccuracy(1 + level);
			weapon.setCritical(2 + level);
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
