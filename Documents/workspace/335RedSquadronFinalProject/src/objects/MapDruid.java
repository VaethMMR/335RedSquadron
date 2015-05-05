package objects;
import sprites.DruidSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapDruid extends SpriteObject{
	public MapDruid(int x, int y){
		super(new DruidSprite(), x, y, 100);
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
