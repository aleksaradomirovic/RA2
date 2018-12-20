package game.item;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.GObject;
import game.Game;
import game.World;

public class Item extends GObject {
	int x, y;
	float mass, rigid;
	public String name;
	public int id;
	int use;
	BufferedImage img;
//	public Rectangle hitBox = new Rectangle();
//	int xs, ys;
	
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
		
		foOptions = new String[] {"Pick Up"};
		hitBox = new Rectangle();
	}
	
	public void draw(Graphics g) {
		
		xs = x-World.px;
		ys = y-World.py;
		
		g.drawImage(img, xs, ys, null);
//		System.out.println("drew");
	}
	
	public void update() {
		hitBox.setBounds(xs, ys, img.getWidth(), img.getHeight());
//		System.out.println(hitBox.x+" "+hitBox.y+" "+hitBox.width+" "+hitBox.height);
	}
	
	public float mass() {
		return mass*use;
	}
	
	public boolean interacts(int x, int y) {
		if(hitBox.contains(x,y)) {
			return true;
		}
		return false;
	}
	
	public void performAction(String action) {
		if(action == "Drop") {
			x = Game.local.x;
			y = Game.local.y;
			
			Game.game.items.add(this);
			Game.local.inventory.items.remove(this);
			System.out.println("Performed \"Drop\" action");
		} else {
			System.out.println("Performed null action");
		}
	}
	
	@Override
	public void runInteract(int select) {
		if(select == 0) {
			Game.local.inventory.items.add(this);
			Game.game.items.remove(this);
			Game.focusObject = null;
		}
	}
}
