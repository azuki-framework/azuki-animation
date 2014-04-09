package jp.afw.azuki.animation.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import jp.afw.azuki.animation.object.MoveAnimationObject;

public class TestAnimationPanel extends AnimationPanel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5466355959065165202L;
	
	private List<MoveAnimationObject> as;
	
	private List<Double> line;

	public TestAnimationPanel() {

		Random rnd = new Random((new Date()).getTime());

		as = new ArrayList<MoveAnimationObject>();
		for (int i = 0; i < 1000; i++) {
			MoveAnimationObject a = new MoveAnimationObject();
			a.setPosition(rnd.nextInt(800 - 20), rnd.nextInt(600 - 20));
			as.add(a);
		}
		
		line = new ArrayList<Double>();
		for (int i = 0 ; i < 100 ; i++) {
			Double value = Double.valueOf(rnd.nextInt(400));
			line.add(value);
		}
	}

	@Override
	protected void doUpdate(final double fps) {
		Random rnd = new Random((new Date()).getTime());
		for (MoveAnimationObject a : as) {
			a.update(fps);

			if (!a.isMove()) {
				a.move(rnd.nextInt(800 - 20), rnd.nextInt(600 - 20), rnd.nextInt(100) + 50, rnd.nextInt(150) + 10);
			}
		}
	}

	@Override
	protected void doRender(final Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		// System.out.println(getWidth());
		g2.setColor(Color.white);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		
		int[] xs = new int[line.size()+2];		
		int[] ys = new int[line.size()+2];
		xs[0] = 0;
		ys[0] = 600;
		for (int i = 0 ; i < line.size() ; i++) {
			double v = line.get(i);
			xs[i+1] = i * 8;
			ys[i+1] = (int)(600 - v);
		}
		xs[line.size()+1] = (line.size()-1) * 8;
		ys[line.size()+1] = 600;
		

		g2.setColor(Color.black);
		g2.drawPolygon(xs, ys, line.size()+2);
		
		
		for (MoveAnimationObject a : as) {
			a.render(g);
		}
	}

}
