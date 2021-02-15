package ui;

import model.Portfolio;
import model.Stock;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class PortfolioApp {
    private Portfolio portfolio;
    private Scanner input;


    public PortfolioApp() {
        runPortfolio();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPortfolio() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase(Locale.ROOT);

            // q is quit
            if (command.equals("7")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            addMoney();
        } else if (command.equals("2")) {
            addStock();
        } else if (command.equals("3")) {
            sellStock();
        } else if (command.equals("4")) {
            investMoreInExistingStock();
        } else if (command.equals("5")) {
            investPortfolio();
        } else if (command.equals("6")) {
            returnPortfolioBalance();
        }
    }
    // MODIFIES: this
    // EFFECTS: initializes portfolio

    public void init() {
        portfolio = new Portfolio();
        input = new Scanner(System.in);
    }

    // EFFECTS: option menu for user
    private void displayMenu() {
        System.out.println("\nStock Portfolio Simulation Options:");
        System.out.println("\t[1]: Add Money");
        System.out.println("\t[2]: Add Stock");
        System.out.println("\t[3]: Sell and Remove Stock");
        System.out.println("\t[4]: Invest More in Current Stock");
        System.out.println("\t[5]: Invest in Stocks");
        System.out.println("\t[6]: Check Portfolio Balance");
        System.out.println("\t[7]: End Simulation");
    }

    // MODIFIES: this
    // EFFECTS: adds money to the portfolio balance if >= 0.0 and prints balance afterwards
    private void addMoney() {
        System.out.print("Enter amount to deposit: $");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            portfolio.deposit(amount);
        } else {
            System.out.println("Cannot deposit negative amount...\n");
        }

        printBalance(portfolio);
    }


    // MODIFIES: this
    // EFFECTS: call invest methods on portfolio. Invests amount specified for each stock for inputted number of days
    private void investPortfolio() {
        investMenu();

        printInvestingOutcome();
    }

    // MODIFIES: this
    // EFFECTS: sells the stock that is inputted and removes it from the list of stocks active
    private void sellStock() {
        System.out.println("What is the ticker of the stock you would like to sell?");
        String ticker = input.next();

        Stock stock = portfolio.checkForTicker(ticker);

        portfolio.sellStock(stock);


    }

    // MODIFIES: THIS
    // EFFECTS: will add stock with all relevant fields to portfolio
    private void addStock() {
        Stock stock = new Stock();

        ticker(stock);

        stockPrice(stock);

        marketCap(stock);

        riskFactor(stock);

        portfolio.addStock(stock);
    }

    private void printBalance(Portfolio portfolio) {
        System.out.printf("Portfolio balance $%.2f\n", this.portfolio.getBalance());
    }

    private void returnPortfolioBalance() {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Portfolio balance: $" + df.format(portfolio.getBalance()));
    }

    // REQUIRES: ticker must not already be used
    public void ticker(Stock stock) {
        System.out.println("What is the stock ticker? (eg. AAPL)");
        String ticker = input.next();

        while (!ticker.matches("[A-Z]+")) {
            System.out.println("Must be all capital letters.");
            ticker = input.next();
        }
        stock.setSymbol(ticker);
    }

    public void stockPrice(Stock stock) {
        System.out.println("What is the current stock price? (eg. AAPL: 135.37)");
        double stockPrice = input.nextDouble();

        while (stockPrice < 0) {
            System.out.println("Input positive number.");
            stockPrice = input.nextDouble();
        }

        stock.setStockPriceCurrent(stockPrice);
    }

    public void marketCap(Stock stock) {
        System.out.println("What is the market cap of this stock? (eg. AAPL: 2723000000000");
        double marketCap = input.nextDouble();

        while (marketCap < 0) {
            System.out.println("Input positive number.");
            marketCap = input.nextDouble();
        }

        stock.setMarketCap(marketCap);
    }

    public void riskFactor(Stock stock) {
        System.out.println(
                "How risky is the stock from 1 - 5? (eg. 5 is riskiest, could lose or gain the most)");
        int riskFactor = input.nextInt();

        while (riskFactor < 1 | riskFactor > 5) {
            System.out.println("Input integer from between 1 and 5.");
            riskFactor = input.nextInt();
        }

        stock.setRisk(riskFactor);
    }

    private void investMenu() {
        for (Stock i : portfolio.getPortfolioList()) {
            System.out.println("How much would you like to invest in stock " + i.getSymbol() + "?");

            double amount = input.nextDouble();

            if (amount > portfolio.getBalance()) {
                System.out.println("Not enough money in portfolio");
                return;
            }

            i.setCurrentInvestmentWorth(amount);
            portfolio.subtractBalance(amount);
        }
        System.out.println("For how many days would you like to invest?");
        int days = input.nextInt();
        while (days < 0) {
            System.out.println("Please input a positive number for amount of days to invest:");
            days = input.nextInt();
        }
        portfolio.investStocksForDays(days);
    }

    private void printInvestingOutcome() {
        DecimalFormat df = new DecimalFormat("#.##");
        for (Stock i : portfolio.getPortfolioList()) {
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

    private void investMoreInExistingStock() {
        System.out.println("Enter ticker of stock you would like to invest more in: ");
        String ticker = input.next();

        Stock stock = portfolio.checkForTicker(ticker);

        System.out.println("How much would you like to invest?");
        double amount = input.nextInt();

        stock.addInvestmentAmount(amount);
    }
}
