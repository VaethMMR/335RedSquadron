package model;
public abstract class Ranged extends Unit {


   public Ranged(String myName, int myLevel, int myHP, int myMovement, int myStrength, int myMagic, int mySkill, int mySpeed, int myLuck, int myDefense, int myResistance){
      super(myName, myHP, myMovement, myStrength,myMagic,mySkill,mySpeed,myLuck,myDefense,myResistance, myResistance);
   }

   protected abstract void setWeapon();
   public abstract Weapon getWeapon();


}