package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public abstract class Item {
	// private variables
	private int level;
	private String name;
	
	public Item(String name, int level) {
		this.level = level;
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

}