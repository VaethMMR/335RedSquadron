package model;

import objects.MapLanceCaster;
import objects.MapMarksman;
import objects.MapSoldier;
import objects.SpriteObject;
import controller.GamePlay.Team;
import sprites.LanceCasterSprite;
import sprites.SoldierSprite;
import sprites.Sprite;

public class Lancecaster extends Melee {
	private Weapon weapon;
	private SpriteObject sprite;
	private Team team;
	
	public Lancecaster(Team team){
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
			sprite = new MapLanceCaster(x, y);
		else
			sprite = new MapSoldier(x, y);
	}

	@Override
	public SpriteObject getSpriteObject() {
		// TODO Auto-generated method stub
		return sprite;
	}

}
