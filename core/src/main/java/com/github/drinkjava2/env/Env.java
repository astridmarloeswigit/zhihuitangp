package com.github.drinkjava2.env;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

import com.github.drinkjava2.frog.Frog;

/**
 * Env is the living space of frog. Use Java Swing to drawing the environment.
 */
@SuppressWarnings("serial")
public class Env extends JButton {
	/** Metric width is 500 pixels */
	public static final int XSIZE = 300;

	/** Metric height is 500 pixels */
	public static final int YSIZE = 300;

	public int frogAmount = 10;
	public int foodAmount = 500;

	public List<Frog> frogs = new ArrayList<Frog>();
	public List<Food> foods = new ArrayList<Food>();

	public Env() {
		super();
		this.setLayout(null);// 空布局
		this.setBounds(100, 100, XSIZE, YSIZE);
		Random rand = new Random();
		for (int i = 0; i < frogAmount; i++)
			frogs.add(new Frog(rand.nextInt(Env.XSIZE - 3), rand.nextInt(Env.YSIZE - 3)));
		for (int i = 0; i < foodAmount; i++)
			foods.add(new Food(rand.nextInt(Env.XSIZE - 3), rand.nextInt(Env.YSIZE - 3)));
	}

	public void eatFoods() {
		eating = true;
		for (Frog frog : frogs) {
			int j = foods.size() - 1;
			for (int i = j; i >= 0; i--) {
				Food food = foods.get(i);
				if (frog.x == food.x && frog.y == food.y)
					foods.remove(i);
			}
		}
		eating = false;
	}

	static boolean eating = true;

	public void run() throws InterruptedException {
		Random r = new Random();
		int count = 0;
		do {
			count++;
			for (Frog frog : frogs) {
				frog.x += r.nextInt(3) - 1;
				frog.y += r.nextInt(3) - 1;
			}
			eatFoods();
			if (count % 50 != 0)
				continue;
			Image buffImg = createImage(this.getWidth(), this.getHeight());
			Graphics g = buffImg.getGraphics();
			for (Frog frog : frogs)
				frog.show(g);
			for (Food food : foods)
				food.show(g);
			Graphics g2 = this.getGraphics();
			g2.drawImage(buffImg, 0, 0, this);
			Thread.sleep(10);
		} while (true);
	}

}
