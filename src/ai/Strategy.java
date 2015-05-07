package ai;

import java.io.Serializable;
import java.util.List;

import model.*;

public interface Strategy extends Serializable {

	public void strategy(Unit u, GameMap map, List<GridNode> path);
}
