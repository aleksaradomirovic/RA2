package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import game.item.Item;
import game.item.ItemIO;

public class Game implements ActionListener {
	public static void main(String[] args) {
		int mode = JOptionPane.showConfirmDialog(null, "Run in debug mode?", "Startmode", JOptionPane.YES_NO_OPTION);
		
		if(mode == JOptionPane.YES_OPTION) {
			new Window().setup("debug");
			System.out.println("Debug enabled");
		} else {
			new Window().setup(null);
		}
	}
	
	static boolean up, down, left, right;
	
	public static ItemIO itemAssetLoader = new ItemIO();
	static World game;
	static Player local;
	Timer localUpdate = new Timer(1000/60, this);
	
	void start(World i) {
		game = i;
		itemAssetLoader.setup();
		
		local = new Player(game.spawn(), true);
		
		World.px = local.x-640;
		World.py = local.y-400;
		if(game instanceof SinglePlayerWorld) {
			game.characters[0] = local;
			local.inventory.items.add(new Item(1,0,0));
			local.inventory.items.add(new Item(1,0,0));
			local.inventory.items.add(new Item(2,0,0));
//			local.inventory.items.add(new Item(3,0,0));
			game.items.add(new Item(1,0,0));
			game.items.add(new Item(3,100,100));
		}
		
		localUpdate.start();
	}
	
	private void update() {
		if(up) local.y-=5;
		if(down) local.y+=5;
		if(left) local.x-=10;
		if(right) local.x+=10;
		
		World.px = local.x-640;
		World.py = local.y-400;
		
		Window.peripherals.update();
		
		if(game instanceof SinglePlayerWorld) {
			game.update();
		}
//		System.out.println("update");
	}
	
	static void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1280, 800);
		for(Entity p : game.characters) {
			p.draw(g);
		}
		for(Item p : game.items) {
			p.draw(g);
		}
		
		drawExternalGUI(g);
		
		local.inventory.draw(g);
	}
	
	static void drawExternalGUI(Graphics g) {
		if(game.inRangeOfItem() && !local.inventory.visible) {
//			System.out.println("itemrange");
			g.setFont(Window.defaultFont);
			g.setColor(Color.BLACK);
			g.drawString("[F] Pick Up", Window.peripherals.mouseX+12, Window.peripherals.mouseY+15);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}
}
