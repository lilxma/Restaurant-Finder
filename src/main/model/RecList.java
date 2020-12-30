package model;

import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//A list of restaurants that fit within the food budget
public class RecList implements Saveable {
    private List<Restaurant> recommendedList;
    private AllRestaurants allRestaurants;

    //EFFECTS: constructs a new recList
    public RecList(AllRestaurants allRestaurants) {
        recommendedList = new ArrayList<>();
        this.allRestaurants = allRestaurants;

    }

    //MODIFIES: this
    //EFFECTS: adds restaurants within the price range to recList
    public void insertRestaurants(int balance) {

        for (int i = 0; i < allRestaurants.getList().size(); i++) {
            if (balance >= allRestaurants.getList().get(i).getPriceRange()) {
                recommendedList.add(allRestaurants.getList().get(i));
            }
        }
    }

    //EFFECTS: returns a list of all restaurants within the price range
    public String getRecList() {
        String recommendedList = "";
        for (Restaurant r : this.recommendedList) {
            recommendedList += r.getName() + " average prices are $" + r.getPriceRange() + "\n";
        }

        return recommendedList;
    }

    //EFFECTS: returns the size of a list
    public int length() {
        return recommendedList.size();
    }

    //EFFECTS: adds all the elements in allRestaurants to recList
    public void addAll(AllRestaurants allRestaurants) {
        for (int i = 0; i < allRestaurants.getList().size(); i++) {
            recommendedList.add(allRestaurants.getList().get(i));
        }
    }

    //EFFECTS: gets the desired element from the list
    public Restaurant getRestaurant(int i) {
        return recommendedList.get(i);
    }


    @Override
    public void save(PrintWriter printWriter) {
        for (Restaurant i : recommendedList) {
            printWriter.print(i.getName());
            printWriter.print(" average prices are $");
            printWriter.print(i.getPriceRange());
            printWriter.print("\n");
        }

    }
}
