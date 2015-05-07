package ai;

public class PathNode {
	private int h; //heuristic
	private int g; //movement cost
	private int f; // g + h
	private PathNode parent;
	
	public PathNode(){
		
	}
}
