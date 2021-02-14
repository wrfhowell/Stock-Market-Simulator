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
        portfolio.invest();

        portfolio.printInvestingOutcome();
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
        portfolio.addStock();
    }

    private void printBalance(Portfolio portfolio) {
        System.out.printf("Portfolio balance $%.2f\n", this.portfolio.getCash());
    }

    private void investMoreInExistingStock() {
        portfolio.investMoreInExistingStock();
    }

    private void returnPortfolioBalance() {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Portfolio balance: $" + df.format(portfolio.getCash()));
    }
}
