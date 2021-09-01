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

import java.util.Random;

import com.github.drinkjava2.frog.brain.Zone;

/**
 * RandomConnectGroup
 * 
 * 这是一个随机方式连接两端的Group，它是从旧版的CellGroup改造过来，这是一种最简单的神经元排列方式，只有
 * 它代表一组细胞，触突输入区和输出区分别位于Zone内的任意随机两点。至于是否合理则由frog的遗传进化来决定，不合理的RandomConnectGroup会被淘汰掉。
 * 
 *  (还没改造完成，在不破坏原有外在表现的基础上，要平滑将它改造成一个标准Group的子类，也是第一个子类 )
 * 
 * @author Yong Zhu
 * @since 1.0
 */
public class RandomConnectGroup extends Group {
	private static final long serialVersionUID = 1L;

	// TODO need delete below fields, use grid replace
	public Zone groupInputZone; // input distribute zone

	public Zone groupOutputZone; // output distribute zone

	public float cellInputRadius; // input radius of each cell
	public float cellOutputRadius; // output radius of each cell

	public float inputQtyPerCell; // input qty per cell
	public float outputQtyPerCell; // output qty per cell
	// TODO need delete above fields

	public float cellQty; // how many nerve cells in this CellGroup

	private static final Random r = new Random();

	public RandomConnectGroup() {

	}

	public RandomConnectGroup(RandomConnectGroup g) {// clone old CellGroup
		groupInputZone = new Zone(g.groupInputZone);
		groupOutputZone = new Zone(g.groupOutputZone);
		cellInputRadius = g.cellInputRadius;
		cellOutputRadius = g.cellOutputRadius;
		cellQty = g.cellQty;
		inputQtyPerCell = g.inputQtyPerCell;
		outputQtyPerCell = g.outputQtyPerCell;
		fat = g.fat;
		inherit = g.inherit;
	}

	public RandomConnectGroup(float brainWidth, int randomCellQtyPerGroup, int randomInputQtyPerCell,
			int randomOutQtyPerCell) {
		inherit = false;
		groupInputZone = new Zone(r.nextFloat() * brainWidth, r.nextFloat() * brainWidth,
				(float) (r.nextFloat() * brainWidth * .01));
		groupOutputZone = new Zone(r.nextFloat() * brainWidth, r.nextFloat() * brainWidth,
				(float) (r.nextFloat() * brainWidth * .01));
		cellQty = 1 + r.nextInt(randomCellQtyPerGroup);
		cellInputRadius = (float) (0.001 + r.nextFloat() * 2);
		cellOutputRadius = (float) (0.001 + r.nextFloat() * 2);
		inputQtyPerCell = 1 + r.nextInt(randomInputQtyPerCell);
		outputQtyPerCell = 1 + r.nextInt(randomOutQtyPerCell);
	}

}
