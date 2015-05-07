package objects;
import sprites.TruebladeSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapTrueblade extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2194315334880360620L;

	public MapTrueblade(int x, int y){
		super(new TruebladeSprite(), null, x, y, 100);
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