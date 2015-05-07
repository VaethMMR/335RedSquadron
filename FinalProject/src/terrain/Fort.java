package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fort extends Terrain {
	// private variables
	private static transient BufferedImage graphic;
	static {
		try {
			graphic = ImageIO.read(new File("images/fort.png"));
		} catch (IOException e) {
			System.out.println("Image file not found in Fort.java");
		}
	}

	// constructor
	public Fort(int[] location) throws IOException {
		super(false, location);
	}

	// get methods
	public BufferedImage getGraphic() {
		return graphic;
	}

}
