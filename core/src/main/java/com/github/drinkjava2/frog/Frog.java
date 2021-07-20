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

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.github.drinkjava2.frog.brain.Brain;
import com.github.drinkjava2.frog.brain.Eye;

/**
 * Eye of frog, each frog has 1 eye, eye is the input device of frog, eye's
 * input cells are located inside of brain
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class Frog extends JButton {
	/**
	 * if energy is 0, then frog die, energy get from food, food get from god, god
	 * is programmer. any movement and thinking will cost at least 1 energy
	 */
	long energy = 10000;

	Brain brain;
	Eye eye;

	/**
	 * @param x
	 *            x position of env
	 * @param y
	 *            y position of env
	 */
	public Frog(int x, int y) {
		this.setBounds(new Rectangle(x, y,5,5));
		this.setForeground(Color.red);
		this.setBackground(Color.blue); 

		this.addActionListener(new HelloListener(this));

	}

	public static class HelloListener implements ActionListener {
		Frog frog;

		public HelloListener(Frog frog) {
			this.frog = frog;
		}

		public void actionPerformed(ActionEvent e) {
			frog.setVisible(false);
		}
	}
}
