package jp.afw.azuki.animation.object;

import java.awt.Color;
import java.awt.Graphics;

public class MoveAnimationObject extends AnimationObject {

	private double moveX; // 最終終着点
	private double moveY; // 最終終着点
	private double intervalX;
	private double intervalY;

	/**
	 * オブジェクトを移動する。
	 * 
	 * @param aX 移動先X座標
	 * @param aY 移動先Y座標
	 * @param aIntervalX 移動量(pixel/sec)
	 * @param aIntervalY 移動量(pixel/sec)
	 */
	public void move(final double aX, final double aY, final double aIntervalX, final double aIntervalY) {
		moveX = aX;
		moveY = aY;
		intervalX = aIntervalX;
		intervalY = aIntervalY;
	}

	public double getMoveX() {
		return moveX;
	}

	public double getMoveY() {
		return moveY;
	}

	@Override
	protected void doUpdate(final double fps) {
		double x = getX();
		double y = getY();

		if (0 != intervalX) {
			double ix = intervalX / fps;
			if (moveX > x) {
				double dif = moveX - x;
				if (dif < ix) {
					x = moveX;
					intervalX = 0;
				} else {
					x += ix;
				}
			} else {
				double dif = x - moveX;
				if (dif < ix) {
					x = moveX;
					intervalX = 0;
				} else {
					x -= ix;
				}
			}
		}
		if (0 != intervalY) {
			double iy = intervalY / fps;
			if (moveY > y) {
				double dif = moveY - y;
				if (dif < iy) {
					y = moveY;
					intervalY = 0;
				} else {
					y += iy;
				}
			} else {
				double dif = y - moveY;
				if (dif < iy) {
					y = moveY;
					intervalY = 0;
				} else {
					y -= iy;
				}
			}
		}

		setPosition(x, y);
	}

	@Override
	protected void doRender(final Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) getX(), (int) getY(), 20, 20);
	}
	
	public boolean isMove() {
		return !(0 == intervalX && 0 == intervalY);
	}

}
