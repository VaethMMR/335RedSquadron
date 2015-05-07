package objects;
import sprites.DruidSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapDruid extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1101474037095827160L;

	public MapDruid(int x, int y){
		super(new DruidSprite(), null, x, y, 100);
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
