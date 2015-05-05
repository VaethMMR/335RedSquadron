package model;

public class WeaponFactory {
	private Weapon weapon;
	
	public WeaponFactory(Weapon build){
	}
	
	public static Weapon makeWeapon(Unit unit){
		Weapon build = new Weapon(null, 0);
		int level;
		if (unit instanceof Hero ||
			unit instanceof Swordmaster ||
			unit instanceof Thief){
			if(unit instanceof Hero){
				build.setLevel(8);
				build.setName("Great Sword");
			}
			if(unit instanceof Swordmaster){
				build.setLevel(3);
				build.setName("Sleek Sword");
			}
			else {
				build.setLevel(1);
				build.setName("Short Sword");
			}
			level = build.getLevel();
			build.setRange(1);
			build.setMight(2 + level);
			build.setAccuracy(2 + level);
			build.setCritical(2 + level);
			build.setCost(5 * level);
			build.setMagic(0);
			build.setWeight(1);
			return build;
		}

		if (unit instanceof Marksman){
			build.setLevel(2);
			build.setName("Bow");
			level = build.getLevel();
			build.setRange(2);
			build.setMight(1 + level);
			build.setAccuracy(3 + level);
			build.setCritical(1 + level);
			build.setCost(5 * level);
			build.setMagic(0);
			build.setWeight(0);
			return build;
		}

		if(unit instanceof Lancecaster){
			build.setLevel(4);
			build.setName("Lance");
			level = build.getLevel();
			build.setRange(1);
			build.setMight(1 + level);
			build.setAccuracy(3 + level);
			build.setCritical(1 + level);
			build.setCost(5 * level);
			build.setMagic(0);
			build.setWeight(5);
			return build;
		}
		
		if (unit instanceof Axereaver){
			build.setLevel(5);
			build.setName("Axe");
			level = build.getLevel();
			build.setRange(1);
			build.setMight(3 + level);
			build.setAccuracy(1 + level);
			build.setCritical(2 + level);
			build.setCost(5 * level);
			build.setMagic(0);
			build.setWeight(5);
			return build;
		}
		
		if (unit instanceof Sorcerer) {
			build.setLevel(3);
			build.setName("Anima Magic");
			level = build.getLevel();
			build.setRange(2);
			build.setMight(0);
			build.setAccuracy(1 + level);
			build.setCritical(2 + level);
			build.setCost(5 * level);
			build.setMagic(5 + level);
			build.setWeight(0);
			return build;
		}

		if (unit instanceof Saint) {
			build.setLevel(1);
			build.setName("Light Magic");
			level = build.getLevel();
			build.setRange(2);
			build.setMight(0);
			build.setAccuracy(1 + level);
			build.setCritical(2 + level);
			build.setCost(5 * level);
			build.setMagic(3 + level);
			build.setWeight(0);
			return build;
		}

		if (unit instanceof Druid) {
			build.setLevel(8);
			build.setName("Dark Magic");
			level = build.getLevel();
			build.setRange(2);
			build.setMight(0);
			build.setAccuracy(1 + level);
			build.setCritical(2 + level);
			build.setCost(5 * level);
			build.setMagic(10 + level);
			build.setWeight(5);
			return build;
		}

		else {
			return null;
		}
	}

	public Weapon makeWeapon(String name, int level){
		if (name == "Sword"){
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

		if (name == "Bow"){
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
		
		if (name == "Staff"){
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
		
		if (name == "Light Magic"){
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
		
		if (name == "Anima Magic"){
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
		
		if (name == "Dark Magic"){
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
		
		if (name == "Lance"){
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
		
		else{
			return null;
		}
	}
	
}
