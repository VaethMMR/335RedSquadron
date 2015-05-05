package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class Consumable extends Item{
	// private variables
	private int remainingUses;
	private int level;
	private int cost;
	private int health;
	private int resistance;
	private int defense;
	
	// constructor
	public Consumable(String name, int level) {
		super(name,level);
		setName("Lvl " + level +" " + name);
	}
	
	// getters 
	public int getRemainingUses() {
		return this.remainingUses;
	}
   
   public int getLevel(){
	   return this.level;
   }
   
   public int getHealth(){
	   return this.health;
   }
   public int getDefense(){
	   return this.defense;
   }
   public int getResistance(){
	   return this.resistance;
   }
   public int getCost(){
	   return this.cost;
   }
   
   // setters
   public void setCost(int cost){
	   this.cost = cost;
   }
   
   public void setRemainingUses(int remainingUses){
	   this.remainingUses = remainingUses;
   }
   
   public void setHealth(int health){
	   this.health = health;
   }
   
   public void setLevel(int level){
	   this.level = level;
   }
   
   public void setResistance(int level){
	   this.resistance = resistance;
   }
   
   public void setDefense(int level){
	   this.defense = defense;
   }
	
	// misc methods
	public int decreaseRemainingUses() {
		this.remainingUses--;
		return this.remainingUses;
	}
}