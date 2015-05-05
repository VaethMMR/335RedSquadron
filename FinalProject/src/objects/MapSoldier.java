package objects;
import sprites.SoldierSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapSoldier extends SpriteObject{
	public MapSoldier(int x, int y){
		super(new SoldierSprite(), x, y, 100);
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