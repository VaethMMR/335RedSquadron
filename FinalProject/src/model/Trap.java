package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class Trap extends Item{
	// private variables
	private int level;
	private String name;
	private int cost;
	private int damage;

	// constructor
	public Trap(String name, int level) {
		super(name,level);
		setName("Lvl " + level +" " + name);
	}
	
	// get methods
	public int getCost(){
		return this.cost;
	}
	public int getDamage(){
		return this.damage;
	}
	
	// set methods
	public void setCost(int cost){
		this.cost = cost;
	}
	public void setDamage(int damage){
		this.damage = damage;
	}

}
