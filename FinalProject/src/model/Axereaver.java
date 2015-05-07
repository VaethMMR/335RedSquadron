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
			sprite = new MapAxereaver(x, y);
		else
			sprite = new MapWarrior(x, y);
	}

	@Override
	public SpriteObject getSpriteObject() {
		// TODO Auto-generated method stub
		return sprite;
	}

}