package com.github.drinkjava2.env;

import java.awt.Graphics;

/**
 * Food is food for frogs, one food = 100 unit energy
 */
public class Food {
	int x;

	int y;

	public Food(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void show(Graphics g) {
		g.fillOval(x, y, 4, 4);
	}
}
