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
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.github.drinkjava2.frog.brain.Cell;
import com.github.drinkjava2.frog.brain.Organ;
import com.github.drinkjava2.frog.brain.organ.Line;
import com.github.drinkjava2.frog.egg.Egg;
import com.github.drinkjava2.frog.objects.Material;
import com.github.drinkjava2.frog.util.RandomUtils;

/**
 * Frog = cells <br/>
 * cells = actions + photons <br/>
 * 
 * Frog's name is Sam.
 * 
 * 青蛙脑由一个cells三维数组组成，每个cell里可以存在多个行为，行为是由器官决定，同一个细胞可以存在多种行为。光子是信息的载体，永远不停留。
 * 
 * @author Yong Zhu
 * 
 * @since 1.0
 */
public class Frog {// 这个程序大量用到public变量而不是getter/setter，主要是为了编程方便和简洁，但缺点是编程者需要小心维护各个变量
	/** brain cells */
	public List<Cell> cells = new ArrayList<>();

	/** organs */
	public List<Organ> organs = new ArrayList<>();

	public int x; // frog在Env中的x坐标
	public int y; // frog在Env中的y坐标
	public long energy = 100000; // 青蛙的能量为0则死掉
	public boolean alive = true; // 设为false表示青蛙死掉了，将不参与计算和显示，以节省时间
	public int ateFood = 0; // 青蛙曾吃过的食物总数，下蛋时如果两个青蛙能量相等，可以比数量

	static Image frogImg;
	static {
		try {
			frogImg = ImageIO.read(new FileInputStream(Application.CLASSPATH + "frog.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Frog(int x, int y, Egg egg) {// x, y 是虑拟环境的坐标
		this.x = x;
		this.y = y;
		for (Organ org : egg.organs)
			organs.add(org);
	}

	public void initFrog() { // 初始化frog,通常只是调用每个organ的init方法
		for (Organ org : organs)
			org.initFrog(this);// 每个新器官初始化，如果是Group类，它们会生成许多脑细胞
	}

	public void addRandomLines() {// 有一定机率在器官间生成随机的神经连线
		if (alive && RandomUtils.percent(0.2f)) {// 有很小的机率在青蛙活着时就创建新的器官
			Line line = new Line();
			organs.add(line);
			line.initFrog(this);
		}
	}

	public boolean active(Env v) {// 这个active方法在每一步循环都会被调用，是脑思考的最小帧
		// 如果能量小于0、出界、与非食物的点重合则判死
		if (!alive || energy < 0 || Env.outsideEnv(x, y) || Env.bricks[x][y] >= Material.KILLFROG) {
			energy -= 1000; // 死掉的青蛙也要消耗能量，确保淘汰出局
			alive = false;
			return false;
		}
		energy -= 20;
		// 依次调用每个organ的active方法
		for (Organ organ : organs)
			organ.active(this);
		// 依次调用每个cell的active方法，这是写在organ类里的方法，因为同一个器官的cell具有相同的行为
		for (Cell cell : cells)
			cell.organ.active(this, cell);
		addRandomLines();
		return alive;
	}

	public void show(Graphics g) {// 显示青蛙的图象
		if (!alive)
			return;
		g.drawImage(frogImg, x - 8, y - 8, 16, 16, null);
	}

	@SuppressWarnings("unchecked")
	public <T extends Organ> T findOrganByClass(Class<?> claz) {// 根据器官名寻找器官，但不是每个器官都有名字
		for (Organ o : organs)
			if (o != null && o.getClass() == claz)
				return (T) o;
		return null;
	}

	/** Check if x,y,z out of frog's brain range */
	public static boolean outBrainRange(int x, int y, int z) {// 检查指定坐标是否超出frog脑空间界限
		return x < 0 || x >= Env.FROG_BRAIN_XSIZE || y < 0 || y >= Env.FROG_BRAIN_YSIZE || z < 0
				|| z >= Env.FROG_BRAIN_ZSIZE;
	}

}
