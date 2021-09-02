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
package com.github.drinkjava2.frog.brain.group;

import java.awt.Color;
import java.awt.Graphics;

import com.github.drinkjava2.frog.Frog;
import com.github.drinkjava2.frog.brain.BrainPicture;
import com.github.drinkjava2.frog.brain.Cell;
import com.github.drinkjava2.frog.brain.Input;
import com.github.drinkjava2.frog.brain.Organ;
import com.github.drinkjava2.frog.brain.Output;
import com.github.drinkjava2.frog.brain.Zone;
import com.github.drinkjava2.frog.util.RandomUtils;

/**
 * RandomConnectGroup
 * 
 * 这是一个随机方式连接两端的Group，它是从旧版的CellGroup改造过来，这是一种最简单的神经元排列方式，只有一组细胞，触突输入区和输出区分别位于Zone内的任意随机两点。
 * 至于是否合理则由frog的遗传进化来决定，不合理的RandomConnectGroup会被淘汰掉。
 * 
 * (还没改造完成，在不破坏原有外在表现的基础上，要平滑将它改造成一个标准Group的子类，也是第一个子类 )
 * 
 * @author Yong Zhu
 * @since 1.0
 */
public class RandomConnectGroup extends Group {
	private static final long serialVersionUID = 1L;

	public Zone inputZone; // 输入触突区
	public Zone outputZone; // 输出触突区

	@Override
	public void init(Frog f) {
		if (inputZone == null)
			inputZone = RandomUtils.randomPosInZone(this);
		if (outputZone == null)
			outputZone = RandomUtils.randomPosInZone(this);

		Cell c = new Cell();
		Input in = new Input(inputZone);
		in.cell = c;
		c.inputs = new Input[] { in };

		Output out = new Output(outputZone);
		out.cell = c;
		c.outputs = new Output[] { out };

		c.group = this;
		f.cells.add(c);
	}

	@Override
	public Organ[] vary() {
		if (fat <= 0)
			if (RandomUtils.percent(30))
				return new Organ[] {};
		if (RandomUtils.percent(80))
			return new Organ[] { this };
		return new Organ[] { this, newRandomConnGroup(this) };
	}

	public static RandomConnectGroup newRandomConnGroup(Zone z) {
		RandomConnectGroup newOne = new RandomConnectGroup();
		newOne.inputZone = RandomUtils.randomPosInZone(z);
		newOne.outputZone = RandomUtils.randomPosInZone(z);
		return newOne;
	}

	/** Child class can override this method to drawing picture */
	public void drawOnBrainPicture(BrainPicture pic) {// 把自已这个器官在脑图上显示出来，子类可以重写这个方法
		Graphics g = pic.getGraphics();// border
		g.setColor(Color.gray); // 缺省是灰色
		pic.drawZone(g, this);
		pic.drawLine(g, inputZone, outputZone);
		pic.drawZone(g, inputZone);
		pic.fillZone(g, outputZone);
		if (fat > 0) {
			g.setColor(Color.red);
			pic.drawCircle(g, outputZone); // 如果胖了，表示激活过了，下次下蛋少不了这一组
		}
	}

}
