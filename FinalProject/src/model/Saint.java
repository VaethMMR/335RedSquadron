package model;

public class Saint extends SpellCaster {
	private Weapon weapon;

	public Saint(String myName, Weapon weapon, int myLevel, int myHP,
			int myMovement, int myStrength, int myMagic, int mySkill,
			int mySpeed, int myLuck, int myDefense, int myResistance) {
		super(myName, myLevel, myHP, myMovement, myStrength, myMagic, mySkill,
				mySpeed, myLuck, myDefense, myResistance);
		setWeapon();
	}

	protected void setWeapon() {
		weapon = WeaponFactory.makeWeapon(this);
	}

	public Weapon getWeapon() {
		return weapon;
	}

}