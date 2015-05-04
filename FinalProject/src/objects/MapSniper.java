package objects;
import sprites.SniperSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapSniper extends SpriteObject{
	public MapSniper(int x, int y){
		super(new SniperSprite(), null, x, y, 100);
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