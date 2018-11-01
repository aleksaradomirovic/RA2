package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_G) {
			Game.local.inventory.visible = !Game.local.inventory.visible;
		}
		
		if(!Game.local.inventory.visible) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				Game.up = true;
				//			System.out.println("u");
			} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				Game.down = true;
				//			System.out.println("d");
			} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				Game.left = true;
				//			System.out.println("l");
			} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Game.right = true;
				//			System.out.println("r");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Game.up = false;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Game.down = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Game.left = false;
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Game.right = false;
		}
	}

}
