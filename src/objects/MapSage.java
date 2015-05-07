package objects;
import sprites.SageSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapSage extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1926835923973194831L;

	public MapSage(int x, int y){
		super(new SageSprite(), null, x, y, 100);
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