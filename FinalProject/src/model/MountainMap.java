package model;

import java.io.IOException;

import terrain.Bridge;
import terrain.BridgeEntry;
import terrain.Dirt;
import terrain.Fort;
import terrain.Gate;
import terrain.Grass;
import terrain.LeftGate;
import terrain.Mountain;
import terrain.RightGate;
import terrain.Shore;
import terrain.Terrain;
import terrain.Tree;
import terrain.Water;

public class MountainMap {
	private Terrain[][] map;
	public MountainMap() {
		map = new Terrain[20][30];
	}
	
	public Terrain[][] buildMap() throws IOException {
		this.layoutMap();
		return this.map;
	}
	
	private void layoutMap() throws IOException {
		char[][] mapLayout1 = {
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'h', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', },
				{ 'm', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'u', 'u', 'u', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'm', 'm', 'm', 'm', 'm', 'm', 'm', },
				{ 'm', 'G', 'G', 'G', 'G', 'd', 'd', 'd', 'u', 'm', 'u', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'd', 'd', 'd', 'd', 'm', 'm', 'm', },
				{ 'm', 'G', 'G', 'G', 'G', 'd', 'G', 'G', 'u', 'u', 'u', 'G', 'm', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'm', 'm', 'm', 'd', 'm', 'm', 'm', },
				{ 'm', 'G', 'G', 'G', 'G', 'd', 'G', 'G', 'G', 'd', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'm', 'm', 'm', 'd', 'm', 'm', 'm', },
				{ 'm', 'G', 'G', 'G', 'G', 'e', 'G', 'G', 'G', 'e', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'e', 'G', 'G', 'G', 'G', 'G', 'm', 'm', 'l', 'c', 'r', 'm', 'm', },
				{ 's', 's', 's', 's', 's', 'b', 's', 's', 's', 'b', 's', 's', 's', 's', 's', 's', 's', 'b', 's', 's', 's', 's', 's', 's', 's', 's', 'b', 's', 's', 's', },
				{ 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'b', 'm', 'm', 'm', },
				{ 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'b', 'm', 'm', 'm', },
				{ 'd', 'd', 'd', 'd', 'd', 'b', 'd', 'd', 'd', 'b', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'b', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'b', 'd', 'd', 'd', },
				{ 'd', 'd', 'd', 'd', 'd', 'b', 'd', 'd', 'd', 'b', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'b', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'b', 'd', 'd', 'd', },
				{ 'G', 'G', 'G', 'G', 'G', 'd', 'd', 'd', 'd', 'd', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', },
				{ 'g', 'g', 'g', 't', 'g', 't', 'g', 'g', 'd', 'm', 'm', 'm', 't', 't', 't', 't', 't', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 't', 'g', 't', 't', 't', },
				{ 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'd', 'm', 'm', 'm', 't', 't', 't', 't', 'm', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 't', 't', 't', 't', 't', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'g', 'm', 'd', 'm', 'm', 'm', 't', 't', 't', 't', 'm', 'g', 'g', 'g', 'U', 'U', 'U', 'g', 'g', 't', 't', 't', 't', 't', },
				{ 'm', 'm', 'm', 'm', 'm', 'f', 'g', 'm', 'd', 'm', 'm', 'm', 'm', 't', 't', 'm', 'g', 'g', 'g', 'g', 'U', 'H', 'U', 'g', 'g', 'g', 't', 't', 't', 't', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'd', 'm', 'm', 'm', 'm', 'm', 'm', 'g', 'g', 'g', 'g', 'g', 'U', 'U', 'U', 'g', 't', 'g', 't', 't', 't', 't', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'd', 'm', 'm', 'm', 'm', 'm', 'G', 'G', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 't', 't', 't', 't', 't', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'd', 'm', 't', 'm', 'm', 'G', 'G', 'G', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 't', 't', 't', 't', 't', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'd', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', }, };

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 30; j++) {
				if (mapLayout1[i][j] == 'g') {
					Terrain newTerrainPiece = new Grass(new int[] { i, j },
							true);
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'G') {
					Terrain newTerrainPiece = new Grass(new int[] { i, j },
							false);
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'd') {
					Terrain newTerrainPiece = new Dirt(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'U') {
					Terrain newTerrainPiece = new Grass(new int[] { i, j }, true);
					newTerrainPiece.setPlayerSpawnPoint(true);
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'H') {
					Terrain newTerrainPiece = new Grass(new int[] { i, j }, true);
					newTerrainPiece.setPlayerSpawnPoint(true);
					newTerrainPiece.setHeroSpawnPoint(true);
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'u') {
					Terrain newTerrainPiece = new Dirt(new int[] { i, j });
					newTerrainPiece.setAiSpawnPoint(true);
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'h') {
					Terrain newTerrainPiece = new Dirt(new int[] { i, j });
					newTerrainPiece.setAiSpawnPoint(true);
					newTerrainPiece.setHeroSpawnPoint(true);
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 't') {
					Terrain newTerrainPiece = new Tree(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'm') {
					Terrain newTerrainPiece = new Mountain(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'w') {
					Terrain newTerrainPiece = new Water(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'b') {
					Terrain newTerrainPiece = new Bridge(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'e') {
					Terrain newTerrainPiece = new BridgeEntry(
							new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 's') {
					Terrain newTerrainPiece = new Shore(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'f') {
					Terrain newTerrainPiece = new Fort(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'l') {
					Terrain newTerrainPiece = new LeftGate(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'c') {
					Terrain newTerrainPiece = new Gate(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
				if (mapLayout1[i][j] == 'r') {
					Terrain newTerrainPiece = new RightGate(new int[] { i, j });
					this.map[i][j] = newTerrainPiece;
				}
			}
		}
	}
}
