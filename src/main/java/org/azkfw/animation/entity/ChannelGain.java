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
package org.azkfw.animation.entity;

import java.awt.Graphics;

import org.azkfw.animation.object.AnimationObject;

/**
 * このクラスは、チャネルゲイン情報を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/06/04
 * @author kawakicchi
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
