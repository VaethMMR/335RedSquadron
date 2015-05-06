package model;

import objects.SpriteObject;
import controller.GamePlay.Team;

public abstract class Commander extends Melee{
	
	public Commander(Team team) {
		super(team);
	}

	@Override
	protected abstract void setWeapon();

	@Override
	public abstract Weapon getWeapon();

	@Override
	public abstract void setSpriteObject(int x, int y);

	@Override
	public abstract SpriteObject getSpriteObject();
	
	
	
}
