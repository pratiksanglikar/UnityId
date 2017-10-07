/**
 * 
 */
package com.pratiksanglikar.unityid.randomnumber;

import java.io.IOException;
import java.util.List;

/**
 * @author Pratik Sanglikar
 *
 */
public interface RandomSequenceGenerator {

	public List<Integer> generateRandomSequence(int sequenceLength, int min, int max) throws IOException;
}
