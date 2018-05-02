package stockTicker;

import static org.junit.jupiter.api.Assertions.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CompanyTest {

	@Test
	void test() {
		//Build Company object with nulls
		Company company = new Company(null,null,null);
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
		
		
		//Assert handling null datapoints
		assertEquals(null, company.name);
		assertEquals(0.0, company.averageVolume);
		assertEquals(null, company.tickerSymbol);
		assertEquals(null, company.historicTickerData);
		
		//Build happy path object
		google.historicTickerData.add(stock1);
		
		assertEquals("Alphabet Corporation", google.name);
		assertEquals("GOOGL", google.tickerSymbol);
		assertEquals("2017-01-03", google.historicTickerData.get(0).stockDate);
		assertEquals(800.62,google.historicTickerData.get(0).dailyOpen);
		assertEquals(808.01,google.historicTickerData.get(0).dailyClose);
		assertEquals(1959000,google.historicTickerData.get(0).dailyVolume);
		
		//add second stock to get averages data
		google.historicTickerData.add(stock2);
		//add the volume average calculated in the API
		google.setAverageVolume(volumeAverage);
		//10% > than average Volume function
		assertEquals("GOOGL Average Volume: 1715300\n" + 
				"GOOGL 2017-01-03 1959000\n",google.findHighVolumeDays());
		
		//adding exactly 10% + average volume to volume to test its > 10%
		google.historicTickerData.add(stock3);
		assertEquals("GOOGL Average Volume: 1715300\n" + 
				"GOOGL 2017-01-03 1959000\n",google.findHighVolumeDays());
		
		
		//Test findMaxDailyProfit
		//stock1 has the highest difference
		assertEquals("Highest Yield Date For " + google.tickerSymbol + ": " +
		stock1.stockDate + " $" + dollarFormat.format(stock1.dailyClose-stock1.dailyOpen),google.findMaxDailyProfit());
		
		//Test findMonthlyAverages
		double openAverage = ((stock1.dailyOpen+stock2.dailyOpen+stock3.dailyOpen)/(double)google.historicTickerData.size());
		double closeAverage = ((stock1.dailyClose+stock2.dailyClose+stock3.dailyClose)/(double)google.historicTickerData.size());
		
		//returns string with the monthly averages for open and close
		assertEquals("Month: " + stock1.stockDate.substring(0,7) + " total for " + google.name + " average opening: $" + dollarFormat.format(openAverage) + " average closing: $" + dollarFormat.format(closeAverage),google.calculateMonthlyAverages("01"));
		
		//calculate monthly average for a month not in the dataset
		assertEquals("Month: " + "invalid".substring(0,7) + " total for " + google.name + " average opening: $0 average closing: $0" ,google.calculateMonthlyAverages("09"));

		
		//other things to test:
		//passing in various datatypes from API to create a stock, though they should all be String since its reading CSV
		//need 0.0 tests for double types
		//need max double tests
		

	}

}

