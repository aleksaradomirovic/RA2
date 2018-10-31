package game;

import java.awt.Dimension;

public class Player extends Entity {
	boolean isLocal;
	public Player(Dimension location, boolean isLocal) {
		super(location);
		this.isLocal = isLocal;
	}
	
	@Override
	void setPosition() {
		if(isLocal) {
			xs = 640;
			ys = 400;
		} else {
			xs = x-World.px;
			ys = y-World.py;
		}
	}
}
