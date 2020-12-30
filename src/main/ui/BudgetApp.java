package ui;

import exceptions.NegativeAmountException;
import model.AllRestaurants;
import model.FoodBudget;
import model.RecList;
import persistence.Loader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

// Budget App class
public class BudgetApp {
    private FoodBudget budget;
    private RecList recommendedList;
    public static final String BUDGET_FILE = "./data/FoodBudget.txt";
    private static final String LIST_FILE = "./data/RecommendedList.txt";
    private String listRestaurants;
    private String message;

    //MODIFIES: FoodBudget
    //EFFECTS: Sets up the initial budget
    public void initializeBudget(JTextField tf) {
        String text = tf.getText();
        try {
            int balance = Integer.parseInt(text);
            try {
                budget = new FoodBudget(balance);
            } catch (NegativeAmountException e) {
                saveScreen("Integer balance must be above $0");
            }
            findRestaurants(budget.getBudget());
            saveBudget();
        } catch (NumberFormatException e) {
            saveScreen("A valid integer balance was not inputted.");
        } catch (NullPointerException e) {
            System.out.println("Can't save invalid input");
        }
    }

    //MODIFIES: FoodBudget
    //EFFECTS: gets user input to run the budget app
    public void newPurchase(JTextField tf) {
        String text = tf.getText();
        int savedAmount = budget.getBudget();
        int currBalance = Integer.parseInt(text);
        try {
            budget.addPurchase(currBalance);
        } catch (NegativeAmountException | NullPointerException e) {
            saveScreen("Purchase too large!"
                    + " Your balance will be below zero! Try adding a smaller purchase");
            try {
                budget = new FoodBudget(savedAmount);
            } catch (NegativeAmountException ex) {
                saveScreen("Purchase too large! Your balance will be below zero!"
                        + " Try adding a smaller purchase");
            }
        }
        findRestaurants(budget.getBudget());
        saveBudget();

    }

    //MODIFIES: FoodBudget
    //EFFECTS: adds money into the food budget
    public void addingMoney(JTextField tf) {
        String text = tf.getText();
        int currBalance = Integer.parseInt(text);
        budget.addingMoney(currBalance);
        findRestaurants(budget.getBudget());
        saveBudget();
    }

    //MODIFIES: RecList
    //EFFECTS: finds all restaurants within the price range. If no restaurants found end the program
    public void findRestaurants(int price) {
        AllRestaurants restaurantList = new AllRestaurants();
        restaurantList.listOfAllRestaurants();
        recommendedList = new RecList(restaurantList);
        recommendedList.insertRestaurants(price);
        if (recommendedList.getRecList().length() > 0) {
            System.out.println("Here are some restaurants in your price range: \n" + recommendedList.getRecList());
        } else {
            System.out.println("No restaurants in your price range.");
        }
        saveList();
    }


    // MODIFIES: FoodBudget
    // EFFECTS: if file exists loads budget from BUDGET_FILE
    // otherwise load display menu
    public void loadBudget() {
        try {
            List<String> balance = Loader.readFile(new File(BUDGET_FILE));
            int amount = Integer.parseInt(balance.get(0));
            budget = new FoodBudget(amount);
            message = (Integer.toString(budget.getBudget()));
        } catch (NegativeAmountException e) {
            saveScreen("Your balance is below $0");
        } catch (IOException e) {
            saveScreen("unable to load budget");
        } catch (IndexOutOfBoundsException e) {
            new Frame();
        }
    }

    //EFFECTS: returns the message
    public String getMessage() {
        return message;
    }

    // MODIFIES: RecList
    // EFFECTS: if file exists loads recommended list from LIST_FILE
    // otherwise load display menu
    public void loadList() {
        try {
            AllRestaurants allRestaurants = Loader.readRestaurants(new File(LIST_FILE));
            recommendedList = new RecList(allRestaurants);
            recommendedList.addAll(allRestaurants);
            if (recommendedList.getRecList().length() > 0) {
                listRestaurants = "\n" + recommendedList.getRecList();
            } else {
                listRestaurants = "No restaurants in your price range";
            }
        } catch (IOException e) {
            listRestaurants = "Unable to load restaurants";
            new Frame();
        }
    }

    //EFFECTS: returns the parsed list of restaurants
    public String getListRestaurants() {
        return listRestaurants;
    }

    // EFFECTS: saves the recommended list of restaurants
    public void saveList() {
        try {
            Writer listSaver = new Writer(new File(LIST_FILE));
            listSaver.write(recommendedList);
            listSaver.close();
        } catch (FileNotFoundException e) {
            saveScreen("Unable to save to " + LIST_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: saves the budget of program
    public void saveBudget() {
        try {
            Writer budgetSaver = new Writer(new File(BUDGET_FILE));
            budgetSaver.write(budget);
            budgetSaver.close();
        } catch (IOException e) {
            saveScreen("Unable to save to " + BUDGET_FILE);
        } catch (NullPointerException e) {
            System.out.println("Nothing saved");
        }
    }

    //EFFECTS: saves both the list and the budget
    public void saveAll() {
        saveList();
        saveBudget();
    }


    // EFFECTS: resets data in files
    public void reset() {
        try {
            Writer budgetSaver = new Writer(new File(BUDGET_FILE));
            budget = new FoodBudget(0);
            budgetSaver.write(budget);
            budgetSaver.close();
            Writer listSaver = new Writer(new File(LIST_FILE));
            recommendedList = new RecList(new AllRestaurants());
            listSaver.write(recommendedList);
            listSaver.close();
        } catch (IOException ex) {
            saveScreen("Unable to save to " + BUDGET_FILE);
        } catch (NegativeAmountException e) {
            saveScreen("An invalid balance was stored.");
        }
    }

    // EFFECTS: adds a new save screen
    public void saveScreen(String string) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(frame, string, "Message", JOptionPane.PLAIN_MESSAGE);
        saveAll();
    }


}
