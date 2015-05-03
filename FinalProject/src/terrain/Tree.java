package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tree extends Terrain {
	// private variables
	private BufferedImage graphic;

	// constructor
	public Tree(int[] location) throws IOException {
		super(false, location);
		BufferedImage tree1 = ImageIO.read(new File("images/tree1.png"));
		BufferedImage tree2 = ImageIO.read(new File("images/tree2.png"));
		BufferedImage tree3 = ImageIO.read(new File("images/tree3.png"));
		BufferedImage tree4 = ImageIO.read(new File("images/tree4.png"));
		this.graphic = buildGraphic(tree1, tree2, tree3, tree4);
	}

	// get methods
	public BufferedImage getGraphic() {
		return this.graphic;
	}
	
	// misc methods
		private BufferedImage buildGraphic(BufferedImage tree1, BufferedImage tree2, BufferedImage tree3, BufferedImage tree4) {
			// combine the two types of mini Grass images in random order to create
			// a randomized full size Grass tile graphic
			Random randomizer = new Random();
			BufferedImage[] imageChoices = { tree1, tree2, tree3, tree4};
			BufferedImage[] images = new BufferedImage[4];
			for (int i = 0; i < 4; i++) {
				int picker = randomizer.nextInt(4);
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
