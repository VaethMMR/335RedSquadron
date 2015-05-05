package ai;

import java.util.*;

import model.*;

public class AStar4WayPathfinder implements Pathfinder {
	private LinkedList<GridNode> open;
	private LinkedList<GridNode> closed;
	private LinkedList<GridNode> adjacents;
	private Grid grid;
	private GridNode target;
	private LinkedList<GridNode> path;
	private AI ai;

	public AStar4WayPathfinder(Grid mapGrid, GridNode enemy, AI us) {
		grid = mapGrid;
		target = enemy;
		// Instantiate two empty lists
		open = new LinkedList<GridNode>();
		closed = new LinkedList<GridNode>();
		adjacents = new LinkedList<GridNode>();
		path = new LinkedList<GridNode>();

		ai = us;
	}

	@Override
	public LinkedList<GridNode> findPath(GridNode node) {
		open.add(node);
		GridNode target = find(node);
		while (target != null) {
			path.add(target);
			target = target.getParent();
		}
		return reverse(path);
	}

	private LinkedList<GridNode> reverse(LinkedList<GridNode> path) {
		LinkedList<GridNode> reverse = new LinkedList<GridNode>();
		for (int i = path.size() - 1; i > -1; i--)
			reverse.add(path.get(i));
		return reverse;

	}

	private GridNode find(GridNode node) {
		int fVal = 0;
		int min = -1;
		GridNode next = null;

		// Bases
		if (node == target)
			return node;

		// Recursion
		for (GridNode n : open) {
			fVal = n.getF();
			if (min < 0 || fVal < min) {
				min = n.getF();
				next = n;
			}
		}
		open.remove(next);
		closed.add(next);

		if (next.getLeft() != null)
			adjacents.add(next.getLeft());
		if (next.getRight() != null)
			adjacents.add(next.getRight());
		if (next.getUp() != null)
			adjacents.add(next.getUp());
		if (next.getDown() != null)
			adjacents.add(next.getDown());

		for (GridNode n : adjacents)
			if (n.getHasH() && !(closed.contains(n)))
				if (!open.contains(n)) {
					open.add(n);
					n.setParent(next);
					n.calculateG(n.getParent());
					n.calculateF();
				}
				else {
					// Special check: checking G cost to see if
					// there is a better path
				 if (next.getG() + 10 < n.getG()) {
					n.setParent(next);
					n.calculateG(n.getParent());
					n.calculateF();
				}
			}
		// Reduce the list to reduce runtime of algorithm
		adjacents.clear();
		return find(next);
	}
}
