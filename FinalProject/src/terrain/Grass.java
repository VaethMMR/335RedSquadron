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

	// constructor
	public Grass(int[] location) throws IOException {
		super(true, location);
		BufferedImage grass1 = ImageIO.read(new File("images/grass1.png"));
		BufferedImage grass2 = ImageIO.read(new File("images/grass2.png"));
		this.graphic = buildGraphic(grass1, grass2);
	}

	// get methods
	public BufferedImage getGraphic() {
		return this.graphic;
	}

	// misc methods
	private BufferedImage buildGraphic(BufferedImage grass1,
			BufferedImage grass2) {
		// combine the two types of mini Grass images in random order to create
		// a randomized full size Grass tile graphic
		Random randomizer = new Random();
		BufferedImage[] imageChoices = { grass1, grass2 };
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
