package persistence;

import exceptions.NegativeAmountException;
import model.AllRestaurants;
import model.FoodBudget;
import model.RecList;
import model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Unit tests for Writer class
public class WriterTest {
    private static final String TEST_BUDGET = "./data/SampleBudget.txt";
    private static final String TEST_LIST = "./data/SampleRecList.txt";
    private Writer testWriter;
    private Writer testListWriter;
    private RecList recList;
    private AllRestaurants restaurantList;
    private FoodBudget budget;

    // Restaurant fields as sample data
    private Restaurant miku;
    private Restaurant japadog;
    private Restaurant fiveSails;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException, NegativeAmountException {
        testWriter = new Writer(new File(TEST_BUDGET));
        budget = new FoodBudget(40);
        testListWriter = new Writer(new File(TEST_LIST));
        restaurantList = new AllRestaurants();

        japadog = new Restaurant("Japadog", 10);
        miku = new Restaurant("Miku", 30);
        fiveSails = new Restaurant("Five_Sails_Restaurant", 55);

        restaurantList.addRestaurant(japadog);
        restaurantList.addRestaurant(miku);
        restaurantList.addRestaurant(fiveSails);

        recList = new RecList(restaurantList);
        recList.addAll(restaurantList);
    }

    @Test
    // save budget to file
    void testWriteBudget() {
        testWriter.write(budget);
        testWriter.close();

        // try to read budget and verify that it has the expected value
        try {
            List<String> balance = Loader.readFile(new File(TEST_BUDGET));
            int amount = Integer.parseInt(balance.get(0));
            budget = new FoodBudget(amount);
            assertEquals(40, budget.getBudget());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (NegativeAmountException e) {
            fail("NegativeAmountException should not have been thrown");
        }
    }

    @Test
    // save list to file
    void testWriteList() {
        testListWriter.write(recList);
        testListWriter.close();

        try {
            AllRestaurants allRestaurants = Loader.readRestaurants(new File(TEST_LIST));
            RecList testList = new RecList(allRestaurants);
            testList.addAll(allRestaurants);
            assertEquals(3, testList.length());
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }


}
