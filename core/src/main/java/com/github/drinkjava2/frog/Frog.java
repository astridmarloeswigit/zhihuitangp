/*
 * Copyright 2018 the original author or authors. 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.github.drinkjava2.frog;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import com.github.drinkjava2.frog.brain.Brain;
import com.github.drinkjava2.frog.brain.Eye;

/**
 * Frog = brain + body(mouth, eye, leg)
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class Frog {
	public int x;
	public int y;
	public int face = 1 + new Random().nextInt(4); // 0:sleep 1:up 2:right 3:down 4:left 5:right
	public int move = 0; // 0: stop 1:up 2:right 3:down 4:left 5:right turn 6 left turn
	public long energy = 10000;

	static Image frog_up;
	static {
		try {
			frog_up = ImageIO.read(new FileInputStream(new File("").getAbsolutePath() + "/frog_up.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Brain brain;
	Eye eye;

	public Frog(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void show(Graphics g) {
		g.drawImage(frog_up, x - 16, y - 16, 32, 32, null);
	}

}
