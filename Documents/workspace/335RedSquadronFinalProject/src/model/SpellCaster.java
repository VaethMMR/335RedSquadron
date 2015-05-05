package model;

import objects.SpriteObject;
import sprites.Sprite;
import controller.GamePlay.Team;

public abstract class SpellCaster extends Ranged {


   public SpellCaster(Team team){
	   super(team);
   }

   public boolean heal(Unit other){
      
      other.setHp(other.getCurrentHp() + 5 + getMagic() / 2);
      if(other.getCurrentHp() > other.getHp()){//if it heals more than max hp reset current hp to max. 
         other.setHp(other.getHp());
      }
      
      return true;
   }

   protected abstract void setWeapon();
   public abstract Weapon getWeapon();

protected abstract void setSpriteObject();
public abstract SpriteObject getSpriteObject();
	
}
