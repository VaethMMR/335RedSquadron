package sprites;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class SpriteBuilder extends Sprite {
	private static final int MAX_FRAMES = 4; // the number of frames in each movement
	
	private BufferedImage sheet; // the sprite sheet
	private State previousState; // the last state of the sprite

	public SpriteBuilder(String fileName){
		if (sheet == null) // load the sprite sheet into memory
			try{
				sheet = ImageIO.read(new File(fileName));
			}catch(Exception e){};
		previousState = State.IDLE;
	}
	
	@Override
	public void setState(State state){ 
		previousState = getState();
		super.setState(state);
	}

	@Override
	public Image getImage() {		
		if (sheet == null)
			return null;
		
		int row = 0, col = frame;
		// Set state of movement
		switch(getState()){
			case IDLE: 
				col = 0;
				switch (previousState) {
					case MOVING_UP:
						row = 0;
						break;
					case MOVING_DOWN:
						row = 1;
						break;
					case MOVING_LEFT:
						row = 2;
						break;
					case MOVING_RIGHT:
						row = 3;
						break;
					default:
						break;
				}
				break;		
			case MOVING_UP:
				row = 0;
				break;
			case MOVING_DOWN:
				row = 1;
				break;
			case MOVING_LEFT:
				row = 2;
				break;
			case MOVING_RIGHT:
				row = 3;
				break;
			default:
				break;
		}
		
		frame = (frame + 1) % MAX_FRAMES; // increment frame
				
		// get the subimage of the frame from the row and column
		BufferedImage temp = sheet.getSubimage(col * 32, row * 32, 32, 32);
		return temp;
	}
}