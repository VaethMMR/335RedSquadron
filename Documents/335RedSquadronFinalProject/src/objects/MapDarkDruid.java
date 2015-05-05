package objects;
import sprites.DarkDruidSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapDarkDruid extends SpriteObject{
	public MapDarkDruid(int x, int y){
		super(new DarkDruidSprite(), x, y, 100);
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
