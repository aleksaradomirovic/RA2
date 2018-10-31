package game;

import java.awt.Dimension;

//server class, separate from game so that Multiplayer can be instituted
public class World {
	Player[] characters;
	void update() {
		
	}
	
	Dimension spawn() {
		return new Dimension(0,0);
	}
	
	static int px, py;
}
