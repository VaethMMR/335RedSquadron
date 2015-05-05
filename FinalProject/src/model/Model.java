package model;

import controller.GamePlay;

public class Model {
	private GamePlay game;
	
	public Model(GamePlay game){this.game = game;}
	
	public GamePlay getGame(){ return game;}
}
