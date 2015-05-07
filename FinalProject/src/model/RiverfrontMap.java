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

public class RiverfrontMap {
	private Terrain[][] map;
	public RiverfrontMap() {
		map = new Terrain[20][30];
	}
	
	public Terrain[][] buildMap() throws IOException {
		this.layoutMap();
		return this.map;
	}
	
	private void layoutMap() throws IOException {
		char[][] mapLayout1 = {
				{ 't', 't', 't', 't', 't', 't', 't', 't', 't', 'h', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', 't', },
				{ 't', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'u', 'u', 'u', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 't', 't', 't', 't', 't', 't', 't', },
				{ 't', 'G', 'G', 'G', 'G', 'd', 'd', 'd', 'u', 't', 'u', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 't', 't', 't', 't', 't', 't', 't', },
				{ 't', 'G', 'G', 'G', 'G', 'd', 'G', 'G', 'u', 'u', 'u', 'G', 't', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 't', 't', 't', 't', 't', 't', 't', },
				{ 't', 'G', 'G', 'G', 'G', 'd', 'G', 'G', 'G', 'd', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 't', 't', 't', 't', 't', 't', 't', },
				{ 't', 'G', 'G', 'G', 'G', 'e', 'G', 'G', 'G', 'e', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 't', 't', 'l', 'c', 'r', 't', 't', },
				{ 's', 's', 's', 's', 's', 'b', 's', 's', 's', 'b', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 'b', 's', 's', 's', },
				{ 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', },
				{ 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', },
				{ 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', },
				{ 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'b', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'b', 'w', 'w', 'w', },
				{ 'G', 'G', 'G', 'G', 'G', 'd', 'd', 'd', 'd', 'd', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', },
				{ 'g', 'g', 'g', 't', 'g', 't', 'g', 'g', 'd', 't', 'g', 'g', 'g', 'g', 'g', 'g', 't', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', },
				{ 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'd', 'g', 'g', 'g', 'g', 't', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'g', 'm', 'd', 't', 'g', 't', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'U', 'U', 'U', 'g', 'g', 'g', 'g', 'g', 'g', 'g', },
				{ 'm', 'm', 'm', 'm', 'm', 'f', 'g', 'm', 'd', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'U', 'H', 'U', 'g', 'g', 'g', 'g', 'g', 'g', 'g', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'd', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'U', 'U', 'U', 'g', 'g', 'g', 'g', 'g', 'g', 'g', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'd', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'd', 'g', 't', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', },
				{ 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'd', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', }, };

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
