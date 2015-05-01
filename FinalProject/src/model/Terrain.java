package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
// 335 Final Project - Red Squadron
// Authors: Alex Guyot and John Oney

public abstract class Terrain {
	// private variables
	private Unit unit;
	private Item item;
	private boolean standable;
	private int[] location;
	private BufferedImage graphicTile;
	
	// constructor
	public Terrain(boolean standable, int[] location) {
		this.unit = null;
		this.item = null;
		this.standable = standable;
		this.location = location;
	}
	
	// getters and setters
	public Unit getUnit() {
		return this.unit;
	}
	
	public boolean setUnit(Unit newUnit) {
		if (this.unit == null) {
			this.unit = newUnit;
			return true;
		} else {
			// TODO: throw a space full exception
			return false;
		}
	}
	
	public boolean getStandable() {
		return this.standable;
	}
	
	public int[] getLocation() {
		return this.location;
	}
	
	// misc methods
	public Unit moveUnit() {
		Unit moveUnit = this.unit;
		this.unit = null;
		return moveUnit;
	}
	
	private void buildImage() {
		try {
			BufferedImage piece1 = ImageIO.read(new File("images/grass1.png"));
			BufferedImage piece2 = ImageIO.read(new File("images/grass2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		if (this.unit != null) {
			return "[" + this.unit.getClass().getSimpleName().substring(0,1) + "]";
		}
		if (this.item != null) {
			return "[" + this.item.getClass().getSimpleName().substring(0,1) + "]";
		}
		return "[ ]";
	}
}
