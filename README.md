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
- Current public perception 1-5 (1 being bad, 5 being good)

An example of text with **bold** and *italic* fonts.  Note that the IntelliJ markdown previewer doesn't seem to render 


---

## User Stories

- As a user, I want to be able to add money to my account
- As a user, I want to add or remove a stock to my portfolio
- As a user, I want to input factors for that stock
- As a user, I want to view how my portfolio is performing (overall and individual stocks)

#### **Example:**

I want to invest in Apple:

- I add $5000 to account
- I choose to add stock to my portfolio
- I set the stock symbol to AAPL
- I set the current price to 136.83 
- I set the risk value to a 3 (this is based on your opinion)
- I set the amount I want to invest to $5000
- I set public perception to a 5 (great public perception currently)
- I set interest in stock to a 4 (decent amount of attention) 


- I want to invest my portfolio, I hit invest and set time frame to 30 days
- Now I check portfolio, it is now worth $5300
- I can check individaul stock performance
    - Only stock is apple, it went from $5000 - $5300
