package model;
public abstract class Melee extends Unit {


   public Melee(String myName, Weapon weapon, int myLevel, int myHP, int myMovement, int myStrength, int myMagic, int mySkill, int mySpeed, int myLuck, int myDefense, int myResistance){
      super(myName, myLevel, myHP, myMovement, myStrength,myMagic,mySkill,mySpeed,myLuck,myDefense,myResistance);
   }
   
   protected abstract void setWeapon();
   public abstract Weapon getWeapon();
   



}