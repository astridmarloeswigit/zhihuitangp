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
package com.github.drinkjava2.frog.egg;

import java.io.Serializable;
import java.util.Random;

/**
 * Egg is the static structure description of frog, can save as text file, to
 * build a frog, first need build a egg.<br/>
 * 
 * 蛋存在的目的是为了以最小的字节数串行化存储Frog,它是Frog的生成算法描述，而不是Frog本身，这样一来Frog就不能"永生"了，因为每一个egg都不等同于
 * 它的母体， 而且每一次测试，大部分条件反射的建立都必须从头开始训练，类似于人类，无论人类社会有多聪明， 婴儿始终是一张白纸，需要花大量的时间从头学习。
 * 
 */
public class Egg implements Serializable {
	private static final long serialVersionUID = 2L;
	public static final int BRAIN_WIDTH = 800;
	public int randomCellGroupQty = 100;
	public int randomCellQtyPerGroup = 5;
	public int randomInputQtyPerCell = 10;
	public int randomOutQtyPerCell = 5;
	
	
	
	public CellGroup[] cellgroups;
	public int realCellGroupQty=0; //z

	public Egg() {
		// default constructor
	}

	private static Random r = new Random();
	
	public Egg(Egg e) {// clone the old egg
		cellgroups = new CellGroup[e.cellgroups.length];
		for (int i = 0; i < e.cellgroups.length; i++) {
			CellGroup oldCellGroup = e.cellgroups[i];
			CellGroup cellGroup = new CellGroup();
			cellgroups[i] = cellGroup;
			cellGroup.groupInputZone = new Zone(oldCellGroup.groupInputZone);
			cellGroup.groupOutputZone = new Zone(oldCellGroup.groupOutputZone);
			cellGroup.cellQty = oldCellGroup.cellQty;
			cellGroup.cellInputRadius = oldCellGroup.cellInputRadius;
			cellGroup.cellOutputRadius = oldCellGroup.cellOutputRadius;
			cellGroup.inputQtyPerCell = oldCellGroup.inputQtyPerCell;
			cellGroup.outputQtyPerCell = oldCellGroup.outputQtyPerCell;
		}
		realCellGroupQty=e.realCellGroupQty;
	}

	public Egg(Egg x, Egg y) { // use 2 eggs to create a zygote
		cellgroups = new CellGroup[x.cellgroups.length + y.cellgroups.length+ randomCellGroupQty/5];
		for (int i = 0; i < x.cellgroups.length; i++) {
			CellGroup oldCellGroup = x.cellgroups[i];
			CellGroup cellGroup = new CellGroup();
			cellgroups[i] = cellGroup;
			cellGroup.groupInputZone = new Zone(oldCellGroup.groupInputZone);
			cellGroup.groupOutputZone = new Zone(oldCellGroup.groupOutputZone);
			cellGroup.cellQty = oldCellGroup.cellQty;
			cellGroup.cellInputRadius = oldCellGroup.cellInputRadius;
			cellGroup.cellOutputRadius = oldCellGroup.cellOutputRadius;
			cellGroup.inputQtyPerCell = oldCellGroup.inputQtyPerCell;
			cellGroup.outputQtyPerCell = oldCellGroup.outputQtyPerCell;
		}
		int xLength = x.cellgroups.length;
		for (int i = 0; i < y.cellgroups.length; i++) {
			CellGroup oldCellGroup = y.cellgroups[i];
			CellGroup cellGroup = new CellGroup();
			cellgroups[xLength + i] = cellGroup;
			cellGroup.groupInputZone = new Zone(oldCellGroup.groupInputZone);
			cellGroup.groupOutputZone = new Zone(oldCellGroup.groupOutputZone);
			cellGroup.cellQty = oldCellGroup.cellQty;
			cellGroup.cellInputRadius = oldCellGroup.cellInputRadius;
			cellGroup.cellOutputRadius = oldCellGroup.cellOutputRadius;
			cellGroup.inputQtyPerCell = oldCellGroup.inputQtyPerCell;
			cellGroup.outputQtyPerCell = oldCellGroup.outputQtyPerCell;
		}
		int yLength=y.cellgroups.length;
		realCellGroupQty=xLength+yLength;
		for (int i = 0; i < randomCellGroupQty/5; i++) {
			CellGroup cellGroup = new CellGroup();
			cellgroups[i+xLength+yLength] = cellGroup;
			cellGroup.groupInputZone = new Zone(r.nextFloat() * BRAIN_WIDTH, r.nextFloat() * BRAIN_WIDTH,
					(float) (r.nextFloat() * BRAIN_WIDTH * .01));
			cellGroup.groupOutputZone = new Zone(r.nextFloat() * BRAIN_WIDTH, r.nextFloat() * BRAIN_WIDTH,
					(float) (r.nextFloat() * BRAIN_WIDTH * .01));
			cellGroup.cellQty = r.nextInt(x.randomCellQtyPerGroup);
			cellGroup.cellInputRadius = (float) (r.nextFloat() * 0.001);
			cellGroup.cellOutputRadius = (float) (r.nextFloat() * 0.001);
			cellGroup.inputQtyPerCell = r.nextInt(x.randomInputQtyPerCell);
			cellGroup.outputQtyPerCell = r.nextInt(x.randomOutQtyPerCell);
		} 
		
	}

	public static Egg createBrandNewEgg() { // create a brand new Egg
		Egg egg = new Egg(); 
		egg.cellgroups = new CellGroup[egg.randomCellGroupQty];
		for (int i = 0; i < egg.randomCellGroupQty; i++) {
			CellGroup cellGroup = new CellGroup();
			egg.cellgroups[i] = cellGroup;
			cellGroup.groupInputZone = new Zone(r.nextFloat() * BRAIN_WIDTH, r.nextFloat() * BRAIN_WIDTH,
					(float) (r.nextFloat() * BRAIN_WIDTH * .01));
			cellGroup.groupOutputZone = new Zone(r.nextFloat() * BRAIN_WIDTH, r.nextFloat() * BRAIN_WIDTH,
					(float) (r.nextFloat() * BRAIN_WIDTH * .01));
			cellGroup.cellQty = r.nextInt(egg.randomCellQtyPerGroup);
			cellGroup.cellInputRadius = (float) (r.nextFloat() * 0.001);
			cellGroup.cellOutputRadius = (float) (r.nextFloat() * 0.001);
			cellGroup.inputQtyPerCell = r.nextInt(egg.randomInputQtyPerCell);
			cellGroup.outputQtyPerCell = r.nextInt(egg.randomOutQtyPerCell);
		}
		egg.realCellGroupQty=egg.cellgroups.length;
		return egg;
	}

}
