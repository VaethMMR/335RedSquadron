package model;

import controller.GamePlay.Team;

public class UnitBuilder {

	public UnitBuilder(Unit build) {
		makeUnit(build);
	}

	public static Unit makeUnit(Unit build) {
		if (build instanceof Commander) {
			if (build == Hero.getHero()) {
				Hero.getHero().setName("Hero");
				Hero.getHero().setLevel(5);
				Hero.getHero().setMagic(0);
				Hero.getHero().setHp(35);
				Hero.getHero().setCurrentHp(30);
				Hero.getHero().setStrength(13);
				Hero.getHero().setSkill(14);
				Hero.getHero().setSpeed(15);
				Hero.getHero().setLuck(10);
				Hero.getHero().setDefense(10);
				Hero.getHero().setResistance(5);
				Hero.getHero().setMoved(false);
				Hero.getHero().setWeapon(new WeaponFactory().makeWeapon("Sword", 12));
				Hero.getHero().getWeapon().setName("Ragnell");
				Hero.getHero().getWeapon().setLevel(12);
				Hero.getHero().setMovement(6);
				Hero.getHero().setSpriteObject(0,0);
			}
			else{
				General.getGeneral().setName("General");
				General.getGeneral().setLevel(10);
				General.getGeneral().setMagic(0);
				General.getGeneral().setCurrentHp(60);
				General.getGeneral().setHp(60);
				General.getGeneral().setStrength(15);
				General.getGeneral().setSkill(15);
				General.getGeneral().setSpeed(7);
				General.getGeneral().setLuck(20);
				General.getGeneral().setDefense(15);
				General.getGeneral().setResistance(8);
				General.getGeneral().setMoved(false);
				General.getGeneral().setWeapon(new WeaponFactory().makeWeapon("Sword", 15));
				General.getGeneral().getWeapon().setName("Alondite");
				General.getGeneral().getWeapon().setLevel(15);
				General.getGeneral().setMovement(5);
				General.getGeneral().setSpriteObject(0,0);
			}
			}
		if (build instanceof Lancecaster) {
			switch (build.getTeam()) {
			case PLAYER:
				build.setName("Lancecaster");
				build.setLevel(5);
				build.setMagic(0);
				build.setCurrentHp(25);
				build.setHp(25);
				build.setStrength(8);
				build.setSkill(12);
				build.setSpeed(8);
				build.setLuck(10);
				build.setDefense(12);
				build.setResistance(3);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Lance", 3));
				build.setMovement(6);
				((Lancecaster) build).setSpriteObject(0,0);
				break;
			case COMPUTER:
				build.setName("Soldier");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(30);
				build.setCurrentHp(30);
				build.setStrength(10);
				build.setSkill(10);
				build.setSpeed(6);
				build.setLuck(20);
				build.setDefense(10);
				build.setResistance(2);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Lance", 3));
				build.setMovement(6);
				((Lancecaster) build).setSpriteObject(0,0);
				break;
			}
		}
		if (build instanceof Swordmaster) {
			switch (build.getTeam()) {
			case PLAYER:
				build.setName("Trueblade");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(30);
				build.setCurrentHp(30);
				build.setStrength(10);
				build.setSkill(17);
				build.setSpeed(15);
				build.setLuck(20);
				build.setDefense(8);
				build.setResistance(4);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Sword", 4));
				build.setMovement(6);
				((Swordmaster) build).setSpriteObject(0,0);
				break;
			case COMPUTER:
				build.setName("Swordmaster");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(30);
				build.setCurrentHp(30);
				build.setStrength(13);
				build.setSkill(14);
				build.setSpeed(12);
				build.setLuck(20);
				build.setDefense(5);
				build.setResistance(2);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Sword", 4));
				build.setMovement(6);
				((Swordmaster) build).setSpriteObject(0,0);
				break;
			}
		}
		if (build instanceof Axereaver) {
			switch (build.getTeam()) {
			case PLAYER:
				build.setName("Axereaver");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(40);
				build.setCurrentHp(40);
				build.setStrength(16);
				build.setSkill(14);
				build.setSpeed(13);
				build.setLuck(12);
				build.setDefense(7);
				build.setResistance(0);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Axe", 5));
				build.setMovement(6);
				((Axereaver) build).setSpriteObject(0,0);
				break;
			case COMPUTER:
				build.setName("Warrior");
				build.setLevel(5);
				build.setMagic(0);
				build.setHp(45);
				build.setCurrentHp(45);
				build.setStrength(15);
				build.setSkill(11);
				build.setSpeed(12);
				build.setLuck(5);
				build.setDefense(9);
				build.setResistance(0);
				build.setMoved(false);
				build.setWeapon(new WeaponFactory().makeWeapon("Axe", 5));
				build.setMovement(6);
				((Axereaver) build).setSpriteObject(0, 0);
				break;
			}
			}
			if (build instanceof Thief) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("Thief");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(15);
					build.setCurrentHp(15);
					build.setStrength(8);
					build.setSkill(20);
					build.setSpeed(20);
					build.setLuck(10);
					build.setDefense(5);
					build.setResistance(5);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Knife", 1));
					build.setMovement(7);
					((Thief) build).setSpriteObject(0,0);
					break;
				case COMPUTER:
					build.setName("Assassin");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(20);
					build.setCurrentHp(20);
					build.setStrength(15);
					build.setSkill(10);
					build.setSpeed(15);
					build.setLuck(7);
					build.setDefense(15);
					build.setResistance(10);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Knife", 1));
					build.setMovement(7);
					((Thief) build).setSpriteObject(0,0);
					break;
				}
			}
			if (build instanceof Marksman) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("Marksman");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(15);
					build.setCurrentHp(15);
					build.setStrength(12);
					build.setSkill(18);
					build.setSpeed(15);
					build.setLuck(10);
					build.setDefense(5);
					build.setResistance(5);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Bow", 3));
					build.setMovement(6);
					((Marksman) build).setSpriteObject(0,0);
					break;
				case COMPUTER:
					build.setName("Sniper");
					build.setLevel(5);
					build.setMagic(0);
					build.setHp(25);
					build.setCurrentHp(25);
					build.setStrength(7);
					build.setSkill(13);
					build.setSpeed(16);
					build.setLuck(8);
					build.setDefense(10);
					build.setResistance(3);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Bow", 3));
					build.setMovement(6);
					((Marksman) build).setSpriteObject(0,0);
					break;
				}
			}
			if (build instanceof Druid) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("Druid");
					build.setLevel(5);
					build.setMagic(15);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(0);
					build.setSkill(18);
					build.setSpeed(13);
					build.setLuck(15);
					build.setDefense(15);
					build.setResistance(12);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Anima Magic", 3));
					build.setMovement(5);
					((Druid) build).setSpriteObject(0,0);
					break;
				case COMPUTER:
					build.setName("Dark Druid");
					build.setLevel(8);
					build.setMagic(18);
					build.setHp(35);
					build.setCurrentHp(35);
					build.setStrength(15);
					build.setSkill(15);
					build.setSpeed(8);
					build.setLuck(5);
					build.setDefense(12);
					build.setResistance(30);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Anima Magic", 3));
					build.setMovement(5);
					((Druid) build).setSpriteObject(0,0);
					break;
				}
			}
			if (build instanceof Saint) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("Bishop");
					build.setLevel(5);
					build.setMagic(12);
					build.setHp(25);
					build.setCurrentHp(25);
					build.setStrength(0);
					build.setSkill(14);
					build.setSpeed(10);
					build.setLuck(30);
					build.setDefense(5);
					build.setResistance(20);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Light Magic", 1));
					build.setMovement(6);
					((Saint) build).setSpriteObject(0,0);
					break;
				case COMPUTER:
					build.setName("Saint");
					build.setLevel(7);
					build.setMagic(5);
					build.setHp(30);
					build.setCurrentHp(30);
					build.setStrength(0);
					build.setSkill(10);
					build.setSpeed(8);
					build.setLuck(20);
					build.setDefense(8);
					build.setResistance(15);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Light Magic", 1));
					build.setMovement(6);
					((Saint) build).setSpriteObject(0,0);
					break;
				}
			}
			if (build instanceof Sorcerer) {
				switch (build.getTeam()) {
				case PLAYER:
					build.setName("Sorcerer");
					build.setLevel(5);
					build.setMagic(12);
					build.setHp(15);
					build.setCurrentHp(15);
					build.setStrength(0);
					build.setSkill(12);
					build.setSpeed(17);
					build.setLuck(10);
					build.setDefense(5);
					build.setResistance(10);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Dark Magic", 8));
					build.setMovement(5);
					((Sorcerer) build).setSpriteObject(0,0);
					break;
				case COMPUTER:
					build.setName("Dark Sage");
					build.setLevel(8);
					build.setMagic(16);
					build.setHp(20);
					build.setCurrentHp(20);
					build.setStrength(0);
					build.setSkill(10);
					build.setSpeed(12);
					build.setLuck(8);
					build.setDefense(8);
					build.setResistance(10);
					build.setMoved(false);
					build.setWeapon(new WeaponFactory().makeWeapon("Dark Magic", 8));
					build.setMovement(5);
					((Sorcerer) build).setSpriteObject(0,0);
					break;
				}
			}
		return build;
	}
}
