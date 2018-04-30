package stockTicker;

public class Stock {
	
	public String stockSymbol;
	String stockDate;
	double dailyOpen;
	double dailyClose;

	
	public Stock(String stockDate, String dailyOpen, String dailyClose) {
		this.stockDate = stockDate;
		this.dailyOpen = Double.parseDouble(dailyOpen);
		this.dailyClose = Double.parseDouble(dailyClose);
	}
	
	public void setStockSymbol(String name) {
		stockSymbol = name;
	}
	
	
}
