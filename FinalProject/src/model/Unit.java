package model;

/* creates a unit with all stats associated
 I added a tostring method
 Author: Connor Broderick
 */
import java.util.Random;
import java.io.*;

import javax.swing.JOptionPane;

import objects.SpriteObject;
import sprites.Sprite;
import controller.GamePlay.Team;

public abstract class Unit {
	private String name;
	private int level;
	private int hp;
	private int currentHp; // since current hp can be different than max hp
	private int movement;
	private int strength;
	private int magic;
	private int skill;
	private int speed;
	private int luck;
	private int defense;
	private int resistance;
	private boolean hasMoved;
	private boolean hasAttacked;
	private Team team;

	// this constructor will take a lot of arguments, that is because unit is in
	// charge of creating all the different units, all of which have different
	// starting
	// stats. It is simply letting the main controller create whatever unit you
	// want.
	public Unit(Team team) {
		this.team = team;
	}

	// just return true until we get map integration
	public boolean move(int[] x) {
		return true;
	}

	/*
	 * not sure how to implement range vs melee attack, basically check the
	 * grid, and if it is a ranged attack the second half of the code in this
	 * method would not be implemented, since there is no attack back. Only
	 * thing missing now is that, and tome type weapons where it is resis and
	 * magic used.
	 */
	public boolean attack(Unit other, int distance) {
		Weapon uWeapon = this.getWeapon();
		Weapon enemyWeapon = other.getWeapon();
		int bonusHit = weaponsTri(uWeapon, other.getWeapon())[0];
		int bonusDmg = weaponsTri(uWeapon, other.getWeapon())[1];
		int range = this.getWeapon().getRange();
		int damage = getDamage(this, other) + bonusDmg;
		int def = getDef(this, other);
		int critical = damage * 3;
		int critChance = 0;
		int nextrandomInt = 0;
		int hitchance = ((skill * 2) + luck + uWeapon.getAccuracy())
				- ((other.speed * 2) + other.luck) + bonusHit;
		// Units that are out of range can not be attacked
		// SpellCasters have at least 2 ranges
		// All other units are strictly limited to 1 range
		// i.e Melee can only attack from 1 distance and have a range of 1
		// but Marksmen can onlt attack from 2 away but have a range of 2
		if (distance > range)
			return false;
		if (damage < 0)
			damage = 0;
		if (damage > 100)
			damage = 100;
		if (currentHp < 0)
			currentHp = 0;
		if (currentHp > 100)
			currentHp = 100;
		if (critical < 0)
			critical = 0;
		if (critical > 100)
			critical = 100;
		if (hitchance < 0)
			hitchance = 0;
		if (hitchance > 100)
			hitchance = 100;
		JOptionPane.showMessageDialog(null, this.name + " attacks "
				+ other.name + "\nAcc: " + hitchance + "\nDmg: " + damage
				+ "\nCrit: " + critical, "Attack Phase",
				JOptionPane.INFORMATION_MESSAGE);

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(hitchance);
		if (randomInt > 100 - hitchance) {// hit confirmed
			critChance = ((skill / 2) + uWeapon.getCritical()) - other.luck;
			nextrandomInt = randomGenerator.nextInt();
			if (nextrandomInt > 100 - critChance) {// if it is a crit, then
													// damage dealt is
													// multiplied by 3
				damage = critical;
				JOptionPane.showMessageDialog(null, "Critical Hit!\nDamage 3x",
						"Attack Phase", JOptionPane.INFORMATION_MESSAGE);
			}
			other.currentHp = other.currentHp - damage; // deal the damage
			JOptionPane.showMessageDialog(null, other.name + " is hit with "
					+ damage + " damge.\nHP : " + other.currentHp,
					"Attack Phase", JOptionPane.INFORMATION_MESSAGE);
			if (other.currentHp <= 0) {// confirmed dead
				other.die(other);
				JOptionPane.showMessageDialog(null, "End attack phase\n"
						+ this.name + " HP: " + this.currentHp + "\n"
						+ other.name + ": Dead", "Attack Phase",
						JOptionPane.INFORMATION_MESSAGE);
				return true;// enemy killed
			}
		} else
			JOptionPane.showMessageDialog(null, "Attack missed." + "\nHP: "
					+ other.currentHp, "Attack Phase",
					JOptionPane.INFORMATION_MESSAGE);
		// Units out of range can not counterattack
		if (other instanceof SpellCaster) {
			if (distance > other.getWeapon().getRange()) {
				JOptionPane.showMessageDialog(null, "End attack phase\n"
						+ this.name + " HP: " + this.currentHp + "\n"
						+ other.name + " HP: " + other.currentHp,
						"Attack Phase", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {
			if (distance != other.getWeapon().getRange()) {
				JOptionPane.showMessageDialog(null, "End attack phase\n"
						+ this.name + " HP: " + this.currentHp + "\n"
						+ other.name + " HP: " + other.currentHp,
						"Attack Phase", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		return other.counter(this, other, uWeapon, enemyWeapon, false);
	}

	private boolean counter(Unit u, Unit other, Weapon uWeapon,
			Weapon otherWeapon, boolean second) {
		// Similar to attack but with a special flag to exit the recursion after
		// two counter calls
		int damage = getDamage(other, u);
		int critical = damage * 3;
		int attackSpeed = other.getSpeed() - otherWeapon.getWeight();
		int otherSpeed = u.getSpeed() - uWeapon.getWeight();
		int critChance = 0;
		int nextrandomInt = 0;
		int hitchance = ((skill * 2) + luck + otherWeapon.getAccuracy())
				- ((u.speed * 2) + u.luck);// weapon triangle would come into
											// affect here as well
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt();
		JOptionPane.showMessageDialog(null, other.name + " attacks " + u.name
				+ "\nAcc: " + hitchance + "\nDmg: " + damage + "\nCrit: "
				+ critical, "Attack Phase", JOptionPane.INFORMATION_MESSAGE);

		if (randomInt > 100 - hitchance) {// hit confirmed
			critChance = ((skill / 2) + otherWeapon.getCritical()) - other.luck;
			nextrandomInt = randomGenerator.nextInt();
			if (nextrandomInt > 100 - critChance) {// if it is a crit, then
													// damage dealt is
													// multiplied by 3
				damage = critical;
				JOptionPane.showMessageDialog(null, "Critical Hit!\nDamage 3x",
						"Attack Phase", JOptionPane.INFORMATION_MESSAGE);
			}
			u.currentHp = u.currentHp - damage; // deal the damage
			JOptionPane.showMessageDialog(null, u.name + " is hit with "
					+ damage + " damage.\nHP : " + u.currentHp, "Attack Phase",
					JOptionPane.INFORMATION_MESSAGE);
			if (u.currentHp <= 0) {// confirmed dead
				u.die(u);
				JOptionPane.showMessageDialog(null, "End attack phase\n"
						+ other.name + " HP: " + other.currentHp + "\n"
						+ u.name + ": Dead", "Attack Phase",
						JOptionPane.INFORMATION_MESSAGE);
				if (u == this)
					return false; // ally dead
				else
					return true; // enemy dead
			}
		} else
			JOptionPane.showMessageDialog(null, "Attack missed.\n" + other.name
					+ " HP: " + other.currentHp, "Attack Phase",
					JOptionPane.INFORMATION_MESSAGE);

		// The Observers are notified here

		if (second == true) {
			// This is the double attack phase
			JOptionPane.showMessageDialog(null, "End attack phase\n" + u.name
					+ " HP: " + u.currentHp + "\n" + other.name + " HP: "
					+ other.currentHp, "Attack Phase",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		// There are two cases: If a unit initiated attack but fast enough to
		// doule attack
		// then the unit will counterattack after this counterattack phase
		// else the other unit will have two consecutive counters, this and the
		// next one
		if (attackSpeed - otherSpeed > 3) {
			if (u == this)
				return counter(other, u, otherWeapon, uWeapon, true);
			else
				return counter(u, other, uWeapon, otherWeapon, true);
		}
		// Single attack counter attack phase
		JOptionPane.showMessageDialog(null, "End attack phase\n" + u.name
				+ " HP: " + u.currentHp + "\n" + other.name + " HP: "
				+ other.currentHp, "Attack Phase",
				JOptionPane.INFORMATION_MESSAGE);
		return false;
	}// end of attack method

	private int getDamage(Unit u, Unit other) {
		if (u instanceof SpellCaster)
			return u.getWeapon().getMagic() + u.getMagic()
					- other.getResistance();
		else
			return u.getWeapon().getMight() + u.getStrength()
					- other.getDefense();
	}

	private int getDef(Unit u, Unit other) {
		if (u instanceof SpellCaster)
			return other.getResistance();
		else
			return other.getDefense();
	}

	private int[] weaponsTri(Weapon uWeapon, Weapon otherWeapon) {
		int[] bonus = { 0, 0 };
		if (uWeapon.getName().contains("Sword")
				|| otherWeapon.getName().contains("Knife"))
			if (otherWeapon.getName().contains("Lance")) {
				bonus[0] = -10;
				bonus[1] = -5;
			}
		if (otherWeapon.getName().contains("Axe")) {
			bonus[0] = 10;
			bonus[1] = 5;
		}
		if (uWeapon.getName().contains("Lance"))
			if (otherWeapon.getName().contains("Axe")) {
				bonus[0] = -10;
				bonus[1] = -5;
			}
		if (otherWeapon.getName().contains("Sword")
				|| otherWeapon.getName().contains("Knife")) {
			bonus[0] = 10;
			bonus[1] = 5;
		}
		if (uWeapon.getName().contains("Axe"))
			if (otherWeapon.getName().contains("Sword")
					|| otherWeapon.getName().contains("Knife")) {
				bonus[0] = -10;
				bonus[1] = -5;
			}
		if (otherWeapon.getName().contains("Lance")) {
			bonus[0] = 10;
			bonus[1] = 5;
		}
		if (uWeapon.getName().contains("Anima Magic"))
			if (otherWeapon.getName().contains("Dark Magic")) {
				bonus[0] = -10;
				bonus[1] = -5;
			}
		if (otherWeapon.getName().contains("Light Magic")) {
			bonus[0] = 10;
			bonus[1] = 5;
		}
		if (uWeapon.getName().contains("Light Magic"))
			if (otherWeapon.getName().contains("Anima Magic")) {
				bonus[0] = -10;
				bonus[1] = -5;
			}
		if (otherWeapon.getName().contains("Dark Magic")) {
			bonus[0] = 10;
			bonus[1] = 5;
		}
		if (uWeapon.getName().contains("Dark Magic"))
			if (otherWeapon.getName().contains("Light Magic")) {
				bonus[0] = -10;
				bonus[1] = -5;
			}
		if (otherWeapon.getName().contains("Anima Magic")) {
			bonus[0] = 10;
			bonus[1] = 5;
		}
		return bonus;
	}

	public String toString() {
		return name + "\n" + getWeapon() + "  " + "Level: " + level
				+ "\nhealth: " + hp + "\n" + "strength: " + strength
				+ "\nmagic: " + magic + "\nskill: " + skill + "\nspeed: "
				+ speed + "\nluck: " + luck + "\ndefense: " + defense
				+ "\nresistance: " + resistance;
	}

	public void wait1() {// apparently wait overrides wait in object so I have
							// changed it to wait1

	}

	public void die(Unit dead) {
		// Play soliloquy
		JOptionPane.showMessageDialog(null, "\n...Damn\n-" + dead.name,
				"Last Words", JOptionPane.INFORMATION_MESSAGE);
	}

	// level up will take growth rates, then use random number generator and
	// adjust stats as needed. will return 1 always.
	public int levelup(int hp1, int str, int mag, int sk, int spd, int luk,
			int def, int res) {
		Random randomGenerator = new Random();
		int randomnum = randomGenerator.nextInt();
		if (randomnum >= 100 - hp1)
			hp = hp + 1;
		randomnum = randomGenerator.nextInt();
		if (randomnum >= 100 - str)
			strength = strength + 1;
		randomnum = randomGenerator.nextInt();
		if (randomnum >= 100 - mag)
			magic = magic + 1;
		randomnum = randomGenerator.nextInt();
		if (randomnum >= 100 - sk)
			skill = skill + 1;
		randomnum = randomGenerator.nextInt();
		if (randomnum >= 100 - spd)
			speed = speed + 1;
		randomnum = randomGenerator.nextInt();
		if (randomnum >= 100 - luk)
			luck = luck + 1;
		randomnum = randomGenerator.nextInt();
		if (randomnum >= 100 - def)
			defense = defense + 1;
		randomnum = randomGenerator.nextInt();
		if (randomnum >= 100 - res)
			resistance = resistance + 1;
		level = level + 1;
		return 1;
	}

	public void useItem(Consumable booster) {
		booster.decreaseRemainingUses();
		if (booster.getName() == "Vulnary") {// then it is a 10 heal!
			currentHp += 10;
			if (currentHp >= hp) {// cant heal more than max hp
				currentHp = hp;
			}
		}// end of heal item

	}

	public void trade(Unit other) {
		// in addition to item it will need a unit argument to know who it is
		// trading with.
	}

	/* All the getter and setter methods are below for each stat */

	public void setMoved(boolean moved) {
		hasMoved = moved;
	}

	public boolean hasMoved() {
		return hasMoved;
	}
	
	public void setAttacked(boolean attacked) {
		hasAttacked = attacked;
	}
	
	public boolean hasAttacked() {
		return hasAttacked;
	}

	public String getName() {
		return name;
	}

	public abstract void setWeapon(Weapon weapon);

	public abstract Weapon getWeapon();

	public int getLevel() {
		return level;
	}

	public void setLevel(int n) {
		level = n;
	}

	public void setHp(int n) {
		currentHp = n;
	}

	public int getHp() {
		return hp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public int getMovement() {
		return movement;
	}

	public void setStrength(int n) {
		strength = n;
	}

	public int getStrength() {
		return strength;
	}

	public void setMagic(int n) {
		magic = n;
	}

	public int getMagic() {
		return magic;
	}

	public void setSkill(int n) {
		skill = n;
	}

	public int getSkill() {
		return skill;
	}

	public void setSpeed(int n) {
		speed = n;
	}

	public int getSpeed() {
		return speed;
	}

	public void setLuck(int n) {
		luck = n;
	}

	public int getLuck() {
		return luck;
	}

	public void setDefense(int n) {
		defense = n;
	}

	public int getDefense() {
		return defense;
	}

	public void setMovement(int n) {
		movement = n;
	}

	public void setResistance(int n) {
		resistance = n;
	}

	public int getResistance() {
		return resistance;
	}

	public Team getTeam() {
		return team;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCurrentHp(int n) {
		this.currentHp = n;
	}

	public int getAttackRange() {
		return movement + this.getWeapon().getRange();
	}

	public abstract void setSpriteObject(int coordinates, int coordinates2);

	public abstract SpriteObject getSpriteObject();
}