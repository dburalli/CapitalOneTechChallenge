package stockTicker;

//This Stock object represents a daily stock data item and the values pertaining to it
public class Stock {

	private String stockSymbol;
	private String stockDate;
	private double dailyOpen;
	private double dailyClose;
	private double dailyVolume;

	
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
	
	//getters
	public String getStockSymbol() {
		return stockSymbol;
	}


	public String getStockDate() {
		return stockDate;
	}


	public double getDailyOpen() {
		return dailyOpen;
	}


	public double getDailyClose() {
		return dailyClose;
	}


	public double getDailyVolume() {
		return dailyVolume;
	}

	
}
