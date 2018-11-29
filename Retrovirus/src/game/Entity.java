package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Entity {
	public int x, y;
	
	public Interface inventory = new Interface();
	
	public Entity(Dimension location) {
		x = location.width;
		y = location.height;
	}
	
	final void draw(Graphics g) {
		setPosition();
		
		g.setColor(Color.YELLOW);
		g.drawRect(xs-20, ys-40, 40, 80);
	}
	
	void update() {
		
	}
	
	int xs, ys;
	void setPosition() {
		xs = x-World.px;
		ys = y-World.py;
	}
}
