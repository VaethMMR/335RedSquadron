package objects;
import sprites.MarksmanSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapRanger extends SpriteObject{
	public MapRanger(int x, int y){
		super(new MarksmanSprite(), null, x, y, 100);
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