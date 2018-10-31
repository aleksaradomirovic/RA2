package game.item;

import java.awt.image.BufferedImage;

public class ItemTemplate {
	int id = -1;
	String name = null, imgfile = null;
	BufferedImage img;
	public ItemTemplate(String[] parsedItemData) {
		for(int i = 0; i < parsedItemData.length; i++) {
			parsedItemData[i] = parsedItemData[i].replace("\t", "");
			
			String s = parsedItemData[i];
			int index = s.lastIndexOf('=')+1;
			if(id == -1 && s.contains("ID")) {
				s = s.replace(" ", "");
				index = s.lastIndexOf('=')+1;
				id = Integer.parseInt(s.substring(index, s.length()-1));
			} else if(name == null && s.contains("name")) {
				s = s.substring(index);
				int open = s.indexOf("\"")+1, close = s.lastIndexOf("\"");
				name = s.substring(open, close);
			} else if(imgfile == null && s.contains("texture")) {
				s = s.substring(index);
				int open = s.indexOf("\"")+1, close = s.lastIndexOf("\"");
				imgfile = s.substring(open, close);
				img = new BufferedImage()
			}
			
			
		}
		System.out.println("id = "+id);
		System.out.println("name = "+name);
		System.out.println("texture file = "+imgfile);
	}
}
