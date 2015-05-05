package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import sprites.*;
import objects.*;

import ai.AI;
import ai.AttackPlayer;
import ai.Roam;
import controller.GamePlay;
import model.Inventory;
import model.Melee;
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
	private GraphicPanel graphics;

	// constructor
	public GameView(GamePlay theGame) {
		this.theGame = theGame;
		this.setLayout(new BorderLayout());// setup our layout manager

//		// Set up the console
//		consoleUI = new JTextArea();
//		consoleUI.setEditable(false);
//		consoleUI.setFont(new Font("monospaced", Font.PLAIN, 12));
//		add(consoleUI, BorderLayout.NORTH);

		// Set up the GUI
		graphics = new GraphicPanel(theGame.getMap());
		add(graphics, BorderLayout.CENTER);

		// Set up the actionPanel
		JPanel actionPanel = new JPanel();
		actionPanel.setPreferredSize(new Dimension(260, 710));
		actionPanel.setLayout(new GridLayout(4, 2));

		// create JList
		playerUnitsModel = new DefaultListModel<String>();
		playerUnits = new JList<String>(playerUnitsModel);
		JScrollPane playerScroll = new JScrollPane(playerUnits);
		playerUnits.setPreferredSize(new Dimension(150, 200));
		// add the JList to the panel
		actionPanel.add(playerScroll);

		// add buttons
		JButton move = new JButton("Move");
		actionPanel.add(move);
		move.addActionListener(new moveActionListener());

		JButton wait = new JButton("Wait");
		actionPanel.add(wait);
		wait.addActionListener(new waitActionListener());
		actionPanel.add(move);
		move.addActionListener(new moveActionListener());

		coordinateEntry = new JTextArea();
		coordinateEntry.setEditable(false);
		actionPanel.add(coordinateEntry);
		coordinateEntry.addFocusListener(new coordinateFocusListener());

		JButton submitMove = new JButton("Submit\nMove");
		actionPanel.add(submitMove);
		submitMove.addActionListener(new submitMoveActionListener());

		JButton attack = new JButton("Attack");
		actionPanel.add(attack);
		attack.addActionListener(new attackActionListener());

		// create JList
		inRangeUnitsModel = new DefaultListModel<String>();
		inRangeUnits = new JList<String>(inRangeUnitsModel);
		JScrollPane targetScroll = new JScrollPane(inRangeUnits);
		inRangeUnits.setPreferredSize(new Dimension(150, 200));
		// add the JList to the panel
		actionPanel.add(targetScroll);

		JButton submitAttack = new JButton("Submit\nAttack");
		actionPanel.add(submitAttack);
		submitAttack.addActionListener(new submitAttackActionListener());

		JButton endTurn = new JButton("End\nTurn");
		actionPanel.add(endTurn);
		endTurn.addActionListener(new endTurnActionListener());

		add(actionPanel, BorderLayout.EAST);

		setupPlayerList(theGame.getPlayerTeam());
	}

	// getters and setters
	public JTextArea getConsole() {
		return consoleUI;
	}

//	public void //setConsole(String consoleOutput) {
//		consoleUI.setText(consoleOutput);
//	}

	// misc methods
	private void setupPlayerList(List<Unit> units) {
		// first clear the list to make sure we don't duplicate Units
		playerUnitsModel.clear();
		// now add all the memories we want
		for (Unit i : units) {
			String listItem = i.getName();
			// now add the element
			playerUnitsModel.addElement(listItem);
			// reset movement capabilities
			for (Unit u : units)
				u.setMoved(false);
		}
	}

	private void setupinRangeList(List<Unit> units) {
		// first clear the list to make sure we don't duplicate Units
		inRangeUnitsModel.clear();
		// now add all the memories we want
		for (Unit i : units) {
			String listItem = i.getName();
			// now add the element
			inRangeUnitsModel.addElement(listItem);
		}
	}

	JPanel gamePanel = this;

	// action listeners
	private class moveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			coordinateEntry.setEditable(true);
			coordinateEntry.setText("Click to\nEnter Coordinates\n[x,y]");
		}
	}

	private class coordinateFocusListener implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {
			Unit theUnit = null;
			String unitName = playerUnits.getSelectedValue();
			List<Unit> units = theGame.getPlayerTeam();
			for (Unit i : units) {
				if (i.getName() == unitName) {
					theUnit = i;
				}
			}
			if (theUnit != null) {
				int[] currentCoordinates = theGame.getMap().getUnitLocations()
						.get(theUnit).getLocation();
				coordinateEntry.setText("[" + currentCoordinates[0] + ","
						+ currentCoordinates[1] + "]");

			}
		}

		@Override
		public void focusLost(FocusEvent e) {
		}
	}

	private class waitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			playerUnitsModel.removeElement(playerUnits.getSelectedValue());
			if(playerUnitsModel.isEmpty() && theGame.getPlayerTeam().size() > 0)
				new endTurnActionListener().actionPerformed(arg0);
		}
	}

	private class attackActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Unit theUnit = null;
			String unitName = playerUnits.getSelectedValue();
			List<Unit> units = theGame.getPlayerTeam();
			for (Unit i : units) {
				if (i.getName() == unitName) {
					theUnit = i;
				}
			}
			if (theUnit != null) {
				List<Unit> inRangeUnits = theGame.getMap().getInRangeAiUnits(
						theUnit);
				setupinRangeList(inRangeUnits);
			}
		}
	}

	private class submitMoveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			boolean moved = false;
			Unit theUnit = null;
			String unitName = playerUnits.getSelectedValue();
			List<Unit> playerTeam = theGame.getPlayerTeam();
			List<Unit> units = new ArrayList<Unit>();
			for (Unit i : playerTeam) {
				units.add(i);
			}
			for (Unit i : units) {
				if (i.getName() == unitName) {
					theUnit = i;
				}
			}
			if (theUnit != null) {
				if (theUnit.hasMoved()) {
					JOptionPane.showMessageDialog(null, "Nice try cheater.\n",
							"Unit has moved this turn",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String coordinateText = coordinateEntry.getText()
							.substring(1,
									coordinateEntry.getText().length() - 1);
					int commaIndex = coordinateText.indexOf(",");
					int[] coordinates = new int[] {
							Integer.parseInt(coordinateText.substring(0,
									commaIndex)),
							Integer.parseInt(coordinateText
									.substring(commaIndex + 1)) };
					if (JOptionPane.showConfirmDialog(null, "Move to ["
							+ coordinates[0] + "," + coordinates[1] + "]",
							"Confirm Move", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						moved = theGame.getMap().moveUnit(theUnit, coordinates);
					if (moved) {
						theUnit.setMoved(true);
						JOptionPane.showMessageDialog(null, theUnit.getName() + " moved to " + coordinateText + ".\n"
						+ "Valid moves:\n-Wait\n-Attack\n-Use\n-Heal", "Move Confirmed", JOptionPane.INFORMATION_MESSAGE);
						//setConsole(theGame.getMap().returnMap());
					}
				}
				gamePanel.repaint();
			}
		}
	}

	private class submitAttackActionListener implements ActionListener {
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
						//setConsole(theGame.getMap().returnMap());
					}
					if (attackingUnit.getCurrentHp() < 1) {
						// figure out which team the dead Unit is on
						theGame.getMap().removeUnit(attackingUnit);
						if (theGame.getPlayerTeam().contains(attackingUnit))
							theGame.getPlayerTeam().remove(attackingUnit);
						else
							theGame.getAiTeam().remove(attackingUnit);
						//setConsole(theGame.getMap().returnMap());
					}
					units.remove(attackingUnit);
					playerUnitsModel.removeElement(playerUnits
							.getSelectedValue());
				}
			}
			gamePanel.repaint();
			if(playerUnitsModel.isEmpty() && theGame.getPlayerTeam().size() > 0)
				new endTurnActionListener().actionPerformed(arg0);
		}
	}

	private class endTurnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// Restore the JList of all living units
			// and reset has moved
			JOptionPane.showMessageDialog(null, "Enemy Phase", "Game Saved", JOptionPane.INFORMATION_MESSAGE);
			setupPlayerList(theGame.getPlayerTeam());
			// run AI Move
			AI ai = theGame.getAI();
			for (int i = 0; i < ai.getTeam().size(); i++) {
				ai.useStrategy(ai.getTeam().get(i));
				if (theGame.getAiTeam().isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Victory!");
					break;
				}
				if (theGame.getPlayerTeam().isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Defeated.");
					break;
				}
				//setConsole(theGame.getMap().returnMap());
			}
			//setConsole(theGame.getMap().returnMap());
			gamePanel.repaint();
			JOptionPane.showMessageDialog(null, "Player Phase", "Game Saved", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}