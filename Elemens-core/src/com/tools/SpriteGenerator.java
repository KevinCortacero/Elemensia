package com.tools;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class SpriteGenerator {

	private final static String IMAGE_FOLDER = "images/";
	public final static String SPRITE_FOLDER = "sprites/";
	private final static String OUT_FOLDER = "out/";

	public static BufferedImage resizeImg(BufferedImage img, int newW, int newH)
	{
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return dimg;      
	}

	public static void main(String[] args) throws IOException, Exception{
		final JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));

		panel.add(new JLabel("Width  (px)   :"));
		final JSpinner width = new JSpinner();
		panel.add(width);

		panel.add(new JLabel("Height (px)   :"));
		final JSpinner height = new JSpinner();
		panel.add(height);

		panel.add(new JLabel("Nb animations :"));
		final JSpinner animations = new JSpinner();
		panel.add(animations);

		panel.add(new JLabel("Nb frames     :"));
		final JSpinner frames = new JSpinner();
		panel.add(frames);

		final JTextField name = new JTextField();
		panel.add(name);
		JButton validate = new JButton("GENERATE"); 
		validate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (! name.getText().replace(" ", "").equals("")){
					try {
						SpriteGenerator.createSprite(frame, (Integer) width.getValue(), (Integer) height.getValue(), (Integer) animations.getValue(), (Integer) frames.getValue(), name.getText());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, "Problem !!", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					frame.dispose();
				}
				else{
					JOptionPane.showMessageDialog(frame, "Please, give a name to the sprite !!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(validate);
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	protected static void createSprite(JFrame frame, int width, int height, int animations, int frames, String name) throws Exception {
		Map<String, BufferedImage[]> sprites = new HashMap<String, BufferedImage[]>();

		final File imageFolder = new File(IMAGE_FOLDER);
		final File spriteFolder = new File(SPRITE_FOLDER);
		final File outFolder = new File(OUT_FOLDER);

		if (!imageFolder.exists() || !imageFolder.isDirectory()){
			JOptionPane.showMessageDialog(frame, "Folder must be created : " + IMAGE_FOLDER, "Inane error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		if (!spriteFolder.exists() || !spriteFolder.isDirectory()){
			JOptionPane.showMessageDialog(frame, "Folder must be created : " + SPRITE_FOLDER, "Inane error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		if (!outFolder.exists() || !outFolder.isDirectory()){
			JOptionPane.showMessageDialog(frame, "Folder must be created : " + OUT_FOLDER, "Inane error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		int count = 0;
		// image pattern : sprite-name_animation-type_number.png
		for(String filename : imageFolder.list()){
			String type = filename.split("_")[1];
			int number = Integer.valueOf(filename.split("_")[2].replace(".png", ""));
			if (!sprites.containsKey(type))
				sprites.put(type, new BufferedImage[frames]);
			BufferedImage image = ImageIO.read(new File(IMAGE_FOLDER + filename));
			
			/*
			if (image.getWidth() != width){
				JOptionPane.showMessageDialog(frame, "Width is not equal to " + width + " for image " + filename, "Inane error", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			if (image.getHeight() != height){
				JOptionPane.showMessageDialog(frame, "Height is not equal to " + height + " for image " + filename, "Inane error", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			
			*/
			image = resizeImg(image, 64, 64);
			sprites.get(type)[number-1] = image;
			count ++;
		}
		if (count != frames*animations){
			JOptionPane.showMessageDialog(frame, count + " files found ! Expected " + frames*animations + " !", "Inane error", JOptionPane.ERROR_MESSAGE);
		}
		
		width = 64;
		height = 64;
		
		for (String type : sprites.keySet()){
			BufferedImage img = new BufferedImage(width*frames, height, BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < frames; i++){
				img.createGraphics().drawImage(sprites.get(type)[i], i*width, 0, null);
			}
			File final_image = new File(SPRITE_FOLDER + name + "_" + type + ".png");
			ImageIO.write(img, "png", final_image);
		}
		count = 0;
		BufferedImage img = new BufferedImage(width*frames, height*animations, BufferedImage.TYPE_INT_ARGB);
		for (String type : sprites.keySet()){
			BufferedImage image = ImageIO.read(new File(SPRITE_FOLDER + name + "_" + type + ".png"));
			img.createGraphics().drawImage(image, 0, height*count, null);
			count ++;
		}
		File final_image = new File(OUT_FOLDER + name + ".png");
		ImageIO.write(img, "png", final_image);
		JOptionPane.showMessageDialog(frame, "Sprite sheet created : " + OUT_FOLDER + name + ".png", "Job done !", JOptionPane.INFORMATION_MESSAGE);
	}
}
