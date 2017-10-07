/**
 * 
 */
package com.pratiksanglikar.unityid.imagegenerator;

import java.io.IOException;
import java.util.List;

/**
 * @author Pratik Sanglikar
 *
 */
public interface RandomImageGenerator {
	public void generateRandomImage(int height, int width, List<Integer> randomSequence)
			throws InsufficientRandomValuesException, IOException;
}
