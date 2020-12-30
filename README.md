# My Personal Project

## A budget tracker

This program is a simple *budget tracker* that when given a user's budget helps users find new places to eat 
that are within their budget.

Given a budget this program can:
- Record the **amount used** from the budget
- Update the **amount remaining** in the budget
- Given a budget find restaurants in the **specified price range** 
- Have the list of **recommended restaurants change** with the user's change in budget

This program is designed to be used by anyone who wants a simple way to track their food budget. It was designed with 
university students in mind who want to stay within their food budget. Using this program will make 
the process of finding new affordable places to eat easier than ever. I've long struggled with finding restaurants that 
look appetizing and fit in my price range without doing additional research before hand. This has taken up large amounts 
of my time and I often find myself returning to the same restaurants I've been to before because of the hassle of 
finding a new budget friendly place to eat. Therefore, I wanted to create a program that automated that process for me. 

## User Stories
- As a user, I want to be able to create a new budget 
- As a user, I want to be able to add a new purchase to my budget
- As a user, I want to view the balance remaining in my budget
- As a user, I want to add a restaurant to my recommended list of budget friendly restaurants (and view restaurants 
that fit my budget)
- As a user, I want to be able to save my current budget and recommended list to file
- As a user, I want to be able to reload my budget and recommended list from file when the program starts

## Instructions for Grader
- You can generate the first required event by clicking "create a new food budget". This takes in a budgeted amount and 
  finds all the restaurants that are within the food budget. 
  (I technically have 2 add X to Y) 
  1. One of them is adding a new purchase to my food budget. After creating a new food budget you can view this by 
     clicking "print budget to screen". 
  2. My other add X to Y is adding a restaurant to a recommended list of restaurants. To view the recommended list click
   "print recommended list to screen". 
   To add further purchases press "add a new purchase to budget". This will decrease the amount in the food budget and 
   you can generate a new list of recommended restaurants.
- You can generate the second required event by clicking "add money into budget". This will increase the amount in the
  food budget (my first add X to Y). 
  For my second add X to Y, you can press the reset button under the FILE icon on the top left hand corner. This will 
  reset the recommended list, deleting all saved restaurants, and the amount in the food budget.
- You can trigger my audio component by clicking "Save budget to file" or "Save recommended list to file" 
- You can save the balance by clicking "Save budget to file" or save the recommended list by clicking 
  "Save recommended list to file" 
- You can reload the state of my application after saving by clicking "Print budget to screen" or "Print recommended 
  list to screen". This will display the saved state of the application. 
  
  NOTE: Only click "create a new food budget" to wipe the old balance in food budget and create a new food budget. 
  
  ## Phase 4: Task 2
  I have chosen to make the FoodBudget class robust. The constructor and the addPurchase methods now throw a 
  NegativeAmountException if the balance is a negative amount. This ensures that the food budget cannot be initialized
  as a negative amount. Additionally, if you add a large purchase that decreases the balance to a negative value that 
  purchase will not go through and the original balance is kept. 

  ## Phase 4: Task 3
  In my GUI class there is poor cohesion. I improved this by separating the methods dealing more with the gui and 
  structural components into a Frame class and the methods focused more on the the application and running of my budget 
  app in a BudgetApp class. This increased cohesion as each class focused more on a single responsibility. I could 
  improve this even more by separating those classes into smaller classes with more specific responsibilities.
  Another area I could improve is the association between the AllRestaurants class and BudgetApp class. To reduce
  coupling and increase cohesion I changed the association between the two so my BudgetApp class does not need 
  AllRestaurants fields and there are methods that now relate the two. Now the two classes are more 
  loosely coupled and it is easier to change the BudgetApp class without worrying about the AllRestaurants class. 
  The code is also much more concise as I want my BudgetApp class to only display the list of recommended 
  restaurants not all the restaurants. 
  
  **The UML diagram does not include dependencies**


