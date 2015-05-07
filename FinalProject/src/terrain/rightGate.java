package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RightGate extends Terrain {

	// private variables
	private static BufferedImage graphic;
	static {
		try {
			graphic = ImageIO.read(new File("images/rightGate.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in RightGate.java");
		}
	}

	// constructor
	public RightGate(int[] location) throws IOException {
		super(true, location);
	}

	// get methods
	public BufferedImage getGraphic() {
		return graphic;
	}

}
