//package view;
//
//import java.awt.Graphics;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.util.TreeSet;
//
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//
//import objects.*;
//import sprites.Sprite.State;
//
//public class Castle {
//	private TreeSet<Character> keySet;
//	private SpriteObject trueblade;
//	private SpriteObject lancecaster;
//	private SpriteObject druid;
//	private SpriteObject hero;
//	private SpriteObject axereaver;
//	private SpriteObject ranger;
//	private SpriteObject sage;
//	private SpriteObject bishop;
//	private SpriteObject thief;
//
//	private SpriteObject warrior;
//	private SpriteObject darkDruid;
//	private SpriteObject soldier;
//	private SpriteObject sniper;
//	private SpriteObject swordmaster;
//	private SpriteObject darkSage;
//	private SpriteObject saint;
//	private SpriteObject general;
//
//	private SpriteObject cursor;
//	private JFrame frame;
//	private JPanel panel;
//	private Timer animTimer;
//
//	public Castle() {
//
//		keySet = new TreeSet<Character>();
//		
//		hero = new MapHero(480, 192);
//		axereaver = new MapAxereaver(416, 192);
//		ranger = new MapMarksman(416, 160);
//		bishop = new MapBishop(448, 128);
//		thief = new MapThief(448, 224);
//		trueblade = new MapTrueblade(512, 224);
//		lancecaster = new MapLanceCaster(512, 160);
//		druid = new MapDruid(576, 160);
//		sage = new MapSage(576, 224);
//		
//		
//		darkDruid = new MapDarkDruid(32, 32);
//		soldier = new MapMarksman(384, 352);
//		sniper = new MapSniper(320, 192);
//		swordmaster = new MapSwordmaster(227, 32);
//		darkSage = new MapDarkSage(160, 32);
//		saint = new MapSaint(96, 160);
//		general = new MapGeneral(64, 48);
//		warrior = new MapWarrior(256, 320);
//		
//		hero.getSprite().setState(State.MOVING_RIGHT);
//		axereaver.getSprite().setState(State.MOVING_RIGHT);
//		ranger.getSprite().setState(State.MOVING_RIGHT);
//		bishop.getSprite().setState(State.MOVING_RIGHT);
//		thief.getSprite().setState(State.MOVING_RIGHT);
//		trueblade.getSprite().setState(State.MOVING_RIGHT);
//		lancecaster.getSprite().setState(State.MOVING_RIGHT);
//		druid.getSprite().setState(State.MOVING_RIGHT);
//		sage.getSprite().setState(State.MOVING_RIGHT);
//		
//		general.getSprite().setState(State.MOVING_DOWN);
//		darkDruid.getSprite().setState(State.MOVING_DOWN);
//		darkSage.getSprite().setState(State.MOVING_DOWN);
//		warrior.getSprite().setState(State.MOVING_RIGHT);
//		saint.getSprite().setState(State.MOVING_RIGHT);
//		swordmaster.getSprite().setState(State.MOVING_RIGHT);
//		soldier.getSprite().setState(State.MOVING_RIGHT);
//		sniper.getSprite().setState(State.MOVING_RIGHT);
//		
//		hero.getSprite().setState(State.IDLE);
//		axereaver.getSprite().setState(State.IDLE);
//		ranger.getSprite().setState(State.IDLE);
//		bishop.getSprite().setState(State.IDLE);
//		thief.getSprite().setState(State.IDLE);
//		trueblade.getSprite().setState(State.IDLE);
//		lancecaster.getSprite().setState(State.IDLE);
//		druid.getSprite().setState(State.IDLE);
//		sage.getSprite().setState(State.IDLE);
//		
//		general.getSprite().setState(State.IDLE);
//		darkDruid.getSprite().setState(State.IDLE);
//		darkSage.getSprite().setState(State.IDLE);
//		warrior.getSprite().setState(State.IDLE);
//		saint.getSprite().setState(State.IDLE);
//		swordmaster.getSprite().setState(State.IDLE);
//		soldier.getSprite().setState(State.IDLE);
//		sniper.getSprite().setState(State.IDLE);
//		
//		cursor = new Cursor(400, 300, null);
//		hero.start();
//		axereaver.start();
//		ranger.start();
//		sage.start();
//		bishop.start();
//		thief.start();
//		trueblade.start();
//		lancecaster.start();
//		druid.start();
//		
//		darkDruid.start();
//		soldier.start();
//		sniper.start();
//		swordmaster.start();
//		darkSage.start();
//		saint.start();
//		general.start();
//		warrior.start();
//		druid.start();
//		cursor.start();
//
//		// creates the panel that actually draws the sprites
//		panel = new JPanel() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -8240246280302885575L;
//			private JPanel pan = new JPanel();
//
//			public void paintComponent(Graphics g) {
//				super.paintComponent(g);
//
//				Image bg = new ImageIcon("images/Castle.png").getImage();
//				pan.setLayout(new GridLayout(30, 12, -1, -1));
//				this.add(pan);
//				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
//				hero.draw(g);
//				axereaver.draw(g);
//				ranger.draw(g);
//				sage.draw(g);
//				bishop.draw(g);
//				thief.draw(g);
//				trueblade.draw(g);
//				lancecaster.draw(g);
//				druid.draw(g);
//				
//				darkDruid.draw(g);
//				soldier.draw(g);
//				sniper.draw(g);
//				swordmaster.draw(g);
//				darkSage.draw(g);
//				saint.draw(g);
//				general.draw(g);
//				warrior.draw(g);
//				cursor.draw(g);
//			}
//		};
//		// creates the timer for animating the panel
//		animTimer = new Timer(15, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				if (cursor.getSelected() == null)
//					moveAction(cursor);
//				else
//					moveAction(cursor.getSelected());
//
//				// repaint the panel
//				panel.repaint();
//			}
//
//			private void moveAction(SpriteObject sprite) {
//				if (sprite != null) { // move the warrior according to what
//					// keys are being pressed
//					if (keySet.contains('w') || keySet.contains('a')
//							|| keySet.contains('s') || keySet.contains('d')
//							|| keySet.contains('z') || keySet.contains('x')) {
//						if (keySet.contains('w')) {
//							sprite.moveUp();
//							cursor.setPosition(sprite.position.x,
//									sprite.position.y);
//						} else if (keySet.contains('a')) {
//							sprite.moveLeft();
//							cursor.setPosition(sprite.position.x,
//									sprite.position.y);
//						} else if (keySet.contains('s')) {
//							sprite.moveDown();
//							cursor.setPosition(sprite.position.x,
//									sprite.position.y);
//						} else if (keySet.contains('d')) {
//							sprite.moveRight();
//							cursor.setPosition(sprite.position.x,
//									sprite.position.y);
//						} else if (keySet.contains('z')) {
//							if (sprite instanceof Cursor) 
//								cursor.setSelected(general);
//							 else {
//								cursor.setSelected(null);
//								sprite.moveStop();
//							}
//						}
//
//						else if (keySet.contains('x')) {
//							cursor.setSelected(null);
//							sprite.setPosition(400, 300);
//							sprite.moveStop();
//						}
//					}
//				}
//
//			}
//
//		});
//
//		frame = new JFrame();
//		frame.add(panel);
//
//		frame.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				keySet.add(arg0.getKeyChar());
//			}
//
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				keySet.remove(arg0.getKeyChar());
//			}
//		});
//
//		frame.setSize(944, 400);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//
//		animTimer.start();
//	}
//	public static void main(String[] args) {
//		new Castle();	
//	}
//}