package model;

public class WeaponFactory {
	private Weapon weapon;
	private static Weapon build;

	public WeaponFactory(){
	}
	
	public static Weapon makeWeapon(Unit unit){
		int level;
		if (unit instanceof Hero ||
			unit instanceof Swordmaster ||
			unit instanceof Thief){
			build = new Sword(0, null);
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
			build = new Bow(0, null);
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
			build = new Lance(null, 0);
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
			build = new Axe(null, 0);
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
			build = new Elemental(null, 0);
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
			build = new Light(null, 0);
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
			build = new Dark(null, 0);
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
			weapon = new Sword(level, name);
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
		
		if (name == "Staff"){
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
		
		if (name == "Light Magic"){
			weapon = new Light(name, level);
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
			weapon = new Elemental(name, level);
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
			weapon = new Dark(name, level);
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
			weapon = new Lance(name, level);
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
