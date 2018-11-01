package game.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.World;

public class Item {
	int x, y;
	float mass, rigid;
	public String name;
	public int id;
	int use;
	BufferedImage img;
	
	public Item(int id, int x, int y) {
		this.id = id;
		
		ItemTemplate t = ItemIO.getItem(id);
		mass = t.mass;
		rigid = t.rigid;
		name = t.name;
		img = t.img;
		use = t.use;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		int xs, ys;
		
		xs = x-World.px;
		ys = y-World.py;
		
		g.drawImage(img, xs, ys, null);
//		System.out.println("drew");
	}
	
	public void update() {
		
	}
	
	public float mass() {
		return mass*use;
	}
}
