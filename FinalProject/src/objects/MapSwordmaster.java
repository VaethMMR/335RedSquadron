package objects;
import sprites.SwordmasterSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapSwordmaster extends SpriteObject{
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
