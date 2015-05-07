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
		return remainingUses;
	}
   
   public int getLevel(){
	   return level;
   }
   
   public int getHealth(){
	   return health;
   }
   public int getDefense(){
	   return defense;
   }
   public int getResistance(){
	   return resistance;
   }
   public int getCost(){
	   return cost;
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
   
   public void setResistance(int resistance){
	   this.resistance = resistance;
   }
   
   public void setDefense(int defense){
	   this.defense = defense;
   }
	
	// misc methods
   /**
	 * This method decreases the number of uses from 3 down to 0
	 * 
	 * @return remainingUses
	 * 			  returns the number of remaining uses           
	 */
	public int decreaseRemainingUses() {
		this.remainingUses--;
		return this.remainingUses;
	}
}