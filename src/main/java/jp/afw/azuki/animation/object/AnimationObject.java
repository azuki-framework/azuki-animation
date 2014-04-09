package jp.afw.azuki.animation.object;

import java.awt.Graphics;

public abstract class AnimationObject {

	private double x;
	private double y;

	public final void setPosition(final double aX, final double aY) {
		x = aX;
		y = aY;
	}

	public final double getX() {
		return x;
	}
	
	public final double getY() {
		return y;
	}

	public void update(final double fps) {
		doUpdate(fps);
	}

	public void render(final Graphics g) {
		doRender(g);
	}

	protected abstract void doUpdate(final double fps);

	protected abstract void doRender(final Graphics g);

}
