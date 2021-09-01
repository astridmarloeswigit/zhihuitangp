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
package com.github.drinkjava2.frog.brain;

import com.github.drinkjava2.frog.Frog;

/**
 * Organ is a part of frog, organ can be saved in egg
 * 
 * 器官是脑的一部分，多个器官在脑内可以允许重叠出现在同一位置。
 * 
 * @author Yong Zhu
 * @since 1.0.4
 */
public class Organ extends Zone {
	private static final long serialVersionUID = 1L;
	public String name; // 显示在脑图上的器官名称
	public long fat = 0; // 如果细胞活跃多，fat值高，则保留（以及变异）的可能性大，反之则舍弃掉
	public int qtyLimit = 5; // 最多允许数量
	public boolean inherit = false; // 器官是继承来的，还是变异来的，后者淘汰率会高

	/** If active in this organ's zone? */
	public boolean outputActive(Frog f) {
		for (Cell cell : f.cells) {
			for (Output output : cell.outputs) { //
				if (cell.energy > 10 && this.nearby(output)) {
					f.cellGroups[cell.group].fat++;
					cell.energy -= 30;
					return true;
				}
			}
		}
		return false;
	}

	/** make a new copy of current organ */
	public Organ newCopy() { // 新建一份，用于从蛋复制到Frog
		Organ newOrgan = null;
		try {
			newOrgan = this.getClass().newInstance();
			copyXYR(this, newOrgan);
			newOrgan.name = this.name;
			newOrgan.fat = this.fat;
			return newOrgan;
		} catch (Exception e) {
			throw new UnknownError("Can not make new Organ copy for " + this);
		}
	}

	/** Set X, Y, Radius of current Organ */
	public Organ setXYR(float x, float y, float radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		return this;
	}

	/** Set X, Y, Radius, name of current Organ */
	public Organ setXYRN(float x, float y, float radius, String name) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.name = name;
		return this;
	}

	/** Only call once when frog created , Child class can override this method */
	public void init(Frog f) {
	}

	/** Each loop step call active method, Child class can override this method */
	public void active(Frog f) {
	}

}
