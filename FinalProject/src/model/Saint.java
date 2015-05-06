package model;

import objects.MapBishop;
import objects.MapSaint;
import objects.SpriteObject;
import sprites.Sprite;
import controller.GamePlay.Team;

public class Saint extends SpellCaster {
	private Weapon weapon;
	private SpriteObject spriteObject;
	private Team team;
	
	public Saint(Team team){
		super(team);
		this.team = team;
	}

	protected void setWeapon() {
		weapon = WeaponFactory.makeWeapon(this);
	}

	public Weapon getWeapon() {
		return weapon;
	}

	@Override
	public void setSpriteObject(int x, int y) {
		if(team == Team.PLAYER)
			spriteObject = new MapBishop(x, y);
		else
			spriteObject = new MapSaint(x, y);
	}

	@Override
	public SpriteObject getSpriteObject() {
		return spriteObject;
	}

}