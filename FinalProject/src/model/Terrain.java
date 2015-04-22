package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public abstract class Terrain {
	// private variables
	private Unit unit;
	private boolean standable;
	
	// constructor
	public Terrain(boolean standable) {
		this.unit = null;
		this.standable = standable;
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
	
	// misc methods
	public Unit moveUnit() {
		Unit moveUnit = this.unit;
		this.unit = null;
		return moveUnit;
	}
}
