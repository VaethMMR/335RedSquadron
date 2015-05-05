package model;
// 335 Final Project - Red Squadron
// Authors: Connor Broderick

public class Weapon extends Item {
private int might;
private int critical;
private int accuracy;
private int cost;
private int range;
private int level;
private int magic;

   public Weapon(String name, int level){
	   super(name,level);
	   setName("Lvl " + level +" " + name);

   }

   // get Methods         
   public int getMight(){
      return might;
   }
   
   public int getCritical(){
      return critical;
   }

   public int getAccuracy(){
      return accuracy;
   }
   public int getCost(){
	   return cost;
   }
   public int getRange(){
	   return range;
   }
   public int getLevel(){
	   return level;
   }
   public int getMagic(){
	   return magic;
   }
   
   // Set Methods
   public void setMight(int might){
	   this.might = might;
   }
   public void setCritical(int critical){
	   this.critical = critical;
   }
   public void setAccuracy(int accuracy){
	   this.accuracy = accuracy;
   }
   public void setRange(int range){
	   this.range = range;
   }
   public void setCost(int cost){
	   this.cost = cost;
   }
   public void setMagic(int magic){
	   this.magic = magic;
   }

}
