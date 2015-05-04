package model;

import java.util.LinkedList;
import java.util.List;

public class Thief extends Melee {
	private Weapon weapon;
	private GameMap map;

	public Thief(String myName, Weapon weapon, int myLevel, int myHP,
			int myMovement, int myStrength, int myMagic, int mySkill,
			int mySpeed, int myLuck, int myDefense, int myResistance,
			GameMap map) {
		super(myName, weapon, myLevel, myHP, myMovement, myStrength, myMagic,
				mySkill, mySpeed, myLuck, myDefense, myResistance);
		this.map = map;
		setWeapon();
	}

	protected void setWeapon() {
		weapon = WeaponFactory.makeWeapon(this);
	}

	public Weapon getWeapon() {
		return weapon;
	}

	// Thieves can interact with the map. Namely they can unlock doors
	public void unlock(GameMap map) {
		int[] location = map.getUnitLocations().get(this).getLocation();
		int x = location[0];
		int y = location[1];
		List<Terrain> adjacents = new LinkedList<Terrain>();
		// Border conditions
		// Check adjacents for a door
		// Check if door was unlocked (door.standable will be true)
		// unlock the door (set standable to true)
		if (x - 1 >= 0)
			adjacents.add(map.getTerrain(x - 1, y));
		if (x + 1 < map.getColumns())
			adjacents.add(map.getTerrain(x + 1, y));
		if (y - 1 >= 0)
			adjacents.add(map.getTerrain(x, y - 1));
		if (y + 1 < map.getRows())
			adjacents.add(map.getTerrain(x, y + 1));
		// We are guaranteed 2 squares
		for (Terrain terrain : adjacents)
			if (terrain instanceof Door) {
				if (terrain.getStandable() == false)
					((Door) terrain).setStandable(true);
				return;
			}
	}
}
