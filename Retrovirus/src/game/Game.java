package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import game.item.Item;
import game.item.ItemIO;
import terrain.Region;

public class Game implements ActionListener {
	public static void main(String[] args) {
		int mode = JOptionPane.showConfirmDialog(null, "Run in debug mode?", "Startmode", JOptionPane.YES_NO_OPTION);

		if (mode == JOptionPane.YES_OPTION) {
			new Window().setup("debug");
			System.out.println("Debug enabled");
		} else {
			new Window().setup(null);
		}
	}
	
	public static Rectangle screen = new Rectangle(World.px, World.py, 1280, 800);

	static boolean up, down, left, right;

	public static ItemIO itemAssetLoader = new ItemIO();
	public static World game;
	public static Player local;
	Timer localUpdate = new Timer(1000 / 60, this);
	
	public static GObject focusObject;
	static int foselect = 0;

	void start(World i) {
		game = i;
		itemAssetLoader.setup();

		local = new Player(game.spawn(), true);

		World.px = local.x - 640;
		World.py = local.y - 400;
		if (game instanceof SinglePlayerWorld) {
			game.characters[0] = local;
			local.inventory.items.add(new Item(1, 0, 0));
//			local.inventory.items.add(new Item(1, 0, 0));
//			local.inventory.items.add(new Item(2, 0, 0));
			// local.inventory.items.add(new Item(3,0,0));
			game.items.add(new Item(1, 0, 0));
			game.items.add(new Item(3, 100, 100));
		}

		localUpdate.start();
	}

	private void update() {
		if(focusObject == null) foselect = 0;
		
		if (up)
			local.y -= 2;
		if (down)
			local.y += 2;
		if (left)
			local.x -= 4;
		if (right)
			local.x += 4;

		World.px = local.x - 640;
		World.py = local.y - 400;

		Window.peripherals.update();
		
		screen.setBounds(World.px, World.py, 1280, 800);

		if (game instanceof SinglePlayerWorld) {
			game.update();
		}
		// System.out.println("update");
	}

	public static boolean loadSequence = false;

	static void draw(Graphics g) {
		if (!loadSequence) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 1280, 800);
			
			game.draw(g);
			
			for (Entity p : game.characters) {
				try {
					p.draw(g);
				} catch (NullPointerException e) {
					System.err.println("2-tick player load, ignore exception");
				}
			}
			for (Item p : game.items) {
				p.draw(g);
			}

			drawExternalGUI(g);
			
			local.inventory.draw(g);

		} else {
			// TODO region load screen
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1280, 800);
			g.setColor(Color.RED);
			g.setFont(Window.defaultFont);
			g.drawRect(500, 200, 280, 20);
			g.fillRect(500, 200, (int) (280 * (Region.loading / Region.REGION_TOTAL_TILES)), 20);
			g.drawString(Region.loading + " / " + Region.REGION_TOTAL_TILES + " tiles loaded", 500, 300);
		}
	}

	static void drawExternalGUI(Graphics g) {
		if(focusObject != null) {
			g.setColor(Color.GRAY);
			g.fillRect(focusObject.xs, focusObject.ys, 100, 14*focusObject.foOptions.length);
			g.setColor(Color.BLACK);
			g.drawRect(focusObject.xs, focusObject.ys, 100, 14*focusObject.foOptions.length);
			
			for(int i = 0; i < focusObject.foOptions.length; i++) {
				if(i == foselect) {
					g.setColor(Color.RED);
					g.fillRect(focusObject.xs+1, focusObject.ys+1+(14*i), 99, 13);
				}
				
				g.setFont(Window.defaultFont);
				g.setColor(Color.BLACK);
				g.drawString(focusObject.foOptions[i], focusObject.xs+1, focusObject.ys+11+(14*i));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}
}
