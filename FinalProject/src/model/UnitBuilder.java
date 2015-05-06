package model;

import controller.GamePlay.Team;

public class UnitBuilder {

	public UnitBuilder(Unit build) {
		makeUnit(build);
	}

	public static Unit makeUnit(Unit build) {

		if (build instanceof Hero) {
			switch (build.getTeam()) {
			case PLAYER:
				build.setName("playerHero");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(30);
				build.setCurrentHp(30);
				build.setStrength(10);
				build.setSkill(14);
				build.setSpeed(10);
				build.setLuck(10);
				build.setDefense(10);
				build.setResistance(5);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Sword", 4));
				build.setMovement(6);
				((Hero) build).setSpriteObject();
				break;
			case COMPUTER:
				build.setName("General");
				build.setLevel(5);
				build.setMagic(0);
				build.setCurrentHp(30);
				build.setHp(30);
				build.setStrength(15);
				build.setSkill(15);
				build.setSpeed(8);
				build.setLuck(20);
				build.setDefense(15);
				build.setResistance(8);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Sword", 4));
				build.setMovement(5);
				((Hero) build).setSpriteObject();
				break;
			}
		}
		if (build instanceof Lancecaster) {
			switch (build.getTeam()) {
			case PLAYER:
				build.setName("playerLancecaster");
				build.setLevel(5);
				build.setMagic(0);
				build.setCurrentHp(30);
				build.setHp(30);
				build.setStrength(10);
				build.setSkill(14);
				build.setSpeed(10);
				build.setLuck(10);
				build.setDefense(10);
				build.setResistance(5);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Lance", -1));
				build.setMovement(6);
				((Lancecaster) build).setSpriteObject();
				break;
			case COMPUTER:
				build.setName("Soldier");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(30);
				build.setCurrentHp(30);
				build.setStrength(15);
				build.setSkill(15);
				build.setSpeed(8);
				build.setLuck(20);
				build.setDefense(15);
				build.setResistance(8);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Lance", -1));
				build.setMovement(5);
				((Lancecaster) build).setSpriteObject();
				break;
			}
		}
		if (build instanceof Swordmaster) {
			switch (build.getTeam()) {
			case PLAYER:
				build.setName("playerSwordmaster");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(30);
				build.setCurrentHp(30);
				build.setStrength(10);
				build.setSkill(14);
				build.setSpeed(10);
				build.setLuck(10);
				build.setDefense(10);
				build.setResistance(5);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Sword", -1));
				build.setMovement(6);
				((Swordmaster) build).setSpriteObject();
				break;
			case COMPUTER:
				build.setName("Swordmaster");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(30);
				build.setCurrentHp(30);
				build.setStrength(15);
				build.setSkill(15);
				build.setSpeed(8);
				build.setLuck(20);
				build.setDefense(15);
				build.setResistance(8);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Sword", -1));
				build.setMovement(5);
				((Swordmaster) build).setSpriteObject();
				break;
			}
		}
		if (build instanceof Axereaver) {
			switch (build.getTeam()) {
			case PLAYER:
				build.setName("playerAxereaver");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(30);
				build.setCurrentHp(30);
				build.setStrength(10);
				build.setSkill(14);
				build.setSpeed(10);
				build.setLuck(10);
				build.setDefense(10);
				build.setResistance(5);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Axe", -1));
				build.setMovement(6);
				((Axereaver) build).setSpriteObject();
				break;
			case COMPUTER:
				build.setName("Warrior");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(30);
				build.setCurrentHp(30);
				build.setStrength(15);
				build.setSkill(15);
				build.setSpeed(8);
				build.setLuck(20);
				build.setDefense(15);
				build.setResistance(8);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Axe", -1));
				build.setMovement(5);
				((Axereaver) build).setSpriteObject();
				break;
			}
			}
			if (build instanceof Thief) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("playerThief");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(10);
					build.setSkill(14);
					build.setSpeed(10);
					build.setLuck(10);
					build.setDefense(10);
					build.setResistance(5);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Knife", -1));
					build.setMovement(6);
					((Thief) build).setSpriteObject();
					break;
				case COMPUTER:
					build.setName("Assassin");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(15);
					build.setSkill(15);
					build.setSpeed(8);
					build.setLuck(20);
					build.setDefense(15);
					build.setResistance(8);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Knife", -1));
					build.setMovement(5);
					((Thief) build).setSpriteObject();
					break;
				}
			}
			if (build instanceof Marksman) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("playerMarksman");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(10);
					build.setSkill(14);
					build.setSpeed(10);
					build.setLuck(10);
					build.setDefense(10);
					build.setResistance(5);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Bow", -1));
					build.setMovement(6);
					((Marksman) build).setSpriteObject();
					break;
				case COMPUTER:
					build.setName("Sniper");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(15);
					build.setSkill(15);
					build.setSpeed(8);
					build.setLuck(20);
					build.setDefense(15);
					build.setResistance(8);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Bow", -1));
					build.setMovement(5);
					((Marksman) build).setSpriteObject();
					break;
				}
			}
			if (build instanceof Druid) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("playerDruid");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(10);
					build.setSkill(14);
					build.setSpeed(10);
					build.setLuck(10);
					build.setDefense(10);
					build.setResistance(5);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Anima Magic", -1));
					build.setMovement(6);
					((Druid) build).setSpriteObject();
					break;
				case COMPUTER:
					build.setName("Dark Druid");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(15);
					build.setSkill(15);
					build.setSpeed(8);
					build.setLuck(20);
					build.setDefense(15);
					build.setResistance(8);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Anima Magic", -1));
					build.setMovement(5);
					((Druid) build).setSpriteObject();
					break;
				}
			}
			if (build instanceof Saint) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("playerSaint");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(10);
					build.setSkill(14);
					build.setSpeed(10);
					build.setLuck(10);
					build.setDefense(10);
					build.setResistance(5);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Light Magic", -1));
					build.setMovement(6);
					((Saint) build).setSpriteObject();
					break;
				case COMPUTER:
					build.setName("Saint");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(15);
					build.setSkill(15);
					build.setSpeed(8);
					build.setLuck(20);
					build.setDefense(15);
					build.setResistance(8);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Light Magic", -1));
					build.setMovement(5);
					((Saint) build).setSpriteObject();
					break;
				}
			}
			if (build instanceof Sorcerer) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("playerSorcerer");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(10);
					build.setSkill(14);
					build.setSpeed(10);
					build.setLuck(10);
					build.setDefense(10);
					build.setResistance(5);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Dark Magic", -1));
					build.setMovement(6);
					((Sorcerer) build).setSpriteObject();
					break;
				case COMPUTER:
					build.setName("Dark Sage");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(15);
					build.setSkill(15);
					build.setSpeed(8);
					build.setLuck(20);
					build.setDefense(15);
					build.setResistance(8);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Dark Magic", -1));
					build.setMovement(5);
					((Sorcerer) build).setSpriteObject();
					break;
				}
			}
		return build;
	}
}
