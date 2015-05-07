package model;

import java.io.Serializable;

public class ConsumableFactory implements Serializable {
	private Consumable consumable;
	
	public ConsumableFactory(){
	}
	
	public Consumable makeConsumable(String name, int level){
		if(name == "Health Potion"){
			consumable = new Consumable(name, level);
			consumable.setHealth(5);
			consumable.setDefense(0);
			consumable.setResistance(0);
			consumable.setLevel(1);
			consumable.setCost(5);
			consumable.setRemainingUses(3);
			return consumable;
		}
		if(name == "Defense Potion"){
			consumable = new Consumable(name, level);
			consumable.setDefense(1);
			consumable.setResistance(0);
			consumable.setHealth(0);
			consumable.setLevel(1);
			consumable.setCost(5);
			consumable.setRemainingUses(3);
			return consumable;
		}
		if(name == "Resistance Potion"){
			consumable = new Consumable(name, level);
			consumable.setResistance(2);
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
