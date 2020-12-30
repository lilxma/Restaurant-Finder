package model;


import exceptions.NegativeAmountException;
import persistence.Saveable;

import java.io.PrintWriter;

//Represents a budget
public class FoodBudget implements Saveable {
    int balance;

    //EFFECTS: creates a new budget
    public FoodBudget(int initialBudget) throws NegativeAmountException {
        if (initialBudget >= 0) {
            balance = initialBudget;
        } else {
            throw new NegativeAmountException();
        }
    }

    //MODIFIES: this
    //EFFECTS: deducts the purchased cash value from the balance
    public void addPurchase(int price) throws NegativeAmountException {
        balance -= price;
        if (balance < 0) {
            throw new NegativeAmountException();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds the inputted amount to the budget
    public void addingMoney(int money) {
        balance += money;
    }

    //EFFECTS: returns the balance remaining in the budget
    public int getBudget() {
        return balance;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(balance);
    }


}
