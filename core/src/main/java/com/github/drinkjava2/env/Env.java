package com.github.drinkjava2.env;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.github.drinkjava2.frog.Frog;

/**
 * Env is the living space of frog. Use Java Swing to drawing the environment.
 */
@SuppressWarnings("serial")
public class Env extends JPanel {
	/** Metric width is 500 pixels */
	public static final int XSIZE = 500;

	/** Metric height is 500 pixels */
	public static final int YSIZE = 500;

	public int frogAmount = 10;
	public int foodAmount = 10;

	public List<Frog> frogs = new ArrayList<Frog>();
	public List<Food> foods = new ArrayList<Food>();
	 

	public Env() {
		super();
		this.setLayout(null);// 空布局
		Random rand = new Random();
		for (int i = 0; i < frogAmount; i++)
			frogs.add(new Frog(rand.nextInt(Env.XSIZE - 3), rand.nextInt(Env.YSIZE - 3)));
		for (int i = 0; i < foodAmount; i++)
			foods.add(new Food(rand.nextInt(Env.XSIZE - 3), rand.nextInt(Env.YSIZE - 3)));
	}

	@Override
	public void paint(Graphics g) { 
		super.paint(g);
		for (Frog frog : frogs)
			frog.show(g);
		for (Food food : foods)
			food.show(g);
	}

}
