package game;

import java.awt.Graphics;
import java.util.ArrayList;

import game.item.Item;

public class Interface {
	ArrayList<Item> items = new ArrayList<Item>();
	
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
			}
			System.out.println(items.get(i).name+": "+k);
		}
	}
}
