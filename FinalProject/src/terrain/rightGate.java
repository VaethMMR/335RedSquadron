package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class rightGate extends Terrain {

	// private variables
	private BufferedImage graphic;

	// constructor
	public rightGate(int[] location) throws IOException {
		super(true, location);
		this.graphic = ImageIO.read(new File("images/rightGate.png"));
	}

	// get methods
	public BufferedImage getGraphic() {
		return this.graphic;
	}

}
