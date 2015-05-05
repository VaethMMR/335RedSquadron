package objects;
import sprites.LanceCasterSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapLanceCaster extends SpriteObject{
	public MapLanceCaster(int x, int y){
		super(new LanceCasterSprite(), null, x, y, 100);
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
