package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio {

    // Fields:
    private double valueCurrentlyInvested;
    private double cash;
    ArrayList<Stock> portfolio;
    private Scanner input;

    // Getters:
    public double getValueCurrentlyInvested() {
        return valueCurrentlyInvested;
    }

    public double getCash() {
        return this.cash;
    }

    public ArrayList<Stock> getPortfolioList() {
        return this.portfolio;
    }

    // Setters:


    // Methods:

    public Portfolio() {
        this.cash = 0.00;
        this.valueCurrentlyInvested = 0.00;
        portfolio = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // REQUIRES: positive amount
    // MODIFIES: this
    // EFFECTS: add the amount inputted onto the current portfolio balance
    public void deposit(double amount) {
        this.cash += amount;
    }

    // REQUIRES: positive amount
    // MODIFIES: this
    // EFFECTS: subtract the amount inputted onto the current portfolio balance
    public void subtractBalance(double amount) {
        this.cash -= amount;
    }

//    //EFFECTS: invests each stock for the inputted amount of days
//    public void invest(int days) {
//        for (Stock i : portfolio) {
//            i.investForTime(days);
//        }
//    }

    // MODIFIES: this
    // EFFECTS: add a new stock to the portfolio arraylist
    public void addStock() {
        Stock stock = new Stock();

        stock.ticker();

        stock.stockPrice();

        stock.marketCap();

        stock.riskFactor();

        this.portfolio.add(stock);
    }


    public void investStocksForDays(int days) {
        for (Stock i : portfolio) {
            i.setDaysToInvest(days);

            i.investIndividualStock();
        }

        updateValue();
    }

    private void updateValue() {
        this.valueCurrentlyInvested = 0;
        for (Stock i : this.portfolio) {
            this.valueCurrentlyInvested += i.getCurrentInvestmentWorth();
        }
    }

    public Stock checkForTicker(String ticker) {
        for (Stock i : portfolio) {
            if (ticker.equals(i.getSymbol())) {
                return i;
            }
        }
        System.out.println("No stock found with that symbol");
        return null;
    }

    public void sellStock(Stock stock) {
        double amountInvested = stock.getCurrentInvestmentWorth();
        stock.setCurrentInvestmentWorth(0);

        this.cash += amountInvested;

        portfolio.remove(stock);
    }

    public void invest() {
        for (Stock i : portfolio) {
            System.out.println("How much would you like to invest in stock " + i.getSymbol() + "?");

            double amount = input.nextDouble();

            if (amount > cash) {
                System.out.println("Not enough money in portfolio");
                return;
            }

            i.setCurrentInvestmentWorth(amount);
            this.subtractBalance(amount);
        }
        System.out.println("For how many days would you like to invest?");
        int days = input.nextInt();
        while (days < 0) {
            System.out.println("Please input a positive number for amount of days to invest:");
            days = input.nextInt();
        }
        this.investStocksForDays(days);
    }

    public void printInvestingOutcome() {
        DecimalFormat df = new DecimalFormat("#.##");
        for (Stock i : portfolio) {
            System.out.println("Stock " + i.getSymbol() + " outcome:");
            System.out.println("Invested for " + i.getDaysToInvest() + " days.");
            System.out.println("Previous stock price: $" + df.format(i.getStockPricePrevious()));
            System.out.println("Current stock price: $" + df.format(i.getStockPriceCurrent()));
            System.out.println("Initial investment: $" + df.format(i.getInitialInvestment()));
            System.out.println("Current investment worth: $" + df.format(i.getCurrentInvestmentWorth()));
            System.out.println("Gains/losses: " + df.format(i.getCurrentInvestmentWorth() - i.getInitialInvestment()));
            System.out.println("");
        }
    }

    public void investMoreInExistingStock() {
        System.out.println("Enter ticker of stock you would like to invest more in: ");
        String ticker = input.next();

        Stock stock = this.checkForTicker(ticker);

        System.out.println("How much would you like to invest?");
        double amount = input.nextInt();

        stock.setCurrentInvestmentWorth(stock.getCurrentInvestmentWorth() + amount);
    }
}
