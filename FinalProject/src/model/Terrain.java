package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public abstract class Terrain {
	// private variables
	private Unit unit;
	private boolean standable;
	private int[] location;
	
	// constructor
	public Terrain(boolean standable, int[] location) {
		this.unit = null;
		this.standable = standable;
		this.location = location;
	}
	
	// getters and setters
	public Unit getUnit() {
		return this.unit;
	}
	
	public boolean setUnit(Unit newUnit) {
		if (this.unit == null) {
			this.unit = newUnit;
			return true;
		} else {
			// TODO: throw a space full exception
			return false;
		}
	}
	
	public boolean getStandable() {
		return this.standable;
	}
	
	public int[] getLocation() {
		return this.location;
	}
	
	// misc methods
	public Unit moveUnit() {
		Unit moveUnit = this.unit;
		this.unit = null;
		return moveUnit;
	}
	
	public String toString() {
		return "[" + this.location[0] + "," + this.location[1] + "]";
	}
}
