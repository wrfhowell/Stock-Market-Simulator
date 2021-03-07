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
