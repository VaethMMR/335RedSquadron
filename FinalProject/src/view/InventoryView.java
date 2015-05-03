package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Inventory;
import model.Item;
import model.Unit;
import model.Weapon;
import controller.GamePlay;

public class InventoryView extends JPanel {
	private GamePlay theGame;
	private Inventory theInventory;
	private DefaultTableModel data;
	private JTable stats;
	private JScrollPane scroll2;
	private JTable table;
	private JList<String> playerUnits;
	private DefaultListModel<String> playerUnitsModel;
	private Weapon weapon;

	public InventoryView(Inventory theInventory, GamePlay theGame) {
		this.theGame = theGame;
		this.theInventory = theInventory;
		this.setLayout(new GridLayout(3, 1));
		data = new DefaultTableModel();
		table = new JTable(data) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		Color ivory = new Color(255, 255, 208);
		table.setOpaque(true);
		table.setFillsViewportHeight(true);
		table.setBackground(ivory);
		table.setRowHeight(55);
		add(table);
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new MouseAdapter());
		setUpTable(theInventory);
		
		String[] columnNames2 = { "Item", "Range", "Might", "Accuracy",
		"Critical", "Magic" };
		String[][] test = new String[1][7];

		stats = new JTable(test, columnNames2) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		stats.setRowHeight(55);
		scroll2 = new JScrollPane();
		scroll2.setViewportView(stats);
		add(scroll2);
		
		JButton equip = new JButton("Use");
		JPanel bottomPanel = new JPanel();
		add(bottomPanel);
		bottomPanel.setLayout(new GridLayout(1,2));
		
		playerUnitsModel = new DefaultListModel<String>();
		playerUnits = new JList<String>(playerUnitsModel);
		playerUnits.setPreferredSize(new Dimension(150,200));
		// add the JList to the panel
		bottomPanel.add(this.playerUnits);
		
		bottomPanel.add(equip);
		equip.addActionListener(new ButtonListener());
		
		setupInventoryList(theGame.getPlayerTeam());

	}

	public void setUpTable(Inventory theInventory) {
		data.setRowCount(5);
		data.setColumnCount(5);
		int counter = 0;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (theInventory.getInventory().size() > counter) {
					data.setValueAt(theInventory.getInventory().get(counter).getName(), row, col);
					counter++;
				}

			}
		}
	}
	
	public void setupInventoryList(List<Unit> units) {		
		//first clear the list to make sure we don't duplicate Units
		playerUnitsModel.clear();
		//now add all the memories we want
		for(Unit i : units){
			String listItem= i.getName();
			//now add the element
			playerUnitsModel.addElement(listItem);
		}
	}
	
	private class MouseAdapter implements MouseListener {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if ((table.getValueAt(table.getSelectedRow(),
					table.getSelectedColumn()) != null)) {
				weapon = null;
				String weaponName = (String) table.getValueAt(
						table.getSelectedRow(), table.getSelectedColumn());

				List<Item> inventoryList = theInventory.getInventory();
				for (Item i : inventoryList) {
					if (i.getName() == weaponName) {
						weapon = (Weapon) i;
					}
				}
				stats.setValueAt(weapon.getName(), 0, 0);
				stats.setValueAt(weapon.getRange() + "", 0, 1);
				stats.setValueAt(weapon.getMight() + "", 0, 2);
				stats.setValueAt(weapon.getAccuracy() + "", 0, 3);
				stats.setValueAt(weapon.getCritical() + "", 0, 4);
				stats.setValueAt(weapon.getMagic() + "", 0, 5);
			}
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectionModel() != null){
				if(!playerUnits.isSelectionEmpty()){
					Unit theUnit = null;
					String unitName = playerUnits.getSelectedValue();
					List<Unit> units = theGame.getPlayerTeam();
					for(Unit i : units){
						if (i.getName() == unitName) {
							theUnit = i;
						}
					}
					// The weapon increases the unit's stats
					//theUnit.setWeapon(true);
					//theUnit.setStrength(theUnit.getStrength() + weapon.getMight());
					//theUnit.setLuck(theUnit.getLuck() + weapon.getCritical());
					//theUnit.setSkill(theUnit.getSkill() + weapon.getAccuracy());
					//TODO the range of the weapon currently does not affect unit
				}
			}
		}
		
	}

}