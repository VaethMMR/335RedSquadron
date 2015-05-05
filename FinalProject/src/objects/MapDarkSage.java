package objects;
import sprites.DarkSageSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapDarkSage extends SpriteObject{
	public MapDarkSage(int x, int y){
		super(new DarkSageSprite(), x, y, 100);
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