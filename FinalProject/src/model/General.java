package model;

import objects.MapGeneral;
import objects.SpriteObject;
import controller.GamePlay.Team;
import model.*;

public final class General extends Commander {

	private SpriteObject sprite;
	private Weapon weapon;
	private static General general;

	private General(Team team) {
		super(team);
		team = team.COMPUTER;
	}

	public static synchronized General getGeneral() {

		if (general == null)
			general = new General(Team.COMPUTER);

		return general;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setSpriteObject(int x, int y) {
		sprite = new MapGeneral(x, y);
	}

	public SpriteObject getSpriteObject() {
		return sprite;
	}

}
