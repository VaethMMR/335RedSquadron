package ai;

import terrain.Terrain;
import model.*;

public class GridNode {

	private Terrain key;
	private int h;
	private int g = 0;
	private int f;
	private boolean hasH;
	private boolean occupied;
	private GridNode parent;
	private GridNode left;
	private GridNode right;
	private GridNode up;
	private GridNode down;

	protected GridNode(Terrain newKey) {
		key = newKey;
		hasH = key.getStandable();
	}

	protected void setKey(Terrain t) {
		key = t;
	}

	protected void setLeft(GridNode newGridNode) {
		left = newGridNode;
	}

	protected void setRight(GridNode newGridNode) {
		right = newGridNode;
	}

	protected void setUp(GridNode newGridNode) {
		up = newGridNode;
	}
	
	protected void setHasH(boolean b){
		hasH = b;
	}

	protected void setDown(GridNode newGridNode) {
		down = newGridNode;
	}
	
	protected void calculateH(GridNode start, int[] target){
		h = Math.abs(start.getKey().getLocation()[0] - target[0])
				+ Math.abs(start.getKey().getLocation()[1] - target[1]);
	}
	
	protected void calculateG(GridNode p){
		g = p.getG() + 10;
	}

	protected int calculateF(){
		return f = g + h;
	}
	
	protected void setParent(GridNode p){
		parent = p;
	}
	
	protected void setOccupied(boolean isOcc){
		occupied = isOcc;
	}
	
	public boolean isOccupied(){return occupied;}
	
	public boolean getHasH(){
		return hasH;
	}
	
	public Terrain getKey() {
		return key;
	}

	public GridNode getLeft() {
		return left;
	}

	public GridNode getRight() {
		return right;
	}

	public GridNode getUp() {
		return up;
	}

	public GridNode getDown() {
		return down;
	}

	public int getH(){return h;}
	
	public int getG(){return g;}
	
	public int getF(){return f;}
	
	public GridNode getParent(){return parent;}
}
