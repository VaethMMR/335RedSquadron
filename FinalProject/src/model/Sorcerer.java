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
     this.team = team;
   }	   

   		protected void setWeapon(){
			weapon = WeaponFactory.makeWeapon(this);
		}
		
		public Weapon getWeapon(){
			return weapon;
		}

		public void setSpriteObject(int x, int y) {
			if(team == Team.PLAYER)
				sprite = new MapSage(x, y);
			else
				sprite = new MapDarkSage(x, y);
		}

		@Override
		public SpriteObject getSpriteObject() {
			return sprite;
		}


}