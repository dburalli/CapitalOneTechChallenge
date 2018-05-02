package stockTicker;

//This Stock object represents a daily stock data item and the values pertaining to it
public class Stock {
	
	String stockSymbol;
	String stockDate;
	double dailyOpen;
	double dailyClose;
	double dailyVolume;

	
	public Stock(String stockDate, String dailyOpen, String dailyClose, String dailyVolume, String stockSymbol) {

		//not sure how to handle potential nullPointer,
		//Since we are doing averages it is important to not miss one in a series
		//Would it be better to drop the entire month, or just start over?
		this.stockDate = stockDate;
		this.dailyOpen = Double.parseDouble(dailyOpen);
		this.dailyClose = Double.parseDouble(dailyClose);
		this.dailyVolume = Double.parseDouble(dailyVolume);
		this.stockSymbol = stockSymbol;
	}
	
}
