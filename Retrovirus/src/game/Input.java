package game;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener {

	public int mouseX, mouseY;
	Interface inv;

	public void update() {
		mouseY = MouseInfo.getPointerInfo().getLocation().y - Window.frame.getY() - 26;
		mouseX = MouseInfo.getPointerInfo().getLocation().x - Window.frame.getX() - 3;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_G) {
			Game.local.inventory.visible = !Game.local.inventory.visible;
			if (!Game.local.inventory.visible)
				Game.local.inventory.contextOpen = false;
		}

		if (!Game.local.inventory.visible)

		{
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				Game.up = true;
				// System.out.println("u");
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				Game.down = true;
				// System.out.println("d");
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Game.left = true;
				// System.out.println("l");
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Game.right = true;
				// System.out.println("r");
			} else if (e.getKeyCode() == KeyEvent.VK_F) {
				inv.items.add(Game.game.inRangeOfItem());
				Game.game.items.remove(Game.game.inRangeOfItem());
			}
		} else {
			inv = Game.local.inventory;
			if (e.getKeyCode() == KeyEvent.VK_DOWN && inv.select < inv.items.size() - 1) {
				inv.select++;
			} else if (e.getKeyCode() == KeyEvent.VK_UP && inv.select > 0) {
				inv.select--;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Game.up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Game.down = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Game.left = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Game.right = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getX() + ", " + arg0.getY() + "; " + mouseX + ", " + mouseY);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
