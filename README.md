# Stock Market Simulator

---
## Overview
This project will act as a **stock market simulator**. It will allow you to create stocks (either imaginary or 
based on a real stock) and then use your own judgement to assess the stock on various factors (eg. risk/reward, 
market cap, 
time invested). The user will first have to enter money into the account -- money they can withdraw anytime. 
I will have a basic algorithm that will use these factors to determine the amount of money you would 
have potentially gained or lost in the time frame that was provided. Multiple stocks can be added to an overall 
portfolio. A user can then get statistics about their portfolio including total amount of money invested, 
money gained/lost, etc...

---

## Individual Stock Factors

Whether a certain stock will go up or down over time will depend on the **factors** a user indicates.

For each stock a user will indicate:
- Stock Symbol (eg. AAPL)
- Current stock price (real or imaginary)
- Risk from 1 - 5 (more risk has potential for greater wins or greater losses)
- Amount of time you want to invest (eg. amount of days)
- Quantity (eg. how much money to invest)
- Current public perception 1-5 (1 being bad, 5 being good)  -- not yet implemented


---

## User Stories

- As a user, I want to be able to add money to my portfolio
- As a user, I want to be able to add a stock to my portfolio. That stock should contain information
  about stock price, market cap and other important factors
- As a user, I want to be able to invest money into that stock, and then let it invest for a certain amount of days to
  see the outcome of if I have gainer or lost money in the investment
- As a user, I want to view how my portfolio is performing (overall and individual stocks)
- As a user, I want to be able to sell and remove a stock from my portfolio

#### Persistence Stories:
- As a user, I want to be able to save my current stock portfolio
- As a user, I want to be able to load a previous stock portfolio

#### **Example:**

I want to invest in Apple:

- I add $5000 to account
- I choose to add stock to my portfolio
- I set the stock symbol to AAPL
- I set the current price to 136.83 
- I set the risk value to a 3 (this is based on your opinion)


- I want to invest my portfolio, I hit invest, set the amount of money I want to invest in each stock and 
  set time frame to 30 days
- Now I check portfolio, it is now worth $5300
- I can check individual stock performance
    - Only stock is apple, it went from $5000 - $5300

---

## Phase 4

#### Task 2

- I made the Stock class more robust. 
  - In the addInvestmentAmount method, I threw a NegativeDoubleException() if someone tries to invest
    using a negative amount of money. This is caught in PortfolioApp class when a user will invest more in a current 
    stock that they already have, and thus this negative investment amount won't be added to the stock
  - I also threw exceptions for all the setters to ensure that no stock could be 
  created by the GUI or PortfolioApp with invalid parameters. So these exceptions were caught in either portfolio or 
  GUI/Portfolio app classes
    - Thrown exceptions were:
      - NonCapLetterException and TickerLengthException (for setSymbol)
      - NegativeDoubleException (for all investment amount, price, marketcap setters)
      - NegativeIntException (for days invested setter)
      - RiskOutOfBoundaryException (for setRisk setter)
  - Every thrown exception is tested for in the StockTestClass  
    
#### Task 3

- Check UML_Design_Diagram.pdf for UML diagram
- How to improve design:
    - Somehow combine the GUI with the PortfolioApp so that any user input in the GUI can also
    show up in the command line. This should full implement the features in both GUI and PortfolioApp
    - Might be good to also refactor the GUI into simpler parts instead of having 1 long GUI class. I should have
    subclasses to create different parts of the GUI (eg. different menus, accepting inpute, etc.).
    - Should probably refactor portfolio to take a more general form of investment. Perhaps something that is an
    interface, and then multiple subclasses implementing that interface that include Stock, bond, ETF, etc.
    - Make all classes more robust  