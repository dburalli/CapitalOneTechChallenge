package stockTicker;

public class Stock {
	
	public String stockSymbol;
	String stockDate;
	double dailyOpen;
	double dailyClose;
	public int dailyVolume;

	
	public Stock(String stockDate, String dailyOpen, String dailyClose, String dailyVolume) {
		this.stockDate = stockDate;
		this.dailyOpen = Double.parseDouble(dailyOpen);
		this.dailyClose = Double.parseDouble(dailyClose);
		this.dailyVolume = Integer.parseInt(dailyVolume);
	}
	
	public void setStockSymbol(String name) {
		stockSymbol = name;
	}
	
	
}
