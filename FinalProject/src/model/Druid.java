package model;

public class Druid extends SpellCaster{
	private Weapon weapon;
	
	public Druid(String myName, int myLevel, int myHP,
			int myMovement, int myStrength, int myMagic, int mySkill,
			int mySpeed, int myLuck, int myDefense, int myResistance) {
		super(myName, myLevel, myHP, myMovement, myStrength, myMagic, mySkill,
				mySpeed, myLuck, myDefense, myResistance);
	}
	protected void setWeapon(){
		weapon = WeaponFactory.makeWeapon(this);
	}
	
	public Weapon getWeapon(){
		return weapon;
	}

	
}
