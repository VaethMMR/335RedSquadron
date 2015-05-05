package terrain;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Dirt extends Terrain {
	// private variables
	private BufferedImage graphic;
	private static BufferedImage dirt;
	static {
		try {
			dirt = ImageIO.read(new File("images/dirt.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in Dirt.java");
		}
	}

	// constructor
	public Dirt(int[] location) throws IOException {
		super(true, location);
		this.graphic = buildGraphic();
	}

	// get methods
	public BufferedImage getGraphic() {
		return this.graphic;
	}

	// misc methods
	private BufferedImage buildGraphic() {
		// combine mini dirt images in random rotations to create
		// a randomized full size Dirt tile graphic
		// Drawing the rotated image at the required drawing locations
		
		Random randomizer = new Random();
		int type = dirt.getType();
		// create the final graphic
		BufferedImage graphic = new BufferedImage(32, 32, type);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int picker = randomizer.nextInt(5);
				AffineTransform rotate = AffineTransform.getRotateInstance(Math.toRadians(90*picker), dirt.getWidth() / 2, dirt.getHeight() / 2);
				AffineTransformOp op = new AffineTransformOp(rotate, AffineTransformOp.TYPE_BILINEAR);
				graphic.createGraphics().drawImage(op.filter(dirt, null), 16 * j,
						16 * i, null);
			}
		}
		return graphic;
	}

}
