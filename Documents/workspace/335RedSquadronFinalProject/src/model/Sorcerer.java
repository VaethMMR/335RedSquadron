package model;

import objects.MapDarkSage;
import objects.MapSage;
import objects.SpriteObject;
import controller.GamePlay.Team;
import sprites.DarkSageSprite;
import sprites.SageSprite;
import sprites.Sprite;

public class Sorcerer extends SpellCaster {
	private Weapon weapon;
	private SpriteObject sprite;
	private Team team;

   public Sorcerer(Team team){
	   super(team);
     
   }	   

   		protected void setWeapon(){
			weapon = WeaponFactory.makeWeapon(this);
		}
		
		public Weapon getWeapon(){
			return weapon;
		}

		protected void setSpriteObject() {
			if(team == Team.PLAYER)
				sprite = new MapSage(500, 500);
			else
				sprite = new MapDarkSage(500, 500);
		}

		@Override
		public SpriteObject getSpriteObject() {
			return sprite;
		}


}