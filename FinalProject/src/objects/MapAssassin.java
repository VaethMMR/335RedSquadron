package objects;

import model.Unit;
import sprites.AssassinSprite;
import sprites.Sprite;
import sprites.ThiefSprite;

	public class MapAssassin extends SpriteObject{
		public MapAssassin(int x, int y){
			super(new AssassinSprite(), null, x, y, 100);
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
