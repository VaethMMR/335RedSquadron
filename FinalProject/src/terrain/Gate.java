package terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gate extends Terrain {

	// private variables
	private BufferedImage graphic;

	// constructor
	public Gate(int[] location) throws IOException {
		super(true, location);
		this.graphic = ImageIO.read(new File("images/Gate.png"));
	}

	// get methods
	public BufferedImage getGraphic() {
		return this.graphic;
	}

}
