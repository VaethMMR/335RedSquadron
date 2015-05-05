package objects;

import sprites.CursorSprite;

public class Cursor extends SpriteObject {
	private SpriteObject select;
	public Cursor(int x, int y, SpriteObject sprite){
		super(new CursorSprite(), x, y, 100);
		select = null;
	}
	
	public void setSelected(SpriteObject sprite){
		select = sprite;
	}
	
	public SpriteObject getSelected(){
		return select;
	}

		
}
