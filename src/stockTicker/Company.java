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
	
	public String calculateMonthlyAverages(String month) {
		double closeTotal = 0.0;
		double openTotal = 0.0;
		double count = 0.0;
		String date = "invalid";
		//cycle through each stock in the company, not ideal if its a long list though
		for(Stock stock: historicTickerData) {
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
		
		return ("Month: " + date.substring(0,7) + " total for " + name + " average opening: $" + df.format(openTotal) + " average closing: $" + df.format(closeTotal));
	}

}
