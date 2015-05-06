package model;

import objects.MapHero;
import objects.SpriteObject;
import controller.GamePlay.Team;
import terrain.*;

//for all of these, not sure what unique things we going to add for each class so they are just extensions for now.
public final class Hero extends Commander {
	private Weapon weapon;
	private SpriteObject sprite;
	private static Hero hero;
	private Team team;

   private Hero(Team team){
	   super(team);
	   team = Team.PLAYER;
   }
   
   public static synchronized Hero getHero(){
		
		if(hero == null)
			hero = new Hero(Team.PLAYER);
		
		return hero;
	}
   
   public boolean seize(Terrain piece){
	   if(piece == Throne.getThrone())
		   return true;
	   return false;
   }
   
   protected void setWeapon(){
		weapon = WeaponFactory.makeWeapon(this);
	}
	
	public Weapon getWeapon(){
		return weapon;
	}

	@Override
	public void setSpriteObject(int x, int y) {
			sprite = new MapHero(x, y);		
	}
			
	@Override
	public SpriteObject getSpriteObject() {
		return sprite;
	}




}