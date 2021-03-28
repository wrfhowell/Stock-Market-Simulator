package ui;

import model.Portfolio;
import model.Stock;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/portfolio.json";
    private Portfolio portfolio;
    private Stock stock;

    private JPanel mainMenu;
    private JButton addMoneyButton;
    private JButton addStockButton;
    private JButton viewStocksButton;
    private JButton sellStockButton;
    private JButton investInStocksButton;
    private JButton savePortfolioButton;
    private JButton loadPortfolioButton;
    private JButton endButton;

    private JPanel addStockMenu;
    private JTextField stockTicker;
    private JTextField stockPriceCurrent;
    private JTextField marketCap;
    private JTextField risk;

    private JPanel viewStockMenu;

    private JLabel stockList;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public GUI() {
        super("Stock Portfolio");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

        initializeMainMenu();

        initializeSubMenus();
    }

    public void initializeMainMenu() {
        portfolio = new Portfolio();

        mainMenu = new JPanel();
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));
        add(mainMenu);
        mainMenu.setVisible(true);

        addHomeScreenLabel();

        createMenuButtons();
        addButtonsToMenu();
        setButtonAction();
    }

    private void addHomeScreenLabel() {
        JLabel homeLabel = new JLabel("Hello, welcome to your stock portfolio");
        homeLabel.setFont(new Font("Georgia", Font.BOLD, 35));
        mainMenu.add(homeLabel);
    }

    private void createMenuButtons() {
        addMoneyButton = new JButton("Add Money to Portfolio Balance");
        addStockButton = new JButton("Add a New Stock");
        viewStocksButton = new JButton("View Current Stocks");
        sellStockButton = new JButton("Sell a Stock");
        investInStocksButton = new JButton("Invest in Your Stocks Now");
        savePortfolioButton = new JButton("Save Current Portfolio");
        loadPortfolioButton = new JButton("Load Previous Portfolio");
        endButton = new JButton("Exit application");
    }

    private void addButtonsToMenu() {
        addButtonToMenu(addMoneyButton);
        addButtonToMenu(addStockButton);
        addButtonToMenu(viewStocksButton);
        addButtonToMenu(sellStockButton);
        addButtonToMenu(investInStocksButton);
        addButtonToMenu(savePortfolioButton);
        addButtonToMenu(loadPortfolioButton);
        addButtonToMenu(endButton);
    }

    private void addButtonToMenu(JButton button) {
        button.setFont(new Font("Georgia", Font.BOLD, 12));
        button.setBackground((Color.BLACK));
        mainMenu.add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    private void setButtonAction() {
        addMoneyButton.addActionListener(this);
        addMoneyButton.setActionCommand("Add Money to Portfolio Balance");

        addStockButton.addActionListener(this);
        addStockButton.setActionCommand("Add a New Stock");

        viewStocksButton.addActionListener(this);
        viewStocksButton.setActionCommand("View Current Stocks");

        sellStockButton.addActionListener(this);
        sellStockButton.setActionCommand("Sell a Stock");

        investInStocksButton.addActionListener(this);
        investInStocksButton.setActionCommand("Invest in Your Stocks Now");

        savePortfolioButton.addActionListener(this);
        savePortfolioButton.setActionCommand("Save Current Portfolio");

        loadPortfolioButton.addActionListener(this);
        loadPortfolioButton.setActionCommand("Load Previous Portfolio");

        endButton.addActionListener(this);
        endButton.setActionCommand("Exit application");
    }

    private void initializeSubMenus() {
        initializeViewStockMenu();
        initializeAddStockMenu();
    }

    private void initializeViewStockMenu() {
        viewStockMenu = new JPanel();
       // viewStockMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Your Stocks:");
        title.setFont(new Font("Georgia", Font.BOLD, 20));
        viewStockMenu.add(title);

        stockList = new JLabel(convertToMultiline(portfolio.getStocksAsString()), SwingConstants.CENTER);
        stockList.setFont(new Font("Georgia", Font.BOLD, 12));

        JScrollPane pane = new JScrollPane(stockList);
        pane.setPreferredSize(new Dimension(1200, 300));
        viewStockMenu.add(pane);

        JButton toMenu = new JButton("Back to Main Menu");
        toMenu.setFont(new Font("Georgia", Font.BOLD, 12));
        toMenu.setBackground((Color.BLACK));
        viewStockMenu.add(toMenu);
        pack();
        toMenu.addActionListener(this);
        toMenu.setActionCommand("Back to Main Menu");
    }

    private void initializeAddStockMenu() {
        addStockMenu = new JPanel();

        addInputFields();

        JButton addStockButton = new JButton("Add Stock");
        addStockButton.setFont(new Font("Georgia", Font.BOLD, 12));
        addStockButton.setBackground((Color.BLACK));
        addStockMenu.add(addStockButton);
        pack();
        addStockButton.setActionCommand("Add Stock");
        addStockButton.addActionListener(this);

        JButton mainMenuButton = new JButton("Back to Main Menu");
        mainMenuButton.setFont(new Font("Georgia", Font.BOLD, 12));
        mainMenuButton.setBackground((Color.BLACK));
        addStockMenu.add(mainMenuButton);
        pack();
        mainMenuButton.setActionCommand("Back to Main Menu");
        mainMenuButton.addActionListener(this);
    }

    private void addInputFields() {
        Font font = new Font("Georgia", Font.BOLD, 12);
        Dimension dimension = new Dimension(1200, 40);

        addInput1(font, dimension);

        addInput2(font, dimension);

        addInput3(font, dimension);

        addInput4(font, dimension);
    }

    private void addInput4(Font font, Dimension dimension) {
        JLabel label;
        label = new JLabel("How risky is the stock from 1 - 5? (eg. 5 is riskiest, could lose or gain the most)");
        label.setFont(font);

        risk = new JTextField(5);
        risk.setMaximumSize(dimension);
        addStockMenu.add(label);
        addStockMenu.add(risk);
    }

    private void addInput3(Font font, Dimension dimension) {
        JLabel label = new JLabel("What is the market cap of this stock? (eg. AAPL: $2723000000000)");
        label.setFont(font);

        marketCap = new JTextField(5);
        marketCap.setMaximumSize(dimension);
        addStockMenu.add(label);
        addStockMenu.add(marketCap);
    }

    private void addInput2(Font font, Dimension dimension) {
        JLabel label = new JLabel("What is the current stock price of this stock? (E.g. $135.37)");
        label.setFont(font);

        stockPriceCurrent = new JTextField(5);
        stockPriceCurrent.setMaximumSize(dimension);
        addStockMenu.add(label);
        addStockMenu.add(stockPriceCurrent);
    }

    private void addInput1(Font font, Dimension dimension) {
        JLabel label = new JLabel("What is the ticker/symbol of the stock you would like to sell? (E.g. AAPL)");
        label.setFont(font);

        stockTicker = new JTextField(5);
        stockTicker.setMaximumSize(dimension);
        addStockMenu.add(label);
        addStockMenu.add(stockTicker);
    }

    // EFFECTS: calls methods when button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenuActions(e);
        viewStockMenuActions(e);
        addStockMenuActions(e);
    }

    private void mainMenuActions(ActionEvent e) {
        if (e.getActionCommand().equals("Add a New Stock")) {
            addStockToPortfolioButtonPress();
        } else if (e.getActionCommand().equals("View Current Stocks")) {
            viewStocksActionButtonPress();
        } else if (e.getActionCommand().equals("Save Current Portfolio")) {
            savePortfolio();
        } else if (e.getActionCommand().equals("Load Previous Portfolio")) {
            loadPortfolio();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads portfolio from file
    private void loadPortfolio() {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            portfolio = jsonReader.read();
            System.out.println("Loaded portfolio saved at " + JSON_STORE);
            stockList.setText(portfolio.getStocksAsString());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the portfolio to file in data folder
    private void savePortfolio() {
        try {
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(portfolio);
            jsonWriter.close();
            System.out.println("Saved portfolio to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void addStockToPortfolioButtonPress() {
        add(addStockMenu);
        mainMenu.setVisible(false);
        viewStockMenu.setVisible(false);
        addStockMenu.setVisible(true);
    }

    private void viewStocksActionButtonPress() {
        add(viewStockMenu);
        mainMenu.setVisible(false);
        viewStockMenu.setVisible(true);
        addStockMenu.setVisible(false);
    }

    private void viewStockMenuActions(ActionEvent e) {
        if (e.getActionCommand().equals("Back to Main Menu")) {
            mainMenu.setVisible(true);
            viewStockMenu.setVisible(false);
            addStockMenu.setVisible(false);
        }
    }

    private void addStockMenuActions(ActionEvent e) {
        if (e.getActionCommand().equals("Add Stock")) {
            playSound("./data/CashRegister.wav");
            Stock stock = new Stock();
            stock.setSymbol(stockTicker.getText());
            stock.setStockPriceCurrent(Double.parseDouble(stockPriceCurrent.getText()));
            stock.setMarketCap(Double.parseDouble(marketCap.getText()));
            stock.setRisk(Integer.parseInt(risk.getText()));

            portfolio.addStock(stock);

            stockList.setText(portfolio.getStocksAsString());
        } else if (e.getActionCommand().equals("Back to Main Menu")) {
            mainMenu.setVisible(true);
            viewStockMenu.setVisible(false);
            addStockMenu.setVisible(false);
        }
    }

    public static String convertToMultiline(String orig) {
        return "<html>" + orig.replaceAll("\n", "<br/>") + "</html>";
    }

    // EFFECTS: Plays sound from given file string
    //          Credit to: https://www.programcreek.com/java-api-examples/?class=javax.sound.sampled.Clip&method=start
    public void playSound(String soundName) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(soundName));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing the sound");
        }
    }
}
