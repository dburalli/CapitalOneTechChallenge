package stockTicker;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

//this Company object represents an entire entity, and contains a list of Stocks and their daily values
//stock could be stored in this class, but the code looks more organized in a separate class/same package
public class Company {
	
	public ArrayList<Stock> historicTickerData = new ArrayList<Stock>();
	public String name;
	public String tickerSymbol;
	public double averageVolume;

	//a setter called from APIController.java to set the average
	public void setAverageVolume(double averageVolume) {
		this.averageVolume = averageVolume;
	}

	//setting the formats of numbers to beautify them
	DecimalFormat dollarFormat = new DecimalFormat("#.##");
	DecimalFormat volumeFormat = new DecimalFormat("##############");

	//Constructor
	public Company(String name, String tickerSymbol, ArrayList<Stock> historicTickerData) {
		this.name = name;
		this.tickerSymbol = tickerSymbol;
		this.historicTickerData = historicTickerData;
		//this will later set the averages
		dollarFormat.setRoundingMode(RoundingMode.CEILING);
	}

	
	public String calculateMonthlyAverages(String month) throws ArithmeticException{
		double closeTotal = 0.0;
		double openTotal = 0.0;
		double count = 0.0;
		String date = "invalid";
		//cycle through each stock in the company's arraylist, not ideal if its a long list though
		for(Stock stock: historicTickerData) {
			//find the months that correspond to the one passed in
			if(stock.stockDate.split("-")[1].equals(month)) {
				closeTotal = closeTotal + stock.dailyClose;
				openTotal = openTotal + stock.dailyOpen;
				count++;
				date = stock.stockDate;
			}
		}
		//calculate averages, total/number of entries
		closeTotal = closeTotal/count;
		openTotal = openTotal/count;
		
		//output string
		return ("Month: " + date.substring(0,7) + " total for " + name + " average opening: $" + dollarFormat.format(openTotal) + " average closing: $" + dollarFormat.format(closeTotal));
	}
	
	public String findMaxDailyProfit() {
		double maxDifference = 0.0;
		String maxDifferenceDate = "invalid";
		//for each stock in the company's arrayList, get the close - open to find the difference and record the max day profit
		for(Stock stock: historicTickerData) {
			if(stock.dailyClose - stock.dailyOpen > maxDifference) {
				maxDifference = stock.dailyClose - stock.dailyOpen;
				maxDifferenceDate = stock.stockDate;
			}
		}
		return ("Highest Yield Date For " + tickerSymbol + ": " + maxDifferenceDate + " $" + dollarFormat.format(maxDifference));
	}
	
	public String findHighVolumeDays() throws ArithmeticException{
		//Display initial average
		String volumeDays = tickerSymbol + " Average Volume: " +  volumeFormat.format(averageVolume) + "\n";
		//loop through each company's arraylist to compare the average volume to the daily volume and print it out if its higher than average + 10%
		for(Stock stock: historicTickerData) {
			System.out.println(stock.dailyVolume);
			//dailyVolume - average / |average| * 100
			 if(((stock.dailyVolume - averageVolume)/Math.abs(averageVolume) )* 100 > 10) {
				 //Please display the ticker symbol, date, and volume for each day 
				volumeDays += stock.stockSymbol + " " + stock.stockDate + " " + volumeFormat.format(stock.dailyVolume) + "\n";
			}
		}
		return volumeDays;
	}

}
