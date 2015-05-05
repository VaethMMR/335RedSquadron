package model;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public class Stats {
	// private variables
	private int movement;
	private int strength;
	private int magic;
	private int skill;
	private int speed;
	private int luck;
	private int defense;
	private int resistance;

	public Stats(int level) {
		this.movement = level * 2;
		this.strength = level * 2;
		this.magic = level * 2;
		this.skill = level * 2;
		this.speed = level * 2;
		this.luck = level * 2;
		this.defense = level * 2;
		this.resistance = level * 2;
	}

}
