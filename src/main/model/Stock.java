package model;

import java.util.Scanner;

public class Stock {
    //Fields:
    private String symbol;
    private double stockPriceCurrent;
    private double stockPricePrevious;
    private double currentInvestmentWorth;
    private double initialInvestment;
    private double sharesBought;
    private int daysToInvest;
    private int risk; //from 1 - 10, 10 being riskiest
//    private int publicPerception; // from 1- 10, 10 being the best. MAY add later
//    private boolean positiveNews; // true if positive news. MAY add later
//    private boolean negativeNews; // false if negative news MAY add later
    private double marketCap; //
    private Scanner input;

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

    public double getInitialInvestment() {
        return initialInvestment;
    }

    public int getDaysToInvest() {
        return daysToInvest;
    }

    public int getRisk() {
        return this.risk;
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

    public Stock() {
        input = new Scanner(System.in);
    }

    public void addInvestmentAmount(double amount) {
        currentInvestmentWorth += amount;
    }

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

            currentInvestmentWorth = stockPriceCurrent * sharesBought;
        }

    }

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


}
