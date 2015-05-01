package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Inventory;
import model.Weapon;
import model.WeaponFactory;
import controller.GamePlay;

public class ShopView extends JPanel {
	private GamePlay theGame;
	private String[][] data;
	private JScrollPane scroll2;
	private JTable table;
	private JTable stats;
	private WeaponFactory factory;

	public ShopView(GamePlay theGame) {
		this.theGame = theGame;
		this.factory = new WeaponFactory();
		this.setLayout(new GridLayout(3,1));
		this.setBackground(Color.WHITE);
		String[] names = {"Sword","Sword","Sword", "Bow","Bow","Bow","Staff","Staff","Staff"
				,"Axe","Axe","Axe","Health Potion", "Defense Potion", "Attack Potion",
				"Trap"};
		int[] levels = {1,2,3,1,2,3,1,2,3,1,2,3,1,1,1,1};
		int[] costs = {5,10,15,5,10,15,5,10,15,5,10,15,5,5,5,10};
		String[] columnNames = {"Item", "level", "cost"};
		data = new String[16][3];
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
		
		String [] columnNames2 = {"Item","Range","Might","Accuracy","Critical"};
		String[][] test = new String[1][5];
		
		
		stats = new JTable(test, columnNames2);
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
		for(int row = 0; row<16;row++){
			data[row][0] = names[row];
			data[row][1] = levels[row] +"";
			data[row][2] = costs[row] + "";
				
		}
			
	}

	private class cellListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			scroll2.setVisible(true);
			String name = (String) table.getValueAt(table.getSelectedRow(), 0);
			int level = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1));
			Weapon weapon = factory.makeWeapon(name, level);
			stats.setValueAt(name +"", 0, 0);
			stats.setValueAt(weapon.getRange() +"", 0, 1);
			stats.setValueAt(weapon.getMight() +"", 0, 2);
			stats.setValueAt(weapon.getAccuracy() +"", 0, 3);
			stats.setValueAt(weapon.getCritical() +"", 0, 4);
			
		}
	}
	
	private class buttonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectionModel() != null){
				String name = (String) table.getValueAt(table.getSelectedRow(), 0);
				int level = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1));
				Weapon weapon = factory.makeWeapon(name, level);
				theGame.getInventory().add(weapon);
				theGame.getInventoryView().setUpTable(theGame.getInventory());
			}
		}
		
	}
}