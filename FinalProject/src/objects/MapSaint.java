package objects;
import sprites.SaintSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapSaint extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7903363852425190032L;

	public MapSaint(int x, int y){
		super(new SaintSprite(), null, x, y, 100);
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