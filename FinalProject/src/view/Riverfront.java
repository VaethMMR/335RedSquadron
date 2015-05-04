package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.GameMap;
import objects.*;
import sprites.Sprite.State;

public class Riverfront {
	private TreeSet<Character> keySet;
	private SpriteObject trueblade;
	private SpriteObject lancecaster;
	private SpriteObject druid;
	private SpriteObject hero;
	private SpriteObject axereaver;
	private SpriteObject ranger;
	private SpriteObject sage;
	private SpriteObject bishop;
	private SpriteObject thief;

	private SpriteObject warrior;
	private SpriteObject darkDruid;
	private SpriteObject soldier;
	private SpriteObject sniper;
	private SpriteObject swordmaster;
	private SpriteObject darkSage;
	private SpriteObject saint;
	private SpriteObject general;
	private GraphicPanel riverfront;
	private SpriteObject cursor;
	private JFrame frame;
	private JPanel panel;
	private Timer animTimer;

	public Riverfront() {
		riverfront = (GraphicPanel) panel;
		keySet = new TreeSet<Character>();
		
		hero = new MapHero(576, 512);
		axereaver = new MapAxereaver(512, 512);
		ranger = new MapRanger(448, 512);
		bishop = new MapBishop(544, 480);
		thief = new MapThief(480, 480);
		trueblade = new MapTrueblade(416, 480);
		lancecaster = new MapLanceCaster(576, 448);
		druid = new MapDruid(512, 448);
		sage = new MapSage(448, 448);
		
		darkDruid = new MapDarkDruid(32, 32);
		soldier = new MapSoldier(96, 32);
		sniper = new MapSniper(160, 32);
		swordmaster = new MapSwordmaster(224, 32);
		darkSage = new MapDarkSage(32, 96);
		saint = new MapSaint(96, 96);
		general = new MapGeneral(160, 96);
		warrior = new MapWarrior(224, 96);
		
		hero.getSprite().setState(State.MOVING_LEFT);
		axereaver.getSprite().setState(State.MOVING_LEFT);
		ranger.getSprite().setState(State.MOVING_LEFT);
		bishop.getSprite().setState(State.MOVING_LEFT);
		thief.getSprite().setState(State.MOVING_LEFT);
		trueblade.getSprite().setState(State.MOVING_LEFT);
		lancecaster.getSprite().setState(State.MOVING_LEFT);
		druid.getSprite().setState(State.MOVING_LEFT);
		sage.getSprite().setState(State.MOVING_LEFT);
		
		general.getSprite().setState(State.MOVING_DOWN);
		darkDruid.getSprite().setState(State.MOVING_DOWN);
		darkSage.getSprite().setState(State.MOVING_DOWN);
		warrior.getSprite().setState(State.MOVING_RIGHT);
		saint.getSprite().setState(State.MOVING_RIGHT);
		swordmaster.getSprite().setState(State.MOVING_RIGHT);
		soldier.getSprite().setState(State.MOVING_DOWN);
		sniper.getSprite().setState(State.MOVING_RIGHT);
		
		hero.getSprite().setState(State.IDLE);
		axereaver.getSprite().setState(State.IDLE);
		ranger.getSprite().setState(State.IDLE);
		bishop.getSprite().setState(State.IDLE);
		thief.getSprite().setState(State.IDLE);
		trueblade.getSprite().setState(State.IDLE);
		lancecaster.getSprite().setState(State.IDLE);
		druid.getSprite().setState(State.IDLE);
		sage.getSprite().setState(State.IDLE);
		
		general.getSprite().setState(State.IDLE);
		darkDruid.getSprite().setState(State.IDLE);
		darkSage.getSprite().setState(State.IDLE);
		warrior.getSprite().setState(State.IDLE);
		saint.getSprite().setState(State.IDLE);
		swordmaster.getSprite().setState(State.IDLE);
		soldier.getSprite().setState(State.IDLE);
		sniper.getSprite().setState(State.IDLE);
		
		cursor = new Cursor(400, 300, null);
		hero.start();
		axereaver.start();
		ranger.start();
		sage.start();
		bishop.start();
		thief.start();
		trueblade.start();
		lancecaster.start();
		druid.start();
		
		darkDruid.start();
		soldier.start();
		sniper.start();
		swordmaster.start();
		darkSage.start();
		saint.start();
		general.start();
		warrior.start();
		druid.start();
		cursor.start();

		// creates the panel that actually draws the sprites
		//GameMap map = new GameMap();
		panel = new GraphicPanel(null) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2298200931090302643L;
			private JPanel pan = new JPanel();

			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				Image bg = new ImageIcon("images/Riverfront.png").getImage();
				pan.setLayout(new GridLayout(20, 18, -1, -1));
				this.add(pan);
				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
				hero.draw(g);
				axereaver.draw(g);
				ranger.draw(g);
				sage.draw(g);
				bishop.draw(g);
				thief.draw(g);
				trueblade.draw(g);
				lancecaster.draw(g);
				druid.draw(g);
				
				darkDruid.draw(g);
				soldier.draw(g);
				sniper.draw(g);
				swordmaster.draw(g);
				darkSage.draw(g);
				saint.draw(g);
				general.draw(g);
				warrior.draw(g);
				cursor.draw(g);
			}
		};
		// creates the timer for animating the panel
		animTimer = new Timer(15, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (cursor.getSelected() == null)
					moveAction(cursor);
				else
					moveAction(cursor.getSelected());

				// repaint the panel
				panel.repaint();
			}

			private void moveAction(SpriteObject sprite) {
				if (sprite != null) { // move the warrior according to what
					// keys are being pressed
					if (keySet.contains('w') || keySet.contains('a')
							|| keySet.contains('s') || keySet.contains('d')
							|| keySet.contains('z') || keySet.contains('x')) {
						if (keySet.contains('w')) {
							sprite.moveUp();
							cursor.setPosition(sprite.position.x,
									sprite.position.y);
						} else if (keySet.contains('a')) {
							sprite.moveLeft();
							cursor.setPosition(sprite.position.x,
									sprite.position.y);
						} else if (keySet.contains('s')) {
							sprite.moveDown();
							cursor.setPosition(sprite.position.x,
									sprite.position.y);
						} else if (keySet.contains('d')) {
							sprite.moveRight();
							cursor.setPosition(sprite.position.x,
									sprite.position.y);
						} else if (keySet.contains('z')) {
							if (sprite instanceof Cursor) 
								cursor.setSelected(trueblade);
							 else {
								cursor.setSelected(null);
								sprite.moveStop();
							}
						}

						else if (keySet.contains('x')) {
							cursor.setSelected(null);
							sprite.setPosition(400, 300);
							sprite.moveStop();
						}
					}
				}

			}

		});

		frame = new JFrame();
		frame.add(panel);

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				keySet.add(arg0.getKeyChar());
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				keySet.remove(arg0.getKeyChar());
			}
		});

		frame.setSize(640, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		animTimer.start();
	}
	public static void main(String[] args) {
		new Riverfront();	
	}
}