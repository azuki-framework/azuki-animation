package jp.afw.azuki.animation.entity;

import java.awt.Graphics;

import jp.afw.azuki.animation.object.AnimationObject;

/**
 * このクラスは、チャネルゲイン情報を保持するクラスです。
 * 
 * @author kawakicchi
 * 
 */
public class ChannelGain extends AnimationObject {

	private double dispValue;

	private double realValue;

	private double moveValue;

	public ChannelGain() {
	}

	public void setValue(final double aValue) {
		dispValue = aValue;
		realValue = aValue;
		moveValue = 0;
	}

	/**
	 * 今の値から指定時間(ms)かけて指定の値に移動する。
	 * 
	 * @param aValue
	 * @param aTime
	 */
	public void moveValue(final double aValue, final double aTime) {
		realValue = aValue;
		moveValue = (dispValue > realValue) ? (dispValue - realValue) / aTime * 1000 : (realValue - dispValue) / aTime * 1000;
	}

	public double getDispValue() {
		return dispValue;
	}

	@Override
	protected void doUpdate(double fps) {
		if (0 == moveValue)
			return;

		double addValue = moveValue / fps;
		if (dispValue > realValue) {
			dispValue -= addValue;
			if (dispValue <= realValue) {
				moveValue = 0;
				dispValue = realValue;
			}
		} else {
			dispValue += addValue;
			if (dispValue >= realValue) {
				moveValue = 0;
				dispValue = realValue;
			}
		}
	}

	@Override
	protected void doRender(final Graphics g) {
	}
}
