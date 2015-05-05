package model;

public class Tree extends Terrain {

	public Tree(int[] location) {
		super(false, location);
	}
	
	public String toString() {
		return "[|]";
	}

}
