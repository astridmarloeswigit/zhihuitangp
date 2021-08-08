package com.github.drinkjava2.frog.env;

import static java.lang.Math.round;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.github.drinkjava2.frog.Frog;
import com.github.drinkjava2.frog.egg.CellGroup;
import com.github.drinkjava2.frog.egg.Zone;

/**
 * BrainStructure show first frog's brain structure, for debug purpose
 */
@SuppressWarnings("serial")
public class BrainStructure extends JPanel {

	public BrainStructure() {
		super();
		this.setLayout(null);// 空布局
		this.setBounds(500, 0, 1000, 1000);
	}

	void drawZone(Graphics g, Zone z) {
		g.drawRect(round(z.x - z.radius), round(z.y - z.radius), round(z.radius * 2), round(z.radius * 2));
	}

	void fillZone(Graphics g, Zone z) {
		g.fillRect(round(z.x - z.radius), round(z.y - z.radius), round(z.radius * 2), round(z.radius * 2));
	}

	private static Color color(float i) {
		if (i <= 1)
			return Color.GRAY;
		if (i <= 3)
			return Color.ORANGE;
		if (i <= 10)
			return Color.YELLOW;
		if (i <= 20)
			return Color.GREEN;
		if (i <= 50)
			return Color.CYAN;
		if (i <= 100)
			return Color.BLUE;
		return Color.MAGENTA;
	}

	public void drawBrain(Frog frog) {
		if (!Application.SHOW_FIRST_FROG_BRAIN)
			return;
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

		for (int j = 0; j < frog.egg.realCellGroupQty; j++) {
			CellGroup group = frog.egg.cellgroups[j];
			g.setColor(color(group.cellQty));// 取随机色
			g.drawLine(round(group.groupInputZone.x), round(group.groupInputZone.y), round(group.groupOutputZone.x),
					round(group.groupOutputZone.y));
			drawZone(g, group.groupInputZone);
			fillZone(g, group.groupOutputZone);
		}

	}
}
