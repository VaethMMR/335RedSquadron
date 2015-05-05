package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.swing.JOptionPane;

import controller.GamePlay;

// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class GameMap extends Observable {
	// private variables
	private Terrain[][] map;
	private Map<Unit, Terrain> unitLocations = new HashMap<Unit, Terrain>();
	private List<Unit> playerTeam = new ArrayList<Unit>();
	private List<Unit> aiTeam = new ArrayList<Unit>();
	private GamePlay theGame;
	private int rows;
	private int columns;
	private String type;
	
	// constructor
	public GameMap(String type, GamePlay theGame, int columns, int rows) {
		map = new Terrain[columns][rows];
		this.rows = rows;
		this.columns = columns;
		layoutMap(type);
		this.type = type;
	}
	
	// getters and setters
	public Terrain[][] getMap() {
		return this.map;
	}
	
	public void setMap(Terrain[][] newMap) {
		this.map = newMap;
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	public Terrain getTerrain(int x, int y){
		return this.map[x][y];
	}
	
	public List<Unit> getAITeam(){return aiTeam;}
	
	public Map<Unit, Terrain> getUnitLocations() {
		return this.unitLocations;
	}
	
	public ArrayList<Unit> getUnitsOnMap() {
		List<Unit> unitsOnMap = new ArrayList<Unit>();
		unitsOnMap.addAll(this.unitLocations.keySet());
		return (ArrayList<Unit>) unitsOnMap;
	}
	
	public List<Unit> getPlayerTeam() {
		return this.playerTeam;
	}
	
	public void setPlayerTeam(List<Unit> playerTeam) {
		this.playerTeam = playerTeam;
	}
	
	public void setAiTeam(List<Unit> aiTeam) {
		this.aiTeam = aiTeam;
	}

	// misc methods
	private void layoutMap(String type) {
		
//		String[][] arr = new String[30][20];
//			for(int j = 0; j < 20; j++){
//				System.out.println("\n");
//				for(int i = 0; i < 30; i++)					
//					System.out.print("[" + i + "," + j + "]" + "\t");
//			}

		int[][] mapLayout1 = new int[columns][rows];
		
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < columns; i++) {
				if (mapLayout1[i][j] == 0) {
					Terrain newTerrainPiece = new Grass(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 1) {
					Terrain newTerrainPiece = new Dirt(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 2) {
					Terrain newTerrainPiece = new Tree(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 3) {
					Terrain newTerrainPiece = new Mountain(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 4) {
					Terrain newTerrainPiece = new Water(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 5) {
					Terrain newTerrainPiece = new Bridge(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				}
				/*if (i < 6 && j > 6 && j < 12) {
					Terrain newTerrainPiece = new Tree(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				} else if (i < 8 && j > 14 && j < 25) {
					Terrain newTerrainPiece = new Mountain(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				} else if (i > 19 && j < 7) {
					Terrain newTerrainPiece = new Water(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				} else {
					Terrain newTerrainPiece = new Grass(new int[]{i,j});
					this.map[i][j] = newTerrainPiece;
				}*/
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
		Terrain clearLocation = this.unitLocations.get(toRemove);
		clearLocation.moveUnit();
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
		
		//Make sure coordinates are in bounds
		if(newCoordinates[0] < 0 || newCoordinates[1] < 0 || newCoordinates[1] >= this.getMap()[0].length || newCoordinates[0] >= this.getMap().length)
				return false;
		// get the Terrain piece the Unit toMove is on
		// make sure the space to move to is not full
 				if (this.map[newCoordinates[0]][newCoordinates[1]].getUnit() != null) {
					if(playerTeam.contains(toMove))
						JOptionPane.showMessageDialog(null, "There is already a Unit in that space.");
					return false;
					// TODO: Throw space full exception or something
				}
		int[] currentCoordinates = {this.unitLocations.get(toMove).getLocation()[0], this.unitLocations.get(toMove).getLocation()[1]};
		return moveUnit(currentCoordinates, newCoordinates);
	}
	
	// move Unit by passing in the location
	public boolean moveUnit(int[] currentCoordinates, int[] newCoordinates) {
		// get the Terrain piece and the Unit on it
		Terrain location = this.map[currentCoordinates[0]][currentCoordinates[1]];
		// make sure there is a Unit here
		if (location.getUnit() == null) {
			JOptionPane.showMessageDialog(null, "Unit not found at given move coordinates");
			// TODO: throw exception
			return false;
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
		int spacesToMove = horizontal + vertical;
		if (spacesToMove > toMove.getMovement()) {
			JOptionPane.showMessageDialog(null, "Specified coordinates are out of this Unit's movement range.");
			// TODO: Throw exception
			return false;
		}

		// move toMove to the new location
		location.moveUnit();
		Terrain newLocation = this.map[newCoordinates[0]][newCoordinates[1]];
		newLocation.setUnit(toMove);
		this.unitLocations.put(toMove, newLocation);
		return true;
	}
	
	public List<Unit> getInRangeAiUnits(Unit theUnit) {
		List<Unit> inRangeUnits = new ArrayList<Unit>();
		int[] location = this.getUnitLocations().get(theUnit).getLocation();
		int range = theUnit.getResistance();
		int[] attackWidth = new int[] {location[0] - range - 1, location[0] + range + 1};
		int[] attackHeight = new int[] {location[1] - range - 1, location[1] + range + 1};
		if (attackWidth[0] < 0) {
			attackWidth[0] = 0;
		}
		if (attackWidth[1] > rows-1) {
			attackWidth[1] = rows-1;
		}
		if (attackHeight[0] < 0) {
			attackHeight[0] = 0;
		}
		if (attackHeight[1] > columns-1) {
			attackHeight[1] = columns-1;
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
	
	public List<Unit> getInRangePlayerUnits(Unit theUnit) {
		List<Unit> inRangeUnits = new ArrayList<Unit>();
		int[] location = this.getUnitLocations().get(theUnit).getLocation();
		int range = theUnit.getResistance();
		int[] attackWidth = new int[] {location[0] - range - 1, location[0] + range + 1};
		int[] attackHeight = new int[] {location[1] - range - 1, location[1] + range + 1};
		if (attackWidth[0] < 0) {
			attackWidth[0] = 0;
		}
		if (attackWidth[1] > rows-1) {
			attackWidth[1] = rows-1;
		}
		if (attackHeight[0] < 0) {
			attackHeight[0] = 0;
		}
		if (attackHeight[1] > columns-1) {
			attackHeight[1] = columns-1;
		}
		for (int i = attackWidth[0]; i < attackWidth[1]; i++) {
			for (int j = attackHeight[0]; j < attackHeight[1]; j++) {
				if (this.getMap()[i][j].getUnit() != null) {
					if (this.playerTeam.contains(this.getMap()[i][j].getUnit())) {
						inRangeUnits.add(this.getMap()[i][j].getUnit());
					}
				}
			}
		}
		return inRangeUnits;
	}
	
	// print the map, with Units, to the console
	public void printMap() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.println(this.map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// return the map as text
	public String returnMap() {
		String mapString = "";
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < columns; i++) {
				mapString += this.map[i][j];
			}
			mapString += "\n";
		}
		return mapString;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

}
