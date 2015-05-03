package objects;
import sprites.ThiefSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapThief extends SpriteObject{
	public MapThief(int x, int y){
		super(new ThiefSprite(), x, y, 100);
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