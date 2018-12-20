package terrain;

import java.awt.Color;
import java.awt.Graphics;

import game.World;

public class Tile {
	//absolute xy
	int x, y;
	
	public static final int HEIGHT = 80, WIDTH = 160;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	int xs, ys;
	
	
	
	void draw(Graphics g) {
		xs = x-World.px;
		ys = y-World.py;
		
		if((xs > -WIDTH && xs < 1280) && (ys > -HEIGHT && ys < 800)) {
			g.setColor(Color.BLACK);
			g.drawRect(xs, ys, WIDTH, HEIGHT);
		}
	}
}
