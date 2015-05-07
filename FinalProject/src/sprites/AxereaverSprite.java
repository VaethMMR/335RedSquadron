package sprites;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class AxereaverSprite extends Sprite {
	private static final int leftBorder = 8, vertBorder = 5; // magic numbers for the sheet
	private static final int MAX_FRAMES = 4; // more magic numbers
	private static final int width = 32, height = 34; // width and height of a single frame
	
	private static BufferedImage sheet; // the sprite sheet
	private State previousState; // the last state of the frog

	// TODO 11: talk about WarriorSprite
	public AxereaverSprite(){
		if (sheet == null) // load the sprite sheet into memory
			try{
				sheet = ImageIO.read(new File("images/AxereaverWalkingAlpha.png"));
			}catch(Exception e){};
		previousState = State.IDLE; // what is this for?
	}
	
	@Override
	public void setState(State state){ // why is this being overridden?
		previousState = getState();
		super.setState(state);
	}

	@Override
	public Image getImage() {
		// TODO 12: finish implementing getImage in Warrior. 
		
		if (sheet == null)
			return null;
		
		int row = 0, col = frame, offset = 3, yoffset = 0, xoffset = 0;
		// Unlike explosion, we have to consider the sprite's different states
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
	
		/*
		 * This is a bit of hard-coding because certain 
		 * frames in the sheet are thinner than others
		 */
		if (row == 3 && (col == 4 || col == 1))
			offset = 4;
		if(row == 0){
			yoffset = -4;
			xoffset = 3;
			if(col == 3)
				xoffset = 8;
		}
		if(row == 1){
			xoffset = 5;
			yoffset = -2;
		}
		if(row == 2)
			yoffset = 1;
		if(row == 3)
			yoffset = 2;
		
		frame = (frame + 1) % MAX_FRAMES; // increment frame
		
		// get the subimage of the frame from the row and column
		BufferedImage temp = sheet.getSubimage((leftBorder + col * width) - xoffset, row * (height + vertBorder) - yoffset, width - offset, height);
		
		// Up-scaling the frame by 2x
		return temp.getScaledInstance(2*width, 2*height, Image.SCALE_DEFAULT);
	}

	@Override
	public int getHeight() {
		return 2 * height;
	}

	@Override
	public int getWidth() {
		return 2 * width;
	}
}