package model;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.GamePlay;

public class Model extends Observable implements Serializable, Observer {
	private JList<String> saved;
	private JOptionPane savePanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4356959910080573208L;

//	private GamePlay map;
	private GameMap map;

	public Model(GameMap map) {
		this.map = map;
		JScrollPane saveScroll = new JScrollPane(saved);
		savePanel = new JOptionPane();
		savePanel.add(saveScroll);
		savePanel.setName("Save States");
		map.addObserver(this);
	}

	public GameMap getMap() {
		return map;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		map = (GameMap) o;	
		setChanged();
		notifyObservers();
	}
}
