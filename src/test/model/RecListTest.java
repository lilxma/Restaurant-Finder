package model;

import exceptions.NegativeAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit tests for the RecList class
class RecListTest {
    FoodBudget budget;
    RecList recList;
    AllRestaurants restaurantList;

    private Restaurant fable;
    private Restaurant miku;
    private Restaurant danbo;
    private Restaurant japadog;
    private Restaurant fiveSails;

    @BeforeEach
    public void runBefore() throws NegativeAmountException {
        budget = new FoodBudget(50);
        restaurantList = new AllRestaurants();
        recList = new RecList(restaurantList);


        danbo = new Restaurant("Danbo", 15);
        japadog = new Restaurant("Japadog", 10);
        fable = new Restaurant("Fable", 20);
        miku = new Restaurant("Miku", 30);
        fiveSails = new Restaurant("Five_Sails_Restaurant", 55);
        restaurantList.addRestaurant(danbo);
        restaurantList.addRestaurant(japadog);
        restaurantList.addRestaurant(fable);
        restaurantList.addRestaurant(miku);
        restaurantList.addRestaurant(fiveSails);
    }


    @Test
    public void testInsertRestaurant() {
        assertEquals(0, recList.length());
        recList.insertRestaurants(50);
        assertEquals(4, recList.length());
    }

    @Test
    public void testGetRecList() {
        recList.insertRestaurants(10);
        assertEquals(1, recList.length());
        assertEquals("Japadog average prices are $10" + "\n", recList.getRecList());
    }

    @Test
    public void testAddAll() {
        RecList newList = new RecList(restaurantList);
        newList.addAll(restaurantList);
        assertEquals(5, newList.length());
        assertEquals("Danbo", newList.getRestaurant(0).getName());
        assertEquals(15, newList.getRestaurant(0).getPriceRange());
        assertEquals("Japadog", newList.getRestaurant(1).getName());
        assertEquals("Fable", newList.getRestaurant(2).getName());
        assertEquals("Miku", newList.getRestaurant(3).getName());
        assertEquals("Five_Sails_Restaurant", newList.getRestaurant(4).getName());
    }


}
