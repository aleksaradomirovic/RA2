package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.item.Item;
import game.item.ItemIO;
import game.item.ItemTemplate;

public class Interface {
	ArrayList<Item> items = new ArrayList<Item>();
	int[] itemCounts;
	boolean visible = false;
	int select = 1;
	boolean contextOpen = false;

	void draw(Graphics g) {
		boolean[] marked = new boolean[items.size()];
		itemCounts = new int[ItemIO.assets.size() + 1];
		for (int i = 0; i < items.size(); i++) {
			int k = 0;
			if (!marked[i]) {
				for (int j = 1; j < items.size(); j++) {
					if (items.get(j).id == items.get(i).id) {
						k++;
						marked[j] = true;
					}
				}
				itemCounts[items.get(i).id] = k;
				// System.out.println(items.get(i).name+": "+k);

			}
		}
		if (visible) {
			g.setColor(Color.GRAY);
			g.fillRect(680, 100, 560, 600);
			g.setColor(Color.BLACK);
			g.drawRect(680, 100, 560, 600);
			g.setFont(Window.defaultFont);
			g.drawLine(680, 120, 1240, 120);
			g.drawString("Inventory", 681, 117);

			int s = 0;
			for (int i = 0; i < itemCounts.length; i++) {
				if (itemCounts[i] > 0) {
					if (select == i - s + 1) {
						if (contextOpen) {
							g.setColor(Color.GRAY);
							g.fillRect(800, 140 + ((i - s) * 11), 200, 200);
							g.setColor(Color.BLACK);
							g.drawRect(800, 140 + ((i - s) * 11), 200, 200);
							ItemTemplate tempAsset = ItemIO.getItem(i);
							g.setColor(Color.WHITE);
							g.drawString(tempAsset.name, 801, 150 + ((i - s) * 11));
							g.setColor(Color.BLACK);
							int n = ((i - s) * 11);
							for (int j = 0; j < tempAsset.options.length; j++) {
								g.drawRect(800, 328 + n - (j * 12), 100, 12);
							}

							// if(tempAsset.img.getWidth() > tempAsset.img.getHeight()) {
							// g.drawImage(tempAsset.img, 801, 141+((i-s)*11), 100,
							// 100*tempAsset.scaleImage(), null);
							// } else if(tempAsset.img.getHeight() > tempAsset.img.getWidth()) {
							// g.drawImage(tempAsset.img, 801, 141+((i-s)*11), 100*tempAsset.scaleImage(),
							// 100, null);
							// } else {
							// g.drawImage(tempAsset.img, 801, 141+((i-s)*11), 100, 100, null);
							// }
						}

						// must be last
						g.setColor(Color.RED);
					} else
						g.setColor(Color.BLACK);

					g.drawString(ItemIO.getItem(i).name + " (" + itemCounts[i] + ")", 681, 150 + ((i - s) * 11));
				} else {
					s++;
				}
			}
		}

	}

	void upselect() {
		int o = select;
		int i = o - 1;

		while (i >= 0 && itemCounts[i] < 1)
			i--;

		if (i < 0)
			select = o;
		else
			select = i;
		System.out.println(i);
	}

	void downSelect() {
		int o = select;
		int i = o + 1;

		while (i < itemCounts.length && itemCounts[i] < 1)
			i++;

		if (i >= itemCounts.length)
			select = o;
		else
			select = i;
		System.out.println(i);
	}
}
