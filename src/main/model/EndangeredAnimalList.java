package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//Represents a list of endangered animals
public class EndangeredAnimalList implements Writable {
    private String name;
    private LinkedList<EndangeredAnimal> list;
    private LinkedList<EndangeredAnimal> extinct;

    //EFFECTS: constructs an endangered animal list that has a name
    public EndangeredAnimalList(String name) {
        this.name = name;
        list = new LinkedList<EndangeredAnimal>();
        extinct = new LinkedList<EndangeredAnimal>();
    }

    //EFFECTS: return the name of the list
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: adds the animal to the end of the list and returns true and adds the event to the EventLog
    public boolean addEndangeredAnimal(EndangeredAnimal e) {
        list.addLast(e);
        EventLog.getInstance().logEvent(new Event("Animal profile has been added "
                + "to the Endangered Animal List."));
        return true;
    }

    //MODIFIES: this
    //EFFECTS: removes the animal from the list when given its name and return true if it is successful and adds the
    //         event to the EventLog
    //         if the given name is not in the list, return false
    public boolean removeEndangeredAnimal(String s) {
        for (EndangeredAnimal endangered: list) {
            if (s.equals(endangered.getName())) {
                list.remove(endangered);
                EventLog.getInstance().logEvent(new Event("Animal profile has been removed "
                        + "from the Endangered Animal List."));
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: returns a list of all animals with a conservation status of Extinct
    public LinkedList<EndangeredAnimal> extinctList() {
        for (EndangeredAnimal e: list) {
            if (e.getConservationStatus().equals("Extinct")) {
                extinct.addLast(e);
            }
        }
        return extinct;
    }

    //MODIFIES: this
    //EFFECTS: returns true if the given animal name is already in the list and false otherwise
    public boolean alreadyInList(String s) {
        for (EndangeredAnimal endangered: list) {
            if (s.equals(endangered.getName())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns an unmodifiable list of animal profiles in this endangered animal list
    public List<EndangeredAnimal> getAnimals() {
        return Collections.unmodifiableList(list);
    }

    @Override
    // EFFECTS: makes an object that holds a list & returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("list", endangeredToJson());
        return json;
    }

    // EFFECTS: returns animal profiles in this endangered animal list as a JSON array
    private JSONArray endangeredToJson() {
        JSONArray jsonArray = new JSONArray();

        for (EndangeredAnimal e : list) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
