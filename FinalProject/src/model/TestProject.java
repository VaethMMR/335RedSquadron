package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

import controller.GamePlay;

// Some miscellaneous tests
public class TestProject {

	@Test
	public void test() {
		// test GamePlay
		GamePlay newGame = new GamePlay();
		// print map with initial Unit distribution
		newGame.getMap().printMap();
		// move the Unit at [0,0] to [5,5]
		newGame.getMap().moveUnit(new int[]{0,0}, new int[]{5,5});
		newGame.getMap().printMap();
		
		ArrayList<Unit> unitsInGame = newGame.getMap().getUnitsOnMap();
		newGame.getMap().moveUnit(unitsInGame.get(2), new int[]{4,5});
		newGame.getMap().printMap();
	}

}
