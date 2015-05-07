package objects;
import sprites.DarkSageSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapDarkSage extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6014488819765051332L;

	public MapDarkSage(int x, int y){
		super(new DarkSageSprite(), null, x, y, 100);
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