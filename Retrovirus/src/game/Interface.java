package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.item.Item;

public class Interface {
	ArrayList<Item> items = new ArrayList<Item>();
	boolean visible = false;
	
	void draw(Graphics g) {
		boolean[] marked = new boolean[items.size()];
		for(int i = 0; i < items.size(); i++) {
			int k = 0;
			if(!marked[i]) {
				for(int j = 0; j < items.size(); j++) {
					if(items.get(j).id == items.get(i).id) {
						k++;
						marked[j] = true;
					}
				}
//				System.out.println(items.get(i).name+": "+k);
				
				if(visible) {
					g.setColor(Color.GRAY);
					g.fillRect(680, 100, 560, 600);
					g.setColor(Color.BLACK);
					g.drawRect(680, 100, 560, 600);
					g.setFont(Window.defaultFont);
					g.drawLine(680, 120, 1240, 120);
					g.drawString("Inventory", 681, 117);
				}
			}
		}
	}
}
