package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import controller.GamePlay;
import terrain.Terrain;
import model.GameMap;
import model.Unit;

public class GraphicPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 321562980917862556L;
	private GamePlay theGame;
	private GameMap theMap;
	private Unit selectedUnit = null;
	private BufferedImage moveHighlighter;
	private BufferedImage attackHighlighter;
	private BufferedImage cursor;
	private BufferedImage barrier;
	private BufferedImage mine;
	private int[] selectedTile = new int[] { 0, 0 };

	public GraphicPanel(GamePlay theGame) {
		this.theGame = theGame;
		this.theMap = theGame.getMap();
		theMap.addObserver(this);
		this.setPreferredSize(new Dimension(850, 710));
		loadImages();
		this.addMouseListener(new MouseAdapter());
	}

	private void loadImages() {
		try {
			cursor = ImageIO.read(new File("images/Gamecursor.png"));
			moveHighlighter = ImageIO.read(new File("images/highlight.png"));
			attackHighlighter = ImageIO.read(new File(
					"images/attackHighlight.png"));
			barrier = ImageIO.read(new File("images/barrier.png"));
			mine = ImageIO.read(new File("images/mine.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(Observable o, Object arg) {
		theMap = (GameMap) o;
		repaint();

	}

	public void paintComponent(Graphics g) {
		drawMap(g, theMap);
	}

	public void drawMap(Graphics g, GameMap theMap) {
		for (int i = 0; i < theMap.getRows(); i++) {
			for (int j = 0; j < theMap.getColumns(); j++) {
				drawTile(g, theMap.getMap()[i][j], j * 32, i * 32);
			}
		}
	}

	public void drawTile(Graphics g, Terrain terrainPiece, int x, int y) {
		if (!terrainPiece.hasMoveHighlight()
				&& !terrainPiece.hasAttackHighlight()
				&& !terrainPiece.getSelected()) {
			// if the terrain piece is not flagged for highlighting, draw it
			// normally
			g.drawImage(terrainPiece.getGraphic(), x, y, 32, 32, null);
		} else {
			// if the terrain piece is flagged for highlighting
			if (terrainPiece.hasMoveHighlight()) {
				// add a move highlight (blue)
				g.drawImage(
						highlightTile(terrainPiece.getGraphic(),
								moveHighlighter), x, y, 32, 32, null);
			} else if (terrainPiece.getSelected()) {
				g.drawImage(highlightTile(terrainPiece.getGraphic(), cursor),
						x, y, 32, 32, null);
			} else {
				// add an attack highlight (red)
				g.drawImage(
						highlightTile(terrainPiece.getGraphic(),
								attackHighlighter), x, y, 32, 32, null);
			}
		}
		if (terrainPiece.getUnit() != null) {
			// if the terrain piece has a Unit on it, draw the Unit
			terrainPiece.getUnit().setSpriteObject(x + 16, y + 16);
			terrainPiece.getUnit().getSpriteObject().draw(g);
		}
		if (terrainPiece.getItem() != null) {
			// if there is an item on the tile
			if (terrainPiece.getItem().getName().equals("Mine")) {
				g.drawImage(mine, x, y, 32, 32, null);
			} else if (terrainPiece.getItem().getName().equals("Barrier")) {
				g.drawImage(barrier, x, y, 32, 32, null);
			}
		}
	}

	public BufferedImage highlightTile(BufferedImage tileGraphic,
			BufferedImage highlighter) {
		// create a new 32x32 image to apply the highlight on
		BufferedImage highlighted = new BufferedImage(tileGraphic.getWidth(),
				tileGraphic.getHeight(), BufferedImage.TYPE_INT_ARGB);

		// combine the moveHighlighter image with the tileGraphic
		Graphics g = highlighted.getGraphics();
		g.drawImage(tileGraphic, 0, 0, this);
		g.drawImage(highlighter, 0, 0, this);

		return highlighted;
	}

	private void removeHighlights() {
		ArrayList<Terrain> tiles = (ArrayList<Terrain>) theMap
				.getHighlightedTiles();
		for (Terrain tile : tiles) {
			tile.setMoveHighlighted(false);
			tile.setAttackHighlighted(false);
		}
	}

	private void addHighlights(Unit theUnit) {
		removeHighlights();
		ArrayList<Terrain> moveTiles = (ArrayList<Terrain>) theMap
				.getPossibleMoves(theUnit);
		for (Terrain tile : moveTiles) {
			tile.setMoveHighlighted(true);
		}
		ArrayList<Terrain> attackTiles = (ArrayList<Terrain>) theMap
				.getPossibleAttacks(theUnit);
		for (Terrain tile : attackTiles) {
			tile.setMoveHighlighted(false);
			tile.setAttackHighlighted(true);
		}
		repaint();
	}

	private void selectUnit(Unit theUnit) {
		selectedUnit = theUnit;
		addHighlights(theUnit);
	}

	private void selectEnemyUnit(Unit theUnit) {
		selectedUnit = theUnit;
	}

	private void deselectUnit() {
		selectedUnit = null;
		removeHighlights();
		repaint();
	}

	private class MouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			Terrain clickedTile = theMap.getMap()[e.getY() / 32][e.getX() / 32];
			theMap.getMap()[selectedTile[0]][selectedTile[1]]
					.setSelected(false);
			clickedTile.setSelected(true);
			selectedTile = clickedTile.getLocation();
			if (clickedTile.getUnit() != null) {
				// if there is a Unit on the clicked tile
				if (theMap.getPlayerTeam().contains(clickedTile.getUnit())) {
					// if the clicked Unit is on the player's team
					if (selectedUnit == clickedTile.getUnit()) {
						// if the clicked Unit is the selected Unit, deselect
						deselectUnit();
					} else {
						// if the clicked Unit is not already selected, select
						// it
						selectUnit(clickedTile.getUnit());
					}
				} else {
					// the clicked Unit is on the AI's team
					if (selectedUnit != null) {
						// a player Unit is currently selected
						if (clickedTile.hasAttackHighlight()) {
							// the enemy Unit is within the selected Unit's
							// attack range
							int choice = JOptionPane.showConfirmDialog(null,
									"Do you wish to attack "
											+ clickedTile.getUnit().getName()
											+ "?",
									"Choose yes to to move and attack "
											+ clickedTile.getUnit().getName(),
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (choice == JOptionPane.YES_OPTION) {
								// move selected unit to be in range of enemy
								// unit and attack
									// attack the enemy unit

									// Get a heuristic from the attackingUnit to the
									// defendingUnit
									// to pass in a distance
									// Manhattan formula
									int aX = theGame.getMap().getUnitLocations()
											.get(selectedUnit).getLocation()[0];
									int aY = theGame.getMap().getUnitLocations()
											.get(selectedUnit).getLocation()[1];
									int dX = theGame.getMap().getUnitLocations()
											.get(selectedUnit).getLocation()[0];
									int dY = theGame.getMap().getUnitLocations()
											.get(clickedTile.getUnit())
											.getLocation()[1];
									int range = Math.abs(aX - dX)
											+ Math.abs(aY - dY);
									selectedUnit.attack(clickedTile.getUnit(),
											range);
									if (clickedTile.getUnit().getCurrentHp() < 1) {
										// figure out which team the dead Unit is on
										theGame.getMap().removeUnit(
												clickedTile.getUnit());
										if (theGame.getPlayerTeam().contains(
												clickedTile.getUnit()))
											theGame.getPlayerTeam().remove(
													clickedTile.getUnit());
										else
											theGame.getAiTeam().remove(
													clickedTile.getUnit());
									}
									if (selectedUnit.getCurrentHp() < 1) {
										// figure out which team the dead Unit is on
										theGame.getMap().removeUnit(selectedUnit);
										theGame.getPlayerTeam()
												.remove(selectedUnit);
										deselectUnit();
									}
								}
							}
						} else {
							deselectUnit();
							selectEnemyUnit(clickedTile.getUnit());
						}
					}
			} else {
				// there is no Unit on the clicked tile
				if (selectedUnit != null && (clickedTile.hasMoveHighlight() || clickedTile.hasAttackHighlight())) {
					// if a Unit is currently selected and the clicked tile is
					// in its movement range
					if (clickedTile.getItem() != null) {
						// if an item is on the clicked tile
						int choice = JOptionPane.showConfirmDialog(null,
								"Choose 'Yes' to move here and add this "
										+ clickedTile.getItem().getName()
										+ "to your inventory.",
								"Move and add Item to Inventory?",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (choice == JOptionPane.YES_OPTION) {
							// move unit here and add the item to the inventory
							// and remove it from the map
						}
					} else {
						// the clicked tile is in movement range and empty of
						// items
						// move the Unit to the tile
						theMap.moveUnit(selectedUnit, clickedTile.getLocation());
						deselectUnit();
					}
				} else {
					// if no Unit is currently selected and/or the clicked tile
					// is out of the
					// selected Unit's movement range
					deselectUnit();
				}
			}
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
		}
	        	
	}
}
