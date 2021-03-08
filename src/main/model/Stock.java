package model;

import org.json.JSONObject;
import persistence.Writable;


// Class stock represents a stock by storing its symbol, current stock price, stock price when bought, amount of money
// invested in the stock, market cap of stock, how risky the stock is, amount of share bought in stock, and how many
// days the user would like to invest this stock

// CITATION: Note the methods toJson are  modified versions of similar
// methods found in the CPSC 210 JsonSerializationDemo project
public class Stock implements Writable {
    //Fields:
    private String symbol;
    private double stockPriceCurrent;
    private double stockPricePrevious;
    private double currentInvestmentWorth;
    private double initialInvestment;
    private double sharesBought;
    private int daysToInvest;
    private int risk; //from 1 - 5, 5 being riskiest
//    private int publicPerception; // from 1- 10, 10 being the best. MAY add later
//    private boolean positiveNews; // true if positive news. MAY add later
//    private boolean negativeNews; // false if negative news MAY add later
    private double marketCap;

    public Stock() {
        this.symbol = "";
        this.stockPriceCurrent = 0.00;
        this.stockPricePrevious = 0.00;
        this.currentInvestmentWorth = 0.00;
        this.initialInvestment = 0.00;
        this.sharesBought = 0;
        this.daysToInvest = 0;
        this.risk = 1;
        this.marketCap = 10000000;
    }

    public Stock(String symbol, double stockPriceCurrent, double stockPricePrevious, double currentInvestmentWorth,
                 double initialInvestment, double sharesBought, int daysToInvest, int risk, double marketCap) {
        this.symbol = symbol;
        this.stockPriceCurrent = stockPriceCurrent;
        this.stockPricePrevious = stockPricePrevious;
        this.currentInvestmentWorth = currentInvestmentWorth;
        this.initialInvestment = initialInvestment;
        this.sharesBought = sharesBought;
        this.daysToInvest = daysToInvest;
        this.risk = risk;
        this.marketCap = marketCap;
    }

    //Getters:
    public String getSymbol() {
        return symbol;
    }

    public double getStockPriceCurrent() {
        return stockPriceCurrent;
    }

    public double getStockPricePrevious() {
        return stockPricePrevious;
    }

    public double getCurrentInvestmentWorth() {
        return currentInvestmentWorth;
    }

    public double getSharesBought() {
        return sharesBought;
    }

    public int getRisk() {
        return risk;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public double getInitialInvestment() {
        return initialInvestment;
    }

    public int getDaysToInvest() {
        return daysToInvest;
    }


    //Setters:
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setStockPriceCurrent(double price) {
        this.stockPriceCurrent = price;
    }

    public void setStockPricePrevious(double price) {
        this.stockPricePrevious = price;
    }

    public void setCurrentInvestmentWorth(double amount) {
        this.currentInvestmentWorth = amount;
    }

    public void setDaysToInvest(int days) {
        this.daysToInvest = days;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    //Methods:

    // MODIFIES: this
    // EFFECTS: increases current investment in stock by inputted amount
    public void addInvestmentAmount(double amount) {
        currentInvestmentWorth += amount;
    }

    // MODIFIES: this
    // EFFECTS: invests stock for amount of days
    //              - takes into account risk (riskier has more potential for more loss or more gain)
    //              - max stock price cannot surpass marketCap
    public void investIndividualStock() {
        sharesBought = currentInvestmentWorth / stockPriceCurrent;
        initialInvestment = currentInvestmentWorth;
        setStockPricePrevious(stockPriceCurrent);

        for (int i = 0; i < daysToInvest; i++) {

            double riskFactor = getRiskFactor();
            double max = stockPriceCurrent + (stockPriceCurrent * riskFactor);

            if (max > marketCap) {
                max = marketCap;
            }

            double min = stockPriceCurrent - (stockPriceCurrent * riskFactor); ///fix

            stockPriceCurrent = (Math.random() * (max - min) + min);

            if (stockPriceCurrent < 0) {
                stockPriceCurrent = 0.00;
            }

            currentInvestmentWorth = stockPriceCurrent * sharesBought;
        }

    }

    // EFFECTS: returns a riskFactor based on the amount of risk the stock has. More risk factor for riskier stock
    public double getRiskFactor() {
        if (risk == 1) {
            return 0.05;
        } else if (risk == 2) {
            return 0.1;
        } else if (risk == 3) {
            return 0.2;
        } else if (risk == 4) {
            return 0.3;
        } else {
            return 0.5;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("symbol", symbol);
        json.put("stock price current", stockPriceCurrent);
        json.put("stock price previous", stockPricePrevious);
        json.put("current investment worth", currentInvestmentWorth);
        json.put("initial investment", initialInvestment);
        json.put("shares bought", sharesBought);
        json.put("days to invest", daysToInvest);
        json.put("risk", risk);
        json.put("market cap", marketCap);
        return json;
    }
}
