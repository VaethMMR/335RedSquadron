package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LeftGate extends Terrain {

	// private variables
	private static transient BufferedImage graphic;
	static {
		try {
			graphic = ImageIO.read(new File("images/leftGate.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in LeftGate.java");
		}
	}
	
	// constructor
	public LeftGate(int[] location) throws IOException {
		super(false, location);
	}

	// get methods
	public BufferedImage getGraphic() {
		return graphic;
	}

}
