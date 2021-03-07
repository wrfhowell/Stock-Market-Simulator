package persistence;

import model.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkStock(String symbol, double stockPriceCurrent, double stockPricePrevious,
                              double currentInvestmentWorth, double initialInvestment, double sharesBought,
                              int daysToInvest, int risk, double marketCap, Stock stock) {
        assertEquals(symbol, stock.getSymbol());
        assertEquals(stockPriceCurrent, stock.getStockPriceCurrent());
        assertEquals(stockPricePrevious, stock.getStockPricePrevious());
        assertEquals(currentInvestmentWorth, stock.getCurrentInvestmentWorth());
        assertEquals(initialInvestment, stock.getInitialInvestment());
        assertEquals(sharesBought, stock.getSharesBought());
        assertEquals(daysToInvest, stock.getDaysToInvest());
        assertEquals(risk, stock.getRisk());
        assertEquals(marketCap, stock.getMarketCap());
        assertEquals(stockPriceCurrent, stock.getStockPriceCurrent());
    }
}
