package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javax.swing.JOptionPane;

import terrain.Terrain;
import view.InventoryView;
import controller.GamePlay;
import exceptions.TileNotStandableException;
import exceptions.TileOccupiedException;

// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class GameMap extends Observable {
	// private variables
	private Terrain[][] map;
	private Map<Unit, Terrain> unitLocations = new HashMap<Unit, Terrain>();
	private List<Unit> playerTeam = new ArrayList<Unit>();
	private List<Unit> aiTeam = new ArrayList<Unit>();
	// private GamePlay theGame;
	private int rows;
	private int columns;

	// constructor
	public GameMap(String type, GamePlay theGame, int rows, int columns)
			throws IOException {
		this.rows = rows;
		this.columns = columns;
		if (type == "Riverfront") {
			map = new RiverfrontMap().buildMap();
		} else {
			map = new MountainMap().buildMap();
		}
	}

	// getters and setters
	public Terrain[][] getMap() {
		return this.map;
	}

	public void setMap(Terrain[][] newMap) {
		this.map = newMap;
		notifyObservers();
	}

	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}

	public Map<Unit, Terrain> getUnitLocations() {
		return this.unitLocations;
	}

	/**
	 * This method returns the units currently on the map
	 * 
	 * @return unitsOnMap
	 * 			  returns an ArrayList<Unit> for the units on the map           
	 */
	public List<Unit> getUnitsOnMap() {
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
	
	/**
	 * This method gets the highlighted terrain pieces
	 * 
	 * @return highlightedTiles
	 * 			  returns an ArrayList<Terrain> for the highlighted tiles           
	 */
	public List<Terrain> getHighlightedTiles() {
		ArrayList<Terrain> highlightedTiles = new ArrayList<Terrain>();
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				if (this.map[i][j].hasMoveHighlight() || this.map[i][j].hasAttackHighlight()) {
					highlightedTiles.add(this.map[i][j]);
				}
			}
		}
		return highlightedTiles;
	}

	// misc methods
	/**
	 * This method places a unit on the specified terrain piece on the map
	 * 
	 * @param newUnit
	 *            An instance of the Unit
	 * @param coordinates
	 *            an int[] of coordinates
	 * @return boolean
	 * 			  returns true if unit was successfully placed
	 * @throws TileOccupiedException
	 * 		      occurs if tile is occupied
	 * @throws TileNotStandableException
	 * 		      occurs if tile isn't standable           
	 */
	public boolean placeUnit(Unit newUnit, int[] coordinates) {
		Terrain location = this.map[coordinates[0]][coordinates[1]];
		try {
			location.setUnit(newUnit);
			this.unitLocations.put(newUnit, location);
			return true;
		} catch (TileOccupiedException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		} catch (TileNotStandableException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}

	/**
	 * This method removes a unit from a terrain piece
	 * 
	 * @param toRemove
	 *            A Unit to remove
	 * 
	 * @return boolean
	 * 			  returns true if the unit was removed           
	 */
	public boolean removeUnit(Unit toRemove) {
		// make sure the Unit toRemove is on the map
		if (!this.unitLocations.containsKey(toRemove)) {
			return false;
		}
		Terrain clearLocation = this.unitLocations.get(toRemove);
		clearLocation.moveUnit();
		this.unitLocations.remove(toRemove);
		return true;
	}

	/**
	 * This method moves a unit to a different location
	 * 
	 * @param toMove
	 *            A Unit to move
	 *            
	 * @param newCoordinates
	 *            int[] new coordinates to move to
	 * 
	 * @return boolean
	 * 			  returns true if the unit was moved           
	 */
	public boolean moveUnit(Unit toMove, int[] newCoordinates) {
		// make sure the Unit toMove is on the map
		if (!this.unitLocations.containsKey(toMove)) {
			return false;
		}
		// Make sure coordinates are in bounds
		if (newCoordinates[0] < 0 || newCoordinates[1] < 0
				|| newCoordinates[0] >= this.getRows()
				|| newCoordinates[1] >= this.getColumns()) {
			return false;
		}
		// get the Terrain piece the Unit toMove is on
		// make sure the space to move to is not full
		if (this.map[newCoordinates[0]][newCoordinates[1]].getUnit() != null) {
			if (playerTeam.contains(toMove)) {
				return false;
			}
			// TODO: make ai handle exception instead of checking for true/false return
			return false;
		} else if (!this.map[newCoordinates[0]][newCoordinates[1]].getStandable()) {
			return false;
		}
		int[] currentCoordinates = this.unitLocations.get(toMove).getLocation();
		boolean moved = moveUnit(currentCoordinates, newCoordinates);
		notifyObs();
		return moved;
	}

	// move Unit by passing in the location
	private boolean moveUnit(int[] currentCoordinates, int[] newCoordinates) {
		// get the Terrain piece and the Unit on it
		Terrain location = this.map[currentCoordinates[0]][currentCoordinates[1]];
		// make sure there is a Unit here
		if (location.getUnit() == null) {
			JOptionPane.showMessageDialog(null,
					"Unit not found at given move coordinates");
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
		int spacesToMove = horizontal + vertical - 1;
		if (spacesToMove > toMove.getMovement()) {
			JOptionPane
					.showMessageDialog(null,
							"Specified coordinates are out of this Unit's movement range.");
			return false;
		}

		// move toMove to the new location
		location.moveUnit();
		Terrain newLocation = this.map[newCoordinates[0]][newCoordinates[1]];
		newLocation.setUnit(toMove);
		this.unitLocations.put(toMove, newLocation);
		return true;
	}

	/**
	 * This method gets the in range  ai units
	 * 
	 * @param theUnit
	 *            A Unit to get a list of its in range units for
	 * 
	 * @return inRangeUnits
	 * 			  returns a List<Unit> of in range ai units           
	 */
	public List<Unit> getInRangeAiUnits(Unit theUnit) {
		List<Unit> inRangeUnits = new ArrayList<Unit>();
		int[] location = this.getUnitLocations().get(theUnit).getLocation();
		int range = theUnit.getResistance();
		int[] attackWidth = new int[] { location[0] - range - 1,
				location[0] + range + 1 };
		int[] attackHeight = new int[] { location[1] - range - 1,
				location[1] + range + 1 };
		if (attackWidth[0] < 0) {
			attackWidth[0] = 0;
		}
		if (attackWidth[1] > rows - 1) {
			attackWidth[1] = rows - 1;
		}
		if (attackHeight[0] < 0) {
			attackHeight[0] = 0;
		}
		if (attackHeight[1] > columns - 1) {
			attackHeight[1] = columns - 1;
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

	/**
	 * This method gets the in range  player units
	 * 
	 * @param theUnit
	 *            A Unit to get a list of its in range units for
	 * 
	 * @return inRangeUnits
	 * 			  returns a List<Unit> of in range player units           
	 */
	public List<Unit> getInRangePlayerUnits(Unit theUnit) {
		List<Unit> inRangeUnits = new ArrayList<Unit>();
		int[] location = this.getUnitLocations().get(theUnit).getLocation();
		int range = theUnit.getResistance();
		int[] attackWidth = new int[] { location[0] - range - 1,
				location[0] + range + 1 };
		int[] attackHeight = new int[] { location[1] - range - 1,
				location[1] + range + 1 };
		if (attackWidth[0] < 0) {
			attackWidth[0] = 0;
		}
		if (attackWidth[1] > rows - 1) {
			attackWidth[1] = rows - 1;
		}
		if (attackHeight[0] < 0) {
			attackHeight[0] = 0;
		}
		if (attackHeight[1] > columns - 1) {
			attackHeight[1] = columns - 1;
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
	
	/**
	 * This method gets the possible moves of a unit
	 * 
	 * @param theUnit
	 *            A Unit to get a list of its in range units for
	 * 
	 * @return possibleMoves
	 * 			  returns a List<Terrain> of of possible terrain pieces to move to           
	 */
	public List<Terrain> getPossibleMoves(Unit theUnit) {
		ArrayList<Terrain> possibleMoves = new ArrayList<Terrain>();
		int[] location = this.getUnitLocations().get(theUnit).getLocation();
		int movement = theUnit.getMovement();
		int negYBound, negXBound, posYBound, posXBound;
		if ((location[0] - movement) < 0) {
			negYBound = 0;
		} else {
			negYBound = location[0] - movement;
		}
		if ((location[1] - movement) < 0) {
			negXBound = 0;
		} else {
			negXBound = location[1] - movement;
		}
		if ((location[0] + movement) > this.rows) {
			posYBound = this.rows;
		} else {
			posYBound = location[0] + movement;
		}
		if ((location[1] + movement) > this.columns) {
			posXBound = this.columns;
		} else {
			posXBound = location[1] + movement;
		}
		for (int i = negYBound; i < posYBound; i++) {
			for (int j = negXBound; j < posXBound; j++) {
				// make sure the new location is within toMove's movement range
				int horizontal = (location[0] - i);
				if (horizontal < 0) {
					horizontal *= -1;
				}
				int vertical = (location[1] - j);
				if (vertical < 0) {
					vertical *= -1;
				}
				int spacesToMove = horizontal + vertical - 1;
				if (spacesToMove <= movement) {
					if (this.map[i][j].getUnit() == null && this.map[i][j].getStandable()) {
						possibleMoves.add(this.map[i][j]);
					}
				}
			}
		}
		return possibleMoves;
	}
	
	/**
	 * This method gets the possible attack moves
	 * 
	 * @param theUnit
	 *            A Unit to get a list of its attack moves for
	 * 
	 * @return possibleAttacks
	 * 			  returns a List<Terrain> of possible attacks           
	 */
	public List<Terrain> getPossibleAttacks(Unit theUnit) {
		ArrayList<Terrain> possibleAttacks = new ArrayList<Terrain>();
		int[] location = this.getUnitLocations().get(theUnit).getLocation();
		int range = theUnit.getWeapon().getRange();
		int negYBound, negXBound, posYBound, posXBound;
		if ((location[0] - range) < 0) {
			negYBound = 0;
		} else {
			negYBound = location[0] - range;
		}
		if ((location[1] - range) < 0) {
			negXBound = 0;
		} else {
			negXBound = location[1] - range;
		}
		if ((location[0] + range) > this.rows) {
			posYBound = this.rows;
		} else {
			posYBound = location[0] + range + 1;
		}
		if ((location[1] + range + 1) > this.columns) {
			posXBound = this.columns;
		} else {
			posXBound = location[1] + range + 1;
		}
		
		for (int i = negYBound; i < posYBound; i++) {
			for (int j = negXBound; j < posXBound; j++) {
				
				// make sure the new location is within toMove's movement range
				int horizontal = (location[0] - i);
				if (horizontal < 0) {
					horizontal *= -1;
				}
				int vertical = (location[1] - j);
				if (vertical < 0) {
					vertical *= -1;
				}
				int spacesToAttack = horizontal + vertical - 1;
				if (spacesToAttack <= range) {
					if (this.map[i][j].getStandable()) {
						if (!this.playerTeam.contains(this.getMap()[i][j].getUnit())) {
							possibleAttacks.add(this.map[i][j]);
						}
					}
				}
			}
		}
		return possibleAttacks;
	}

	private void notifyObs() {
		this.setChanged();
		this.notifyObservers();
	}

	public Terrain getTerrain(int y, int x) {
		return map[x][y];
	}

	public List<Unit> getAITeam() {
		return aiTeam;
	}
	
	public boolean layTrap(Unit theUnit, Trap theTrap){
		Terrain location = this.getUnitLocations().get(theUnit);
		int[] newLocation = location.getLocation();
		int y = newLocation[1];
		int x = newLocation[0]+1;
		if((this.getMap()[x][y].getStandable() == true) && (this.getMap()[x][y].getUnit() == null)){
			this.getMap()[x][y].setItem(theTrap);
			this.notifyObservers();
		}
		return false;	
	}
}
