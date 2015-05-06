package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ai.*;
import view.GameView;
import view.InventoryView;
import view.ShopView;
import model.*;

public class GamePlay extends JFrame { // implements Observer{
	// private variables
	private GameMap map;
	private List<Unit> playerTeam;
	private List<Unit> aiTeam;
	private GameView console;
	private Model model;
	private AI ai;
	private Inventory inventory;
	private InventoryView inventoryView;
	private int choice = 2;
	private Model m;
	
	// constructor
	public GamePlay() {
		// make player team
		playerTeam = new ArrayList<Unit>();
//		Unit playerHero = new Hero("PlayerHero", null, 20, 15, 15, 10, 15, 15, 15, 15, 15, 5, Team.PLAYER);
//		Unit playerMelee = new Axereaver("PlayerMelee", null, 1, 1, 50, 1, 1, 1, 1, 1, 0, 1, Team.PLAYER);
//		Unit playerRanged = new Marksman("PlayerRanged", null, 1, 1, 50, 1, 1, 1, 1, 1, 1, 5, Team.PLAYER);
//		Unit playerSaint = new Saint("PlayerSaint", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2, Team.PLAYER);
//		Unit playerSorcerer = new Sorcerer("PlayerSorcerer", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 3, Team.PLAYER);
//		Unit playerAxereaver = new Axereaver("PlayerAxereaver", null, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1, Team.PLAYER);
		playerTeam = new ArrayList<Unit>();
		playerTeam.add(UnitBuilder.makeUnit(new Hero(Team.PLAYER)));
		playerTeam.add(UnitBuilder.makeUnit(new Swordmaster(Team.PLAYER)));
//		playerTeam.add(UnitBuilder.makeUnit(new Lancecaster(Team.PLAYER)));
		playerTeam.add(UnitBuilder.makeUnit(new Axereaver(Team.PLAYER)));
		playerTeam.add(UnitBuilder.makeUnit(new Marksman(Team.PLAYER)));
		playerTeam.add(UnitBuilder.makeUnit(new Saint(Team.PLAYER)));
		playerTeam.add(UnitBuilder.makeUnit(new Sorcerer(Team.PLAYER)));
//		playerTeam.add(UnitBuilder.makeUnit(new Druid(Team.PLAYER)));
//		playerTeam.add(UnitBuilder.makeUnit(new Thief(Team.PLAYER)));
		
		// make ai team
		aiTeam = new ArrayList<Unit>();
		aiTeam.add(UnitBuilder.makeUnit(new Hero(Team.COMPUTER)));
		aiTeam.add(UnitBuilder.makeUnit(new Swordmaster(Team.COMPUTER)));
//		aiTeam.add(UnitBuilder.makeUnit(new Lancecaster(Team.COMPUTER)));
		aiTeam.add(UnitBuilder.makeUnit(new Axereaver(Team.COMPUTER)));
		aiTeam.add(UnitBuilder.makeUnit(new Marksman(Team.COMPUTER)));
		aiTeam.add(UnitBuilder.makeUnit(new Saint(Team.COMPUTER)));
		aiTeam.add(UnitBuilder.makeUnit(new Sorcerer(Team.COMPUTER)));
//		aiTeam.add(UnitBuilder.makeUnit(new Druid(Team.COMPUTER)));
//		aiTeam.add(UnitBuilder.makeUnit(new Thief(Team.COMPUTER)));

		// make map
		
		try {
			this.map = new GameMap("GrassMap", this, 20, 30);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		map.setPlayerTeam(playerTeam);
		map.setAiTeam(aiTeam);
		
		
		// place units on map
				int playerCounter = 1;
				int aiCounter = 1;
				for (int i = 0; i < this.getMap().getRows(); i++) {
					for (int j = 0; j < this.getMap().getColumns(); j++) {
						if (playerCounter > playerTeam.size() && aiCounter > aiTeam.size()) {
							break;
						}
						if (this.getMap().getMap()[i][j].getPlayerSpawnPoint()) {
							if (playerCounter > playerTeam.size() && aiCounter > aiTeam.size()) {
								break;
							}
							if (this.getMap().getMap()[i][j].getHeroSpawnPoint()) {
								map.placeUnit(playerTeam.get(0), new int[]{i,j});
							} else {
								map.placeUnit(playerTeam.get(playerCounter), new int[]{i,j});
								playerCounter++;
							}
						} else if (this.getMap().getMap()[i][j].getAiSpawnPoint()) {
							if (playerCounter > playerTeam.size() && aiCounter > aiTeam.size()) {
								break;
							}
							if (this.getMap().getMap()[i][j].getHeroSpawnPoint()) {
								map.placeUnit(aiTeam.get(0), new int[]{i,j});
							} else {
								map.placeUnit(aiTeam.get(aiCounter), new int[]{i,j});
								aiCounter++;
							}
						}
					}
				}

		
		//The final implement once RF and Castle are subclassed
//		while(choice == JOptionPane.CANCEL_OPTION){
//		int quit;
//		Object[] maps = {"Riverfront", "Castle"};
//		int choice = JOptionPane.showOptionDialog(null, "Choose a Map to load", "New", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, maps, maps[0]);
//		try {
//			if(choice == JOptionPane.YES_OPTION){
//					this.map = new Riverfront("Riverfront", this, 20, 18);
//				break;
//			}
//			else if(choice == JOptionPane.NO_OPTION){
//				this.map = new Castle("Castle", this, 30, 12);
//				break;
//		}
//			else{
//				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
//					System.exit(0);
//			}
//		} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
		map.setPlayerTeam(playerTeam);
		map.setAiTeam(aiTeam);
		
//		// place units on map
//		map.placeUnit(playerTeam.get(0), new int[]{0,0});
//		map.placeUnit(playerTeam.get(1), new int[]{1,0});
//		map.placeUnit(playerTeam.get(2), new int[]{0,1});
//		map.placeUnit(playerTeam.get(3), new int[]{1,1});
//		map.placeUnit(playerTeam.get(4), new int[]{0,2});
//		map.placeUnit(playerTeam.get(5), new int[]{2,0});
//		
//		map.placeUnit(aiTeam.get(0), new int[]{19,17});
//		map.placeUnit(aiTeam.get(1), new int[]{18,17});
//		map.placeUnit(aiTeam.get(2), new int[]{19,16});
//		map.placeUnit(aiTeam.get(3), new int[]{18,16});
//		map.placeUnit(aiTeam.get(4), new int[]{19,15});
//		map.placeUnit(aiTeam.get(5), new int[]{17,17});
		
		m = new Model(this);
		ai = new AI(aiTeam, m);
		this.console = new view.GameView(m, this);

		setLayout(new BorderLayout()); // set the layout manager

		this.setPreferredSize(new Dimension(1250, 710));
		final JTabbedPane tabPane = new JTabbedPane();
		
		// Set up the Inventory
		inventory = new Inventory(25, 100);
		inventoryView = new InventoryView(inventory, this);
		tabPane.add(this.console, "Map");
		tabPane.add(inventoryView, "Inventory");
		add(tabPane, BorderLayout.CENTER);
		
		// Set up the shop
		tabPane.add(new ShopView(this), "Shop");
		add(tabPane, BorderLayout.CENTER);
		
		// Set up tab change listener
		tabPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	inventoryView.setupInventoryList(getPlayerTeam());
	        }
	    });

		// set up close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// pack the GUI
		this.pack();
		}

	
	// getters and setters
	public GameMap getMap() {
		return this.map;
	}
	
	public Model getModel(){
		return this.m;
	}
	
	public AI getAI(){
		return ai;
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
	
	public Inventory getInventory(){
		return this.inventory;
	}
	
	public InventoryView getInventoryView(){
		return inventoryView;
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
	
	public static enum Team{
		PLAYER, COMPUTER;
	}
	
	// main method
	public static void main(String[] arg) {
		GamePlay newGame = new GamePlay();
		newGame.setVisible(true);
				
		//newGame.getGameView().setConsole(newGame.getMap().returnMap());

		//newGame.getGameView().setConsole(newGame.getMap().returnMap());
		
		//newGame.getGameView().setConsole(newGame.getMap().returnMap());
		}
}