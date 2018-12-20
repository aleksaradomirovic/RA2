package terrain;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;

public class Region {
	public static final int REGION_WIDTH = 512; //1.536km, 262,144 tiles
	public static final long REGION_TOTAL_TILES = (long) Math.pow(REGION_WIDTH, 2);
	public Rectangle drawBox;
	public Tile[][] terrain;
	
	public static int loading = 0;
	int x, y; //absolutes
	
	public Region(int xOrder, int yOrder) {
		x = xOrder*REGION_WIDTH*Tile.WIDTH;
		y = yOrder*REGION_WIDTH*Tile.HEIGHT;
		
		drawBox = new Rectangle(x-Tile.WIDTH,y-Tile.HEIGHT,(REGION_WIDTH+1)*Tile.WIDTH,(REGION_WIDTH+1)*Tile.HEIGHT);
	}
	
	public void load() {
		terrain = new Tile[REGION_WIDTH][REGION_WIDTH];
		
		Game.loadSequence = true;
		loading = 0;
		for(int i = 0; i < REGION_WIDTH; i++) {
			for(int j = 0; j < REGION_WIDTH; j++) {
				terrain[i][j] = new Tile(x + (i*Tile.WIDTH), y + (j*Tile.HEIGHT));
				
				
				loading++;
//				System.out.println("Tile "+loading+" / "+REGION_TOTAL_TILES);
			}
		}
		Game.loadSequence = false;
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < REGION_WIDTH; i++) {
			for(int j = 0; j < REGION_WIDTH; j++) {
				terrain[i][j].draw(g);
//				System.out.println("drew tile");
			}
		}
	}
}
