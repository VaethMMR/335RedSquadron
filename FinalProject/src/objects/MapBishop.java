package objects;
import sprites.SpriteBuilder;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapBishop extends SpriteObject{
	public MapBishop(int x, int y){
		super(new SpriteBuilder("images/BishopWalkingAlpha.png"), null, x, y, 100);
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