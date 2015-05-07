package objects;
import sprites.GeneralSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapGeneral extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3424750922746551111L;

	public MapGeneral(int x, int y){
		super(new GeneralSprite(), null, x, y, 100);
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