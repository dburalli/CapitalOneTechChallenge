package stockTicker;

import static org.junit.jupiter.api.Assertions.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CompanyTest {

	@Test
	void happyPathTest() {
		DecimalFormat dollarFormat = new DecimalFormat("#.##");
		dollarFormat.setRoundingMode(RoundingMode.CEILING);
		
		//Build Google Company Object
		//Build ArrayList of Stocks
		Stock stock1 = new Stock("2017-01-03","800.62","808.01","1959000","GOOGL");
		Stock stock2 = new Stock("2017-01-04","809.89","807.77","1515300","GOOGL");
		//stock 3 is 10% + volumeAverage volume
		Stock stock3 = new Stock("2017-01-05","807.50","813.02","1886830","GOOGL");
		ArrayList<Stock> testStock = new ArrayList<Stock>();
		Company google = new Company("Alphabet Corporation","GOOGL",testStock);
		
		//Build happy path object
		google.getHistoricTickerData().add(stock1);
		
		assertEquals("Alphabet Corporation", google.getName());
		assertEquals("GOOGL", google.getTickerSymbol());
		assertEquals("2017-01-03", google.getHistoricTickerData().get(0).getStockDate());
		assertEquals(800.62,google.getHistoricTickerData().get(0).getDailyOpen());
		assertEquals(808.01,google.getHistoricTickerData().get(0).getDailyClose());
		assertEquals(1959000,google.getHistoricTickerData().get(0).getDailyVolume());
		
		//add second stock to get averages data
		google.getHistoricTickerData().add(stock2);
		google.getHistoricTickerData().add(stock3);
		
		//Test findMonthlyAverages
		double openAverage = ((stock1.getDailyOpen()+stock2.getDailyOpen()+stock3.getDailyOpen())/(double)google.getHistoricTickerData().size());
		double closeAverage = ((stock1.getDailyClose()+stock2.getDailyClose()+stock3.getDailyClose())/(double)google.getHistoricTickerData().size());
		
		//returns string with the monthly averages for open and close
		assertEquals("Month: " + stock1.getStockDate().substring(0,7) + " total for " + google.getName() + " average opening: $" + dollarFormat.format(openAverage) + " average closing: $" + dollarFormat.format(closeAverage),google.calculateMonthlyAverages("01"));
		
		//calculate monthly average for a month not in the dataset
		assertEquals("Month: " + "invalid".substring(0,7) + " total for " + google.getName() + " average opening: $0 average closing: $0" ,google.calculateMonthlyAverages("09"));

		
		//other things to test:
		//passing in various datatypes from API to create a stock, though they should all be String since its reading CSV
		//need 0.0 tests for double types
		//need max double tests
		
	}
	
	@Test
	void nullTest() {
		//Build Company object with nulls
		Company company = new Company(null,null,null);
		//Assert handling null datapoints
		assertEquals(null, company.getName());
		assertEquals(0.0, company.getAverageVolume());
		assertEquals(null, company.getTickerSymbol());
		assertEquals(null, company.getHistoricTickerData());
	
	}
	
	@Test
	void testVolumeAverage() {
		double volumeAverage = 1715300;
		DecimalFormat dollarFormat = new DecimalFormat("#.##");
		dollarFormat.setRoundingMode(RoundingMode.CEILING);
		
		//Build Google Company Object
		//Build ArrayList of Stocks
		Stock stock1 = new Stock("2017-01-03","800.62","808.01","1959000","GOOGL");
		Stock stock2 = new Stock("2017-01-04","809.89","807.77","1515300","GOOGL");
		//stock 3 is 10% + volumeAverage volume
		Stock stock3 = new Stock("2017-01-05","807.50","813.02","1886830","GOOGL");
		ArrayList<Stock> testStock = new ArrayList<Stock>();
		Company google = new Company("Alphabet Corporation","GOOGL",testStock);
		
		//Build happy path object
		google.getHistoricTickerData().add(stock1);
		
		//add second stock to get averages data
		google.getHistoricTickerData().add(stock2);
		//add the volume average calculated in the API
		google.setAverageVolume(volumeAverage);
		//10% > than average Volume function
		assertEquals("GOOGL Average Volume: 1715300\n" + 
				"GOOGL 2017-01-03 1959000\n",google.findHighVolumeDays());
		
		//adding exactly 10% + average volume to volume to test its > 10%
		google.getHistoricTickerData().add(stock3);
		assertEquals("GOOGL Average Volume: 1715300\n" + 
				"GOOGL 2017-01-03 1959000\n",google.findHighVolumeDays());
		
	}
	
	@Test
	void testMaxDailyProfit() {
		DecimalFormat dollarFormat = new DecimalFormat("#.##");
		dollarFormat.setRoundingMode(RoundingMode.CEILING);
		
		//Build Google Company Object
		//Build ArrayList of Stocks
		Stock stock1 = new Stock("2017-01-03","800.62","808.01","1959000","GOOGL");
		Stock stock2 = new Stock("2017-01-04","809.89","807.77","1515300","GOOGL");
		//stock 3 is 10% + volumeAverage volume
		ArrayList<Stock> testStock = new ArrayList<Stock>();
		Company google = new Company("Alphabet Corporation","GOOGL",testStock);
		
		//Build happy path object
		google.getHistoricTickerData().add(stock1);
		
		//add second stock to get averages data
		google.getHistoricTickerData().add(stock2);
		//add the volume average calculated in the API
		
		//Test findMaxDailyProfit
		//stock1 has the highest difference
		assertEquals("Highest Yield Date For " + google.getTickerSymbol() + ": " +
		stock1.getStockDate() + " $" + dollarFormat.format(stock1.getDailyClose()-stock1.getDailyOpen()),google.findMaxDailyProfit());
	}

}

