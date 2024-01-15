package persistence;

import model.EndangeredAnimal;
import model.EndangeredAnimalList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//This [JsonWriterTest] references code from the [JsonSerializationDemo]
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            EndangeredAnimalList ea = new EndangeredAnimalList("My animal profiles");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAnimalProfiles() {
        try {
            EndangeredAnimalList ea = new EndangeredAnimalList("My animal profiles");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAnimalProfiles.json");
            writer.open();
            writer.write(ea);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAnimalProfiles.json");
            ea = reader.read();
            assertEquals("My animal profiles", ea.getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAnimalProfiles() {
        try {
            EndangeredAnimalList ea = new EndangeredAnimalList("My animal profiles");
            ea.addEndangeredAnimal(new EndangeredAnimal("Tiger", "Endangered", 3900,
                    "Tigers are found only in Asia."));
            ea.addEndangeredAnimal(new EndangeredAnimal("Blue Whale", "Endangered", 25000,
                    "Blue Whales are found in all oceans except the Arctic."));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAnimalProfiles.json");
            writer.open();
            writer.write(ea);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAnimalProfiles.json");
            ea = reader.read();
            assertEquals("My animal profiles", ea.getName());
            List<EndangeredAnimal> animals = ea.getAnimals();
            checkEndangeredAnimal("Tiger", "Endangered", 3900, "Tigers are found only in Asia.",
                    animals.get(0));
            checkEndangeredAnimal("Blue Whale", "Endangered", 25000,
                    "Blue Whales are found in all oceans except the Arctic.", animals.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}