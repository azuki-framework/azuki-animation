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
package org.azkfw.animation.object;

import java.awt.Graphics;

/**
 * このクラスは、アニメーション用のオブジェクトクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/06/04
 * @author Kawakicchi
 */
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
