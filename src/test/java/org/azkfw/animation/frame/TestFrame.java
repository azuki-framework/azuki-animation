package org.azkfw.animation.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.azkfw.animation.panel.FrequencyAnimationPanel;

public class TestFrame extends JFrame implements WindowListener, ActionListener {

	private FrequencyAnimationPanel panel;

	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuFileOpen;
	private JMenuItem menuFileOpenData;

	public TestFrame() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuFileOpen = new JMenu("Open");
		menuFileOpenData = new JMenuItem("Data file");
		menuFileOpenData.addActionListener(this);
		menuFileOpen.add(menuFileOpenData);
		menuFile.add(menuFileOpen);
		menuBar.add(menuFile);
		setJMenuBar(menuBar);

		addWindowListener(this);
		setBounds(10, 10, 816, 692);

		panel = new FrequencyAnimationPanel();
		add(panel);
		panel.start();
	}

	private void doOpenDataFile() {
		JFileChooser filechooser = new JFileChooser();
		int selected = filechooser.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = filechooser.getSelectedFile();
		}
	}

	public void actionPerformed(final ActionEvent e) {
		if (e.getSource().equals(menuFileOpenData)) {
			doOpenDataFile();
		}
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		panel.stop();
		dispose();
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
