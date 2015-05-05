package model;

public class Door extends Terrain {
	boolean standable;
	
	public Door(boolean standable, int[] location) {
		super(standable, location);
		// TODO Auto-generated constructor stub
	}
	
	// Doors standability can be overwridden
	public void setStandable(boolean standable){
		this.standable = standable;
	}

}
