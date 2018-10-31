package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

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
	
	public static ItemIO itemAssetLoader = new ItemIO();
	static World game;
	Player local;
	Timer localUpdate = new Timer(1000/60, this);
	
	void start(World i) {
		game = i;
		itemAssetLoader.setup();
		
		local = new Player(game.spawn(), true);
		
		World.px = local.x+640;
		World.py = local.y+400;
		if(game instanceof SinglePlayerWorld) {
			game.characters[0] = local;
		}
		
		localUpdate.start();
	}
	
	private void update() {
		if(game instanceof SinglePlayerWorld) {
			game.update();
		}
	}
	
	static void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1280, 800);
		for(Entity p : game.characters) {
			p.draw(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}
}
