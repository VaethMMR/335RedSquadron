package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public abstract class Equipment extends Item {
	// private variables
	private int cost;
	private String name;
		
	// constructor
	public Equipment(int cost, String name) {
		super(cost,name);
	}

}