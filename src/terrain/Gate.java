package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gate extends Terrain {

	// private variables
	private static transient BufferedImage graphic;
	static {
		try {
			graphic = ImageIO.read(new File("images/gate.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in Gate.java");
		}
	}

	// constructor
	public Gate(int[] location) throws IOException {
		super(true, location);
	}

	// get methods
	public BufferedImage getGraphic() {
		return graphic;
	}

}
