package terrain;

import java.awt.image.BufferedImage;

import exceptions.TileNotStandableException;
import exceptions.TileOccupiedException;
import model.Item;
import model.Unit;

public abstract class Terrain {
	// private variables
	private Unit unit;
	private Item item;
	private boolean standable;
	private boolean playerSpawnPoint;
	private boolean aiSpawnPoint;
	private boolean heroSpawnPoint;
	private boolean moveHighlight = false;
	private boolean attackHighlight = false;
	private int[] location;
	
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
	
	public void setUnit(Unit newUnit) {
		if (this.unit == null) {
			if (this.standable) {
				this.unit = newUnit;
			} else {
				throw new TileNotStandableException();
			}
		} else {
			throw new TileOccupiedException();
		}
	}
	
	public boolean getStandable() {
		return this.standable;
	}
	
	public boolean getPlayerSpawnPoint() {
		return this.playerSpawnPoint;
	}
	
	public void setPlayerSpawnPoint(boolean spawnPoint) {
		this.playerSpawnPoint = spawnPoint;
	}
	
	public boolean getAiSpawnPoint() {
		return this.aiSpawnPoint;
	}
	
	public void setAiSpawnPoint(boolean spawnPoint) {
		this.aiSpawnPoint = spawnPoint;
	}
	
	public boolean getHeroSpawnPoint() {
		return this.heroSpawnPoint;
	}
	
	public void setHeroSpawnPoint(boolean spawnPoint) {
		this.heroSpawnPoint = spawnPoint;
	}
	
	public int[] getLocation() {
		return this.location;
	}
	
	public boolean hasMoveHighlight() {
		return this.moveHighlight;
	}
	
	public void setMoveHighlighted(boolean highlighted) {
		this.moveHighlight = highlighted;
	}
	
	public boolean hasAttackHighlight() {
		return this.attackHighlight;
	}
	
	public void setAttackHighlighted(boolean highlighted) {
		this.attackHighlight = highlighted;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public void setItem(Item theItem) {
		this.item = theItem;
	}
	
	public abstract BufferedImage getGraphic();
	
	// misc methods
	public Unit moveUnit() {
		Unit moveUnit = this.unit;
		this.unit = null;
		return moveUnit;
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
