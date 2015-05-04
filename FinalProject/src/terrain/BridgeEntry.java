package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BridgeEntry extends Terrain {
	// private variables
	private static BufferedImage graphic;
	static {
		try {
			graphic = ImageIO.read(new File("images/bridgeEntry.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in BridgeEntry.java");
		}
	}

	// constructor
	public BridgeEntry(int[] location) throws IOException {
		super(false, location);
	}

	// get methods
	public BufferedImage getGraphic() {
		return graphic;
	}

}
