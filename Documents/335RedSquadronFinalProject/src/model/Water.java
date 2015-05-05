package model;

public class Water extends Terrain {

	public Water(int[] location) {
		super(false, location);
	}

	public String toString() {
		return "[~]";
	}
	
}
