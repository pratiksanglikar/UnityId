/**
 * 
 */
package com.pratiksanglikar.unityid.imagegenerator;

/**
 * The exception is thrown when the number of provided random numbers are not sufficient to generate the 
 * image of given size.
 * 
 * @author Pratik Sanglikar
 *
 */
public class InsufficientRandomValuesException extends Exception {
	
	private static final long serialVersionUID = -3342903148782206709L;

	public InsufficientRandomValuesException() {
		super();
	}
	
	public InsufficientRandomValuesException(String message) {
		super(message);
	}
}
