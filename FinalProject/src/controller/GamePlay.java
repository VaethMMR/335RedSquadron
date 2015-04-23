package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class GamePlay {
	// private variables
	private GameMap map;
	private List<Unit> playerTeam;
	private List<Unit> aiTeam;

	// constructor
	public GamePlay() {
		// make map
		this.map = new GameMap("GrassMap");
		
		// make player team
		playerTeam = new ArrayList<Unit>();
		Unit playerHero = new Hero("PlayerHero", null, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		Unit playerMelee = new Axereaver("PlayerMelee", null, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		Unit playerRanged = new Marksman("PlayerRanged", null, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		Unit playerSaint = new Saint("PlayerSaint", null, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		Unit playerSorcerer = new Sorcerer("PlayerSorcerer", null, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		Unit playerAxereaver = new Axereaver("PlayerAxereaver", null, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		playerTeam.add(playerHero);
		playerTeam.add(playerMelee);
		playerTeam.add(playerRanged);
		playerTeam.add(playerSaint);
		playerTeam.add(playerSorcerer);
		playerTeam.add(playerAxereaver);
		
		// make ai team
		aiTeam = new ArrayList<Unit>();
		Unit aiHero = new Hero("aiHero", null, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20);
		Unit aiMelee = new Axereaver("aiMelee", null, 0, 0, 20, 20, 0, 0, 0, 0, 0, 0);
		Unit aiRanged = new Marksman("aiRanged", null, 0, 0, 20, 20, 0, 0, 0, 0, 0, 0);
		Unit aiSaint = new Saint("aiSaint", null, 0, 0, 20, 20, 0, 0, 0, 0, 0, 0);
		Unit aiSorcerer = new Sorcerer("aiSorcerer", null, 0, 0, 20, 20, 0, 0, 0, 0, 0, 0);
		Unit aiAxereaver = new Axereaver("aiAxereaver", null, 0, 0, 20, 20, 0, 0, 0, 0, 0, 0);
		aiTeam.add(aiHero);
		aiTeam.add(aiMelee);
		aiTeam.add(aiRanged);
		aiTeam.add(aiSaint);
		aiTeam.add(aiSorcerer);
		aiTeam.add(aiAxereaver);

		// place units on map
		map.placeUnit(playerTeam.get(0), new int[]{0,0});
		map.placeUnit(playerTeam.get(1), new int[]{1,0});
		map.placeUnit(playerTeam.get(2), new int[]{0,1});
		map.placeUnit(playerTeam.get(3), new int[]{1,1});
		map.placeUnit(playerTeam.get(4), new int[]{0,2});
		map.placeUnit(playerTeam.get(5), new int[]{2,0});
		
		map.placeUnit(aiTeam.get(0), new int[]{24,24});
		map.placeUnit(aiTeam.get(1), new int[]{23,24});
		map.placeUnit(aiTeam.get(2), new int[]{24,23});
		map.placeUnit(aiTeam.get(3), new int[]{23,23});
		map.placeUnit(aiTeam.get(4), new int[]{24,22});
		map.placeUnit(aiTeam.get(5), new int[]{22,24});
	}
	
	// getters and setters
	public GameMap getMap() {
		return this.map;
	}
	
	// misc methods
	public boolean moveUnit(Unit toMove, int[] newCoordinates) {
		//int currentPosition = ;
		return true;
	}

}
