/**
 * 
 */
package com.pratiksanglikar.unityid.randomnumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates the sequence of Random numbers using the HTTP call to the Random.org API.
 * 
 * @author Pratik Sanglikar
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
				partialNumbers = getFromRandomOrg(min, max);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			randomNumbers.addAll(partialNumbers);
			sequenceLength -= 10000;
		}	
		return randomNumbers;
	}
	
	/**
	 * gets the next 10000 random numbers in the sequence.
	 * @param min min number within the range.
	 * @param max max number within the range.
	 * @return List of 10000 random numbers.
	 * 
	 * @throws IOException if the network request fails.
	 * @throws InterruptedException if the thread is interrupted.
	 */
	private List<Integer> getFromRandomOrg(int min, int max) throws IOException, InterruptedException {
		if(!checkQuota()) {
			System.out.println("Quota exceeded! waiting for 10 minutes!");
			Thread.sleep(1000 * 60 * 10);
		}
		List<Integer> partialNumbers = new ArrayList<Integer>(10000);
		String url = "https://www.random.org/integers/?num=10000&col=1&base=10&format=plain&rnd=new&min="+ min + "&max=" + max;
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(makeGetRequest(url)));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			partialNumbers.add(Integer.parseInt(inputLine));
		}
		in.close();
		System.out.println("Request " + url + "success!");
		Thread.sleep(500);
		return partialNumbers;
	}
	
	/**
	 * Checks the quota from the random.org
	 * @return
	 * @throws IOException
	 */
	private boolean checkQuota() throws IOException {
		String url = "https://www.random.org/quota/?format=plain";
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(makeGetRequest(url)));
		int quota = Integer.parseInt(in.readLine());
		if(quota <= 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * makes a HTTP GET request to the specified URL and returns the reference to the InputStream of HTTP response.
	 * 
	 * @param url url to make the HTTP GET request to.
	 * @return InputStream of the response of the HTTP request.
	 * @throws IOException if the network request fails.
	 */
	private InputStream makeGetRequest(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		Object body = con.getContent();
		if(responseCode != 200) {
			throw new IOException("HTTP request " + url + " failed with status: " + responseCode + " and message " + body);
		}
		return con.getInputStream();
	}

}
