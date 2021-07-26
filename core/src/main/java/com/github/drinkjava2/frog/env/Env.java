package com.github.drinkjava2.frog.env;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.github.drinkjava2.frog.Frog;
import com.github.drinkjava2.frog.egg.Egg;
import com.github.drinkjava2.frog.util.EggTool;

/**
 * Env is the living space of frog. draw it on JPanel
 */
@SuppressWarnings("serial")
public class Env extends JPanel {
	/** Speed of test */
	public static int SHOW_SPEED = 10;

	/** Steps of one test round */
	public static int STEPS_PER_ROUND = 1000;

	/** Virtual environment x size is 500 pixels */
	public int ENV_XSIZE = 300;

	/** Virtual environment y size is 500 pixels */
	public int ENV_YSIZE = 300;

	public int FOOD_QTY = 2500; // as name

	public int EGG_QTY = 10; // as name

	public List<Frog> frogs = new ArrayList<Frog>();
	public List<Food> foods = new ArrayList<Food>();
	public List<Egg> eggs;

	public Env() {
		super();
		this.setLayout(null);// 空布局
		this.setBounds(100, 100, ENV_XSIZE, ENV_YSIZE);
	}

	private void rebuildFrogAndFood() {
		frogs.clear();
		foods.clear();
		Random rand = new Random();
		for (int i = 0; i < eggs.size(); i++) { // 1个Egg生出4个Frog
			frogs.add(new Frog(rand.nextInt(ENV_XSIZE - 3), rand.nextInt(ENV_YSIZE - 3), eggs.get(i)));
			frogs.add(new Frog(rand.nextInt(ENV_XSIZE - 3), rand.nextInt(ENV_YSIZE - 3), eggs.get(i)));
			frogs.add(new Frog(rand.nextInt(ENV_XSIZE - 3), rand.nextInt(ENV_YSIZE - 3), eggs.get(i)));
			frogs.add(new Frog(rand.nextInt(ENV_XSIZE - 3), rand.nextInt(ENV_YSIZE - 3), eggs.get(i)));
		}
		for (int i = 0; i < FOOD_QTY; i++)
			foods.add(new Food(rand.nextInt(ENV_XSIZE - 3), rand.nextInt(ENV_YSIZE - 3)));
	}

	public void run() throws InterruptedException {
		EggTool.loadEggs(this); // 从磁盘加载egg，或新建一批egg
		int round = 1;
		do {
			Application.mainFrame.setTitle("Frog test round: " + round++);
			rebuildFrogAndFood();
			for (int i = 0; i < STEPS_PER_ROUND; i++) {
				for (Frog frog : frogs)
					frog.active(this);
				if (i % SHOW_SPEED != 0) // 显示会拖慢速度
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
			}
			EggTool.layEggs(this);
		} while (true);
	}
}
