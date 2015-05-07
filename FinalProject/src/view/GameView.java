package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ai.AI;
import controller.GamePlay;
import exceptions.TileNotStandableException;
import exceptions.TileOccupiedException;
import model.Hero;
import model.Melee;
import model.Model;
import model.Unit;
import model.Weapon;

@SuppressWarnings("serial") // TODO: It's warning that we need a serial ID here,
							// but don't we only need that in Model?
public class GameView extends JPanel {
	private GamePlay theGame;
	private JList<String> playerUnits;
	private JList<String> inRangeUnits;
	private DefaultListModel<String> inRangeUnitsModel;
	private DefaultListModel<String> playerUnitsModel;
	private JTextArea coordinateEntry;
	private JTextArea winCondition;
	private GraphicPanel graphics;
	private Model m;

	// constructor
	public GameView(Model m, GamePlay theGame) {
		this.theGame = theGame;
		this.m = m;
		this.setLayout(new BorderLayout());
		
		// Set up the GUI
		graphics = new GraphicPanel(theGame);
		add(graphics, BorderLayout.CENTER);
		
		// Set up the HUD
		JPanel hud = new JPanel();
		hud.setPreferredSize(new Dimension(260,710));
		hud.setLayout(new GridLayout(4, 1));
		
		// display the current win condition
		//if (theGame.getMap().get)
		String goal = "Destroy all enemy units.";
		winCondition = new JTextArea(goal);
		winCondition.setEditable(false);
		winCondition.setPreferredSize(new Dimension(260,50));
		hud.add(winCondition);
		
		
		
		// set up end turn button
		JButton endTurn = new JButton("End Turn");
		hud.add(endTurn);
		endTurn.addActionListener(new endTurnActionListener(m));

		// add the HUD to the GameView
		add(hud, BorderLayout.EAST);
		
		// create JList
		/*playerUnitsModel = new DefaultListModel<String>();
		playerUnits = new JList<String>(playerUnitsModel);
		playerUnits.setPreferredSize(new Dimension(150,200));
		// add the JList to the panel
		actionPanel.add(this.playerUnits);
		
		// add buttons
		JButton move = new JButton("Move");
		actionPanel.add(move);
		move.addActionListener(new moveActionListener());*/
		
		//winCondition.addFocusListener(new coordinateFocusListener());
		
		/*JButton submitMove = new JButton("Submit Move");
		actionPanel.add(submitMove);
		submitMove.addActionListener(new submitMoveActionListener());
		
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
		submitAttack.addActionListener(new submitAttackActionListener());*/
		
		//setupPlayerList(theGame.getPlayerTeam());
		
	}
	
	// misc methods
	private void setupPlayerList(List<Unit> units) {		
		// first clear the list to make sure we don't duplicate Units
		playerUnitsModel.clear();
		// now add the Units from the units List back into the ListModel
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
				List<Unit> inRangeUnits = theGame.getMap().getInRangeAiUnits(theUnit);
				setupinRangeList(inRangeUnits);
			}
		}
	}
	
	private class submitMoveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Unit theUnit = null;
			String unitName = playerUnits.getSelectedValue();
			List<Unit> playerTeam = theGame.getPlayerTeam();
			List<Unit> units = new ArrayList<Unit>();
			for(Unit i : playerTeam) {
				units.add(i);
			}
			for(Unit i : units){
				if (i.getName() == unitName) {
					theUnit = i;
				}
			}
			if (theUnit != null) {
				String coordinateText = coordinateEntry.getText().substring(1,coordinateEntry.getText().length()-1);
				int commaIndex = coordinateText.indexOf(",");
				int[] coordinates = new int[] {Integer.parseInt(coordinateText.substring(0,commaIndex)), Integer.parseInt(coordinateText.substring(commaIndex+1,coordinateText.length()))};
				try {
					boolean moved = theGame.getMap().moveUnit(theUnit, coordinates);
					if (moved) {
						units.remove(theUnit);
						playerUnitsModel.removeElement(playerUnits.getSelectedValue());
					}
				} catch (TileOccupiedException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (TileNotStandableException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}
	

	private class submitAttackActionListener implements ActionListener
			{
		public submitAttackActionListener(){
		}
		public void actionPerformed(ActionEvent arg0) {
			// find the attacking Unit
			Unit attackingUnit = null;
			String unitName = playerUnits.getSelectedValue();
			List<Unit> playerTeam = theGame.getPlayerTeam();
			List<Unit> units = new ArrayList<Unit>();
			for (Unit i : playerTeam) {
				units.add(i);
			}
			for (Unit i : units) {
				if (i.getName() == unitName) {
					attackingUnit = i;
				}
			}
			if (attackingUnit != null) {
				// find defending Unit
				Unit defendingUnit = null;
				String defenderName = inRangeUnits.getSelectedValue();
				List<Unit> aiTeam = theGame.getAiTeam();
				List<Unit> aiUnits = new ArrayList<Unit>();
				for (Unit i : aiTeam) {
					aiUnits.add(i);
				}
				for (Unit i : aiUnits) {
					if (i.getName() == defenderName) {
						defendingUnit = i;
					}
				}
				if (defendingUnit != null) {
					// initiate attack
					// Get a heuristic from the attackingUnit to the
					// defendingUnit
					// to pass in a distance
					// Manhattan formula
					int aX = theGame.getMap().getUnitLocations()
							.get(attackingUnit).getLocation()[0];
					int aY = theGame.getMap().getUnitLocations()
							.get(attackingUnit).getLocation()[1];
					int dX = theGame.getMap().getUnitLocations()
							.get(defendingUnit).getLocation()[0];
					int dY = theGame.getMap().getUnitLocations()
							.get(defendingUnit).getLocation()[1];
					int range = Math.abs(aX - dX) + Math.abs(aY - dY);
					attackingUnit.attack(defendingUnit, range);
					if (defendingUnit.getCurrentHp() < 1) {
						// figure out which team the dead Unit is on
						theGame.getMap().removeUnit(defendingUnit);
						if (theGame.getPlayerTeam().contains(defendingUnit))
							theGame.getPlayerTeam().remove(defendingUnit);
						else
							theGame.getAiTeam().remove(defendingUnit);
					}
					if (attackingUnit.getCurrentHp() < 1) {
						// figure out which team the dead Unit is on
						theGame.getMap().removeUnit(attackingUnit);
						if (theGame.getPlayerTeam().contains(attackingUnit))
							theGame.getPlayerTeam().remove(attackingUnit);
						else
							theGame.getAiTeam().remove(attackingUnit);
					}
					units.remove(attackingUnit);
					playerUnitsModel.removeElement(playerUnits
							.getSelectedValue());
				}
			}
			if (playerUnitsModel.isEmpty()
					&& theGame.getPlayerTeam().size() > 0)
				new endTurnActionListener(m).actionPerformed(arg0);
		}
	}
	
	private class endTurnActionListener implements ActionListener{
		Model m;
		public endTurnActionListener(Model m){
			this.m = m;
		}
		public void actionPerformed(ActionEvent arg0) {
			//Restore the JList of all living units
			setupPlayerList(theGame.getPlayerTeam());
			// run AI Move
			AI ai = theGame.getAI();
			for(int i = 0; i < ai.getTeam().size(); i++){
				ai.useStrategy(ai.getTeam().get(i));
//					if(theGame.getMap);
				if(theGame.getAiTeam().isEmpty() == true){
					JOptionPane.showMessageDialog(null, " Victory.");
					break;
				}
				if(!theGame.getPlayerTeam().contains(Hero.getHero())){
					JOptionPane.showMessageDialog(null, " Defeat.");
					break;
				}
			}
			setupPlayerList(theGame.getMap().getPlayerTeam());
		}
	}

}
