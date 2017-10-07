/**
 * 
 */
package com.pratiksanglikar.unityid.imagegenerator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * @author Pratik Sanglikar
 *
 */
public class RandomJPGImageGenerator implements RandomImageGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pratiksanglikar.unityid.imagegenerator.RandomImageGenerator#
	 * generateRandomImage(int, int, java.util.List)
	 */
	@Override
	public void generateRandomImage(int height, int width, List<Integer> randomSequence)
			throws InsufficientRandomValuesException, IOException {
		if (height * width * 3 > randomSequence.size()) {
			throw new InsufficientRandomValuesException("Image of width" + width + " and height " + height
					+ " can not built using " + randomSequence.size());
		}
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int alpha = 1;//randomSequence.get(i * j);
				int red = randomSequence.get(i * j);
				int green = randomSequence.get(i * j + 1);
				int blue = randomSequence.get(i * j + 2);
				int pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
				image.setRGB(i, j, pixel);
			}
		}
		writeImage(image);
	}

	private void writeImage(BufferedImage image) throws IOException {
		File imageFile = new File("image.jpg");
		ImageIO.write(image, "jpg", imageFile);
	}

}
