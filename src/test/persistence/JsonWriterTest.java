package persistence;

import model.Stock;
import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation: note these tests are based on the CPSC 210 JsonSerializationDemo althought they have been
// significantly modified
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Portfolio portfolio = new Portfolio(1000, 3000);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Portfolio portfolio = new Portfolio(1000, 3000);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPortfolio.json");
            writer.open();
            writer.write(portfolio);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPortfolio.json");
            portfolio = reader.read();
            assertEquals(1000, portfolio.getBalance());
            assertEquals(3000, portfolio.getValueCurrentlyInvested());
            assertEquals(0, portfolio.getPortfolioList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Portfolio portfolio = new Portfolio(4000, 5000);
            Stock stock1 = new Stock("AAPL", 250, 250, 3000,
                    3000, 12, 0, 2, 30000000);
            Stock stock2 = new Stock("GOOGL", 320, 320, 2000,
                    2000, 6.25, 0, 5, 40000000);

            portfolio.addStock(stock1);
            portfolio.addStock(stock2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPortfolio.json");
            writer.open();
            writer.write(portfolio);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPortfolio.json");
            portfolio = reader.read();

            assertEquals(4000, portfolio.getBalance());
            assertEquals(5000, portfolio.getValueCurrentlyInvested());
            List<Stock> stockList = portfolio.getPortfolioList();
            assertEquals(2, stockList.size());
            checkStock("AAPL", 250, 250, 3000,
                    3000, 12, 0 ,2, 30000000, stockList.get(0));
            checkStock("GOOGL", 320, 320, 2000,
                    2000, 6.25, 0 ,5, 40000000, stockList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
