package jp.afw.azuki.animation.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.afw.azuki.animation.object.AnimationObject;
import jp.afw.azuki.animation.object.MoveAnimationObject;
import jp.afw.core.io.CsvBufferedReader;

public class FrequencyAnimationPanel extends AnimationPanel {

	private List<Double> headers;
	private List<List<Double>> datas;
	private int index;

	private int status = 0;

	private List<MoveAnimationObject> maos;

	public FrequencyAnimationPanel() {
		headers = null;
		datas = null;
		status = 0;
		index = 0;
		maos = null;
	}

	public void setFile(final File aFile) {
		status = 1;
		headers = null;
		datas = new ArrayList<List<Double>>();
		index = 0;

		CsvBufferedReader reader = null;
		try {
			reader = new CsvBufferedReader(aFile.getAbsolutePath(), "SJIS");

			List<String> buf = reader.readCsvLine();
			List<Double> h = new ArrayList<Double>();			
			for (int i = 1; i < buf.size(); i++) {
				h.add(Double.parseDouble(buf.get(i)));
			}
			headers = h;

			while (null != (buf = reader.readCsvLine())) {
				List<Double> d = new ArrayList<Double>();
				for (int i = 1; i < buf.size(); i++) {
					d.add(Double.parseDouble(buf.get(i)));
				}
				datas.add(d);
			}
			
			double interval = 800.f / headers.size();
			maos = new ArrayList<MoveAnimationObject>();
			for (int i = 0 ; i < headers.size() ; i++) {
				MoveAnimationObject mao = new MoveAnimationObject();
				mao.setPosition( interval * i, 600);
				maos.add(mao);
			}

			status = 2;
		} catch (IOException ex) {
			status = -1;
			ex.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	@Override
	protected void doUpdate(final double fps) {
		switch (status) {
		case 2: {

			boolean moveFlag = false;
			for (MoveAnimationObject mao : maos) {
				if (mao.isMove()) {
					moveFlag = true;
					break;
				}
			}

			if (moveFlag) {
				for (MoveAnimationObject mao : maos) {
					mao.update(fps);
				}
			} else {
				if (index >= datas.size()) {
					index = 0;
				}
				
				List<Double> ds = datas.get(index);
				
				double max = -30.f;
				double min = -110.f;
				double interval = 600.f / (max - min);
				
				for (int i = 0 ; i < headers.size() ; i++ ) {
					double value = ds.get(i);
					MoveAnimationObject mao = maos.get(i);

					double x = mao.getX();
					double y = 600 - ((value - min) * interval);
					
					mao.moveWithTime(x, y, 0.1);
				}
				index++;
			}

			break;
		}
		default:
			break;
		}

	}

	@Override
	protected void doRender(final Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		switch (status) {
		case 0: {
			String message = "データを選択してください";

			g.setColor(Color.red);
			g.setFont(new Font("Dialog", Font.PLAIN, 16));

			FontMetrics fm = g.getFontMetrics();
			int width = fm.stringWidth(message);

			g.drawString(message, (int) ((double) (800 - width) / 2.f), (int) ((double) (600 - 16) / 2.f));

			break;
		}
		case 1: {
			String message = "データを読込中・・・";

			g.setColor(Color.red);
			g.setFont(new Font("Dialog", Font.PLAIN, 16));

			FontMetrics fm = g.getFontMetrics();
			int width = fm.stringWidth(message);

			g.drawString(message, (int) ((double) (800 - width) / 2.f), (int) ((double) (600 - 16) / 2.f));

			break;
		}
		case 2: {


			int xPoints[] = new int[maos.size()];
			int yPoints[] = new int[maos.size()];
			
			for (int i = 0 ; i < maos.size() ; i++ ) {
				xPoints[i] = (int)(maos.get(i).getX());
				yPoints[i] = (int)(maos.get(i).getY());
			}
			g.setColor(Color.black);
			g.drawPolyline(xPoints, yPoints, maos.size());
			
			g.setColor(Color.red);
			g.setFont(new Font("Dialog", Font.PLAIN, 16));
			g.drawString((index+1) + "/" + datas.size() , 5, 20);
			break;
		}
		case -1: {
			String message = "読込に失敗しました。";

			g.setColor(Color.red);
			g.setFont(new Font("Dialog", Font.PLAIN, 16));

			FontMetrics fm = g.getFontMetrics();
			int width = fm.stringWidth(message);

			g.drawString(message, (int) ((double) (800 - width) / 2.f), (int) ((double) (600 - 16) / 2.f));

			break;
		}
		default:
			break;
		}
	}

}
