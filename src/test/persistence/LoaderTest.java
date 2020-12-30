package persistence;

import exceptions.NegativeAmountException;
import model.FoodBudget;
import model.RecList;
import model.AllRestaurants;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Unit tests for Loader class
public class LoaderTest {


    @Test
    void testParseBudget() {
        try {
            Loader loader = new Loader();
            List<String> balance = Loader.readFile(new File("./data/SampleBudget.txt"));
            int amount = Integer.parseInt(balance.get(0));
            FoodBudget budget = new FoodBudget(amount);
            assertEquals(40, budget.getBudget());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException should not have been thrown");
        }
    }

    @Test
    void testParseRecommendedList() {
        try {
            AllRestaurants allRestaurants = Loader.readRestaurants(new File("./data/SampleRecList.txt"));
            RecList testList = new RecList(allRestaurants);
            testList.addAll(allRestaurants);

            assertEquals("Japadog", testList.getRestaurant(0).getName());
            assertEquals("Miku", testList.getRestaurant(1).getName());
            assertEquals("Five_Sails_Restaurant", testList.getRestaurant(2).getName());

        } catch (Exception e) {
            fail("IOException should not have been thrown");
        }

    }

    @Test
    void testIOException() {
        try {
            Loader.readFile(new File("./no/path/SampleBudget.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
