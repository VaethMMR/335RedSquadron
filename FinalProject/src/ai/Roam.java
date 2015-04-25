package ai;

import model.*;

public class Roam implements Strategy {

	public void strategy(Unit unit, Model model) {
		//move unit along an x y coordinate plane
		GameMap map = model.getGame().getMap();
		int[] currentPos = map.getUnitLocations().get(unit).getLocation();
		int xAxis = map.getMap().length;
		int yAxis = map.getMap()[0].length;
		int mv = unit.getMovement();
		int[] destination = {currentPos[0] - mv/2, currentPos[1] - mv/2};
		while(map.moveUnit(unit, destination) == false){
			//Stay in bounds
			if(destination[0] < 0 || destination[1] < 0 || destination[0] >= xAxis || destination[1] >= yAxis){
			if(destination[0] < 0)
				destination[0] = 0;
			if(destination[0] >= xAxis)
				destination[0] = xAxis - 1;
			if(destination[1] < 0)
				destination[1] = 0;
			if(destination[1] >= yAxis)
				destination[1] = yAxis - 1;
			}
			else{
				//it is occupied by a unit
				//same position is always valid, override occupied terrain condition
				if(destination[0] == currentPos[0] && destination[1] == currentPos[1])
					break;
				destination[0]++;
			}
		}
	}
	
}
