package model;

import objects.MapMarksman;
import objects.MapSniper;
import objects.SpriteObject;
import controller.GamePlay.Team;
import sprites.SniperSprite;
import sprites.Sprite;

//for all of these, not sure what unique things we going to add for each class so they are just extensions for now.
public class Marksman extends Ranged {
	private Weapon weapon;
	private SpriteObject sprite;
	private Team team;
	
   public Marksman(Team team){
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
			sprite = new MapMarksman(500, 500);
		else
			sprite = new MapSniper(500, 500);
	}

	@Override
	public SpriteObject getSpriteObject() {
		// TODO Auto-generated method stub
		return sprite;
	}
}