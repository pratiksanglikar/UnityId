/**
 * 
 */
package com.pratiksanglikar.unityid.imagegenerator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Generates the Random JPG image of given height, width and the RGB values of
 * each pixel.
 * 
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
		if (height * width > randomSequence.size()) {
			throw new InsufficientRandomValuesException("Image of width" + width + " and height " + height
					+ " can not built using " + randomSequence.size());
		}
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int pixel = randomSequence.get(i * width + j);
				image.setRGB(i, j, pixel);
			}
		}
		writeImage(image);
	}

	/**
	 * writes the imageBuffer image to the disk.
	 * 
	 * @param image
	 * @throws IOException
	 */
	private void writeImage(BufferedImage image) throws IOException {
		File imageFile = new File("random-image-" + new Date().toString() + ".jpg");
		ImageIO.write(image, "jpg", imageFile);
	}

}
