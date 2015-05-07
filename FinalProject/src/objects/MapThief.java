package objects;
import sprites.ThiefSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapThief extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9183840333595157295L;

	public MapThief(int x, int y){
		super(new ThiefSprite(), null, x, y, 100);
	}
	
	@Override
	public void setSelected(SpriteObject sprite) {
		//Does nothing
	}

	@Override
	public SpriteObject getSelected() {
		//Do nothing
		return null;
	}
}