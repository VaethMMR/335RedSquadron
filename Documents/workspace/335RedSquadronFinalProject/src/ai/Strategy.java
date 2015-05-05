package ai;

import java.util.List;

import model.*;

public interface Strategy {

	public void strategy(Unit u, GameMap map, List<GridNode> path);
}
