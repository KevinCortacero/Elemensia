package com.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageConcatenator {
	
	public static void main(String[] args) throws IOException, Exception{
		Map<String, List<BufferedImage>> animations = new HashMap<String, List<BufferedImage>>();
		
		final File file = new File("images");
		
		// image pattern sprite-name_animation-type_number.png
		for(String filename : file.list()){
			String name = filename.split("_")[0];
			String type = filename.split("_")[1];
			int number = Integer.valueOf(filename.split("_")[2].split(".")[0]);
			if (!animations.containsKey(name))
				animations.put(name, new ArrayList<BufferedImage>());
			animations.get(name).add(number, ImageIO.read(new File(filename)));
		}

		int widthImg1 = img1.getWidth();
		int heightImg1 = img1.getHeight();

		int widthImg2 = img2.getWidth();
		int heightImg2 = img2.getHeight();

		BufferedImage img = new BufferedImage(
				widthImg1+widthImg2, // Final image will have width and height as
				heightImg1+heightImg2, // addition of widths and heights of the images we already have
				BufferedImage.TYPE_INT_RGB);

		boolean image1Drawn = img.createGraphics().drawImage(img1, 0, 0, null); // 0, 0 are the x and y positions
		if(!image1Drawn) System.out.println("Problems drawing first image"); //where we are placing image1 in final image

		boolean image2Drawn = img.createGraphics().drawImage(img2, widthImg1, 0, null); // here width is mentioned as width of
		if(!image2Drawn) System.out.println("Problems drawing second image"); // image1 so both images will come in same level
		// horizontally
		File final_image = new File("Final.jpg"); // “png can also be used here”
		boolean final_Image_drawing = ImageIO.write(img, "jpeg", final_image); //if png is used, write “png” instead “jpeg”
		if(!final_Image_drawing) System.out.println("Problems drawing final image");
	}
}
