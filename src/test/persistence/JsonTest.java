package persistence;

import model.EndangeredAnimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

//This [JsonTest] references code from the [JsonSerializationDemo]
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkEndangeredAnimal(String name, String status, int population, String description,
                                         EndangeredAnimal animal) {
        assertEquals(name, animal.getName());
        assertEquals(status, animal.getConservationStatus());
        assertEquals(population, animal.getPopulation());
        assertEquals(description, animal.getDescription());
    }
}
