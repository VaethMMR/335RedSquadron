package model;

import objects.SpriteObject;
import controller.GamePlay.Team;

public abstract class Commander extends Melee{
	
	public Commander(Team team) {
		super(team);
	}

	@Override
	public abstract void setWeapon(Weapon weapon);

	@Override
	public abstract Weapon getWeapon();

	@Override
	public abstract void setSpriteObject(int x, int y);

	@Override
	public abstract SpriteObject getSpriteObject();
	
	
	
}
