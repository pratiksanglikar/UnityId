/**
 * 
 */
package com.pratiksanglikar.unityid.imagegenerator;

import java.io.IOException;
import java.util.List;

/**
 * The interface defined the method to generate a random image with required specifications.
 * @author Pratik Sanglikar
 *
 */
public interface RandomImageGenerator {
	
	/**
	 * The method generates an image with specified height, width and the RGB values.
	 * 
	 * @param height height of the required image.
	 * @param width width of the required image.
	 * @param randomSequence the list of the RGB values for each pixel in the image. 
	 * 
	 * @throws InsufficientRandomValuesException when provided RGB pixel values are not sufficient for generating the image.
	 * @throws IOException when there is problem writing the image to the disk. 
	 */
	public void generateRandomImage(int height, int width, List<Integer> randomSequence)
			throws InsufficientRandomValuesException, IOException;
}
