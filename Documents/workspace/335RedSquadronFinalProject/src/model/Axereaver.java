package model;

import objects.MapAxereaver;
import objects.MapWarrior;
import objects.SpriteObject;
import controller.GamePlay.Team;
import sprites.AxereaverSprite;
import sprites.Sprite;
import sprites.WarriorSprite;

//for all of these, not sure what unique things we going to add for each class so they are just extensions for now.
public class Axereaver extends Melee {
	private Weapon weapon;
	private SpriteObject sprite;
	private Team team;

   public Axereaver(Team team){
     super(team);
     this.team = team;
  	setWeapon();
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
			sprite = new MapAxereaver(500, 500);
		else
			sprite = new MapWarrior(500, 500);
	}

	@Override
	public SpriteObject getSpriteObject() {
		// TODO Auto-generated method stub
		return sprite;
	}

}