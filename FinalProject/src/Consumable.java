// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public abstract class Consumable {
	// private variables
	private int cost;
	private String name;
	private int remainingUses;
	
	public Consumable(int cost, String name, int remainingUses) {
		this.cost = cost;
		this.name = name;
		this.remainingUses = remainingUses;
	}

}
