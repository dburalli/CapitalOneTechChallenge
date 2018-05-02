package webServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import stockTicker.Company;
import stockTicker.Stock;

public class ApiController {
	
	public static void setStockDataFromAPI(Company company, String startDate, String endDate){
		

			
		  try {
			  
			//Create a map to hold the tokens passed in from the tokenizer (i.e. API key and connection string vars)
			// make final because they shouldn't be altered
			final Map<String, String> urlTokens = Tokenizer.fileTokenizer();
			String connectionURL = urlBuilder(company.tickerSymbol, startDate, endDate, urlTokens);

			URL url = new URL(connectionURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/csv");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			//read the headers because we don't need them
			br.readLine();
			
			//find the average volume while inputing to avoid an extra O(n) loop
			//only disadvantage is that you're adding logic in an API call
			//is the trade off worth it?
			double count = 0.0;
			double totalVolume = 0.0;

			//loop through each line of the returned CSV
			while ((output = br.readLine()) != null) {
				//this is going to break if there is no data, or the values change on their table
				String[] parsedData = output.split(",");
				try{
					//add the stock to the list
					Stock stock = new Stock(parsedData[0],parsedData[1],parsedData[4],parsedData[5],company.tickerSymbol);
					company.historicTickerData.add(stock);
					//this logic adds to the total of VOLUME to get the average
					totalVolume = totalVolume + Double.parseDouble(parsedData[5]);
				}catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Malformed CSV Row");
				}
				//increase count of dailyVolumes added to get the average
				count++;
			}
			//sets the average volume for the company
			company.setAverageVolume(totalVolume/count);

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {
			  
			e.printStackTrace();
			
		  } catch (NullPointerException e) {
			  //incase any of the file read in is bad
			  e.printStackTrace();		  
		  }
		  
		}
		
		public static String urlBuilder(String tickerSymbol, String startTime, String endTime, Map<String, String> urlTokens) throws NullPointerException{
		
			return urlTokens.get("BASE_URL")+tickerSymbol+urlTokens.get("FILE_FORMAT")+
					urlTokens.get("START_DATE")+startTime+urlTokens.get("END_DATE")+endTime+
					urlTokens.get("API_KEY")+urlTokens.get("QUANDL_API_KEY");
			
		}
		
}
