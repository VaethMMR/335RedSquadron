package ai;

import java.util.*;

import javax.swing.JOptionPane;

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
			// if our range + distance is out of the bounds of the path, we are
			// obviously in attack range
			if (i + attackRange >= path.size() - 1) {
				if (!(path.get(i).isOccupied())) {
					destination = path.get(i);
					break;
				}
				// If the space is occupied, look for another in range the we
				// can attack from
				else if (count > 1 && attackRange > 1) {
					recalibrate(unit, destination, path, i, count);
					break;
				}
				count--;
			}
		}
		while (destination.isOccupied() && destination.getParent() != null) {
			destination = destination.getParent();
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

	private void recalibrate(Unit unit, GridNode destination,
			List<GridNode> path, int i, int count) {
		if (path.get(i).getLeft() != null && !(path.get(i).getLeft().isOccupied())) {
			destination = path.get(i).getLeft();
		} else if (path.get(i).getLeft() != null && !(path.get(i).getRight().isOccupied())) {
			destination = path.get(i).getRight();
		} else if (path.get(i).getLeft() != null && !(path.get(i).getUp().isOccupied())) {
			destination = path.get(i).getUp();
		} else if (path.get(i).getLeft() != null && !(path.get(i).getDown().isOccupied())) {
			destination = path.get(i).getDown();
		}
		count--;
	}
}
