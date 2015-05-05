package ai;

import model.Model;
import model.Unit;

public interface Offensive extends Strategy {
	public void strategy(Unit unit, Model model);
}
