package model;

import objects.MapSwordmaster;
import objects.MapTrueblade;
import objects.SpriteObject;
import controller.GamePlay.Team;
import sprites.Sprite;
import sprites.SwordmasterSprite;
import sprites.TruebladeSprite;

public class Swordmaster extends Melee {
	private Weapon weapon;
	private SpriteObject sprite;
	private Team team;
	
	public Swordmaster(Team team){
		super(team);
		this.team = team;
	}
	
	protected void setWeapon(){
		weapon = WeaponFactory.makeWeapon(this);
	}
	
	public Weapon getWeapon(){
		return weapon;
	}

	@Override
	protected void setSpriteObject() {
		if(team == Team.PLAYER)
			sprite = new MapTrueblade(500, 500);
		else
			sprite = new MapSwordmaster(500, 500);
	}

	@Override
	public SpriteObject getSpriteObject() {
		// TODO Auto-generated method stub
		return sprite;
	}

}
