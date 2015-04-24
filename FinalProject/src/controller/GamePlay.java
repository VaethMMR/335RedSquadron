package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import view.GameView;
import model.*;

public class GamePlay extends JFrame {
	// private variables
	private GameMap map;
	private List<Unit> playerTeam;
	private List<Unit> aiTeam;
	private GameView console;
	private Model model;
	private Ai ai;
	
	// constructor
	public GamePlay() {
		
		// make player team
		playerTeam = new ArrayList<Unit>();
		Unit playerHero = new Hero("PlayerHero", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1);
		Unit playerMelee = new Axereaver("PlayerMelee", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1);
		Unit playerRanged = new Marksman("PlayerRanged", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 5);
		Unit playerSaint = new Saint("PlayerSaint", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2);
		Unit playerSorcerer = new Sorcerer("PlayerSorcerer", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 3);
		Unit playerAxereaver = new Axereaver("PlayerAxereaver", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1);
		playerTeam.add(playerHero);
		playerTeam.add(playerMelee);
		playerTeam.add(playerRanged);
		playerTeam.add(playerSaint);
		playerTeam.add(playerSorcerer);
		playerTeam.add(playerAxereaver);
		
		// make ai team
		aiTeam = new ArrayList<Unit>();
		Unit aiHero = new Hero("aiHero", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1);
		Unit aiMelee = new Axereaver("aiMelee", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1);
		Unit aiRanged = new Marksman("aiRanged", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 5);
		Unit aiSaint = new Saint("aiSaint", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2);
		Unit aiSorcerer = new Sorcerer("aiSorcerer", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 3);
		Unit aiAxereaver = new Axereaver("aiAxereaver", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1);
		aiTeam.add(aiHero);
		aiTeam.add(aiMelee);
		aiTeam.add(aiRanged);
		aiTeam.add(aiSaint);
		aiTeam.add(aiSorcerer);
		aiTeam.add(aiAxereaver);

		// make map
		this.map = new GameMap("GrassMap", this);
		map.setPlayerTeam(playerTeam);
		map.setAiTeam(aiTeam);
		
		this.console = new view.GameView(this);		
		
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
		
		this.model = new Model(this);

		setLayout(new BorderLayout()); // set the layout manager
		this.setPreferredSize(new Dimension(530, 625));
		// add the ICritterView JPanel
		this.add(console, BorderLayout.CENTER);

		// set up close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// pack the GUI
		this.pack();
	}
	
	// getters and setters
	public GameMap getMap() {
		return this.map;
	}
	
	public GameView getGameView() {
		return this.console;
	}
	
	public List<Unit> getPlayerTeam() {
		return this.playerTeam;
	}
	
	public List<Unit> getAiTeam() {
		return this.aiTeam;
	}
	
	// misc methods
	public boolean moveUnit(Unit toMove, int[] newCoordinates) {
		//int currentPosition = ;
		return true;
	}
	
	public boolean removeUnit(Unit toRemove) {
		//return this.getPlayerTeam().remove(toRemove);
		return true;
	}
	
	// main method
	public static void main(String[] arg) {
		GamePlay newGame = new GamePlay();
		newGame.setVisible(true);
				
		newGame.getGameView().setConsole(newGame.getMap().returnMap());

		newGame.getGameView().setConsole(newGame.getMap().returnMap());
		
		newGame.getGameView().setConsole(newGame.getMap().returnMap());
		}

}
