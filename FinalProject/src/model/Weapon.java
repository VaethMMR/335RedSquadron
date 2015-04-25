package model;
// 335 Final Project - Red Squadron
// Authors: Connor Broderick

public class Weapon extends Item {
private int might;
private int critical;
private int accuracy;

//Still did not see a weapon on github, so I have made a very simple one. I'm not sure how John wants to extend the classes
//and make it all integrate so I just hardcoded one for the milestone. 
   public Weapon(int cost, String name){
      super(5, "bronze sword");
      might = 4;
      critical = 5;
      accuracy = 60;
   }

            
   public int getMight(){
      return might;
   }
   
   public int getCritical(){
      return critical;
   }

   public int getAccuracy(){
      return accuracy;
   }




}
