package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class Map {
	// private variables
	private Terrain[][] map;
	
	// constructor
	public Map(String type) {
		map = new Terrain[25][25];
		layoutMap(type);
	}
	
	// getters and setters
	public Terrain[][] getMap() {
		return this.map;
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
				Terrain newTerrainPiece = new Grass();
				this.map[i][j] = newTerrainPiece;
			}
		}
	}
	
	public void printMap() {
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				if (this.map[i][j].getClass().getSimpleName().equals("Grass")) {
					if (this.map[i][j].getUnit() == null) {
						System.out.print("[ ]");
					} else {
						System.out.print("[U]");
					}
				}
			}
			System.out.println();
		}
	}
}
