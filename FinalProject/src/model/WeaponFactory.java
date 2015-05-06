package model;

public class WeaponFactory {
	private Weapon weapon;
	private static Weapon build;

	public WeaponFactory() {
	}

	public static Weapon makeWeapon(Unit unit) {
		int level;
		if (unit instanceof Hero || unit instanceof Swordmaster
				|| unit instanceof Thief) {
			build = new Sword(0, null);
			if (unit == Hero.getHero()) {
				build.setLevel(8);
				build.setName("Ragnell");
			}
			if(unit == General.getGeneral()){
				build.setLevel(10);
				build.setName("Alondite");
			}
			if (unit instanceof Swordmaster) {
				build.setLevel(3);
				build.setName("Sleek Sword");
			} else {
				build.setLevel(1);
				build.setName("Short Sword");
			}
			level = build.getLevel();
			build.setRange(1);
			build.setMight(5 + level);
			build.setAccuracy(90 + level);
			build.setCritical(5 + level);
			build.setCost(5 * level);
			build.setMagic(0);
			build.setWeight(1);
			return build;
		}

		if (unit instanceof Marksman) {
			build = new Bow(0, null);
			build.setLevel(2);
			build.setName("Bow");
			level = build.getLevel();
			build.setRange(2);
			build.setMight(3);
			build.setAccuracy(95);
			build.setCritical(15);
			build.setCost(0);
			build.setMagic(0);
			build.setWeight(0);
			return build;
		}

		if (unit instanceof Lancecaster) {
			build = new Lance(null, 0);
			build.setLevel(4);
			build.setName("Lance");
			level = build.getLevel();
			build.setRange(1);
			build.setMight(5 + level);
			build.setAccuracy(85);
			build.setCritical(5);
			build.setCost(5 * level);
			build.setMagic(0);
			build.setWeight(5);
			return build;
		}

		if (unit instanceof Axereaver) {
			build = new Axe(null, 0);
			build.setLevel(7);
			build.setName("Axe");
			level = build.getLevel();
			build.setRange(1);
			build.setMight(5 + level);
			build.setAccuracy(75);
			build.setCritical(0);
			build.setCost(5 * level);
			build.setMagic(0);
			build.setWeight(5);
			return build;
		}

		if (unit instanceof Sorcerer) {
			build = new Elemental(null, 0);
			build.setLevel(3);
			build.setName("Anima Magic");
			level = build.getLevel();
			build.setRange(2);
			build.setMight(0);
			build.setAccuracy(80);
			build.setCritical(5);
			build.setCost(5 * level);
			build.setMagic(5 + level);
			build.setWeight(0);
			return build;
		}

		if (unit instanceof Saint) {
			build = new Light(null, 0);
			build.setLevel(1);
			build.setName("Light Magic");
			level = build.getLevel();
			build.setRange(2);
			build.setMight(0);
			build.setAccuracy(90);
			build.setCritical(5 + level);
			build.setCost(5 * level);
			build.setMagic(3 + level);
			build.setWeight(0);
			return build;
		}

		if (unit instanceof Druid) {
			build = new Dark(null, 0);
			build.setLevel(8);
			build.setName("Dark Magic");
			level = build.getLevel();
			build.setRange(2);
			build.setMight(0);
			build.setAccuracy(80);
			build.setCritical(15 + level);
			build.setCost(5 * level);
			build.setMagic(10 + level);
			build.setWeight(5);
			return build;
		}

		else {
			return null;
		}
	}

	public Weapon makeWeapon(String name, int level) {
		if (name == "Sword") {
			weapon = new Sword(level, name);
			weapon.setRange(1);
			weapon.setMight(2 + level);
			weapon.setAccuracy(80 + level);
			weapon.setCritical(2 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(2);
			return weapon;
		}
		
		if( name == "Axe")
			weapon.setRange(1);
			weapon.setMight(5 + level);
			weapon.setAccuracy(75 + level);
			weapon.setCritical(0 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(5);

		if (name == "Bow") {
			weapon = new Bow(level, name);
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
			weapon = new Staff(name, level);
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
			weapon = new Light(name, level);
			weapon.setRange(2);
			weapon.setMight(0);
			weapon.setAccuracy(90 + level);
			weapon.setCritical(5 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(3 + level);
			weapon.setWeight(0);
			return weapon;
		}

		if (name == "Anima Magic") {
			weapon = new Elemental(name, level);
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
			weapon = new Dark(name, level);
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
			weapon = new Lance(name, level);
			weapon.setRange(1);
			weapon.setMight(5 + level);
			weapon.setAccuracy(85 + level);
			weapon.setCritical(5 + level);
			weapon.setCost(5 * level);
			weapon.setMagic(0);
			weapon.setWeight(5);
			return weapon;
		}

		else {
			return null;
		}
	}

}
