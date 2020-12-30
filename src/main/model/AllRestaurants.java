package model;

import java.util.ArrayList;
import java.util.List;

//List of all restaurants
public class AllRestaurants {
    private List<Restaurant> restaurants;

    //EFFECTS: constructs an empty list of restaurants
    public AllRestaurants() {
        restaurants = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a restaurant into the list
    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    //EFFECTS: returns the list of restaurants
    public List<Restaurant> getList() {
        return restaurants;
    }

    //MODIFIES: AllRestaurants
    //EFFECTS: Data of all restaurants
    public void listOfAllRestaurants() {
        Restaurant danbo = new Restaurant("Danbo", 15);
        Restaurant japadog = new Restaurant("Japadog", 10);
        Restaurant fable = new Restaurant("Fable", 20);
        Restaurant miku = new Restaurant("Miku", 30);
        Restaurant fiveSails = new Restaurant("Five_Sails_Restaurant", 55);
        addRestaurant(danbo);
        addRestaurant(japadog);
        addRestaurant(fable);
        addRestaurant(miku);
        addRestaurant(fiveSails);
    }


}

