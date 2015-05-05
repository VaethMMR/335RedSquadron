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

public class Model implements Serializable, Observer {
	private JList<String> saved;
	private JPanel loadGame;
	private DefaultListModel saveModel;
	private JOptionPane savePanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4356959910080573208L;

	private GamePlay game;

	public Model(GamePlay game) {
		this.game = game;
		saveModel = new DefaultListModel();
		saved = new JList(saveModel);
		JScrollPane saveScroll = new JScrollPane(saved);
		savePanel = new JOptionPane();
		savePanel.add(saveScroll);
		savePanel.setName("Save States");
	}

	public GamePlay getGame() {
		return game;
	}
	
	public void save(Model m) {

		try {
			// Write to out with FileOutputStream
			FileOutputStream stream = new FileOutputStream("BattleSave.ser");

			// Write object with ObjectOutputStream
			ObjectOutputStream out = new ObjectOutputStream(stream);

			// Write it
			out.writeObject(m);

			// Close stream
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean load(Model m) {
		// We are in progress of a game
		if (m.game.getMap() != null) {
			if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(null,
					"Load game?\n" + "All unsaved data will be lost.", "Load",
					JOptionPane.YES_NO_CANCEL_OPTION)) {
				JOptionPane.showMessageDialog(null, "Load canceled.", "Load",
						JOptionPane.INFORMATION_MESSAGE);
				;
				return false;
			}
		}
		File filename = new File("Battlesave.ser");

		// set in stream
		FileInputStream fis;
		try {
			fis = new FileInputStream(filename);

			// read in stream
			BufferedInputStream bis = new BufferedInputStream(fis);

			// read object
			ObjectInputStream ois = new ObjectInputStream(bis);
			Object in = ois.readObject();
			ois.close();
			return true;
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found", "404",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		 
	}
}
//
//
//
// // Write object out to disk
// obj_out.writeObject ( myObject );
// }
