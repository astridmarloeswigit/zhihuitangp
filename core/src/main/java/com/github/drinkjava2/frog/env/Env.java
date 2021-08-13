package com.github.drinkjava2.frog.env;

import java.awt.Color;
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
	public static int SHOW_SPEED = 50; // 测试速度，1~1000, 数值越小，速度越慢

	public static int ENV_WIDTH = 400; // 虚拟环境宽度, 可调

	/** Virtual environment y size pixels */
	public static int ENV_HEIGHT = ENV_WIDTH; // 虚拟环境高度, 可调，通常取正方形

	/** Frog's brain display width on screen, not important */
	public static final int FROG_BRAIN_DISP_WIDTH = 400; // Frog的脑图在屏幕上的显示大小,可调

	/** Frog's brain width */
	public static final float FROG_BRAIN_WIDTH = 1000; // frog的脑宽度固定为1000,不要调整它,因为器官的大小是假定脑宽为1000的

	/** Steps of one test round */
	public static int STEPS_PER_ROUND = 3000;// 每轮测试步数,可以调整

	/** Delete eggs at beginning of each run */
	public static boolean DELETE_EGGS = false;// 每次运行是否先删除保存的蛋

	static {
		if (DELETE_EGGS)
			EggTool.deleteEggs();
	}

	public int FOOD_QTY = 4000; // 食物数量

	public int EGG_QTY = 50; // 每轮下n个蛋，只有最优秀的前n个青蛙们才允许下蛋

	public static boolean pause = false; // 暂停测试

	private static final Random r = new Random(); // 随机数发生器

	public boolean[][] foods = new boolean[ENV_WIDTH][ENV_HEIGHT];// 食物数组定义

	public List<Frog> frogs = new ArrayList<Frog>();
	public List<Egg> eggs;

	public Env() {
		super();
		this.setLayout(null);// 空布局
		this.setBounds(1, 1, ENV_WIDTH, ENV_HEIGHT);
	}

	private void rebuildFrogAndFood() {
		frogs.clear();
		for (int i = 0; i < ENV_WIDTH; i++) {// clear foods
			for (int j = 0; j < ENV_HEIGHT; j++) {
				foods[i][j] = false;
			}
		}
		Random rand = new Random();
		for (int j = 0; j < 12; j++) {// 第一名多生出12个蛋
			Egg zygote = new Egg(eggs.get(0), eggs.get(r.nextInt(eggs.size())));
			frogs.add(new Frog(ENV_WIDTH / 2 + rand.nextInt(90), ENV_HEIGHT / 2 + rand.nextInt(90), zygote));
		}
		for (int i = 0; i < eggs.size() - 3; i++) { // 1个Egg生出4个Frog，但是最后3名不生蛋(名额让给了第一名)
			for (int j = 0; j < 4; j++) {
				Egg zygote = new Egg(eggs.get(i), eggs.get(r.nextInt(eggs.size())));
				frogs.add(new Frog(ENV_WIDTH / 2 + rand.nextInt(90), ENV_HEIGHT / 2 + rand.nextInt(90), zygote));
			}
		}

		System.out.println("Created " + 4 * eggs.size() + " frogs");
		for (int i = 0; i < FOOD_QTY; i++)
			foods[rand.nextInt(ENV_WIDTH - 3)][rand.nextInt(ENV_HEIGHT - 3)] = true;
	}

	private void drawFood(Graphics g) {
		for (int x = 0; x < ENV_WIDTH; x++)
			for (int y = 0; y < ENV_HEIGHT; y++)
				if (foods[x][y]) {
					g.fillOval(x, y, 4, 4);
				}
	}

	private static void sleep() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() throws InterruptedException {
		EggTool.loadEggs(this); // 从磁盘加载egg，或新建一批egg
		int round = 1;
		Image buffImg = createImage(this.getWidth(), this.getHeight());
		Graphics g = buffImg.getGraphics();
		long t1, t2;
		do {
			if (pause) {
				sleep();
				continue;
			}
			t1 = System.currentTimeMillis();
			rebuildFrogAndFood();
			boolean allDead = false;
			for (int i = 0; i < STEPS_PER_ROUND; i++) {
				if (allDead) {
					System.out.println("All dead at round:" + i);
					break; // 全死光了就直接跳到下一轮,以节省时间
				}
				allDead = true;
				for (Frog frog : frogs)
					if (frog.active(this))
						allDead = false;
				if (i % SHOW_SPEED != 0) // 画青蛙会拖慢速度
					continue;
				// 开始画青蛙
				g.setColor(Color.white);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setColor(Color.BLACK);
				for (Frog frog : frogs)
					frog.show(g);

				drawFood(g);
				Graphics g2 = this.getGraphics();
				g2.drawImage(buffImg, 0, 0, this);
			}

			EggTool.layEggs(this);
			Application.brainStructure.drawBrain(frogs.get(0));
			t2 = System.currentTimeMillis();
			Application.mainFrame.setTitle("Frog test round: " + round++ + ", time used: " + (t2 - t1) + " ms, x="
					+ frogs.get(0).x + ", y=" + frogs.get(0).y);
		} while (true);
	}
}
