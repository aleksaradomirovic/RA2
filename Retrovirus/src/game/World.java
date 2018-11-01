package game;

import java.awt.Dimension;
import java.util.ArrayList;

import game.item.Item;

//server class, separate from game so that Multiplayer can be instituted
public class World {
	Player[] characters;
	ArrayList<Item> items = new ArrayList<Item>();
	void update() {
		for(Player p : characters) {
			p.update();
		}
		for(Item p : items) {
			p.update();
		}
	}
	
	Dimension spawn() {
		return new Dimension(0,0);
	}
	
	public static int px, py;
}
