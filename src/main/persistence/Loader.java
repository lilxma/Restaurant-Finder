package persistence;

import model.AllRestaurants;
import model.Restaurant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Reads account data from file
public class Loader {
    public static final String DELIMITER = " ";

    // EFFECTS: returns a list of restaurants parsed from file, throws
    // IOException if a exception is raised when opening/reading from file
    public static AllRestaurants readRestaurants(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    // throws IOException if a exception is raised when opening/reading from file
    public static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of restaurants parsed from list of strings
    // where each string contains data for one restaurant
    public static AllRestaurants parseContent(List<String> fileContent) {
        AllRestaurants recList = new AllRestaurants();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            recList.addRestaurant(parseRestaurant(lineComponents));
        }

        return recList;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 4 where element 0 represents the
    // name of the restaurant and element 4 represents the average price
    // EFFECTS: returns a Restaurant constructed from components
    public static Restaurant parseRestaurant(List<String> components) {
        String name = components.get(0);
        int price = Integer.parseInt(components.get(4).substring(1,3));
        return new Restaurant(name, price);
    }

}



