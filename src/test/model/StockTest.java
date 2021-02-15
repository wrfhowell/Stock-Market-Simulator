package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    private Stock testStock;

    @BeforeEach
    void runBefore() {
        testStock = new Stock();
        testStock.setCurrentInvestmentWorth(500);
        testStock.setDaysToInvest(1);
        testStock.setMarketCap(1000000);
        testStock.setRisk(3);
        testStock.setStockPriceCurrent(123);
        testStock.setSymbol("AAPL");
    }

    @Test
    void testGetDaysToInvest() {
        assertEquals(1, testStock.getDaysToInvest());
    }

    @Test
    void testAddInvestmentAmount() {
        testStock.addInvestmentAmount(150);
        assertEquals(650, testStock.getCurrentInvestmentWorth());
    }

    @Test
    void testGetRiskFactor() {
        testStock.setRisk(1);
        assertEquals(0.05, testStock.getRiskFactor());

        testStock.setRisk(2);
        assertEquals(0.1, testStock.getRiskFactor());

        testStock.setRisk(3);
        assertEquals(0.2, testStock.getRiskFactor());

        testStock.setRisk(4);
        assertEquals(0.3, testStock.getRiskFactor());

        testStock.setRisk(5);
        assertEquals(0.5, testStock.getRiskFactor());
    }

    @Test
    void testInvestIndividualStockMaxMarketCap() {
        // test many times to account for randomness
        for (int i = 0; i < 20; i++) {
            runBefore();

            testStock.setMarketCap(testStock.getStockPriceCurrent());

            testStock.investIndividualStock();

            assertEquals(500, testStock.getInitialInvestment());
            assertTrue(testStock.getStockPriceCurrent() <= testStock.getStockPricePrevious() &&
                    testStock.getStockPriceCurrent() >= 98.4);
        }
    }

    @Test
    void testInvestIndividualStockMaxNotMarketCap() {
        for (int i = 0; i < 20; i++) {
            runBefore();

            testStock.investIndividualStock();

            assertEquals(500, testStock.getInitialInvestment());
            assertTrue(testStock.getStockPriceCurrent() <= 147.6 &&
                    testStock.getStockPriceCurrent() >= 98.4);
            assertTrue(400 <= testStock.getCurrentInvestmentWorth() &&
                    testStock.getCurrentInvestmentWorth() <= 600);
        }
    }

    @Test
    void testInvestIndividualStockManyDays() {
        for (int i = 0; i < 20; i++) {
            runBefore();

            testStock.setDaysToInvest(3);
            testStock.investIndividualStock();

            assertEquals(500, testStock.getInitialInvestment());

            assertTrue(testStock.getStockPriceCurrent() <= 212.544 &&
                    testStock.getStockPriceCurrent() >= 62.976);
            assertTrue(864 >= testStock.getCurrentInvestmentWorth() &&
                    testStock.getCurrentInvestmentWorth() >= 256);
        }
    }
}