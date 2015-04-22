package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class GamePlay {
	// private variables
	private Map map;
	private List<Unit> playerTeam;
	private List<Unit> aiTeam;

	// constructor
	public GamePlay() {
		// make map
		this.map = new Map("GrassMap");
		
		// make player team
		playerTeam = new ArrayList<Unit>();
		Unit playerHero = new Hero(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit playerMelee = new Melee(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit playerRanged = new Ranged(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit playerSaint = new Saint(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit playerSorcerer = new Sorcerer(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit playerAxereaver = new Axereaver(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		playerTeam.add(playerHero);
		playerTeam.add(playerMelee);
		playerTeam.add(playerRanged);
		playerTeam.add(playerSaint);
		playerTeam.add(playerSorcerer);
		playerTeam.add(playerAxereaver);
		
		// make ai team
		aiTeam = new ArrayList<Unit>();
		Unit aiHero = new Hero(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit aiMelee = new Melee(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit aiRanged = new Ranged(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit aiSaint = new Saint(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit aiSorcerer = new Sorcerer(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Unit aiAxereaver = new Axereaver(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		aiTeam.add(aiHero);
		aiTeam.add(aiMelee);
		aiTeam.add(aiRanged);
		aiTeam.add(aiSaint);
		aiTeam.add(aiSorcerer);
		aiTeam.add(aiAxereaver);

		// place units on map
		map.getMap()[0][0].setUnit(playerTeam.get(0));
		map.getMap()[1][0].setUnit(playerTeam.get(1));
		map.getMap()[0][1].setUnit(playerTeam.get(2));
		map.getMap()[1][1].setUnit(playerTeam.get(3));
		map.getMap()[0][2].setUnit(playerTeam.get(4));
		map.getMap()[2][0].setUnit(playerTeam.get(5));

		map.getMap()[24][24].setUnit(aiTeam.get(0));
		map.getMap()[23][24].setUnit(aiTeam.get(1));
		map.getMap()[24][23].setUnit(aiTeam.get(2));
		map.getMap()[23][23].setUnit(aiTeam.get(3));
		map.getMap()[24][22].setUnit(aiTeam.get(4));
		map.getMap()[22][24].setUnit(aiTeam.get(5));
	}
	
	// getters and setters
	public Map getMap() {
		return this.map;
	}

}
