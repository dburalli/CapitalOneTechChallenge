package stockTicker;

import java.util.*;
import webServices.ApiController;

public class StockLogicController {

	public static void main(String[] args) {
		//Following steps need to take place:
		//create 3 Arraylists for each stock ticker (COF, GOOGL, and MSFT)
		//use API to gather the data from Jan - June of 2017
		// sample output {"GOOGL": {"month":"2017-01", "average_open": "815.43", "average_close": "$818.34"}
		
		//need to confirm open/close or adj. open/close
		
		//Create Company object to control each piece
		ArrayList<Stock> googleStockData = new ArrayList<Stock>();
		ArrayList<Stock> microsoftStockData = new ArrayList<Stock>();
		ArrayList<Stock> capitalOneFinanceStockData = new ArrayList<Stock>();
		
		//date range required
		final String START_DATE = "2017-01-01";
		final String END_DATE = "2017-06-30";
		
		//Create each company object 
		//Could read from the WIKI file index to create an object for each company
		//could also allow users to input the Ticker Symbol and check it against the list 
		ArrayList<Company> companyList = new ArrayList<Company>();
		Company google = new Company("Alphabet Corp.", "GOOGL", googleStockData);
		Company microsoft = new Company("Microsoft Corp.", "MSFT", microsoftStockData);
		Company capitalOneFinance = new Company("Capital One Finance", "COF", capitalOneFinanceStockData);
		//add companies to list of companies to loops through
		companyList.add(google);
		companyList.add(microsoft);
		companyList.add(capitalOneFinance);
		
		
		//call the API for each company to fill in the stock objects for each days data
		for(Company company: companyList) {
			ApiController.setStockDataFromAPI(company, START_DATE, END_DATE);
		}	
		
		int choice = 0;
		//User prompt
		while (choice != 4) {
			Scanner reader = new Scanner(System.in);
			System.out.println("Please enter a number corresonding with one of the following choices:");
			System.out.println("1 - Display The Average Monthly Opening And Closing Prices");
			System.out.println("2 - Display Data That Illustrates The Day Yielding The Largest Gain");
			System.out.println("3 - Display Data Of Days With Volume Trade Over 10%");
			System.out.println("4 - Exit Application");
			
			//make sure its an int we are reading in, otherwise print out error message
			try {
				choice = reader.nextInt();
				if (choice < 1 || choice > 4) {
					System.out.println("Please enter a valid choice");
				}
			}catch(InputMismatchException e) {
				System.out.println("Please enter a valid choice");
			}
			
			//control flow for user choice
			switch(choice) {
			case 1: displayAverages(companyList);
				break;
			case 2: displayGreatestGain(companyList);
				break;
			case 3: displayHighVolumeDays(companyList);
				break;
			case 4: System.out.println("Goodbye");
				reader.close();
				System.exit(0);
				break;
			default: System.out.println("Invalid choice, please choose 1 - 4");
				break;
			}
		}
		
		
		//cycle through list of companies
		
		//System.out.println(google.calculateMonthlyAverages("06"));
		//System.out.println(google.findHighVolumeDays());

		
		
	}
	
	public static void displayAverages(ArrayList<Company> companyList) {
		for(Company company: companyList) {
		//for each company, get each month, 1 - 6
			for(int i = 1; i <= 6; i++) {
				System.out.println(company.calculateMonthlyAverages("0" + Integer.toString(i)));
			}
		System.out.println("________________________________________________");
		}
		System.out.println("\n\n\n");
	}
	
	public static void displayGreatestGain(ArrayList<Company> companyList) {
		for(Company company: companyList) {
					System.out.println(company.findMaxDailyProfit());
			}
		System.out.println("\n\n\n");
	}
	
	public static void displayHighVolumeDays(ArrayList<Company> companyList) {
		for(Company company: companyList) {
			System.out.println(company.findHighVolumeDays());
			System.out.println("________________________________________________");
		}
		System.out.println("\n\n\n");
	}
	
}
