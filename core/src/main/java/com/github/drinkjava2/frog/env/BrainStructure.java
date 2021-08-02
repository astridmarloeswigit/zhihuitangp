package com.github.drinkjava2.frog.env;

import static java.lang.Math.round;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.github.drinkjava2.frog.Frog;
import com.github.drinkjava2.frog.brain.Cell;
import com.github.drinkjava2.frog.egg.CellGroup;
import com.github.drinkjava2.frog.egg.Zone;

/**
 * Env is the living space of frog. draw it on JPanel
 */
@SuppressWarnings("serial")
public class BrainStructure extends JPanel {

	public BrainStructure() {
		super();
		this.setLayout(null);// 空布局
		this.setBounds(500, 0, 1000, 1000);
	}

	void drawZone(Graphics g, Zone z) {
		g.drawRect(round(z.x - z.radius / 2), round(z.y - z.radius / 2), round(z.radius), round(z.radius));
	}

	void fillZone(Graphics g, Zone z) {
		g.fillRect(round(z.x - z.radius / 2), round(z.y - z.radius / 2), round(z.radius), round(z.radius));
	}

	public void drawBrain(Frog frog) {
		Graphics g = this.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 800);
		g.setColor(Color.black);
		g.drawRect(0, 0, 800, 800);

		g.setColor(Color.red);
		drawZone(g, frog.eye);

		g.setColor(Color.green);
		drawZone(g, frog.happy);

		g.setColor(Color.yellow);
		drawZone(g, frog.hungry);

		g.setColor(Color.gray);
		drawZone(g, frog.moveUp);
		drawZone(g, frog.moveDown);
		drawZone(g, frog.moveLeft);
		drawZone(g, frog.moveRight);

		g.setColor(Color.black);

		int i = 1;
		for (CellGroup group : frog.egg.cellgroups) {
			g.setColor(new Color((i++) * 130 + i * 70000 + i * 3333333));// 取随机色
			g.drawLine(round(group.groupInputZone.x), round(group.groupInputZone.y), round(group.groupOutputZone.x),
					round(group.groupOutputZone.y));
			drawZone(g, group.groupInputZone);
			fillZone(g, group.groupOutputZone);
		}

		for (Cell iterable_element : frog.cells) {

		}

	}
}
