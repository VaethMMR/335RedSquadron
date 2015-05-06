package model;

import objects.MapDarkDruid;
import objects.MapDruid;
import objects.SpriteObject;
import controller.GamePlay.Team;
import sprites.DarkDruidSprite;
import sprites.DruidSprite;
import sprites.Sprite;

public class Druid extends SpellCaster{
	private Weapon weapon;
	private SpriteObject sprite;
	private Team team;
	
	public Druid(Team team){
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
	protected void setSpriteObject() {
		if(team == Team.PLAYER)
			sprite = new MapDruid(500, 500);
		else
			sprite = new MapDarkDruid(500, 500);
	
}
	public SpriteObject getSpriteObject(){
		return sprite;
	}
}
