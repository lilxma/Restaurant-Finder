//package ui;
//
//import exceptions.NegativeAmountException;
//import model.FoodBudget;
//import model.RecList;
//import model.Restaurant;
//import model.AllRestaurants;
//import persistence.Loader;
//import persistence.Writer;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//
//// Graphical interface
//public class Gui extends JPanel implements ActionListener {
//    JPanel buttonPanel;
//    private JButton button1;
//    private JButton button2;
//    private JButton button3;
//    private JButton button4;
//    private JButton button5;
//    private JButton button6;
//    private JButton button7;
//
//    private static FoodBudget budget;
//    public static final String BUDGET_FILE = "./data/FoodBudget.txt";
//    private static final String LIST_FILE = "./data/RecommendedList.txt";
//    private AllRestaurants rlist;
//    private static RecList recommendedList;
//    private String message;
//    private String listRestaurants;
//    private AllRestaurants resList;
//
//    JPanel panel;
//    JLabel label;
//    private static JMenuBar mb;
//    private static JMenu menu;
//
//    // EFFECTS: constructs the GUI
//    public Gui() {
//        button1 = new JButton("Create a new food budget");
//        button1.setActionCommand("newFoodBudget");
//        button1.addActionListener(this);
//        button2 = new JButton("Add a new purchase to budget");
//        button2.setActionCommand("newPurchase");
//        button2.addActionListener(this);
//        button3 = new JButton("Add money into budget");
//        button3.setActionCommand("addingMoney");
//        button3.addActionListener(this);
//        button4 = new JButton("Save budget to file");
//        button4.setActionCommand("saveBudget");
//        button4.addActionListener(this);
//        button5 = new JButton("Save recommended list to file");
//        button5.setActionCommand("saveList");
//        button5.addActionListener(this);
//        button6 = new JButton("Print budget to screen");
//        button6.setActionCommand("printBudget");
//        button6.addActionListener(this);
//        button7 = new JButton("Print recommended list to screen");
//        button7.setActionCommand("printList");
//        button7.addActionListener(this);
//        addButtons();
//
//    }
//
//    // EFFECTS: adds buttons to a JFrame.
//    //the individual components code from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
//    public void addButtons() {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
//        JPanel layout = new JPanel(new GridBagLayout());
//        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
//        buttonPanel = new JPanel(new GridLayout(7, 1, 10, 5));
//        buttonPanel.add(button1);
//        buttonPanel.add(button2);
//        buttonPanel.add(button3);
//        buttonPanel.add(button4);
//        buttonPanel.add(button5);
//        buttonPanel.add(button6);
//        buttonPanel.add(button7);
//        layout.add(buttonPanel);
//        panel.add(layout, BorderLayout.CENTER);
//        add(buttonPanel);
//        JFrame frame = new JFrame();
//        frame.getContentPane().setBackground(Color.BLACK);
//        frame.setSize(1500, 1000);
//
//    }
//
//    //EFFECTS: displays the buttons
//    public static void showButtons() {
//        JFrame frame = new JFrame();
//        frame.add(new Gui());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        topMenu();
//        frame.getContentPane().add(BorderLayout.NORTH, mb);
//        frame.setLocationByPlatform(true);
//        frame.pack();
//        frame.setVisible(true);
//
//
//    }
//
//    //EFFECTS: action events from pressing the buttons
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        switch (e.getActionCommand()) {
//            case "newFoodBudget":
//                popUpPanel("Enter your initial budget in $");
//                break;
//            case "newPurchase":
//                popUpPanel("Enter a new purchase in $");
//                break;
//            case "addingMoney":
//                popUpPanel("Enter the amount you want to add to your budget in $");
//                break;
//            case "saveBudget":
//                saveScreen("Budget saved!");
//                break;
//            case "saveList":
//                saveScreen("Restaurant list saved!");
//                break;
//            case "printBudget":
//                popUpMenu("Your budget is $");
//                break;
//            case "printList":
//                popUpMenu("Here's a list of the recommended restaurants: ");
//                break;
//        }
//    }
//
//    // EFFECTS: additional frame added
//    public void popUpPanel(String string) {
//        JFrame frame = new JFrame();
//        addFrameFunctionality(string);
//        frame.setSize(1500, 1000);
//        JTextField tf = new JTextField(10);
//        JButton enter = new JButton("Enter");
//        enter.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (string.equals("Enter your initial budget in $")) {
//                    initializeBudget(tf);
//                } else if (string.equals("Enter a new purchase in $")) {
//                    newPurchase(tf);
//                } else if (string.equals("Enter the amount you want to add to your budget in $")) {
//                    addingMoney(tf);
//                }
//                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
//            }
//        });
//        panel.add(label);
//        panel.add(tf);
//        panel.add(enter);
//        frame.getContentPane().add(BorderLayout.CENTER, panel);
//        frame.setVisible(true);
//    }
//
//    // EFFECTS: adds a new JPanel and JLabel
//    public void addFrameFunctionality(String string) {
//        panel = new JPanel();
//        label = new JLabel(string);
//    }
//
//
//    //MODIFIES: FoodBudget
//    //EFFECTS: Sets up the initial budget
//    private void initializeBudget(JTextField tf) {
//        String text = tf.getText();
//        try {
//            int balance = Integer.parseInt(text);
//            try {
//                budget = new FoodBudget(balance);
//            } catch (NegativeAmountException e) {
//                saveScreen("Integer balance must be above $0");
//            }
//            findRestaurants(budget.getBudget());
//            saveBudget();
//            saveList();
//        } catch (NumberFormatException e) {
//            saveScreen("A valid integer balance was not inputted.");
//        } catch (NullPointerException e) {
//            System.out.println("Can't save invalid input");
//        }
//    }
//
//    //MODIFIES: FoodBudget
//    //EFFECTS: gets user input to run the budget app
//    private void newPurchase(JTextField tf) {
//        String text = tf.getText();
//        int savedAmount = budget.getBudget();
//        int currBalance = Integer.parseInt(text);
//        try {
//            budget.addPurchase(currBalance);
//        } catch (NegativeAmountException e) {
//            saveScreen("Purchase too large! Your balance will be below zero! Try adding a smaller purchase");
//            try {
//                budget = new FoodBudget(savedAmount);
//            } catch (NegativeAmountException ex) {
//                saveScreen("Purchase too large! Your balance will be below zero! Try adding a smaller purchase");
//            }
//        }
//
//        findRestaurants(budget.getBudget());
//
//        saveBudget();
//
//        saveList();
//
//    }
//
//    //MODIFIES: FoodBudget
//    //EFFECTS: adds money into the food budget
//    private void addingMoney(JTextField tf) {
//        String text = tf.getText();
//        int currBalance = Integer.parseInt(text);
//        budget.addingMoney(currBalance);
//        findRestaurants(budget.getBudget());
//        saveBudget();
//        saveList();
//    }
//
//    //MODIFIES: RecList
//    //EFFECTS: finds all restaurants within the price range. If no restaurants found end the program
//    private void findRestaurants(int price) {
//        rlist = new AllRestaurants();
//        listOfAllRestaurants(rlist);
//        recommendedList = new RecList(rlist);
//        recommendedList.insertRestaurants(price);
//        if (recommendedList.getRecList().length() > 0) {
//            System.out.println("Here are some restaurants in your price range: \n" + recommendedList.getRecList());
//        } else {
//            System.out.println("No restaurants in your price range.");
//        }
//        saveList();
//    }
//
//    //MODIFIES: AllRestaurants
//    //EFFECTS: Data of all restaurants
//    private void listOfAllRestaurants(AllRestaurants rlist) {
//        Restaurant danbo = new Restaurant("Danbo", 15);
//        Restaurant japadog = new Restaurant("Japadog", 10);
//        Restaurant fable = new Restaurant("Fable", 20);
//        Restaurant miku = new Restaurant("Miku", 30);
//        Restaurant fiveSails = new Restaurant("Five_Sails_Restaurant", 55);
//        rlist.addRestaurant(danbo);
//        rlist.addRestaurant(japadog);
//        rlist.addRestaurant(fable);
//        rlist.addRestaurant(miku);
//        rlist.addRestaurant(fiveSails);
//    }
//
//    // EFFECTS: saves the recommended list of restaurants
//    private static void saveList() {
//        try {
//            Writer listSaver = new Writer(new File(LIST_FILE));
//            listSaver.write(recommendedList);
//            listSaver.close();
//        } catch (FileNotFoundException e) {
//            saveScreen("Unable to save to " + LIST_FILE);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // EFFECTS: saves the budget of program
//    private static void saveBudget() {
//        try {
//            Writer budgetSaver = new Writer(new File(BUDGET_FILE));
//            budgetSaver.write(budget);
//            budgetSaver.close();
//        } catch (IOException e) {
//            saveScreen("Unable to save to " + BUDGET_FILE);
//        } catch (NullPointerException e) {
//            System.out.println("Nothing saved");
//        }
//    }
//
//    // EFFECTS: adds a new pop up menu
//    public void popUpMenu(String string) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        if (string.equals("Your budget is $")) {
//            loadBudget();
//            JOptionPane.showMessageDialog(frame, string + " " + message, ":)", JOptionPane.PLAIN_MESSAGE);
//        } else if (string.equals("Here's a list of the recommended restaurants: ")) {
//            loadList();
//            JOptionPane.showMessageDialog(frame, string + " " + listRestaurants, ":)", JOptionPane.PLAIN_MESSAGE);
//        }
//
//    }
//
//    // EFFECTS: adds a new save screen
//    public static void saveScreen(String string) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Toolkit.getDefaultToolkit().beep();
//        JOptionPane.showMessageDialog(frame, string, "Message", JOptionPane.PLAIN_MESSAGE);
//        saveBudget();
//        saveList();
//    }
//
//
//    // MODIFIES: FoodBudget
//    // EFFECTS: if file exists loads budget from BUDGET_FILE
//    // otherwise load display menu
//    private void loadBudget() {
//        try {
//            List<String> balance = Loader.readFile(new File(BUDGET_FILE));
//            int amount = Integer.parseInt(balance.get(0));
//            budget = new FoodBudget(amount);
//            message = (Integer.toString(budget.getBudget()));
//        } catch (NegativeAmountException e) {
//            saveScreen("Your balance is below $0");
//        } catch (IOException e) {
//            saveScreen("unable to load budget");
//        } catch (IndexOutOfBoundsException e) {
//            new Gui();
//        }
//    }
//
//    // MODIFIES: RecList
//    // EFFECTS: if file exists loads recommended list from LIST_FILE
//    // otherwise load display menu
//    private void loadList() {
//        try {
//            resList = Loader.readRestaurants(new File(LIST_FILE));
//            recommendedList = new RecList(resList);
//            recommendedList.addAll(resList);
//            if (recommendedList.getRecList().length() > 0) {
//                listRestaurants = "\n" + recommendedList.getRecList();
//            } else {
//                listRestaurants = "No restaurants in your price range";
//            }
//        } catch (IOException e) {
//            listRestaurants = "Unable to load restaurants";
//            new Gui();
//        }
//    }
//
//    //EFFECTS: adds a drop down top menu
//    private static void topMenu() {
//        mb = new JMenuBar();
//        menu = new JMenu("File");
//        mb.add(menu);
//        JMenuItem quit = new JMenuItem("Quit");
//        quit.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                System.exit(0);
//            }
//        });
//        menu.add(quit);
//        JMenuItem reset = new JMenuItem("Reset All");
//        reset.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                reset();
//            }
//        });
//        menu.add(reset);
//    }
//
//    // EFFECTS: resets data in files
//    private static void reset() {
//        try {
//            Writer budgetSaver = new Writer(new File(BUDGET_FILE));
//            budget = new FoodBudget(0);
//            budgetSaver.write(budget);
//            budgetSaver.close();
//            Writer listSaver = new Writer(new File(LIST_FILE));
//            recommendedList = new RecList(new AllRestaurants());
//            listSaver.write(recommendedList);
//            listSaver.close();
//        } catch (IOException ex) {
//            saveScreen("Unable to save to " + BUDGET_FILE);
//        } catch (NegativeAmountException e) {
//            saveScreen("An invalid balance was stored.");
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                showButtons();
//            }
//        });
//    }
//
//}
//
