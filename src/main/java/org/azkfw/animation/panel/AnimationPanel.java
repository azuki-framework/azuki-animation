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
package org.azkfw.animation.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * このクラスは、アニメーション用のパネルクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/06/04
 * @author Kawakicchi
 */
public abstract class AnimationPanel extends JPanel implements Runnable {

	/** serialVersionUID */
	private static final long serialVersionUID = -7022476459093552005L;

	private Thread thread;
	private boolean running;

	// ダブルバッファリング
	private Graphics graphics;
	private Image bufferImage = null;

	double fps = 60.f;
	double fpsReal = fps;

	/**
	 * コンストラクタ
	 */
	public AnimationPanel() {

	}

	public void start() {
		if (null == thread) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		running = true;
	}

	public void run() {
		while (running) {
			long startNaNoTime = System.nanoTime(); // * 1000000000

			doUpdate(fpsReal);
			doRender();
			paintScreen();

			long proNanoTime = System.nanoTime();
			long difNanoTime = proNanoTime - startNaNoTime;

			double difTime = (double) difNanoTime / 1000000000.f;
			double sleepTime = (1.f / fps) - difTime;

			if (0 < sleepTime) {
				try {
					Thread.sleep((long) (sleepTime * 1000.f)); // s->ms
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			long endNanoTime = System.nanoTime();

			fpsReal = 1.f / ((endNanoTime - startNaNoTime) / 1000000000.f);
		}
		System.exit(0);
	}

	protected abstract void doUpdate(final double fps);

	protected abstract void doRender(final Graphics g);

	/**
	 * バッファにレンダリング
	 */
	private void doRender() {
		if (null == bufferImage) {
			bufferImage = createImage(800, 600);
			if (bufferImage == null) {
				System.out.println("dbImage is null");
				return;
			} else {
				graphics = bufferImage.getGraphics();
			}
		}

		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, 800, 600);

		doRender(graphics);

		double p = fpsReal / fps;
		if (1 < p) {
			p = 1.f;
		} else if (0.5 > p) {
			p = 0.f;
		} else {
			p = (p - 0.5f) * 2;
		}

		graphics.setFont(new Font("Dialog", Font.PLAIN, 16));
		graphics.setColor(new Color((int) (255.f * (1.f - p)), (int) (255.f * p), 0));
		graphics.drawString(String.format("FPS %2.3f", fpsReal), 0, 16);
	}

	/**
	 * バッファを画面に描画
	 */
	private void paintScreen() {
		try {
			Graphics g = getGraphics();
			if ((g != null) && (bufferImage != null)) {
				// XXX: メニューが消される				
				//g.drawImage(bufferImage, 0, 0, null);
				repaint();
			}
			Toolkit.getDefaultToolkit().sync();
			if (g != null) {
				g.dispose();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		if ((g != null) && (bufferImage != null)) {
			g.drawImage(bufferImage, 0, 0, null);
		}
	}
}
