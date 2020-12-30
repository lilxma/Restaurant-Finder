package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit tests for the Restaurant class
class RestaurantTest {
    AllRestaurants restaurantList;
    private Restaurant danbo;
    private Restaurant japadog;

    @BeforeEach
    public void runBefore() {
        restaurantList = new AllRestaurants();
        danbo = new Restaurant("Danbo", 15);
        japadog = new Restaurant("Japadog", 10);

    }

    @Test
    public void testGetName() {
        assertEquals("Danbo", danbo.getName());
    }

    @Test
    public void testGetPriceRange() {
        assertEquals(10, japadog.getPriceRange());
    }



}