package model;
// 335 Final Project - Red Squadron
// Authors: Connor Broderick

public class Weapon extends Item {
private int might;
private int critical;
private int accuracy;
private int cost;
private int range;

//Still did not see a weapon on github, so I have made a very simple one. I'm not sure how John wants to extend the classes
//and make it all integrate so I just hardcoded one for the milestone. 
   public Weapon(String name, int level){
	   super(name,level);

   }

   // get Methods         
   public int getMight(){
      return might;
   }
   
   public int getCritical(){
      return critical;
   }

   public int getAccuracy(){
      return accuracy;
   }
   public int getCost(){
	   return cost;
   }
   public int getRange(){
	   return range;
   }
   
   // Set Methods
   public void setMight(int might){
	   this.might = might;
   }
   public void setCritical(int critical){
	   this.critical = critical;
   }
   public void setAccuracy(int accuracy){
	   this.accuracy = accuracy;
   }
   public void setRange(int range){
	   this.range = range;
   }
   public void setCost(int cost){
	   this.cost = cost;
   }

}
