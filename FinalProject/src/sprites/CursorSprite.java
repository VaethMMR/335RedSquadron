package sprites;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CursorSprite extends Sprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3111358609210591265L;
	private transient BufferedImage cursor;
	
	public CursorSprite() {
		try {
			cursor = ImageIO.read(new File("images/Gamecursor.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	@Override
	public Image getImage() {
		return cursor;
	}

	@Override
	public int getWidth() {
		return 32;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 32;
	}
}
