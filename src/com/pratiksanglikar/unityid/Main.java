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
 * This class is the main entry point of the program.
 * This class initializes the RandomImageGenerators and RandomSequenceGenerators to be used.
 * 
 * @author Pratik Sanglikar
 *
 */
public class Main {

	/**
	 * Main program.
	 * 
	 * @param args it accepts two optional parameters as - height and width of the generated image.
	 * <b>Note: </b> One should either provide both width and height or none.
	 */
	public static void main(String[] args) {
		int height = 128, width = 128;
		if(args.length >= 2) {
			height = Integer.parseInt(args[0]);
			width = Integer.parseInt(args[1]);
		}
		RandomSequenceGenerator sequenceGenerator = new HttpRandomSequenceGenerator();
		//RandomSequenceGenerator sequenceGenerator = new NativeRandomSequenceGenerator();
		RandomImageGenerator imageGenerator = new RandomJPGImageGenerator();
		try {
			List<Integer> randomNumbers = sequenceGenerator.generateRandomSequence(height * width, 0, 1000000000);
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
