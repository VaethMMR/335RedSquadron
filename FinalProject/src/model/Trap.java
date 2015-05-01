package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class Trap extends Item{
	// private variables
	private int level;
	private String name;

	// constructor
	public Trap(String name, int level) {
		super(name,level);
		setName("Lvl " + level +" " + name);
	}

}
