package model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class GameMap {
	// private variables
	private Terrain[][] map;
	private Map<Unit, Terrain> unitLocations = new HashMap<Unit, Terrain>();
	private List<Unit> playerTeam = new ArrayList<Unit>();
	private List<Unit> aiTeam = new ArrayList<Unit>();
	
	// constructor
	public GameMap(String type) {
		map = new Terrain[25][25];
		layoutMap(type);
	}
	
	// getters and setters
	public Terrain[][] getMap() {
		return this.map;
	}
	
	public Map<Unit, Terrain> getUnitLocations() {
		return this.unitLocations;
	}
	
	public ArrayList<Unit> getUnitsOnMap() {
		List<Unit> unitsOnMap = new ArrayList<Unit>();
		unitsOnMap.addAll(this.unitLocations.keySet());
		return (ArrayList<Unit>) unitsOnMap;
	}
	
	public void setPlayerTeam(List<Unit> playerTeam) {
		this.playerTeam = playerTeam;
	}
	
	public void setAiTeam(List<Unit> aiTeam) {
		this.aiTeam = aiTeam;
	}

	// misc methods
	private void layoutMap(String type) {
		/*if (type == "something") {
			// TODO do something
		} else {
			// TODO do something
		}*/
		
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				Terrain newTerrainPiece = new Grass(new int[]{i,j});
				this.map[i][j] = newTerrainPiece;
			}
		}
	}
	
	public boolean placeUnit(Unit newUnit, int[] coordinates) {
		Terrain location = this.map[coordinates[0]][coordinates[1]];
		if (location.setUnit(newUnit)) {
			this.unitLocations.put(newUnit, location);
			return true;
		} else {
			return false;
		}
		// TODO: throw exception if location taken, maybe put exception in Terrain and do Try/Catch here
	}
	
	public boolean removeUnit(Unit toRemove) {
		this.unitLocations.get(toRemove).setUnit(null);
		this.unitLocations.remove(toRemove);
		return true;
		// TODO: some sort of exception thing
	}
	
	// move Unit by passing in the Unit
	public boolean moveUnit(Unit toMove, int[] newCoordinates) {
		// make sure the Unit toMove is on the map
		if (!this.unitLocations.containsKey(toMove)) {
			return false;
		}
		// get the Terrain piece the Unit toMove is on
		int[] currentCoordinates = this.unitLocations.get(toMove).getLocation();
		return moveUnit(currentCoordinates, newCoordinates);
	}
	
	// move Unit by passing in the location
	public boolean moveUnit(int[] currentCoordinates, int[] newCoordinates) {
		// get the Terrain piece and the Unit on it
		Terrain location = this.map[currentCoordinates[0]][currentCoordinates[1]];
		// make sure there is a Unit here
		if (location.getUnit() == null) {
			return false;
		}
		// make sure the space to move to is not full
		if (this.map[newCoordinates[0]][newCoordinates[1]].getUnit() != null) {
			return false;
			// TODO: Throw space full exception or something
		}
		Unit toMove = location.getUnit();
		// make sure the new location is within toMove's movement range
		int horizontal = (currentCoordinates[0] - newCoordinates[0]);
		if (horizontal < 0) {
			horizontal *= -1;
		}
		int vertical = (currentCoordinates[1] - newCoordinates[1]);
		if (vertical < 0) {
			vertical *= -1;
		}
		int spacesToMove = horizontal + vertical - 1;
		if (spacesToMove > toMove.getMovement()) {
			// TODO: Throw exception
			System.out.println(toMove.getName() + toMove.getMovement());
			return false;
		}

		// move toMove to the new location
		location.moveUnit();
		Terrain newLocation = this.map[newCoordinates[0]][newCoordinates[1]];
		newLocation.setUnit(toMove);
		this.unitLocations.put(toMove, newLocation);
		return true;
	}
	
	public List<Unit> getInRangeUnits(Unit theUnit) {
		List<Unit> inRangeUnits = new ArrayList<Unit>();
		int[] location = this.getUnitLocations().get(theUnit).getLocation();
		int range = theUnit.getResistance();
		int[] attackWidth = new int[] {location[0] - range + 1, location[0] + range + 1};
		int[] attackHeight = new int[] {location[1] - range + 1, location[1] + range + 1};
		if (attackWidth[0] < 0) {
			attackWidth[0] = 0;
		}
		if (attackWidth[1] > 24) {
			attackWidth[1] = 25;
		}
		if (attackHeight[0] < 0) {
			attackHeight[0] = 0;
		}
		if (attackHeight[1] > 24) {
			attackHeight[1] = 25;
		}
		for (int i = attackWidth[0]; i < attackWidth[1]; i++) {
			for (int j = attackHeight[0]; j < attackHeight[1]; j++) {
				if (this.getMap()[i][j].getUnit() != null) {
					if (this.aiTeam.contains(this.getMap()[i][j].getUnit())) {
						inRangeUnits.add(this.getMap()[i][j].getUnit());
					}
				}
			}
		}
		return inRangeUnits;
	}
	
	// print the map, with Units, to the console
	public void printMap() {
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				if (this.map[i][j].getClass().getSimpleName().equals("Grass")) {
					if (this.map[i][j].getUnit() == null) {
						System.out.print("[ ]");
					} else {
						if (this.map[i][j].getUnit() instanceof Hero) {
							System.out.print("[H]");							
						} else if (this.map[i][j].getUnit() instanceof Marksman) {
							System.out.print("[R]");							
						} else if (this.map[i][j].getUnit() instanceof Saint) {
							System.out.print("[S]");							
						} else if (this.map[i][j].getUnit() instanceof Sorcerer) {
							System.out.print("[s]");							
						} else if (this.map[i][j].getUnit() instanceof Axereaver) {
							System.out.print("[A]");							
						}
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// return the map as text
	public String returnMap() {
		String mapString = "";
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				if (this.map[i][j].getClass().getSimpleName().equals("Grass")) {
					if (this.map[i][j].getUnit() == null) {
						mapString += "[ ]";
					} else {
						if (this.map[i][j].getUnit() instanceof Hero) {
							mapString += "[H]";
						} else if (this.map[i][j].getUnit() instanceof Marksman) {
							mapString += "[M]";
						} else if (this.map[i][j].getUnit() instanceof Saint) {
							mapString += "[S]";
						} else if (this.map[i][j].getUnit() instanceof Sorcerer) {
							mapString += "[s]";
						} else if (this.map[i][j].getUnit() instanceof Axereaver) {
							mapString += "[A]";
						}
					}
				}
			}
			mapString += "\n";
		}
		return mapString;
	}
}
