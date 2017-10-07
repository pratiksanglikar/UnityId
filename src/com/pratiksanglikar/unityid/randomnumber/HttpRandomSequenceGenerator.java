/**
 * 
 */
package com.pratiksanglikar.unityid.randomnumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pratik Sanglikar
 *
 */
public class HttpRandomSequenceGenerator implements RandomSequenceGenerator {

	/* (non-Javadoc)
	 * @see com.pratiksanglikar.unityid.randomnumber.RandomSequenceGenerator#generateRandomSequence(int, int, int)
	 */
	@Override
	public List<Integer> generateRandomSequence(int sequenceLength, int min, int max) throws IOException {
		List<Integer> randomNumbers = new ArrayList<Integer>(sequenceLength);
		
		while(sequenceLength > 0) {
			List<Integer> partialNumbers = null;
			try {
				partialNumbers = makeHttpRequest(min, max);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			randomNumbers.addAll(partialNumbers);
			sequenceLength -= 10000;
		}	
		return randomNumbers;
	}
	
	
	private List<Integer> makeHttpRequest(int min, int max) throws IOException, InterruptedException {
		List<Integer> partialNumbers = new ArrayList<Integer>(10000);
		String url = "https://www.random.org/integers/?num=10000&col=1&base=10&format=plain&rnd=new&min="+ min + "&max=" + max;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		Object body = con.getContent();
		if(responseCode != 200) {
			throw new IOException("HTTP request " + url + " failed with status: " + responseCode + " and message " + body);
		}

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			partialNumbers.add(Integer.parseInt(inputLine));
		}
		in.close();
		System.out.println("Request " + url + "success!");
		Thread.sleep(500);
		return partialNumbers;
	}

}
