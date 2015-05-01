package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Inventory;
import controller.GamePlay;

public class InventoryView extends JPanel {
	private GamePlay theGame;
	private Inventory theInventory;
	private DefaultTableModel data;

	public InventoryView(Inventory theInventory, GamePlay theGame) {
		this.theGame = theGame;
		this.theInventory = theInventory;
		this.setLayout(new GridLayout(2,1));
		data = new DefaultTableModel();
		JTable table = new JTable(data) {
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
		table.getSelectionModel().addListSelectionListener(new cellListener());
		setUpTable(theInventory);
		
		JTable table2 = new JTable(data);
		table2.setRowHeight(55);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table2);
		add(scroll);

	}

	private void setUpTable(Inventory theInventory) {
		// Item[][] data = new Item[5][5];
		// data.clear();
		// String[][] data = new String[5][5];
		data.setRowCount(5);
		data.setColumnCount(5);
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				//data.setValueAt(theInventory.getItemInInventory(), row, col);
				data.setValueAt("test",row,col);
			}
		}
		// return data;
	}

	private class cellListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			//stats.setText("cake");
		}
	}

}