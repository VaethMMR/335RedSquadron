package ai;

import java.util.*;

import javax.swing.JOptionPane;

import model.*;

public class AttackPlayer implements Offensive {
	
	public void strategy(Unit unit, Model model) {
		Unit target = model.getGame().getMap().getInRangePlayerUnits(unit).get(0);
		Weapon unitWeapon;
		Weapon targetWeapon = null;
		if(unit instanceof Melee)
			unitWeapon = new Weapon("Sword",0);
		else
			unitWeapon = new Weapon("Bow",0);
		
		if(target instanceof Melee)
			targetWeapon = new Weapon("Sword",0);
		else
			targetWeapon = new Weapon("Bow",0);
		
		unit.attack(target, unitWeapon, targetWeapon);
		if(target.getCurrentHp() < 1){
			model.getGame().getPlayerTeam().remove(target);
			model.getGame().getMap().removeUnit(target);
		}
	}
}
