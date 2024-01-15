package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an Endangered Animal profile that has a name, conservation status, population, and description
public class EndangeredAnimal implements Writable {
    private String name;
    private String status;
    private int population;
    private String description;

    //EFFECTS: constructs an endangered animal profile with a name, status, population, and description
    public EndangeredAnimal(String name, String status, int population, String description) {
        this.name = name;
        this.status = status;
        this.population = population;
        this.description = description;
    }

    //EFFECTS: returns name of the animal
    public String getName() {
        return name;
    }

    //EFFECTS: returns the conservation status
    public String getConservationStatus() {
        return status;
    }

    //EFFECTS: returns the population of the species
    public int getPopulation() {
        return population;
    }

    //EFFECTS: returns description
    public String getDescription() {
        return description;
    }

    @Override
    //EFFECTS: returns a JSONObject that has the animal profile information
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("status", status);
        json.put("population", population);
        json.put("description", description);
        return json;
    }
}
