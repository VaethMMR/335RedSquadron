import java.util.*;

// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class Inventory {
	// private variables
	private List<Item> inventory;
	private int maxSize;

	// constructors
	// TODO: Default max size? Pass in Inventory?
	public Inventory(int maxSize) {
		this.inventory = new ArrayList<Item>();
		this.maxSize = maxSize;
	}
	
	public Inventory(ArrayList<Item> inventory, int maxSize) {
		this.inventory = inventory;
		this.maxSize = maxSize;
	}
	
	// getters and setters
	public List<Item> getInventory() {
		return this.inventory;
	}
	
	public void setInventory(ArrayList<Item> newInventory) {
		this.inventory = newInventory;
	}
	
	public int getMaxSize() {
		return this.maxSize;
	}
	
	public void setMaxSize(int newMaxSize) {
		this.maxSize = newMaxSize;
	}

	// misc methods
	public boolean add(Item newItem) {
		if (this.inventory.size() < this.maxSize) {
			this.inventory.add(newItem);
			return true;
		} else {
			// TODO: Maybe throw some exception thing?
			return false;
		}
	}
	
	public boolean remove(Item itemToRemove) {
		if (this.inventory.contains(itemToRemove)) {
			this.inventory.remove(itemToRemove);
			return true;
		} else {
			return false;
		}
	}
	
	public void consume(Consumable toConsume) {
		// TODO: How do we connect the Unit to the inventory? (Private var or passed into method?)
		// TODO: consume the consumable
		toConsume.decreaseRemainingUses();
		// TODO: Make Observable to check if remaining uses = 0
	}
	
	public void layTrap(Trap trapToLay) {
		// TODO: How do we connect the Unit to the inventory? (Private var or passed into method?)
		// TODO: lay the trap
		this.inventory.remove(trapToLay);
		// TODO: Make Observable to remove trap from GUI
	}
	
	public void equip(Equipment toEquip) {
		// TODO: How do we connect the Unit to the inventory? (Private var or passed into method?)
		// TODO: equip the Equipment
		// TODO: Move to subinventory/set as equipped?
		// TODO: Observable
	}
}