package ai;

import java.util.List;

import model.*;

public class Roam implements Strategy {
	
	public void strategy(Unit unit, GameMap map, List<GridNode> path) {
		int[] coordinates = new int[2];
		GridNode destination = path.get(0);
		int count = unit.getMovement();
		for(GridNode node : path){
			destination = node;
			count--;
			if(count < 1 || path.get(path.size()-1) == node)
				break;
		}
		while(destination.isOccupied() && destination.getParent() != null)
			destination = destination.getParent();
		if(destination.getParent() == null)
		//We are back at the start node. There was no path that was open
			return;
		map.moveUnit(unit, destination.getKey().getLocation());
			}
	}
