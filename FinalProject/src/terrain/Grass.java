package terrain;

import java.awt.image.BufferedImage;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Grass extends Terrain {
	// private variables
	private BufferedImage graphic;
	private boolean shortGrass;
	private static BufferedImage grass1;
	private static BufferedImage grass2;
	private static BufferedImage shortGrass1;
	private static BufferedImage shortGrass2;
	static {
		try {
			grass1 = ImageIO.read(new File("images/grass1.png"));
			grass2 = ImageIO.read(new File("images/grass2.png"));
			shortGrass1 = ImageIO.read(new File("images/shortGrass1.png"));
			shortGrass2 = ImageIO.read(new File("images/shortGrass2.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in Grass.java");
		}
	}

	// constructor
	public Grass(int[] location, boolean shortGrass) throws IOException {
		super(true, location);
		this.shortGrass = shortGrass;
		this.graphic = buildGraphic();
	}

	// get methods
	public BufferedImage getGraphic() {
		return this.graphic;
	}

	// misc methods
	private BufferedImage buildGraphic() {
		// combine the two types of mini Grass images in random order to create
		// a randomized full size Grass tile graphic
		Random randomizer = new Random();
		BufferedImage[] imageChoices;
		if (shortGrass) {
			imageChoices = new BufferedImage[] { shortGrass1, shortGrass2 };
		} else {
			imageChoices = new BufferedImage[] { grass1, grass2 };
		}
		BufferedImage[] images = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			int picker = randomizer.nextInt(2);
			images[i] = imageChoices[picker];
		}
		int type = images[0].getType();
		// create the final graphic
		BufferedImage graphic = new BufferedImage(32, 32, type);
		int counter = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				graphic.createGraphics().drawImage(images[counter], 16 * j,
						16 * i, null);
				counter++;
			}
		}
		return graphic;
	}

}
