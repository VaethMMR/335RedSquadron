package objects;
import sprites.SoldierSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapSoldier extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1572354131304442843L;

	public MapSoldier(int x, int y){
		super(new SoldierSprite(), null, x, y, 100);
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