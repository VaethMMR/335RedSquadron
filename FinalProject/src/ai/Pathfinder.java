package ai;

import java.io.Serializable;
import java.util.List;

public interface Pathfinder extends Serializable {

	public List<GridNode> findPath(GridNode start);
}
