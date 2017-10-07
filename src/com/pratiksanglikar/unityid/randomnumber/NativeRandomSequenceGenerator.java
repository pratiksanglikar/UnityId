/**
 * 
 */
package com.pratiksanglikar.unityid.randomnumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Pratik Sanglikar
 *
 */
public class NativeRandomSequenceGenerator implements RandomSequenceGenerator {

	/* (non-Javadoc)
	 * @see com.pratiksanglikar.unityid.RandomSequenceGenerator#generateRandomSequence(int, int, int)
	 */
	@Override
	public List<Integer> generateRandomSequence(int sequenceLength, int min, int max) {
		Random rand = new Random();
		List<Integer> randomNumbers = new ArrayList<Integer>();
		for(int i=0;i<sequenceLength;i++) {
			randomNumbers.add(rand.nextInt(max) + min);
		}
		return randomNumbers;
	}

}
