package model;

import java.io.Serializable;

public class ConsumableFactory implements Serializable {
	private Consumable consumable;
	
	public ConsumableFactory(){
	}
	
	/**
	 * This method creates a consumable that has different effects depending on its name
	 * 
	 * @param name
	 *            The name of the consumable
	 * @param level
	 *            The level of the consumable. All are level 1 by default
	 * @return consumable
	 * 			  returns the created consumable           
	 */
	public Consumable makeConsumable(String name, int level){
		if(name == "Health Potion"){
			consumable = new Consumable(name, level);
			consumable.setHealth(10);
			consumable.setDefense(0);
			consumable.setResistance(0);
			consumable.setLevel(1);
			consumable.setCost(5);
			consumable.setRemainingUses(3);
			return consumable;
		}
		if(name == "Defense Potion"){
			consumable = new Consumable(name, level);
			consumable.setDefense(5);
			consumable.setResistance(0);
			consumable.setHealth(0);
			consumable.setLevel(1);
			consumable.setCost(5);
			consumable.setRemainingUses(3);
			return consumable;
		}
		if(name == "Resistance Potion"){
			consumable = new Consumable(name, level);
			consumable.setResistance(5);
			consumable.setHealth(0);
			consumable.setDefense(0);
			consumable.setLevel(1);
			consumable.setCost(5);
			consumable.setRemainingUses(3);
			return consumable;
		}else{
			return null;
		}
	}
}
