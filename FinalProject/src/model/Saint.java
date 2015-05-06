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

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	@Override
	protected void setSpriteObject() {
		if(team == Team.PLAYER)
			spriteObject = new MapBishop(500, 500);
		else
			spriteObject = new MapSaint(500, 500);
	}

	@Override
	public SpriteObject getSpriteObject() {
		return spriteObject;
	}

}