package persistence;

import model.EndangeredAnimal;
import model.EndangeredAnimalList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//This [JsonReaderTest] references code from the [JsonSerializationDemo]
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            EndangeredAnimalList ea = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAnimalProfiles() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAnimalProfiles.json");
        try {
            EndangeredAnimalList ea = reader.read();
            assertEquals("My animal profiles", ea.getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAnimalProfiles() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAnimalProfiles.json");
        try {
            EndangeredAnimalList ea = reader.read();
            assertEquals("My animal profiles", ea.getName());
            List<EndangeredAnimal> animals = ea.getAnimals();
            checkEndangeredAnimal("Tiger", "Endangered", 3900, "Tigers are found only in Asia.",
                    animals.get(0));
            checkEndangeredAnimal("Blue Whale", "Endangered", 25000,
                    "Blue Whales are found in all oceans except the Arctic.", animals.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}