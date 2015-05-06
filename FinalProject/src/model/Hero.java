package model;

import objects.MapGeneral;
import objects.MapHero;
import objects.SpriteObject;
import controller.GamePlay.Team;
import sprites.GeneralSprite;
import sprites.HeroSprite;
import sprites.Sprite;

//for all of these, not sure what unique things we going to add for each class so they are just extensions for now.
public class Hero extends Melee {
	private Weapon weapon;
	private SpriteObject sprite;
	private Team team;

   public Hero(Team team){
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
			sprite = new MapHero(500, 500);		
		else
			sprite = new MapGeneral(500, 500);
	}

	@Override
	public SpriteObject getSpriteObject() {
		return sprite;
	}




}