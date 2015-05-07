package ai;

import java.io.Serializable;

import model.*;
import terrain.*;

public class Grid implements Serializable {
	
	private GridNode head, corner, target, start;
	private int x, y;
	private Unit ai;
	
	public Grid(GameMap map, int[] target, Unit ai){
			y = map.getRows();
			x = map.getColumns();
			this.ai = ai;
			for(int i = 0; i < x; i ++)
					moveDown(i, x, y, map, target);
		}

		private void moveDown(int i, int x, int y, GameMap map, int[] target) {
			GridNode node;
			head = null;
			for(int j = 0; j < y; j++)
				addRight(map.getTerrain(i, j), target);
			node = corner;
			if(head != corner){
				while(node.getDown() != null)
					node = node.getDown();
				linkLists(head, node);
			}
		}
		
		private void linkLists(GridNode lower, GridNode upper) {
			while(lower != null && upper != null){
				lower.setUp(upper);
				upper.setDown(lower);
				lower = lower.getRight();
				upper = upper.getRight();
				}
	}

		private void addRight(Terrain t, int[] target){
				GridNode node = head;
				GridNode newNode = new GridNode(t);
				if(head == null){
					head = newNode;
				if(corner == null)
					corner = head;
				}
				else{
					while(node.getRight() != null)
						node = node.getRight();
					node.setRight(newNode);
					newNode.setLeft(node);
				}
				if(newNode.getHasH() == true){
					newNode.calculateH(newNode, target);
					if(newNode.getH() == 0)
						setTarget(newNode);
					if(newNode.getKey().getUnit() == ai)
						setStart(newNode);
					if(newNode.getKey().getUnit() != null)
						newNode.setOccupied(true);
				}
			}
			protected void setStart(GridNode start){
				this.start = start;
			}
			
			protected void setTarget(GridNode enemy){
				target = enemy;
			}
			
			public GridNode getStart(){return start;}
			
			public GridNode getTarget(){return target;}
			
			public String toString(){
				GridNode right = corner;
				GridNode down = corner;
				String grid = "";
				while(down != null){
					if(down != corner)
						grid += "\n\n";
					while(right != null){
						grid += "[" + right.getKey().getLocation()[1] + "," + right.getKey().getLocation()[0] + "]";
						right = right.getRight();
						if(right != null)
							grid += "\t";
						}
					down = down.getDown();
					right = down;
					}
				return grid;
			}			

}
