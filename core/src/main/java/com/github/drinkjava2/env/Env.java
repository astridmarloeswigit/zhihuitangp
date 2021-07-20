package com.github.drinkjava2.env;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.github.drinkjava2.frog.Frog;

/**
 * Env is the living space of frog. Use Java Swing to drawing the environment.
 */
@SuppressWarnings("serial")
public class Env extends JPanel {
	/** Metric width is 500 pixels */
	public static final int XSIZE = 200;

	/** Metric height is 500 pixels */
	public static final int YSIZE = 200;

	public Env() {
		super();
		this.setLayout(null);// 空布局
		Random rand = new Random();
		for (int i = 0; i < 100; i++) { 
			Frog frog = new Frog(rand.nextInt(Env.XSIZE-3), rand.nextInt(Env.YSIZE-3));
			add(new JButton());
			add(frog);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.red);
		g.drawRect(0, 0, XSIZE, YSIZE);
	}

}
