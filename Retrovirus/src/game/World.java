package game;

import java.awt.Dimension;
import java.util.ArrayList;

import game.item.Item;
import terrain.Region;

//server class, separate from game so that Multiplayer can be instituted
public class World {
	Region t;
	Player[] characters;
	public ArrayList<Item> items = new ArrayList<Item>();

	void update() {
		for (Player p : characters) {
			p.update();
		}
		for (Item p : items) {
			p.update();
		}
	}

	Dimension spawn() {
		return new Dimension(0, 0);
	}

	public static int px, py;

	Item inRangeOfItem() {
		for (Item e : items) {
			if (e.hitBox.contains(Window.peripherals.mouseX, Window.peripherals.mouseY)) {
				return e;
			}
		}
		return null;
	}

	void loadTerrain() {
		t = new Region(0, 0);
	}
}
