package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.GameMap;
import model.Terrain;

/**
 * This is a class that makes up the graphic representation of the class Map. It
 * extends the interface Observer
 * 
 * @author Hasanain Jamal
 *
 * @see model.Map
 * @see javax.swing.JPanel
 * @see java.util.Observer
 */
public class GraphicPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 321562980917862556L;
	private GameMap theMap;
	
	//images of the character and the ground
	private BufferedImage character, grass1, grass2;

	public GraphicPanel(GameMap theMap) {
		this.theMap = theMap;
		theMap.addObserver(this);
		this.setPreferredSize(new Dimension(850, 710));
		loadImages();
	}
	
	/**
	 * This method loads the images for later use.
	 */
	private void loadImages() {
		try {
			character = ImageIO.read(new File("images/" + theMap.getType() +"Grid.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * updates whenever there is change
	 */
	public void update(Observable o, Object arg) {
		theMap = (GameMap) o;
		repaint();

	}
	/**
	 * paints the map
	 */
	public void paintComponent(Graphics g) {
		drawMap(g, theMap);
	}
	
	/**
	 * Draws entire map onto the graphics passed
	 * @param g Graphics to draw on
	 * @param map Map being drawn
	 * 
	 * @see Tile#drawTile(Graphics, BufferedImage, int, int)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, java.awt.image.ImageObserver)
	 */
	public void drawMap(Graphics g, GameMap theMap) {
		for (int j = 0; j < theMap.getRows(); j++) {
			for (int i = 0; i < theMap.getColumns(); i++) {
				g.drawImage((character.getSubimage(i*32, j*32, 32, 32)), i*32, j*32, null);
			}
		}
		setUnits();
	}
	
	/**
	 * This method draws the tile on the Graphics passed.
	 * 
	 * @param g The graphics to be painted on
	 * @param tile The tile object to paint
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * 
	 * @see java.awt.Graphics#drawImage(Image, int, int, Observer)
	 */
	private void setUnits(){
		//Riverfront
	}
	
//	public void drawTile(Graphics g, BufferedImage map) {
//		g.draw
//		g.drawImage(grass1, x, y, 16, 16, null);
//		g.drawImage(grass2, x+16, y, 16, 16, null);
//		g.drawImage(grass1, x, y+16, 16, 16, null);
//		g.drawImage(grass2, x+16, y+16, 16, 16, null);
//		if(terrainPiece.getUnit() != null) {
//			g.drawImage(character, x, y, 32, 32, null);
//		}
//	}

}
