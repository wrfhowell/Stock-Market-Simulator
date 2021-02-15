package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

// Class portfolio represents a stock market portfolio in which a user can house a balance, multiple stocks and get
// their overall value (combination of balance and current investments). Methods can be used to change manipulate
// the balance and stocks present in portfolio
public class Portfolio {

    // Fields:
    private double valueCurrentlyInvested;
    private double balance;
    ArrayList<Stock> portfolio;
    private Scanner input;

    // Getters:
    public double getValueCurrentlyInvested() {
        return valueCurrentlyInvested;
    }

    public double getBalance() {
        return this.balance;
    }

    public ArrayList<Stock> getPortfolioList() {
        return this.portfolio;
    }

    // Setters:

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Methods:

    public Portfolio() {
        this.balance = 0.00;
        this.valueCurrentlyInvested = 0.00;
        portfolio = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: add the amount inputted onto the current portfolio balance
    public void deposit(double amount) {
        this.balance += amount;
    }

    // REQUIRES: positive amount
    // MODIFIES: this
    // EFFECTS: subtract the amount inputted onto the current portfolio balance
    public void subtractBalance(double amount) {
        this.balance -= amount;
    }


    // MODIFIES: this
    // EFFECTS: add a new stock to the portfolio arraylist
    public void addStock(Stock stock) {
        this.portfolio.add(stock);
    }

    // MODIFIES: this
    // EFFECTS: adds up each invested amount of each stock in a portfolio
    public void updateValue() {
        this.valueCurrentlyInvested = 0;
        for (Stock i : this.portfolio) {
            this.valueCurrentlyInvested += i.getCurrentInvestmentWorth();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the number of days to invest each stock. Individually invest all stock and then update
    // the total value currently invested in all stocks
    public void investStocksForDays(int days) {
        for (Stock i : portfolio) {
            i.setDaysToInvest(days);

            i.investIndividualStock();
        }

        updateValue();
    }

    // EFFECTS: returns stock that matches the ticker
    public Stock checkForTicker(String ticker) {
        for (Stock i : portfolio) {
            if (ticker.equals(i.getSymbol())) {
                return i;
            }
        }
        System.out.println("No stock found with that symbol");
        return null;
    }


    // MODIFIES: this
    // EFFECTS: adds the amount invested in stock to portfolio balance.
    //              - sets amount invested in stock to 0
    //              - removes stock from portfolio
    public void sellStock(Stock stock) {
        double amountInvested = stock.getCurrentInvestmentWorth();
        stock.setCurrentInvestmentWorth(0);

        this.balance += amountInvested;

        portfolio.remove(stock);
    }
}
