package terrain;

import game.Game;

public class Region {
	public static final int REGION_WIDTH = 2048; //6.144km, 4.2 million tiles
	public static final long REGION_TOTAL_TILES = (long) Math.pow(REGION_WIDTH, 2);
	Tile[][] terrain = new Tile[REGION_WIDTH][REGION_WIDTH];
	
	public static int loading = 0;
	int x, y; //absolutes
	
	public Region(int xOrder, int yOrder) {
		x = xOrder*2048*Tile.WIDTH;
		y = yOrder*2048*Tile.HEIGHT;
		
		Game.loadSequence = true;
		loading = 0;
		for(int i = 0; i < REGION_WIDTH; i++) {
			for(int j = 0; j < REGION_WIDTH; j++) {
				terrain[i][j] = new Tile(x + (i*Tile.WIDTH), y + (i*Tile.HEIGHT));
				loading++;
//				System.out.println("Tile "+loading+" / "+REGION_TOTAL_TILES);
			}
		}
		Game.loadSequence = false;
	}
}
