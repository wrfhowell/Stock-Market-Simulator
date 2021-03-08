package persistence;

import org.json.JSONObject;

// CITATION: Writable is class taken from CPSC 210 JsonSerializationDemo project
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
