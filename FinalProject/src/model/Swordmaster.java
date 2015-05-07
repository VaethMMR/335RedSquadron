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
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public Weapon getWeapon(){
		return weapon;
	}

	@Override
	public void setSpriteObject(int x, int y) {
		if(team == Team.PLAYER)
			sprite = new MapTrueblade(x, y);
		else
			sprite = new MapSwordmaster(x, y);
	}

	@Override
	public SpriteObject getSpriteObject() {
		// TODO Auto-generated method stub
		return sprite;
	}

}
