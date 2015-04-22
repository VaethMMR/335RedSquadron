package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

import static org.junit.Assert.*;

import org.junit.Test;

import controller.GamePlay;

// Some miscellaneous tests
public class TestProject {

	@Test
	public void test() {
		// test GamePlay
		GamePlay newGame = new GamePlay();
		
		newGame.getMap().printMap();
		
		/*// Test printing out the map
		Map theMap = new Map("GrassMap");
		
		Unit newUnit = new Hero(null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		theMap.getMap()[1][1].setUnit(newUnit);
		theMap.printMap();
		
		// move the unit and print again
		System.out.println();
		Unit thatUnit = theMap.getMap()[1][1].moveUnit();
		theMap.getMap()[1][3].setUnit(thatUnit);
		theMap.printMap();*/


	}

}
