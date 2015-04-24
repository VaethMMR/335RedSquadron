package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.GamePlay;
import model.Bow;
import model.Melee;
import model.Sword;
import model.Terrain;
import model.Unit;
import model.Weapon;

public class GameView extends JPanel {
	private JTextArea consoleUI;
	private GamePlay theGame;
	private JList<String> playerUnits;
	private JList<String> inRangeUnits;
	private DefaultListModel<String> inRangeUnitsModel;
	private DefaultListModel<String> playerUnitsModel;
	private JTextArea coordinateEntry;

	// constructor
	public GameView(GamePlay theGame) {
		this.theGame = theGame;
		this.setLayout(new BorderLayout());//setup our layout manager
		
		// Set up the console
		consoleUI = new JTextArea();
		consoleUI.setEditable(false);
		consoleUI.setFont(new Font("monospaced", Font.PLAIN, 12));
		add(consoleUI, BorderLayout.NORTH);
		
		// Set up the actionPanel
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new GridLayout(2, 4));
		
		// create JList
		playerUnitsModel = new DefaultListModel<String>();
		playerUnits = new JList<String>(playerUnitsModel);
		playerUnits.setPreferredSize(new Dimension(150,200));
		// add the JList to the panel
		actionPanel.add(this.playerUnits);
		
		// add buttons
		JButton move = new JButton("Move");
		actionPanel.add(move);
		move.addActionListener(new moveActionListener());
		
		coordinateEntry = new JTextArea();
		coordinateEntry.setEditable(false);
		actionPanel.add(coordinateEntry);
		coordinateEntry.addFocusListener(new coordinateFocusListener());
		
		JButton submitMove = new JButton("Submit Move");
		actionPanel.add(submitMove);
		submitMove.addActionListener(new submitMoveActionListener());
		
		JButton endTurn = new JButton("End Turn");
		actionPanel.add(endTurn);
		endTurn.addActionListener(new endTurnActionListener());
		
		JButton attack = new JButton("Attack");
		actionPanel.add(attack);
		attack.addActionListener(new attackActionListener());
		
		// create JList
		inRangeUnitsModel = new DefaultListModel<String>();
		inRangeUnits = new JList<String>(inRangeUnitsModel);
		inRangeUnits.setPreferredSize(new Dimension(150,200));
		// add the JList to the panel
		actionPanel.add(this.inRangeUnits);
		
		JButton submitAttack = new JButton("Submit Attack");
		actionPanel.add(submitAttack);
		submitAttack.addActionListener(new submitAttackActionListener());

		add(actionPanel, BorderLayout.CENTER);
		
		setupPlayerList(theGame.getPlayerTeam());
		
	}
	
	// getters and setters
	public JTextArea getConsole() {
		return consoleUI;
	}
	
	public void setConsole(String consoleOutput) {
		consoleUI.setText(consoleOutput);
	}
	
	// misc methods
	private void setupPlayerList(List<Unit> units) {		
		//first clear the list to make sure we don't duplicate Units
		playerUnitsModel.clear();
		//now add all the memories we want
		for(Unit i : units){
			String listItem= i.getName();
			//now add the element
			playerUnitsModel.addElement(listItem);
		}
	}
	
	private void setupinRangeList(List<Unit> units) {		
		//first clear the list to make sure we don't duplicate Units
		inRangeUnitsModel.clear();
		//now add all the memories we want
		for(Unit i : units){
			String listItem= i.getName();
			//now add the element
			inRangeUnitsModel.addElement(listItem);
		}
	}
	
	// action listeners
	private class moveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			coordinateEntry.setEditable(true);
			coordinateEntry.setText("Click to\nEnter Coordinates\n[x,y]");
		}
	}
	
	private class coordinateFocusListener implements FocusListener{
		@Override
		public void focusGained(FocusEvent e) {
			Unit theUnit = null;
			String unitName = playerUnits.getSelectedValue();
			List<Unit> units = theGame.getPlayerTeam();
			for(Unit i : units){
				if (i.getName() == unitName) {
					theUnit = i;
				}
			}
			if (theUnit != null) {
				int[] currentCoordinates = theGame.getMap().getUnitLocations().get(theUnit).getLocation();
				coordinateEntry.setText("[" + currentCoordinates[0] + "," + currentCoordinates[1] + "]");	
			}
		}

		@Override
		public void focusLost(FocusEvent e) { }
	}
	
	private class attackActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Unit theUnit = null;
			String unitName = playerUnits.getSelectedValue();
			List<Unit> units = theGame.getPlayerTeam();
			for(Unit i : units){
				if (i.getName() == unitName) {
					theUnit = i;
				}
			}
			if (theUnit != null) {
				List<Unit> inRangeUnits = theGame.getMap().getInRangeUnits(theUnit);
				setupinRangeList(inRangeUnits);
			}
		}
	}
	
	private class submitMoveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Unit theUnit = null;
			String unitName = playerUnits.getSelectedValue();
			List<Unit> units = theGame.getPlayerTeam();
			for(Unit i : units){
				if (i.getName() == unitName) {
					theUnit = i;
				}
			}
			if (theUnit != null) {
				String coordinateText = coordinateEntry.getText().substring(1,coordinateEntry.getText().length()-1);
				int commaIndex = coordinateText.indexOf(",");
				int[] coordinates = new int[] {Integer.parseInt(coordinateText.substring(0,commaIndex)), Integer.parseInt(coordinateText.substring(commaIndex+1,coordinateText.length()))};
				theGame.getMap().moveUnit(theUnit, coordinates);
				setConsole(theGame.getMap().returnMap());
				units.remove(theUnit);
				setupPlayerList(units);
			}
		}
	}
	
	private class submitAttackActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			// find the attacking Unit
			Unit attackingUnit = null;
			String unitName = playerUnits.getSelectedValue();
			List<Unit> units = theGame.getPlayerTeam();
			for(Unit i : units){
				if (i.getName() == unitName) {
					attackingUnit = i;
				}
			}
			if (attackingUnit != null) {
				// find defending Unit
				Unit defendingUnit = null;
				String defenderName = inRangeUnits.getSelectedValue();
				List<Unit> aiUnits = theGame.getAiTeam();
				for(Unit i : aiUnits){
					if (i.getName() == defenderName) {
						defendingUnit = i;
					}
				}
				if (defendingUnit != null) {
					// initiate attack
					Weapon attackingWeapon;
					if (attackingUnit instanceof Melee) {
						attackingWeapon = new Sword(10, "Sword");
					} else {
						attackingWeapon = new Bow(10, "Bow");
					}
					boolean killed = attackingUnit.attack(defendingUnit, attackingWeapon, attackingWeapon);
					if (killed) {
						// figure out which team the dead Unit is on
						if (units.contains(defendingUnit)) {
							units.remove(defendingUnit);
							setupPlayerList(units);
						}
						theGame.getMap().removeUnit(defendingUnit);
						setConsole(theGame.getMap().returnMap());
					}
				}
			}
		}
	}
	
	private class endTurnActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			List<Unit> units = theGame.getPlayerTeam();
			setupPlayerList(units);
		}
	}

}
