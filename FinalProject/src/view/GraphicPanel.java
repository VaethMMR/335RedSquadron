package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import terrain.Terrain;
import model.GameMap;
import model.Unit;

public class GraphicPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 321562980917862556L;
	private GameMap theMap;
	private BufferedImage character;
	
	public GraphicPanel(GameMap theMap) {
		this.theMap = theMap;
		theMap.addObserver(this);
		this.setPreferredSize(new Dimension(850, 710));
		loadImages();
		this.addMouseListener(new MouseAdapter());
	}
	
	private void loadImages() {
		try {
			character = ImageIO.read(new File("images/TheHunter.png"));
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
		if (!terrainPiece.getHighlighted()) {
			g.drawImage(terrainPiece.getGraphic(), x, y, 32, 32, null);
		} else {
			System.out.println("Highlighted");
		}
		if(terrainPiece.getUnit() != null) {
			g.drawImage(character, x, y, 32, 32, null);
		}
	}
	
	private void highlightMoves(Unit theUnit) {
		int[] unitLocation = theMap.getUnitLocations().get(theUnit).getLocation();
		theMap.getMap()[unitLocation[0]+1][unitLocation[1]].setHighLighted(true);
		theMap.getMap()[unitLocation[0]][unitLocation[1]+1].setHighLighted(true);
		//theMap.getMap()[unitLocation[0]-1][unitLocation[1]].setHighLighted(true);
		//theMap.getMap()[unitLocation[0]][unitLocation[1]-1].setHighLighted(true);
		repaint();
	}
	
	private class MouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			Terrain clickedTile = theMap.getMap()[e.getY()/32][e.getX()/32];
			if (clickedTile.getUnit() != null) {
				highlightMoves(clickedTile.getUnit());
			}
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) { }

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) { }

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) { }

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) { }
		
	}
}
