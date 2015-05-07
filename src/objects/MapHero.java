package objects;
import sprites.HeroSprite;

// TODO 10: Look at Frog. How could this design be improved (think Factory)
public class MapHero extends SpriteObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7624843858891203301L;

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