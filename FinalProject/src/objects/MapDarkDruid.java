package objects;
import sprites.SpriteBuilder;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapDarkDruid extends SpriteObject{
	public MapDarkDruid(int x, int y){
		super(new SpriteBuilder("images/DarkDruidWalkingAlpha.png"), null, x, y, 100);
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
