package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Consumable;
import model.ConsumableFactory;
import model.Inventory;
import model.Trap;
import model.TrapFactory;
import model.Weapon;
import model.WeaponFactory;
import controller.GamePlay;
import exceptions.InventoryFullException;

public class ShopView extends JPanel {
	private GamePlay theGame;
	private String[][] data;
	private JScrollPane scroll2;
	private JTable table;
	private JTable stats;
	private JTable conStats;
	private WeaponFactory factory;
	private ConsumableFactory cFactory;
	private TrapFactory tFactory;

	public ShopView(GamePlay theGame) {
		this.theGame = theGame;
		this.factory = new WeaponFactory();
		this.cFactory = new ConsumableFactory();
		this.tFactory = new TrapFactory();
		this.setLayout(new GridLayout(3,1));
		this.setBackground(Color.WHITE);
		String[] names = {"Sword","Sword","Sword", "Bow","Bow","Bow","Staff","Staff","Staff"
				,"Axe","Axe","Axe","Health Potion", "Defense Potion", "Resistance Potion",
				"Mine", "Barrier"};
		int[] levels = {1,2,3,1,2,3,1,2,3,1,2,3,1,1,1,1,1};
		int[] costs = {5,10,15,5,10,15,5,10,15,5,10,15,5,5,5,10,10};
		String[] columnNames = {"Item", "level", "cost"};
		data = new String[17][3];
		table = new JTable(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		Color ivory = new Color(255, 255, 208);
		table.setOpaque(true);
		table.setBackground(ivory);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table);
		add(scroll);
		
		// set up consumable stat table
		String [] consumableCols = {"Item", "Level", "Health", "Defense", "Resistance", "Uses"};
		String [][] consData = new String[1][6];
		conStats = new JTable(consData, consumableCols){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		conStats.setRowHeight(55);
		
		// Set up Weapon stat table
		String[][] test = new String[1][6];
		String [] columnNames2 = {"Item","Range","Might","Accuracy","Critical", "Magic"};
		stats = new JTable(test, columnNames2){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		stats.setRowHeight(55);
		scroll2 = new JScrollPane();
		scroll2.setViewportView(stats);
		add(scroll2);
		scroll2.setVisible(false);
		
		table.getSelectionModel().addListSelectionListener(new cellListener());

		setUpTable(names,levels,costs);
		
		JButton buy = new JButton("Buy");
		add(buy);
		buy.addActionListener(new buttonListener());

	}

	private void setUpTable(String[] names, int [] levels, int [] costs) {
		for(int row = 0; row<17;row++){
			data[row][0] = names[row];
			data[row][1] = levels[row] +"";
			data[row][2] = costs[row] + "";
				
		}
			
	}

	private class cellListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(table.getSelectedRow() > 11 && table.getSelectedRow() < 15){
				scroll2.setViewportView(conStats);
				scroll2.setVisible(true);
				String name = (String) table.getValueAt(table.getSelectedRow(), 0);
				int level = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1));
				Consumable consumable = cFactory.makeConsumable(name, level);
				conStats.setValueAt(name +"", 0, 0);
				conStats.setValueAt(consumable.getLevel() +"", 0, 1);
				conStats.setValueAt(consumable.getHealth() +"", 0, 2);
				conStats.setValueAt(consumable.getDefense() +"", 0, 3);
				conStats.setValueAt(consumable.getResistance() +"", 0, 4);
				conStats.setValueAt(consumable.getRemainingUses() +"", 0, 5);
			}else if(table.getSelectedRow() < 12){
				scroll2.setViewportView(stats);
				scroll2.setVisible(true);
				String name = (String) table.getValueAt(table.getSelectedRow(), 0);
				int level = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1));
				Weapon weapon = factory.makeWeapon(name, level);
				stats.setValueAt(name +"", 0, 0);
				stats.setValueAt(weapon.getRange() +"", 0, 1);
				stats.setValueAt(weapon.getMight() +"", 0, 2);
				stats.setValueAt(weapon.getAccuracy() +"", 0, 3);
				stats.setValueAt(weapon.getCritical() +"", 0, 4);
				stats.setValueAt(weapon.getMagic() +"", 0, 5);
			}else{
				scroll2.setVisible(false);
				String name = (String) table.getValueAt(table.getSelectedRow(), 0);
				int level = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1));
				Trap trap = tFactory.makeTrap(name, level);
			}
			
		}
	}
	
	private class buttonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRow() > 11 && table.getSelectedRow() < 15){
				String name = (String) table.getValueAt(table.getSelectedRow(), 0);
				int level = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1));
				Consumable consumable = cFactory.makeConsumable(name, level);
				try {
					theGame.getInventory().add(consumable);
					theGame.getInventory().setNumCoins(theGame.getInventory().getNumCoins() - consumable.getCost());
					theGame.getInventoryView().setUpCoins();
					theGame.getInventoryView().setUpTable(theGame.getInventory());
				} catch(InventoryFullException x) {
					JOptionPane.showMessageDialog(null, x.getMessage());
				}
			}else if(table.getSelectedRow() < 12){
				String name = (String) table.getValueAt(table.getSelectedRow(), 0);
				int level = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1));
				Weapon weapon = factory.makeWeapon(name, level);
				try {
					theGame.getInventory().add(weapon);
					theGame.getInventory().setNumCoins(theGame.getInventory().getNumCoins() - weapon.getCost());
					theGame.getInventoryView().setUpCoins();
					theGame.getInventoryView().setUpTable(theGame.getInventory());
				} catch(InventoryFullException x) {
					JOptionPane.showMessageDialog(null, x.getMessage());
				}
			}else{
				String name = (String) table.getValueAt(table.getSelectedRow(), 0);
				int level = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1));
				Trap trap = tFactory.makeTrap(name, level);
				try {
					theGame.getInventory().add(trap);
					theGame.getInventory().setNumCoins(theGame.getInventory().getNumCoins() - trap.getCost());
					theGame.getInventoryView().setUpCoins();
					theGame.getInventoryView().setUpTable(theGame.getInventory());
				} catch(InventoryFullException x) {
					JOptionPane.showMessageDialog(null, x.getMessage());
				}
			}
		}
		
	}
}