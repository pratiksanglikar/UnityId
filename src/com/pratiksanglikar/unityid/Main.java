/**
 * 
 */
package com.pratiksanglikar.unityid;

import java.io.IOException;
import java.util.List;

import com.pratiksanglikar.unityid.imagegenerator.InsufficientRandomValuesException;
import com.pratiksanglikar.unityid.imagegenerator.RandomImageGenerator;
import com.pratiksanglikar.unityid.imagegenerator.RandomJPGImageGenerator;
import com.pratiksanglikar.unityid.randomnumber.HttpRandomSequenceGenerator;
import com.pratiksanglikar.unityid.randomnumber.NativeRandomSequenceGenerator;
import com.pratiksanglikar.unityid.randomnumber.RandomSequenceGenerator;

/**
 * @author Pratik Sanglikar
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int height = 128, width = 128;
//		RandomSequenceGenerator sequenceGenerator = new NativeRandomSequenceGenerator();
		RandomSequenceGenerator sequenceGenerator = new HttpRandomSequenceGenerator();
		RandomImageGenerator imageGenerator = new RandomJPGImageGenerator();
		try {
			List<Integer> randomNumbers = sequenceGenerator.generateRandomSequence(height * width * 3, 0, 256);
			imageGenerator.generateRandomImage(height, width, randomNumbers);
		} catch (InsufficientRandomValuesException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
			System.exit(2);
		}
	}

}
