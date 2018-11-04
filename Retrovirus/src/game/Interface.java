package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.item.Item;
import game.item.ItemIO;

public class Interface {
	ArrayList<Item> items = new ArrayList<Item>();
	int[] itemCounts;
	boolean visible = false;
	int select = 0;
	
	void draw(Graphics g) {
		boolean[] marked = new boolean[items.size()];
		itemCounts = new int[ItemIO.assets.size()];
		for(int i = 0; i < items.size(); i++) {
			int k = 0;
			if(!marked[i]) {
				for(int j = 0; j < items.size(); j++) {
					if(items.get(j).id == items.get(i).id) {
						k++;
						marked[j] = true;
					}
				}
				itemCounts[items.get(i).id] = k;
//				System.out.println(items.get(i).name+": "+k);
				
			}
		}
		if(visible) {
			g.setColor(Color.GRAY);
			g.fillRect(680, 100, 560, 600);
			g.setColor(Color.BLACK);
			g.drawRect(680, 100, 560, 600);
			g.setFont(Window.defaultFont);
			g.drawLine(680, 120, 1240, 120);
			g.drawString("Inventory", 681, 117);
			
			int s = 0;
			for(int i = 0; i < itemCounts.length; i++) {
				if(itemCounts[i] > 0) {
					if(select == i-s) g.setColor(Color.RED);
					else g.setColor(Color.BLACK);
					
					g.drawString(ItemIO.getItem(i).name+" ("+itemCounts[i]+")", 681, 150+((i-s)*11));
				} else {
					s++;
				}
			}
		}
		
	}
	
	void upselect() {
		int o = select;
		int i = 0;
		
		while(i >= 0 && itemCounts[i] < 1) i--;
		
		if(i < 0) select = o;
		else select = i;
	}
	
	void downSelect() {
		int o = select;
		int i = 0;
		
		while(i < itemCounts.length && itemCounts[i] < 1) i++;
		
		if(i < 0) select = o;
		else select = i;
	}
}
