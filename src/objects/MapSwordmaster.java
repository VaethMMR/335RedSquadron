package objects;
import sprites.SwordmasterSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapSwordmaster extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7466190614635740373L;

	public MapSwordmaster(int x, int y){
		super(new SwordmasterSprite(), null, x, y, 100);
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
