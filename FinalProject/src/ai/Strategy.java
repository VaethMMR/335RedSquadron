package ai;

import model.Model;
import model.Unit;

public interface Strategy {

	public void strategy(Unit u, Model m);
}
