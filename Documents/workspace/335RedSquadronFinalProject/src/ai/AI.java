package ai;

import java.util.ArrayList;
import java.util.List;

import model.*;

//Class AI: Creates strategies implemented for the 
//AI team of units. Also tracks the AI's team by adding
//and removing units

public class AI {
	private List<Unit> team;
	private Strategy s;
	private Model model;
	private Pathfinder p;
	private Grid grid;
	private int[] target;
	private int targetDistance;
	private GridNode targetNode, start;

	public AI(List<Unit> team, Model model) {
		this.team = team;
		this.model = model;
	}

	public void setStrategy(Strategy s) {
		this.s = s;
	}

	public Strategy getStrategy() {
		return s;
	}

	public Model getModel() {
		return model;
	}

	public List<Unit> getTeam() {
		return team;
	}

	public boolean remove(Unit unit) {
		if (!team.contains(unit))
			return false;
		model.getGame().getMap();
		team.remove(unit);
		return true;
	}

	public void useStrategy(Unit u) {
		// Check the range of targets
		target = setInitTarget(model.getGame().getMap(), u);
		grid = new Grid(model.getGame().getMap(), target, u);
		start = grid.getStart();
		targetNode = grid.getTarget();

		p = new AStar4WayPathfinder(grid, targetNode, this);
		if (u.getWeapon() != null && targetDistance > 0
				&& targetDistance < u.getMovement() + u.getWeapon().getRange())
			this.setStrategy(new AttackPlayer());
		else
			this.setStrategy(new Roam());
		s.strategy(u, model.getGame().getMap(), p.findPath(start));
		// Check to see if unit died
//		if (s instanceof AttackPlayer) {
//			if (u.getCurrentHp() < 1) {
//				model.getGame().getMap().removeUnit(u);
//				this.remove(u);
//			}
//		}
	}

	private int[] setInitTarget(GameMap map, Unit ai) {
		// Uses Manhattan formula to set an initial target.
		int distance;
		int min = -1;
		Unit target = null;
		for (Unit u : map.getPlayerTeam()) {
			distance = Math.abs(map.getUnitLocations().get(u).getLocation()[0]
					- map.getUnitLocations().get(ai).getLocation()[0])
					+ Math.abs(map.getUnitLocations().get(u).getLocation()[1]
							- map.getUnitLocations().get(ai).getLocation()[1]);
			if (min == -1 || distance < min) {
				min = distance;
				target = u;
			}
		}
		targetDistance = min;
		return map.getUnitLocations().get(target).getLocation();
	}
}
