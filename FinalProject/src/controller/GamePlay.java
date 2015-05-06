package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ai.*;
import terrain.Terrain;
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
	private JMenu menu;
	private JMenuItem loader;
	private JMenuItem saver;
	private Model m;
	private Random rand;
	
	// constructor
	public GamePlay() {
		// make player team
		playerTeam = new ArrayList<Unit>();
		playerTeam.add(UnitBuilder.makeUnit(Hero.getHero()));
		playerTeam.add(UnitBuilder.makeUnit(new Swordmaster(Team.PLAYER)));
		playerTeam.add(UnitBuilder.makeUnit(new Lancecaster(Team.PLAYER)));
		//playerTeam.add(UnitBuilder.makeUnit(new Axereaver(Team.PLAYER)));
		playerTeam.add(UnitBuilder.makeUnit(new Marksman(Team.PLAYER)));
		playerTeam.add(UnitBuilder.makeUnit(new Saint(Team.PLAYER)));
		//playerTeam.add(UnitBuilder.makeUnit(new Sorcerer(Team.PLAYER)));
		//playerTeam.add(UnitBuilder.makeUnit(new Druid(Team.PLAYER)));
		playerTeam.add(UnitBuilder.makeUnit(new Thief(Team.PLAYER)));

		// make ai team
		aiTeam = new ArrayList<Unit>();
		Random rand = new Random();
		aiTeam.add(UnitBuilder.makeUnit(General.getGeneral()));
		for(int i = 0; i < 5; i++){
			switch(rand.nextInt() % 7){
			case 0:
				aiTeam.add(UnitBuilder.makeUnit(new Swordmaster(Team.COMPUTER)));
				break;
			case 1:
				aiTeam.add(UnitBuilder.makeUnit(new Lancecaster(Team.COMPUTER)));
				break;
			case 2:
				aiTeam.add(UnitBuilder.makeUnit(new Axereaver(Team.COMPUTER)));
				break;
			case 3:
				aiTeam.add(UnitBuilder.makeUnit(new Marksman(Team.COMPUTER)));
				break;
			case 4:
				aiTeam.add(UnitBuilder.makeUnit(new Saint(Team.COMPUTER)));
				break;
			case 5:
				aiTeam.add(UnitBuilder.makeUnit(new Sorcerer(Team.COMPUTER)));
				break;
			case 6:
				aiTeam.add(UnitBuilder.makeUnit(new Druid(Team.COMPUTER)));
				break;
			default:
				aiTeam.add(UnitBuilder.makeUnit(new Thief(Team.COMPUTER)));
				break;
			}
		}
		
		// make map
		try {
			this.map = new GameMap("GrassMap", this, 20, 30);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		map.setPlayerTeam(playerTeam);
		map.setAiTeam(aiTeam);

		// place units on map
		ArrayList<Terrain> playerSpawnPoints = new ArrayList<Terrain>();
		Terrain playerHeroSpawnPoint = null;
		ArrayList<Terrain> aiSpawnPoints = new ArrayList<Terrain>();
		Terrain aiHeroSpawnPoint = null;
		for (int i = 0; i < this.getMap().getRows(); i++) {
			for (int j = 0; j < this.getMap().getColumns(); j++) {
				if (this.getMap().getMap()[i][j].getPlayerSpawnPoint()) {
					if (this.getMap().getMap()[i][j].getHeroSpawnPoint()) {
						playerHeroSpawnPoint = this.getMap().getMap()[i][j];
					} else {
						playerSpawnPoints.add(this.getMap().getMap()[i][j]);
					}
				} else if (this.getMap().getMap()[i][j].getAiSpawnPoint()) {
					if (this.getMap().getMap()[i][j].getHeroSpawnPoint()) {
						aiHeroSpawnPoint = this.getMap().getMap()[i][j];
					} else {
						aiSpawnPoints.add(this.getMap().getMap()[i][j]);
					}
				}
			}
		}
		for (Unit unit: playerTeam) {
			if (unit instanceof Hero) {
				if (playerHeroSpawnPoint != null) {
					map.placeUnit(unit, playerHeroSpawnPoint.getLocation());
				}
			} else {
				if (playerSpawnPoints.size() > 0) {
					map.placeUnit(unit, playerSpawnPoints.remove(0).getLocation());
				}
			}
		}
		for (Unit unit: aiTeam) {
			if (unit instanceof Hero) {
				if (aiHeroSpawnPoint != null) {
					map.placeUnit(unit, aiHeroSpawnPoint.getLocation());
				}
			} else {
				if (aiSpawnPoints.size() > 0) {
					map.placeUnit(unit, aiSpawnPoints.remove(0).getLocation());
				}
			}
		}

		// The final implement once RF and Castle are subclassed
		// while(choice == JOptionPane.CANCEL_OPTION){
		// int quit;
		// Object[] maps = {"Riverfront", "Castle"};
		// int choice = JOptionPane.showOptionDialog(null,
		// "Choose a Map to load", "New", JOptionPane.YES_NO_CANCEL_OPTION,
		// JOptionPane.QUESTION_MESSAGE, null, maps, maps[0]);
		// try {
		// if(choice == JOptionPane.YES_OPTION){
		// this.map = new Riverfront("Riverfront", this, 20, 18);
		// break;
		// }
		// else if(choice == JOptionPane.NO_OPTION){
		// this.map = new Castle("Castle", this, 30, 12);
		// break;
		// }
		// else{
		// if(JOptionPane.showConfirmDialog(null,
		// "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION)
		// == JOptionPane.YES_OPTION);
		// System.exit(0);
		// }
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		map.setPlayerTeam(playerTeam);
		map.setAiTeam(aiTeam);

		m = new Model(this);
		ai = new AI(aiTeam, m);
		this.console = new view.GameView(m, this);

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
		
		// set up menu bar
		JMenuBar menu = new JMenuBar();
		ActionListener listener = new MenuListener(this.getMap(), m);
		JMenu file = new JMenu("File");
		JMenuItem saver = new JMenuItem("Save");
		saver.addActionListener(listener);
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(listener);
		JMenuItem loader = new JMenuItem("Load");
		loader.addActionListener(listener);
		file.add(saver);
		file.add(loader);
		file.add(quit);
		menu.add(file);
		this.setJMenuBar(menu);

		// set up close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// pack the GUI
		this.pack();
	}

	// getters and setters
	public GameMap getMap() {
		return this.map;
	}

	public Model getModel() {
		return this.m;
	}

	public AI getAI() {
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

	public Inventory getInventory() {
		return this.inventory;
	}

	public InventoryView getInventoryView() {
		return inventoryView;
	}

	public static enum Team {
		PLAYER, COMPUTER;
	}

	private class MenuListener implements ActionListener {
		private GameMap map;
		private Model model;
		private MenuListener(GameMap map, Model m){
			this.map = map;
			this.model = m;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			map.notifyObservers();
			if(e.getSource().equals(saver))
				model.save(model);
			if(e.getSource().equals(loader)){
				model.load(model);
				map.notifyObservers();
			}
		}
}
		
	// main method
	public static void main(String[] arg) {
		GamePlay newGame = new GamePlay();
		newGame.setVisible(true);
	}
}