package ai;

import java.util.*;

import javax.swing.JOptionPane;
import terrain.*;
import model.*;

public class AttackPlayer implements Offensive {

	public void strategy(Unit unit, GameMap map, List<GridNode> path) {
		int distance;
		int count = unit.getMovement();
		int range = unit.getMovement() + unit.getWeapon().getRange();
		int attackRange = unit.getWeapon().getRange();
		GridNode target = path.get(path.size() - 1);
		GridNode destination = path.get(0);
		Unit enemy = target.getKey().getUnit();
		for (int i = 0; i < path.size() - 1; i++) {
			// Given the nature of obstacles and collision avoidance, we may find
			// a closer enemy, but we are guaranteed to have at least 1.
			if (i + attackRange < path.size() && map.getPlayerTeam().contains(path.get(i + attackRange).getKey().getUnit())) {
				if (!(path.get(i).isOccupied())) {
					destination = path.get(i);
					break;
				}
				if (i + attackRange < path.size() && map.getPlayerTeam().contains(path.get(i).getKey().getUnit())) {
					if (!(path.get(i).isOccupied())) {
						destination = path.get(i);
						break;
					}
				}
			}
//				// If the space is occupied, look for another in range the we
//				// can attack from
//				else if (count > 1 && attackRange > 1) {
//					recalibrate(unit, destination, path, i, count, map);
//					break;
//				}
				count--;
			}
		
		while (destination.isOccupied() && destination.getParent() != null) {
			destination = recalibrate(enemy, destination, path, path.indexOf(destination), count, map);
			if (destination.getParent() == null)
				// We are back at start, there was no path to traverse
				return;
		}

		// By this point we are guaranteed an attack phase.
		// Move to destination and attack
		map.moveUnit(unit, destination.getKey().getLocation());
		// update heuristic range
		int uX = destination.getKey().getLocation()[0];
		int uY = destination.getKey().getLocation()[1];
		int eX = target.getKey().getLocation()[0];
		int eY = target.getKey().getLocation()[1];
		distance = Math.abs(uX - eX) + Math.abs(uY - eY);
		unit.attack(enemy, distance);
		if (enemy.getCurrentHp() < 1) {
			map.removeUnit(enemy);
			map.getPlayerTeam().remove(enemy);
		}
		if (unit.getCurrentHp() < 1) {
			map.removeUnit(unit);
			map.getAITeam().remove(unit);
		}
	}

	private GridNode recalibrate(Unit unit, GridNode destination,
			List<GridNode> path, int i, int count, GameMap map) {
		while(destination != path.get(0) || destination.isOccupied() == true){
		if (path.get(i).getLeft() != null && !(path.get(i).getLeft().isOccupied())) {
			destination = path.get(i).getLeft();
		} else if (path.get(i).getLeft() != null && !(path.get(i).getRight().isOccupied())) {
			destination = path.get(i).getRight();
		} else if (path.get(i).getLeft() != null && !(path.get(i).getUp().isOccupied())) {
			destination = path.get(i).getUp();
		} else if (path.get(i).getLeft() != null && !(path.get(i).getDown().isOccupied())) {
			destination = path.get(i).getDown();
		}
		else
			destination = destination.getParent();
		map.moveUnit(unit, destination.getKey().getLocation());
		if(i > -1 && path.get(i - 1).equals(destination))
			count++;
	}
		return destination;
	}
}
