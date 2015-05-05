package model;

public class Bridge extends Terrain {

	public Bridge(int[] location) {
		super(true, location);
	}
	
	public String toString() {
		return "[=]";
	}

}
