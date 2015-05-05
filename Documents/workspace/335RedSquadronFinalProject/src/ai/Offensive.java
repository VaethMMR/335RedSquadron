package ai;

import java.util.List;

import model.*;

public interface Offensive extends Strategy {
	public void strategy(Unit unit, GameMap map, List<GridNode> path);
}
