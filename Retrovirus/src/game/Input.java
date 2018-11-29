package game;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.item.Item;
import game.item.ItemIO;

public class Input implements KeyListener, MouseListener {

	public int mouseX, mouseY;

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

		if (Game.local.inventory.visible) {
			if(!Game.local.inventory.contextOpen) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					Game.local.inventory.upselect();
					// System.out.println("u");
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Game.local.inventory.downSelect();
					// System.out.println("d");
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Game.local.inventory.contextOpen = true;
				}
			} else {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					Game.local.inventory.contextSelect--;
					// System.out.println("u");
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Game.local.inventory.contextSelect++;
					// System.out.println("d");
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int s = Game.local.inventory.select;
					Item actionItem = Game.local.inventory.getOfID(s);
					actionItem.performAction(ItemIO.getItem(s).options[Game.local.inventory.contextSelect]);
				}
			}
			
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (Game.local.inventory.contextOpen) {
					Game.local.inventory.contextOpen = false;
				} else {
					Game.local.inventory.visible = false;
				}
			}
		}

		if (!Game.local.inventory.visible) {
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
				boolean[] remove = new boolean[Game.game.items.size()];
				for (int i = 0; i < Game.game.items.size(); i++) {
					Item p = Game.game.items.get(i);
					if (p.hitBox.contains(mouseX, mouseY)) {
						Game.local.inventory.items.add(p);
						// Game.game.items.remove(p);
						remove[i] = true;
					}
				}
				for (int i = 0; i < remove.length; i++) {
					if (remove[i]) {
						Game.game.items.remove(i);
					}
				}

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
