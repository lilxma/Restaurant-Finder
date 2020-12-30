package ui;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// Represents structural components making up GUI
public class Frame extends JFrame implements ActionListener {
    private JFrame mainFrame;
    private JPanel buttonPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JMenuBar mb;
    private JMenu menu;
    private JPanel panel;
    private JLabel label;
    private BudgetApp app;


    public Frame() {
        mainFrame = new JFrame();
        mainFrame.setSize(500,500);
        showButtons();
        app = new BudgetApp();
    }

    //EFFECTS: creates all buttons needed
    private JPanel initializeButtons() {
        button1 = new JButton("Create a new food budget");
        button1.setActionCommand("newFoodBudget");
        button1.addActionListener(this);
        button2 = new JButton("Add a new purchase to budget");
        button2.setActionCommand("newPurchase");
        button2.addActionListener(this);
        button3 = new JButton("Add money into budget");
        button3.setActionCommand("addingMoney");
        button3.addActionListener(this);
        button4 = new JButton("Save budget to file");
        button4.setActionCommand("saveBudget");
        button4.addActionListener(this);
        button5 = new JButton("Save recommended list to file");
        button5.setActionCommand("saveList");
        button5.addActionListener(this);
        button6 = new JButton("Print budget to screen");
        button6.setActionCommand("printBudget");
        button6.addActionListener(this);
        button7 = new JButton("Print recommended list to screen");
        button7.setActionCommand("printList");
        button7.addActionListener(this);
        addButtons();
        return buttonPanel;
    }

    // EFFECTS: adds buttons to a JFrame.
    //the individual components code from https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    private void addButtons() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        buttonPanel = new JPanel(new GridLayout(7, 1, 10, 5));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        layout.add(buttonPanel);
        panel.add(layout, BorderLayout.CENTER);
        add(buttonPanel);
    }

    //EFFECTS: displays the buttons
    public void showButtons() {
        mainFrame.add(initializeButtons());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topMenu();
        mainFrame.getContentPane().add(BorderLayout.NORTH, mb);
        mainFrame.setLocationByPlatform(true);
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    //EFFECTS: action events from pressing the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "newFoodBudget":
                popUpPanel("Enter your initial budget in $");
                break;
            case "newPurchase":
                popUpPanel("Enter a new purchase in $");
                break;
            case "addingMoney":
                popUpPanel("Enter the amount you want to add to your budget in $");
                break;
            case "saveBudget":
                app.saveScreen("Budget saved!");
                break;
            case "saveList":
                app.saveScreen("Restaurant list saved!");
                break;
            case "printBudget":
                popUpMenu("Your budget is $");
                break;
            case "printList":
                popUpMenu("Here's a list of the recommended restaurants: ");
                break;
        }
    }

    // EFFECTS: additional frame added
    public void popUpPanel(String string) {
        JFrame frame = new JFrame();
        addFrameFunctionality(string);
        frame.setSize(1500, 1000);
        JTextField tf = new JTextField(10);
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (string.equals("Enter your initial budget in $")) {
                    app.initializeBudget(tf);
                } else if (string.equals("Enter a new purchase in $")) {
                    app.newPurchase(tf);
                } else if (string.equals("Enter the amount you want to add to your budget in $")) {
                    app.addingMoney(tf);
                }
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        panel.add(label);
        panel.add(tf);
        panel.add(enter);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

    // EFFECTS: adds a new JPanel and JLabel
    public void addFrameFunctionality(String string) {
        panel = new JPanel();
        label = new JLabel(string);
    }

    //EFFECTS: adds a drop down top menu
    private void topMenu() {
        mb = new JMenuBar();
        menu = new JMenu("File");
        mb.add(menu);
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
        menu.add(quit);
        JMenuItem reset = new JMenuItem("Reset All");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.reset();
            }
        });
        menu.add(reset);
    }

    // EFFECTS: adds a new pop up menu
    public void popUpMenu(String string) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (string.equals("Your budget is $")) {
            app.loadBudget();
            JOptionPane.showMessageDialog(frame, string + " " + app.getMessage(), ":)",
                    JOptionPane.PLAIN_MESSAGE);
        } else if (string.equals("Here's a list of the recommended restaurants: ")) {
            app.loadList();
            JOptionPane.showMessageDialog(frame, string + " " + app.getListRestaurants(), ":)",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }




}
