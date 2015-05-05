package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ai.AI;
import view.GameView;
import view.InventoryView;
import view.ShopView;
import model.*;

@SuppressWarnings("serial") // It's warning that we need a serial ID here,
							// but don't we only need that in Model?
public class GamePlay extends JFrame {
	// private variables
	private GameMap map;
	private List<Unit> playerTeam;
	private List<Unit> aiTeam;
	private GameView console;
	private Model model;
	private AI ai;
	private Inventory inventory;
	private InventoryView inventoryView;
	
	// constructor
	public GamePlay() throws IOException {
		
		// make player team
		playerTeam = new ArrayList<Unit>();
		Unit playerHero = new Hero("PlayerHero", null, 20, 15, 15, 10, 15, 15, 15, 15, 15, 1);
		Unit playerMelee = new Axereaver("PlayerMelee", null, 1, 1, 10, 1, 1, 1, 1, 1, 0, 1);
		Unit playerRanged = new Marksman("PlayerRanged", null, 1, 1, 10, 1, 1, 1, 1, 1, 1, 5);
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
		this.map = new GameMap("GrassMap", this, 20, 30);
		map.setPlayerTeam(playerTeam);
		map.setAiTeam(aiTeam);
		
		this.console = new view.GameView(this);
		
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
		
		this.model = new Model(this);
		ai = new AI(aiTeam, model);

		setLayout(new BorderLayout()); // set the layout manager

		this.setPreferredSize(new Dimension(1250, 710));
		final JTabbedPane tabPane = new JTabbedPane();

		
		// Set up the Inventory
		inventory = new Inventory(25, 50);
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
	
	// main method
	public static void main(String[] arg) throws IOException {
		GamePlay newGame = new GamePlay();
		newGame.setVisible(true);
		}

}
