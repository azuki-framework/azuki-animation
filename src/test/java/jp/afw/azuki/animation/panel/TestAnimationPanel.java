package jp.afw.azuki.animation.panel;

import java.awt.Color;
import java.awt.Graphics;
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

	public TestAnimationPanel() {
		as = new ArrayList<MoveAnimationObject>();

		Random rnd = new Random((new Date()).getTime());
		for (int i = 0; i < 1000; i++) {
			MoveAnimationObject a = new MoveAnimationObject();
			a.setPosition(rnd.nextInt(800 - 20), rnd.nextInt(600 - 20));
			as.add(a);
		}
	}

	@Override
	protected void doUpdate(final double fps) {
		Random rnd = new Random((new Date()).getTime());
		for (MoveAnimationObject a : as) {
			a.update(fps);

			if (!a.isMove()) {
				a.move(rnd.nextInt(800 - 20), rnd.nextInt(600 - 20), rnd.nextInt(100) + 50, rnd.nextInt(100) + 50);
			}
		}
	}

	@Override
	protected void doRender(final Graphics g) {
		// System.out.println(getWidth());
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (MoveAnimationObject a : as) {
			a.render(g);
		}
	}

}
