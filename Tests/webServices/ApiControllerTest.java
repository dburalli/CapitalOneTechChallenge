package webServices;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ApiControllerTest {
	String QUANDL_API_KEY = "YourKey";
	String BASE_URL = "https://www.quandl.com/api/v3/datasets/WIKI/";
	String FILE_FORMAT = ".csv?";
	String START_DATE = "start_date=";
	String END_DATE = "&end_date=";
	String API_KEY = "&api_key=";
	
	String tickerSymbol = "GOOGL";
	String startTime = "2017-01-01";
	String endTime = "2017-06-30";
	
	@Test
	void testUrlBuilder() {
		//create the hashmap for tokens
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("QUANDL_API_KEY",QUANDL_API_KEY);
		tokens.put("BASE_URL", BASE_URL);
		tokens.put("FILE_FORMAT", FILE_FORMAT);
		tokens.put("START_DATE", START_DATE);
		tokens.put("END_DATE", END_DATE);
		tokens.put("API_KEY", API_KEY);

		//happy path
		assertEquals("https://www.quandl.com/api/v3/datasets/WIKI/GOOGL.csv?" + 
				"start_date=2017-01-01&end_date=2017-06-30&api_key=YourKey",ApiController.urlBuilder(tickerSymbol,startTime,endTime,tokens));

		//Need a test for a bad API Key
				//Need a test for a Null value in the Map
				//How should these be handled? exit the program? try again? If its in production, should send message to team
		}
	
}
