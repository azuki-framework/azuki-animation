/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * このクラスは、サンプルフレームクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/06/04
 * @author Kawakicchi
 */
public class SampleFrame extends JFrame implements WindowListener, ActionListener {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8999965194391546712L;

	private FrequencyAnimationPanel panel;

	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuFileOpen;
	private JMenuItem menuFileOpenData;

	public SampleFrame() {
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
			if (file.isFile()) {

			}
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
