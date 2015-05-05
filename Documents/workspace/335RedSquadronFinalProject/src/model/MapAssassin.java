package model;

import objects.SpriteObject;
import sprites.Sprite;

public class MapAssassin extends SpriteObject {

	protected MapAssassin(int x, int y) {
		super(new AssassinSprite(), x, y, 100);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setSelected(SpriteObject sprite) {
		// TODO Auto-generated method stub

	}

	@Override
	public SpriteObject getSelected() {
		// TODO Auto-generated method stub
		return null;
	}

}
