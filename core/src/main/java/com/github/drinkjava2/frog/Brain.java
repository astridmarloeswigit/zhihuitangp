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

/**
 * Brain is consisted by lots of cells
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class Brain {
	/** Brain dimension X is 100000 cells */
	private int xSize = 100000;

	/** Brain dimension Y is 100 cells */
	private int ySize = 1000;

	/** Brain dimension Z is 10 cells */
	private int zSize = 10;

	/** cells in brain */
	public Cell[][][] cells;

	public Brain() {
		cells = new Cell[xSize][ySize][zSize];
	}

	public Brain(int xSize, int ySize, int zSize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
		cells = new Cell[xSize][ySize][zSize];
	}
}
