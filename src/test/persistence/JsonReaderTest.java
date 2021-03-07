package persistence;


import model.Stock;
import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Portfolio portfolio = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPortfolio.json");
        try {
            Portfolio portfolio = reader.read();
            assertEquals(3000, portfolio.getBalance());
            assertEquals(0, portfolio.getPortfolioList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPortfolio.json");
        try {
            Portfolio portfolio = reader.read();
            assertEquals(6000 , portfolio.getBalance());
            List<Stock> stockList = portfolio.getPortfolioList();
            assertEquals(2, stockList.size());
            checkStock("AAPL", 532.3472160420449, 500, 1064.6944320840898, 1000, 2, 1, 2, 1.0E8, stockList.get(0));
            checkStock("GOOGLE", 307.92382151849904, 250, 3695.0858582219885, 3000, 12, 1, 5, 2.0E7, stockList.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
