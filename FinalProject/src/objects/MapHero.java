package objects;
import sprites.HeroSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapHero extends SpriteObject{
	public MapHero(int x, int y){
		super(new HeroSprite(), null, x, y, 100);
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