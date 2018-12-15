package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.item.Item;

public class Interface {
	public ArrayList<Item> items = new ArrayList<Item>();
	public boolean visible = false, contextOpen = false;
	int select = 0;

	void draw(Graphics g) {
		if (visible) {
			g.setFont(Window.defaultFont);
			g.setColor(Color.GRAY);
			g.fillRect(690, 50, 540, 700);
			g.setColor(Color.BLACK);
			g.drawRect(690, 50, 540, 700);
			for (int i = 0; i < items.size(); i++) {
				if (select == i) {
					g.setColor(Color.RED);
					g.fillRect(700, 100 + (i * 12), 100, 12);
					g.setColor(Color.BLACK);
				}
				System.out.println(items.get(i).name);
				g.drawString(items.get(i).name, 701, 110 + (i * 12));
			}
		}
	}
}
