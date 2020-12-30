package model;

import exceptions.NegativeAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit tests for the FoodBudget class
class FoodBudgetTest {
    FoodBudget budget;

    @BeforeEach
    public void runBefore() throws NegativeAmountException {
        budget = new FoodBudget(50);
    }

    @Test
    public void testFoodBudgetValidInput() {
        try {
            budget = new FoodBudget(50);
            assertEquals(50, budget.getBudget());
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException not expected to be thrown");
        }
    }

    @Test
    public void testFoodBudgetNegativeInput() {
        try {
            FoodBudget fBudget = new FoodBudget(-10);
            fail();
        } catch (NegativeAmountException e) {
            // expected
        }
    }

    @Test
    public void testAddPurchaseSuccess() {
        try {
            budget.addPurchase(10);
            assertEquals(40, budget.getBudget());
            budget.addPurchase(5);
            assertEquals(35, budget.getBudget());
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException should not have been thrown");
        }
    }

    @Test
    public void testAddPurchaseTooBig() {
        try {
            budget.addPurchase(100);
            assertEquals(50, budget.getBudget());
            fail("Purchase too big, NegativeAmountException should have been thrown.");
        } catch (NegativeAmountException e) {
            // expected
        }
    }

    @Test
    public void testAddMoney() {
        budget.addingMoney(10);
        assertEquals(60, budget.getBudget());
        budget.addingMoney(40);
        assertEquals(100, budget.getBudget());
    }


}