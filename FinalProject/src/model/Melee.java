package model;

import objects.SpriteObject;
import controller.GamePlay.Team;
import sprites.Sprite;

public abstract class Melee extends Unit {


   public Melee(Team team){
	   super(team);
   }
   
   protected abstract void setWeapon();
   public abstract Weapon getWeapon();


public abstract void setSpriteObject(int x, int y);
   
public abstract SpriteObject getSpriteObject();


}