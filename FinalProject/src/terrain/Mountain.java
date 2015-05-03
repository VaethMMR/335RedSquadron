package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mountain extends Terrain {
	// private variables
	private BufferedImage graphic;

	// constructor
	public Mountain(int[] location) throws IOException {
		super(false, location);
		this.graphic = ImageIO.read(new File("images/mountain.png"));
	}

	// get methods
	public BufferedImage getGraphic() {
		return this.graphic;
	}

}
