package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file
public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: saves the saveable to printWriter
    void save(PrintWriter printWriter);

}
