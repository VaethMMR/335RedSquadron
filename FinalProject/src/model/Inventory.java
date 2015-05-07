package model;
import java.io.Serializable;
import java.util.*;

import exceptions.InventoryFullException;

// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class Inventory implements Serializable {
	// private variables
	private List<Item> inventory;
	private int maxSize;
	private int numCoins;

	// constructors
	// TODO: Default max size? Pass in Inventory?
	public Inventory(int maxSize, int numCoins) {
		this.inventory = new ArrayList<Item>();
		this.maxSize = maxSize;
		this. numCoins = numCoins;
	}
	
	public Inventory(ArrayList<Item> inventory, int maxSize, int numCoins) {
		this.inventory = inventory;
		this.maxSize = maxSize;
		this.numCoins = numCoins;
	}
	
	// getters and setters
	public List<Item> getInventory() {
		return this.inventory;
	}
	public int getNumCoins(){
		return this.numCoins;
	}
	
	public void setInventory(ArrayList<Item> newInventory) {
		this.inventory = newInventory;
	}
	public void setNumCoins(int numCoins){
		this.numCoins = numCoins;
	}

	// misc methods
	public void add(Item newItem) {
		if (this.inventory.size() < maxSize) {
			this.inventory.add(newItem);
		} else {
			throw new InventoryFullException();
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