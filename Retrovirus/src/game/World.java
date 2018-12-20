package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import game.item.Item;
import terrain.Region;

//server class, separate from game so that Multiplayer can be instituted
public class World {
	public static final int WORLD_SIZE = 100;
	
	Player[] characters;
	public ArrayList<Item> items = new ArrayList<Item>();
	public Region[][] world = new Region[WORLD_SIZE][WORLD_SIZE];
	public Region[] worldDraw = new Region[4];

	void update() {
		for (Player p : characters) {
			p.update();
		}
		for (Item p : items) {
			p.update();
		}
		
	}
	
	Item checkForInteract(int x, int y) {
		for(Item p : items) {
			if(p.interacts(x, y)) { 
				System.out.println("interact");
				return p;
			}
		}
		System.out.println("no interact");
		return null;
		
	}

	Dimension spawn() {
		return new Dimension(0, 0);
	}

	public static int px, py;
	
	void loadTerrain() {
		for(int i = 0; i < WORLD_SIZE; i++) {
			for(int j = 0; j < WORLD_SIZE; j++) {
				world[i][j] = new Region((-WORLD_SIZE/2)+i,(-WORLD_SIZE/2)+j);
			}
		}
	}
	
	void draw(Graphics g) {
//		System.out.println("draw");
		for(int i = 0; i < WORLD_SIZE; i++) {
			for(int j = 0; j < WORLD_SIZE; j++) {
				if(world[i][j].drawBox.intersects(Game.screen)) {
					if(world[i][j].terrain == null) {
						world[i][j].load();
						System.out.println("load occured");
					}
					
					world[i][j].draw(g);
				}
			}
		}
	}
}
