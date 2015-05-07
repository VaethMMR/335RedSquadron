package objects;
import sprites.AxereaverSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapAxereaver extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2379091327274331210L;

	public MapAxereaver(int x, int y){
		super(new AxereaverSprite(), null, x, y, 100);
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