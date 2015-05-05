package model;
// 335 Final Project - Red Squadron
// Authors: Connor Broderick

public abstract class Weapon extends Item {
private int might;
private int critical;
private int accuracy;
private int cost;
private int range;
private int level;
private int magic;
private int weight;
private String name;

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
   
   public int getWeight(){
	  return weight; 
   }
   
   public String getName(){
	   return name;
   }
   
   // Set Methods
   
   public void setLevel(int level){
   		this.level = level;
   }
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

   public void setWeight(int weight){
   this.weight = weight;
   }
}
