package model;

public class Mountain extends Terrain {

	public Mountain(int[] location) {
		super(false, location);
	}

	public String toString() {
		return "[^]";
	}
	
}
