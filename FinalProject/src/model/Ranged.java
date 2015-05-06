package model;

import objects.SpriteObject;
import sprites.Sprite;
import controller.GamePlay.Team;

public abstract class Ranged extends Unit {


   public Ranged(Team team){
	   super(team);
   }

   protected abstract void setWeapon();
   public abstract Weapon getWeapon();
   
   public abstract void setSpriteObject(int x, int y);
   public abstract SpriteObject getSpriteObject();
}