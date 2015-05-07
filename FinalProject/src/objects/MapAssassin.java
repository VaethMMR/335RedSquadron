package objects;

import sprites.AssassinSprite;

	public class MapAssassin extends SpriteObject{
		/**
		 * 
		 */
		private static final long serialVersionUID = -9129634519433688510L;

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
