package stockTicker;

import java.util.ArrayList;

import webServices.ApiController;

public class StockLogicController {

	public static void main(String[] args) {
		//Following steps need to take place:
		//create 3 linked lists for each stock ticker (COF, GOOGL, and MSFT)
		//use API to gather the data from Jan - June of 2017
		// sample output {"GOOGL": {"month":"2017-01", "average_open": "815.43", "average_close": "$818.34"}
		
		//class objects - dailyStock = node(date, open, close)
		//need to confirm open/close or adj. open/close
		
		//Create Company object to control each piece
		ArrayList<Stock> googleStockData = new ArrayList<Stock>();
		ArrayList<Stock> microsoftStockData = new ArrayList<Stock>();
		ArrayList<Stock> capitalOneFinanceStockData = new ArrayList<Stock>();
		
		//Create each company object 
		//Could read from the WIKI file index to create an object for each company
		ArrayList<Company> companyList = new ArrayList<Company>();
		Company google = new Company("Alphabet Corp.", "GOOGL", googleStockData);
		Company microsoft = new Company("Microsoft Corp.", "MSFT", microsoftStockData);
		Company capitalOneFinance = new Company("Capital One Finance", "COF", capitalOneFinanceStockData);
		//add companies to list of companies to loops through
		companyList.add(google);
		companyList.add(microsoft);
		companyList.add(capitalOneFinance);
		
		//date range required
		String startDate = "2017-01-01";
		String endDate = "2017-06-30";
		
		for(Company company: companyList) {
			ApiController.setStockDataFromAPI(company, startDate, endDate);
		}		
		
		
		//cycle through list of companies
		for(Company company: companyList) {
			//for each company, get each month, 1 - 6
			for(int i = 1; i <= 6; i++) {
				System.out.println(company.calculateMonthlyAverages("0" + Integer.toString(i)));
			}
			System.out.println("________________________________________________");
		}
		
		
	}
	
}
