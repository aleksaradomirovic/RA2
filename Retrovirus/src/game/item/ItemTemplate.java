package game.item;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ItemTemplate {
	int id = -1, productid = -1;
	float mass = 0, rigid = 0;
	int use = 1;
	public String name = null, imgfile = null;
	public BufferedImage img;
	String desc = "";
	public String[] parsedDesc;
	public String[] options;

	public ItemTemplate(String[] parsedItemData) {
		for (int i = 0; i < parsedItemData.length; i++) {
			parsedItemData[i] = parsedItemData[i].replace("\t", "");

			String s = parsedItemData[i];
			int index = s.lastIndexOf('=') + 1;
			if (id == -1 && s.contains("ID")) {
				s = s.replace(" ", "");
				index = s.lastIndexOf('=') + 1;
				id = Integer.parseInt(s.substring(index, s.length() - 1));
			} else if (name == null && s.contains("name")) {
				s = s.substring(index);
				int open = s.indexOf("\"") + 1, close = s.lastIndexOf("\"");
				name = s.substring(open, close);
			} else if (imgfile == null && s.contains("texture")) {
				s = s.substring(index);
				int open = s.indexOf("\"") + 1, close = s.lastIndexOf("\"");
				imgfile = s.substring(open, close);
				try {
					img = ImageIO.read(ItemIO.getImageFile(imgfile));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (mass == 0 && s.contains("mass")) {
				s = s.replace(" ", "");
				index = s.lastIndexOf('=') + 1;
				mass = Float.parseFloat(s.substring(index, s.length() - 1));
			} else if (rigid == 0 && s.contains("rigid")) {
				s = s.replace(" ", "");
				index = s.lastIndexOf('=') + 1;
				rigid = Float.parseFloat(s.substring(index, s.length() - 1));
			} else if (use == 1 && s.contains("use")) {
				s = s.replace(" ", "");
				index = s.lastIndexOf('=') + 1;
				use = Integer.parseInt(s.substring(index, s.length() - 1));
			} else if (productid == -1 && s.contains("product")) {
				s = s.replace(" ", "");
				index = s.lastIndexOf('=') + 1;
				productid = Integer.parseInt(s.substring(index, s.length() - 1));
				// } else if(desc == "" && s.contains("desc")) {
				// s = s.substring(index);
				// int open = s.indexOf("\"")+1, close = s.lastIndexOf("\"");
				// desc = s.substring(open, close);
				// parseDesc();
			}
		}
		System.out.println("id = " + id);
		System.out.println("name = " + name);
		System.out.println("texture file = " + imgfile);
		System.out.println("rigid = " + rigid);
		System.out.println("mass = " + mass);
		System.out.println("uses = " + use);
		System.out.println("product = " + productid);

		options = new String[] { "Drop" };
	}

	public int scaleImage() {
		if (img.getWidth() > img.getHeight()) {
			return img.getHeight() / img.getWidth();
		} else if (img.getHeight() > img.getWidth()) {
			return img.getWidth() / img.getHeight();
		} else {
			return 1;
		}
	}

	// private void parseDesc() {
	// int l = (int)desc.length()/16;
	// parsedDesc = new String[l];
	//
	// int cNum = 0;
	// for(int index = 0; index < l; index++) {
	// for(int i = 0; i < 16; i++) {
	// parsedDesc[index]+=desc.charAt(cNum);
	// cNum++;
	// }
	// }
	// }
}
