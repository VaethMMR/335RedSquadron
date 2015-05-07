package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Water extends Terrain {
	// private variables
	private transient BufferedImage graphic;
	private static transient BufferedImage water1;
	private static transient BufferedImage water2;
	static {
		try {
			water1 = ImageIO.read(new File("images/water1.png"));
			water2 = ImageIO.read(new File("images/water2.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in Water.java");
		}
	}

	// constructor
		public Water(int[] location) throws IOException {
			super(false, location);
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
			BufferedImage[] imageChoices = { water1, water2 };
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
