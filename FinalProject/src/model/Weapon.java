package model;
//335 Final Project - Red Squadron
//Authors: Alex Guyot and John Oney

public abstract class Weapon extends Equipment{
	// private variables
	private int cost;
	private String name;
	private int range;
	private int might;
	private int accuracy;
	private int critical;
	
	// constructor
	public Weapon(int cost, String name, int range, int might, int accuracy, int critical) {
		super(cost, name);
		this.range = range;
		this.might = might;
		this.accuracy = accuracy;
		this.critical = critical;
	}
	
}
