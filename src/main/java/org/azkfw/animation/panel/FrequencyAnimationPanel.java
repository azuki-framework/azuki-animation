package org.azkfw.animation.panel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.azkfw.animation.entity.ChannelGain;
import org.azkfw.animation.entity.SubCarrier;

public class FrequencyAnimationPanel extends AnimationPanel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1843251283537233946L;

	private List<ChannelGain> channelGains;
	private List<SubCarrier> subCarriers;

	private double cntTime;

	public FrequencyAnimationPanel() {
		cntTime = 2.f;

		channelGains = new ArrayList<ChannelGain>();
		for (int i = 0; i < 100; i++) {
			ChannelGain channelGain = new ChannelGain();
			channelGain.setValue(0.f);
			channelGains.add(channelGain);
		}

		subCarriers = new ArrayList<SubCarrier>();
		for (int i = 0; i < 32; i++) {
			SubCarrier subCarrier = new SubCarrier();
			subCarrier.setValue(0.f);
			subCarriers.add(subCarrier);
		}
	}

	@Override
	protected void doUpdate(final double fps) {
		Random rnd = new Random((new Date()).getTime());

		cntTime += 1.f / fps;

		for (ChannelGain channelGain : channelGains) {
			channelGain.update(fps);
		}
		for (SubCarrier subCarrier : subCarriers) {
			subCarrier.update(fps);
		}

		if (2.f < cntTime) {
			cntTime = cntTime - 2.f;
			for (ChannelGain channelGain : channelGains) {
				channelGain.moveValue(rnd.nextDouble(), 2000); // 0.0 ~ 1.0未満
			}
			for (SubCarrier subCarrier : subCarriers) {
				subCarrier.moveValue(rnd.nextInt(10), 2000); // 0 ~ 9
			}
		}
	}

	@Override
	protected void doRender(final Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.white);
		g2.fillRect(0, 0, getWidth(), getHeight());

		// ChannelGain
		int channelGainSize = channelGains.size();
		double channelGainHeight = (double) getHeight() / 2.f;
		double channelGainInterval = (double) getWidth() / (double) (channelGainSize - 1);

		// Fill channelGain back
		int backXs[] = new int[channelGainSize + 2];
		int backYs[] = new int[channelGainSize + 2];
		backXs[0] = 0;
		backYs[0] = (int) channelGainHeight;
		for (int i = 0; i < channelGainSize; i++) {
			ChannelGain channelGain = channelGains.get(i);
			backXs[i + 1] = (int) (i * channelGainInterval);
			backYs[i + 1] = (int) (channelGainHeight * (1.f - channelGain.getDispValue()));
		}
		backXs[channelGainSize + 1] = (int) ((channelGainSize - 1) * channelGainInterval);
		backYs[channelGainSize + 1] = (int) channelGainHeight;
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, (int) channelGainHeight, Color.white);
		g2.setPaint(gp);
		g2.fillPolygon(backXs, backYs, channelGainSize + 2);

		// Draw channelGain line
		int lineXs[] = new int[channelGainSize];
		int lineYs[] = new int[channelGainSize];
		for (int i = 0; i < channelGainSize; i++) {
			ChannelGain channelGain = channelGains.get(i);
			lineXs[i] = (int) (i * channelGainInterval);
			lineYs[i] = (int) (channelGainHeight * (1.f - channelGain.getDispValue()));
		}
		g2.setColor(new Color(0, 0, 255, 255));
		g2.drawPolyline(lineXs, lineYs, channelGainSize);

		// Fill subCarrier back
		int subCarrierSize = subCarriers.size();
		double subCarrierWidth = (double) getWidth() / (double) subCarriers.size();
		double subCarrierHeight = (double) getHeight() * 0.6;
		double subCarrierOffsetY = getHeight() - subCarrierHeight;

		for (int i = 0; i < subCarrierSize; i++) {
			SubCarrier subCarrier = subCarriers.get(i);
			double value = subCarrier.getDispValue();
			double x = i * subCarrierWidth;
			double y = getHeight();
			double height = subCarrierHeight * (10.f - value) / 10.f;

			GeneralPath p = new GeneralPath();
			p.moveTo(x, y);
			p.lineTo(x, y - height);
			p.curveTo(x, y - height, x, y - height - 20.f, x + subCarrierWidth / 2.f, y - height - 20.f);
			p.curveTo(x + subCarrierWidth / 2.f, y - height - 20.f, x + subCarrierWidth, y - height - 20.f, x + subCarrierWidth, y - height);
			p.lineTo(x + subCarrierWidth, y);

			GradientPaint gp2 = new GradientPaint(0, (int) subCarrierOffsetY, new Color(255, 0, 0, 100), 0, getHeight(), new Color(255,0,0,0));
			g2.setPaint(gp2);
			g2.fill(p);

			g2.setColor(Color.red);
			g2.draw(p);
		}
	}

}
