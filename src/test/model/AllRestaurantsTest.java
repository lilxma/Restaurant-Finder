package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit Tests for the AllRestaurants class
public class AllRestaurantsTest {
    AllRestaurants restaurantList;
    private Restaurant miku;

    @BeforeEach
    public void runBefore() {
        restaurantList = new AllRestaurants();
        miku = new Restaurant("Miku", 30);
    }

    @Test
    public void testAddRestaurant() {
        Restaurant fiveSails = new Restaurant("Five Sails Restaurant", 55);
        Restaurant fable = new Restaurant("Fable", 20);
        restaurantList.addRestaurant(fiveSails);
        assertEquals("Five Sails Restaurant", restaurantList.getList().get(0).getName());
        restaurantList.addRestaurant(fable);
        assertEquals(20, restaurantList.getList().get(1).getPriceRange());
    }

    @Test
    public void testGetList() {
        assertEquals(0, restaurantList.getList().size());
        restaurantList.addRestaurant(miku);
        assertEquals(1, restaurantList.getList().size());
        assertEquals("Miku", restaurantList.getList().get(0).getName());
    }

    @Test
    public void testListOfAllRestaurants() {
        AllRestaurants allRes = new AllRestaurants();
        allRes.listOfAllRestaurants();
        assertEquals(5, allRes.getList().size());
        assertEquals("Danbo", allRes.getList().get(0).getName());
        assertEquals("Five_Sails_Restaurant", allRes.getList().get(4).getName());
        assertEquals(10, allRes.getList().get(1).getPriceRange());

    }


}
