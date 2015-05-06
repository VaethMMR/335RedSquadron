package objects;
import sprites.SoldierSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapMarksman extends SpriteObject{
	public MapMarksman(int x, int y){
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