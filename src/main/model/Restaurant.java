package model;

// Represents a restaurant with a name and an average price range in CAD
public class Restaurant {
    private String name;
    private int price;

    //EFFECTS: creates a restaurant with a name and average price range
    public Restaurant(String name, int price) {
        this.name = name;
        this.price = price;
    }

    //EFFECTS: returns name of restaurant
    public String getName() {
        return name;
    }

    //EFFECTS: returns the average price of the restaurants
    public int getPriceRange() {
        return price;
    }

}
