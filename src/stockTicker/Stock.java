package stockTicker;

public class Stock {
	
	public String stockSymbol;
	String stockDate;
	double dailyOpen;
	double dailyClose;
	double dailyVolume;

	
	public Stock(String stockDate, String dailyOpen, String dailyClose, String dailyVolume, String stockSymbol) {
		this.stockDate = stockDate;
		this.dailyOpen = Double.parseDouble(dailyOpen);
		this.dailyClose = Double.parseDouble(dailyClose);
		this.dailyVolume = Double.parseDouble(dailyVolume);
		this.stockSymbol = stockSymbol;
	}
	
}
