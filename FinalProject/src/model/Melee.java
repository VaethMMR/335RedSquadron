package model;

import objects.SpriteObject;
import controller.GamePlay.Team;
import sprites.Sprite;

public abstract class Melee extends Unit {


   public Melee(Team team){
	   super(team);
   }
   
   public abstract void setWeapon(Weapon weapon);
   public abstract Weapon getWeapon();


protected abstract void setSpriteObject();
   
public abstract SpriteObject getSpriteObject();


}