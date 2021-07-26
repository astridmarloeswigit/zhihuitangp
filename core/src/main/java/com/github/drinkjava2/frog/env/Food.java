package com.github.drinkjava2.frog.env;

import java.awt.Graphics;

/**
 * Food is food for frogs, one food = 500 unit energy
 */
public class Food {
	public int energy = 500;

	public int x;

	public int y;

	public Food(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void show(Graphics g) {
		g.fillOval(x, y, 4, 4);
	}
}
