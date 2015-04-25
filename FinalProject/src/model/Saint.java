package model;

public class Saint extends Ranged {


   public Saint(String myName, String myType, int myLevel, int myHP, int myMovement, int myStrength, int myMagic, int mySkill, int mySpeed, int myLuck, int myDefense, int myResistance){
      super(myName, myType, myLevel, myHP, myMovement, myStrength,myMagic,mySkill,mySpeed,myLuck,myDefense,myResistance);
   }

   public boolean heal(Unit other){
      
      other.setHp(other.getCurrentHp() + 5 + getMagic() / 2);
      if(other.getCurrentHp() > other.getHp()){//if it heals more than max hp reset current hp to max. 
         other.setHp(other.getHp());
      }
      
      return true;
   }



}