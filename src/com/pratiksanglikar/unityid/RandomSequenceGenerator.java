/**
 * 
 */
package com.pratiksanglikar.unityid;

import java.util.List;

/**
 * @author Pratik Sanglikar
 *
 */
public interface RandomSequenceGenerator {

	public List<Integer> generateRandomSequence(int sequenceLength, int min, int max);
}
