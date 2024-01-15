package persistence;

import model.EndangeredAnimal;
import model.EndangeredAnimalList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Event;
import model.EventLog;
import org.json.*;

//This [JsonReader] references code from the [JsonSerializationDemo]
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads endangered animal list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads endangered animal list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public EndangeredAnimalList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEndangeredAnimalList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses endangered animal list from JSON object and returns it
    private EndangeredAnimalList parseEndangeredAnimalList(JSONObject jsonObject) {
        JSONArray name = jsonObject.getJSONArray("list");
        EndangeredAnimalList ea = new EndangeredAnimalList("My animal profiles");
        addAnimalToEndangeredAnimalList(ea, name);
        return ea;
    }

    // MODIFIES: ea
    // EFFECTS: parses endangered animals from JSON object and adds them to endangered animal list
    private void addAnimalToEndangeredAnimalList(EndangeredAnimalList ea, JSONArray listOfAnimals) {
        for (Object json : listOfAnimals) {
            JSONObject nextAnimal = (JSONObject) json;
            addAnimalProfile(ea, nextAnimal);

        }
    }

    // MODIFIES: ea
    // EFFECTS: parses endangered animal from JSON object and adds it to endangered animal list
    private void addAnimalProfile(EndangeredAnimalList ea, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String status = jsonObject.getString("status");
        int population = jsonObject.getInt("population");
        String description = jsonObject.getString("description");
        EndangeredAnimal animal = new EndangeredAnimal(name, status, population, description);
        ea.addEndangeredAnimal(animal);
    }
}
