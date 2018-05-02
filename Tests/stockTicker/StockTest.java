package stockTicker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StockTest {

	@Test
	void testStock() {
		
		//happy path
		Stock stock1 = new Stock("2017-01-03","800.62","808.01","1959000","GOOGL");
		
		assertEquals("2017-01-03",stock1.getStockDate());
		assertEquals(800.62,stock1.getDailyOpen());
		assertEquals(808.01,stock1.getDailyClose());
		assertEquals(1959000.0,stock1.getDailyVolume());
		assertEquals("GOOGL",stock1.getStockSymbol());
		
		//wanted to check nulls, see Stock Class for details
		//need to test double max sizes
		//Zero tests
	}

}
