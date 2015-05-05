package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class Trap extends Item{
	// private variables
	private int level;
	private String name;
	private int cost;

	// constructor
	public Trap(String name, int level) {
		super(name,level);
		setName("Lvl " + level +" " + name);
	}
	
	// get methods
	public int getCost(){
		return this.cost;
	}
	
	// set methods
	public void setCost(int cost){
		this.cost = cost;
	}

}
