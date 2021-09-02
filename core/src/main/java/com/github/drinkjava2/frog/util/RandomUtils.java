/* Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.github.drinkjava2.frog.util;

import java.util.Random;

import com.github.drinkjava2.frog.brain.Zone;

/**
 * Random Utilities used in this project
 * 
 * @author Yong Zhu
 * @since 1.0
 */
public class RandomUtils {
	private static final Random rand = new Random();

	public static int nextInt(int i) {
		return rand.nextInt(i);
	}

	public static float nextFloat() {
		return rand.nextFloat();
	}

	public static Zone randomPosInZone(Zone z) {
		return new Zone(z.x - z.r + z.r * 2 * rand.nextFloat(), z.y - z.r + z.r * 2 * rand.nextFloat(),
				z.r * rand.nextFloat() * .02f);
	}

	public static boolean percent(int percent) {
		return rand.nextInt(100) < percent;
	}

	public static float vary(float f) { // 大部分时候不变，有极小机会变异,有极极小机会大变异，有极极极小机会大大大变异
		int i = rand.nextInt(100);
		if (i < 95)
			return f;
		float rate = .05f;
		if (i > 97)
			rate = .1f;
		return (float) (f * ((1 - rate) + rand.nextFloat() * rate * 2));
	}

}
