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
	protected void setWeapon(){
		weapon = WeaponFactory.makeWeapon(this);
	}
	
	public Weapon getWeapon(){
		return weapon;
	}
	@Override
	public void setSpriteObject(int x, int y) {
		if(team == Team.PLAYER)
			sprite = new MapDruid(x, y);
		else
			sprite = new MapDarkDruid(x, y);
}
	
	public SpriteObject getSpriteObject(){
		return sprite;
	}
}
