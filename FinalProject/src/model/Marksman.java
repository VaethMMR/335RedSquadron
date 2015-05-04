package model;
//for all of these, not sure what unique things we going to add for each class so they are just extensions for now.
public class Marksman extends Ranged {
	private Weapon weapon;

   public Marksman(String myName, Weapon weapon, int myLevel, int myHP, int myMovement, int myStrength, int myMagic, int mySkill, int mySpeed, int myLuck, int myDefense, int myResistance){
      super(myName, myLevel, myHP, myMovement, myStrength,myMagic,mySkill,mySpeed,myLuck,myDefense,myResistance);
      setWeapon();
   }
   
   protected void setWeapon(){
		weapon = WeaponFactory.makeWeapon(this);
	}
	
	public Weapon getWeapon(){
		return weapon;
	}
}