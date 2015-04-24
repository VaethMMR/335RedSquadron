package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public abstract class Consumable {
	// private variables
	private int cost;
	private String name;
	private int remainingUses;
	
	// constructor
	public Consumable(int cost, String name, int remainingUses) {
		this.cost = cost;
		this.name = name;
		this.remainingUses = remainingUses;
	}
	
	// getters and setters
	public int getRemainingUses() {
		return this.remainingUses;
	}
   
   public String getName(){
      return name;
   }
	
	// misc methods
	public int decreaseRemainingUses() {
		this.remainingUses--;
		return this.remainingUses;
	}
}