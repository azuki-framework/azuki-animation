package jp.afw.azuki.animation.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import jp.afw.azuki.animation.panel.TestAnimationPanel;

public class TestFrame extends JFrame implements WindowListener {

	private TestAnimationPanel panel;

	public TestFrame() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		panel = new TestAnimationPanel();

		add(panel);

		addWindowListener(this);

		setBounds(10, 10, 800, 600);

		panel.start();
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
