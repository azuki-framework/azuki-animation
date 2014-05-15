package jp.afw.azuki.animation.object;

import java.awt.Graphics;

public abstract class AnimationObject {

	public void update(final double fps) {
		doUpdate(fps);
	}

	public void render(final Graphics g) {
		doRender(g);
	}

	protected abstract void doUpdate(final double fps);

	protected abstract void doRender(final Graphics g);

}
