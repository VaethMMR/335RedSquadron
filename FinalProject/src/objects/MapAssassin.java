package objects;

import sprites.SpriteBuilder;

	public class MapAssassin extends SpriteObject{
		public MapAssassin(int x, int y){
			super(new SpriteBuilder("images/AssassinWalkingAlpha.png"), null, x, y, 100);
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
