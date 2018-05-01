package stockTicker;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Company {
	
	public ArrayList<Stock> historicTickerData = new ArrayList<Stock>();
	public String name;
	public String tickerSymbol;
	DecimalFormat df = new DecimalFormat("#.##");

	
	public Company(String name, String tickerSymbol, ArrayList<Stock> historicTickerData) {
		this.name = name;
		this.tickerSymbol = tickerSymbol;
		this.historicTickerData = historicTickerData;
		//this will later set the averages
		df.setRoundingMode(RoundingMode.CEILING);
	}
	
	public String calculateMonthlyAverages(String month) throws ArithmeticException{
		double closeTotal = 0.0;
		double openTotal = 0.0;
		double count = 0.0;
		String date = "invalid";
		//cycle through each stock in the company, not ideal if its a long list though
		for(Stock stock: historicTickerData) {
			if(stock.stockDate.split("-")[1].equals(month)) {
				closeTotal = closeTotal + stock.dailyClose;
				openTotal = openTotal + stock.dailyOpen;
				System.out.println(stock.dailyClose);
				count++;
				date = stock.stockDate;
			}
		}
		//calculate averages, total/number of entries
		closeTotal = closeTotal/count;
		openTotal = openTotal/count;
		
		//output string
		return ("Month: " + date.substring(0,7) + " total for " + name + " average opening: $" + df.format(openTotal) + " average closing: $" + df.format(closeTotal));
	}
	
	public String findMaxDailyProfit() {
		double maxDifference = 0.0;
		String maxDifferenceDate = "invalid";
		
		for(Stock stock: historicTickerData) {
			if(stock.dailyClose - stock.dailyOpen > maxDifference) {
				maxDifference = stock.dailyClose - stock.dailyOpen;
				maxDifferenceDate = stock.stockDate;
			}
		}
		return ("Highest Yield Date For " + tickerSymbol + ": " + maxDifferenceDate + " $" + maxDifference);
	}
	
	public ArrayList<String> findHighVolumeDays() throws ArithmeticException{
		ArrayList<String> volumeDays = new ArrayList<String>();
		int count = 0;
		long total = 0;
		long average = 0;
		for(Stock stock: historicTickerData) {
			total = total + stock.dailyVolume;
			count++;
		}
		
		average = total/count;
		
		for(Stock stock: historicTickerData) {
			//add logic to see if daily volume is 10% higher than average
			//dailyVolume - average / |average| * 100
			 if((((double)stock.dailyVolume - (double)average)/Math.abs((double)average) )* 100 > 10) {
				
			}
			
		}
		return null;
	}

}
