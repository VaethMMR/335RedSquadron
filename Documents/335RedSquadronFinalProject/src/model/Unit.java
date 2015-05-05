package model;
/* creates a unit with all stats associated
I added a tostring method
Author: Connor Broderick
*/
import java.util.Random;
import java.io.*;

import javax.swing.JOptionPane;

public class Unit {
   private String name; 
   private String type;
   private int level;
   private int hp;
   private int currentHp; //since current hp can be different than max hp
   private int movement;
   private int strength; 
   private int magic;
   private int skill;
   private int speed;
   private int luck;
   private int defense;
   private int resistance;
   
//this constructor will take a lot of arguments, that is because unit is in charge of creating all the different units, all of which have different starting 
//stats. It is simply letting the main controller create whatever unit you want.
   public Unit(String myName, String myType, int myLevel, int myHp, int myMovement, int myStrength, int myMagic, int mySkill, int mySpeed, int myLuck, int myDefense, int myResistance) {
      name = myName;
      type = myType;
      level = myLevel;
      hp = myHp;
      currentHp = myHp;
      movement = myMovement;
      strength = myStrength;
      magic = myMagic;
      skill = mySkill;
      speed = mySpeed;
      luck = myLuck;
      defense = myDefense;
      resistance = myResistance;
   }
   // just return true until we get map integration
   public boolean move(int[] x){
      return true;  
   }
   /* not sure how to implement range vs melee attack, basically check the grid, and if it is a ranged attack
   the second half of the code in this method would not be implemented, since there is no attack back. 
   Only thing missing now is that, and tome type weapons where it is resis and magic used.*/
      public boolean attack(Unit other, Weapon ourWeapon, Weapon enemyWeapon){
      int damagedealt = 0;
      int critChance = 0;
      int nextrandomInt = 0;
      int hitchance = ((skill * 2) + luck + ourWeapon.getAccuracy()) - ((other.speed *2) + other.luck);//weapon triangle would come into affect here as well
      Random randomGenerator = new Random();
      int randomInt = randomGenerator.nextInt(100);
      if(randomInt > 100 - hitchance){//hit confirmed
         damagedealt = strength + ourWeapon.getMight() - other.defense;
         critChance = ((skill / 2)+ ourWeapon.getCritical()) - other.luck;
         nextrandomInt = randomGenerator.nextInt(100); 
         if(nextrandomInt > 100 - critChance){//if it is a crit, then damage dealt is multiplied by 3
            damagedealt = damagedealt * 3;
			JOptionPane.showMessageDialog(null, "Critical Hit!\nDamage 3x");
         }
         other.currentHp = other.currentHp - damagedealt;//deal the damage 
         if(other.currentHp <= 0){//confirmed dead
            other.die();
            return true;//enemy killed
         } 
      }      
      if(speed >= other.speed + 4){//if your speed is 4 greater than the enemy you get to attack twice! Code copy pasted from above for second attack
         randomInt = randomGenerator.nextInt(100);
         if(randomInt > 100 - hitchance){//hit confirmed
            damagedealt = strength + ourWeapon.getMight() - other.defense;
            nextrandomInt = randomGenerator.nextInt(100); 
            if(nextrandomInt > 100 - critChance){//if it is a crit then damage dealt is multiplied by 3
               damagedealt = damagedealt * 3;
   			JOptionPane.showMessageDialog(null, "Critical Hit!\nDamage 3x");
            }
            other.currentHp = other.currentHp - damagedealt;//deal the damage 
            if(other.currentHp <= 0){//confirmed dead
               other.die();
               return true;//enemy killed
            } 
         }
      }//end of second attack
      
      
      //if the enemy is still alive, aka no return true then the enemy gets to attack back! copy and pasted from above except other.defense becomes defense and vice-versa
      hitchance = ((other.skill * 2) + other.luck + enemyWeapon.getAccuracy()) - ((speed *2) + luck);//weapon accuracy and triangle would come into affect here as well
      randomInt = randomGenerator.nextInt(100);
      if(randomInt > 100 - hitchance){//hit confirmed
         damagedealt = other.strength + enemyWeapon.getMight() - defense;
         critChance = ((other.skill / 2) + enemyWeapon.getCritical()) - luck;
         nextrandomInt = randomGenerator.nextInt(100); 
         if(nextrandomInt > 100 - critChance){//if it is a crit, then damage dealt is multiplied by 3
            damagedealt = damagedealt * 3;
			JOptionPane.showMessageDialog(null, "Critical Hit!\nDamage 3x");
         }
         currentHp = currentHp - damagedealt;//deal the damage 
         if(currentHp <= 0){//confirmed dead
            this.die();
            return false;//hero has fallen!
         } 
      }      
      if(other.speed >= speed + 4){//if your speed is 4 greater than the enemy you get to attack twice! Code copy pasted from above for second attack
         randomInt = randomGenerator.nextInt(100);
         if(randomInt > 100 - hitchance){//hit confirmed
            damagedealt = other.strength + enemyWeapon.getMight() - defense;
            nextrandomInt = randomGenerator.nextInt(100); 
            if(nextrandomInt > 100 - critChance){//if it is a crit, then damage dealt is multiplied by 3
               damagedealt = damagedealt * 3;
   			JOptionPane.showMessageDialog(null, "Critical Hit!\nDamage 3x");
            }
            currentHp = currentHp - damagedealt;//deal the damage 
            if(currentHp <= 0){//confirmed dead
               this.die();
               return true;//hero has fallen!
               } 
         }
      }//end of second attack

   return false;
   }// end of attack method

   public String toString(){
      return name + "\n" + type + "  " + "Level: " + level + "\nhealth: " + hp + "\n" + "strength: " + strength + "\nmagic: " + magic + "\nskill: " + skill + "\nspeed: " + speed
      + "\nluck: " + luck + "\ndefense: " + defense + "\nresistance: " + resistance;
   }
   
   public void wait1(){//apparently wait overrides wait in object so I have changed it to wait1
      
   }
   
   public void die(){
      //remove unit from the map
		JOptionPane.showMessageDialog(null, this.getName() + " is dead");
   }
   //level up will take growth rates, then use random number generator and adjust stats as needed. will return 1 always. 
   public int levelup(int hp1, int str, int mag, int sk, int spd, int luk, int def, int res){
      Random randomGenerator = new Random();
      int randomnum = randomGenerator.nextInt(100);
      if(randomnum >= 100 - hp1)
       hp = hp + 1;
      randomnum = randomGenerator.nextInt(100);   
      if(randomnum >= 100 - str)
         strength = strength + 1;
      randomnum = randomGenerator.nextInt(100);   
      if(randomnum >= 100 - mag)
         magic = magic + 1;
      randomnum = randomGenerator.nextInt(100);
      if(randomnum >= 100 - sk)
         skill = skill + 1;
      randomnum = randomGenerator.nextInt(100);
      if(randomnum >= 100 - spd)
         speed = speed + 1;
      randomnum = randomGenerator.nextInt(100);
      if(randomnum >= 100 - luk)
         luck = luck + 1;
      randomnum = randomGenerator.nextInt(100);
      if(randomnum >= 100 - def)
         defense = defense + 1;
      randomnum = randomGenerator.nextInt(100);
      if(randomnum >= 100 - res)
         resistance = resistance + 1;
      level = level + 1;
      return 1;
   }
   
   public void useItem(Consumable booster){
      booster.decreaseRemainingUses();
      if(booster.getName() == "Vulnary"){//then it is a 10 heal!
         currentHp += 10;
         if(currentHp >= hp){//cant heal more than max hp
            currentHp = hp;
         }   
      }//end of heal item
      
   }
   
    public void trade(Unit other){
      //in addition to item it will need a unit argument to know who it is trading with. 
   }


   
   /* All the getter and setter methods are below for each stat */
   
   public String getName() {
		return name;
	}

   public String getType(){
      return type;
   }
   
   public int getLevel(){
      return level;
   }
   
   public void setLevel ( int n ) {
   level = n;
   }

   public void setHp ( int n ) {
   currentHp = n;
   }
   
   public int getHp(){
      return hp;
   }
   
   
   public int getCurrentHp(){
      return currentHp;
   }  
   
   public int getMovement(){
      return movement;
   }
   
   public void setStrength(int n){
      strength = n;
   }
   
   public int getStrength(){
      return strength;
   }
   
   public void setMagic(int n){
      magic = n;
   }   
   
   public int getMagic(){
      return magic;
   }
   
   public void setSkill(int n){
      skill = n;
   }   
         
   public int getSkill(){
      return skill;
   }
   
   public void setSpeed(int n){
      speed = n;
   }
   
   public int getSpeed(){
      return speed;
   }   
   
   public void setLuck(int n){
      luck = n;
   }
      
   public int getLuck(){
      return luck;
   }
   
   public void setDefense(int n){
      defense = n;
   }
   
   public int getDefense(){
      return defense;
   }   
   
   public void setResistance(int n){
      resistance = n;
   }
   
   public int getResistance(){
      return resistance;
   }
   /*
   public int getAttackRange()
      return movement + getRange();
   }
   */ 
}