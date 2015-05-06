package objects;
import sprites.WarriorSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapWarrior extends SpriteObject{
	public MapWarrior(int x, int y){
		super(new WarriorSprite(), null, x, y, 100);
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
