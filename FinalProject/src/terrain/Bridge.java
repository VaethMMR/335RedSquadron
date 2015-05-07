package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bridge extends Terrain {
	// private variables
	private static BufferedImage graphic;
	static {
		try {
			graphic = ImageIO.read(new File("images/bridge.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in Bridge.java");
		}
	}

	// constructor
	public Bridge(int[] location) throws IOException {
		super(true, location);
	}

	// get methods
	public BufferedImage getGraphic() {
		return graphic;
	}

}
