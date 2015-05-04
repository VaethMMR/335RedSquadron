package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Shore extends Terrain {
	// private variables
	private BufferedImage graphic;
	private static BufferedImage shore1;
	private static BufferedImage shore2;
	private static BufferedImage shore3;
	private static BufferedImage shore4;
	private static BufferedImage water1;
	private static BufferedImage water2;
	static {
		try {
			shore1 = ImageIO.read(new File("images/shore1.png"));
			shore2 = ImageIO.read(new File("images/shore2.png"));
			shore3 = ImageIO.read(new File("images/shore3.png"));
			shore4 = ImageIO.read(new File("images/shore4.png"));
			water1 = ImageIO.read(new File("images/water1.png"));
			water2 = ImageIO.read(new File("images/water2.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in Shore.java");
		}
	}

	// constructor
	public Shore(int[] location) throws IOException {
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
		BufferedImage[] imageChoices = { shore1, shore2, shore3, shore4, water1, water2 };
		BufferedImage[] shoreImages = new BufferedImage[4];
		BufferedImage[] waterImages = new BufferedImage[2];
		for (int i = 0; i < 2; i++) {
			int picker = randomizer.nextInt(4);
			shoreImages[i] = imageChoices[picker];
		}
		for (int i = 0; i < 2; i++) {
			int picker = randomizer.nextInt(2);
			waterImages[i] = imageChoices[picker + 4];
		}
		int type = waterImages[0].getType();
		// create the final graphic
		BufferedImage graphic = new BufferedImage(32, 32, type);
		int counter = 0;
		for (int j = 0; j < 2; j++) {
			graphic.createGraphics().drawImage(shoreImages[counter], 16 * j,
					0, null);
			counter++;
		}
		counter = 0;
		for (int j = 0; j < 2; j++) {
			graphic.createGraphics().drawImage(waterImages[counter], 16 * j,
					16, null);
			counter++;
		}
		return graphic;
	}

}
