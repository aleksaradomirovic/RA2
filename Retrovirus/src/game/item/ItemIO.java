package game.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ItemIO {
	File[] itemAssets, imageAssets;
	File itemFolder, imageFolder;
	ArrayList<ItemTemplate> assets = new ArrayList<ItemTemplate>();
	
	public ItemIO() {
		try {
			itemFolder = new File(this.getClass().getResource("data/").toURI());
			imageFolder = new File(this.getClass().getResource("textures/").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setup() {
		itemAssets = itemFolder.listFiles();
		for(File e : itemAssets) {
			if(e.isFile()) {
				System.out.println(e.getName()+ " is file in ITEM ASSETS");
			} else if(e.isDirectory()) {
				System.out.println(e.getName()+ " is folder in ITEM ASSETS");
			}
		}
		
		imageAssets = imageFolder.listFiles();
		for(File e : imageAssets) {
			if(e.isFile()) {
				if(extension(e.getName()).equals("png")) {
					System.out.println(e.getName() + " is PNG file in IMAGE ASSETS");
				} else if(extension(e.getName()).equals("bmp")) {
					System.out.println(e.getName() + " is BMP file in IMAGE ASSETS");
				} else {
					System.out.println(e.getName() + " is INVALID file in IMAGE ASSETS");
				}
			} else if(e.isDirectory()) {
				System.out.println(e.getName()+ " is folder in IMAGE ASSETS");
			}
		}
		
		//load all item templates, errors will self-correct
		getItems();
	}
	
	String extension(String file) {
		String extension = "";

		int i = file.lastIndexOf('.');
		if (i > 0) {
		    extension = file.substring(i+1);
		}
//		System.out.println(extension);
		return extension;
	}
	
	private void getItems() {
		BufferedReader br;
		for(File e : itemAssets) {
			try {
				br = new BufferedReader(new FileReader(e));
				String[] asset = new String[65536];
				String line;
				int i = 0;
				while((line = br.readLine()) != null) {
					asset[i] = line;
					i++;
				}
				
				for(int j = 0; asset[j] != null; j++) {
					int index;
					if(asset[j].contains("{")) {
						index = j;
						while(!asset[j].contains("}")) j++;
						
						String[] item = new String[(j-index)+1];
						
						for(int k = index; k <= j; k++) {
							item[k-index] = asset[k];
						}
						
						assets.add(new ItemTemplate(item));
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
