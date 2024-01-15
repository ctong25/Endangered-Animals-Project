package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class EndangeredAnimalListTest {
    private EndangeredAnimalList testList;
    private EndangeredAnimal a1;
    private EndangeredAnimal a2;
    private EndangeredAnimal a3;
    private EndangeredAnimal a4;
    private EndangeredAnimal a5;
    private EndangeredAnimal a6;

    @BeforeEach
    public void setup() {
        testList = new EndangeredAnimalList("My animal profiles");
        a1 = new EndangeredAnimal("Tiger", "Endangered", 3900,
                "Tigers are found only in Asia.");
        a2 = new EndangeredAnimal("Blue Whale", "Endangered", 25000,
                "Blue Whales are found in all oceans except the Arctic.");
        a3 = new EndangeredAnimal("Woolly Mammoth", "Extinct", 0,
                "Woolly Mammoths used to be found in North America.");
        a4 = new EndangeredAnimal("Polar Bear", "Vulnerable", 31000,
                "Polar Bears are found in North America and Eurasia.");
        a5 = new EndangeredAnimal("Black Rhino", "Critically Endangered", 5000,
                "Black Rhinos are found only in Africa.");
        a6 = new EndangeredAnimal("Dodo", "Extinct", 0,
                "Dodo's were a type of bird.");
    }

    @Test
    void testConstructor() {
        assertEquals("Tiger", a1.getName());
        assertEquals("Endangered", a1.getConservationStatus());
        assertEquals(3900, a1.getPopulation());
        assertEquals("Tigers are found only in Asia.", a1.getDescription());
    }

    @Test
    public void testAddEndangeredAnimal() {
        assertTrue(testList.addEndangeredAnimal(a1));
    }

    @Test
    public void testRemoveEndangeredAnimal() {
        testList.addEndangeredAnimal(a1);
        assertEquals(a1.getName(), "Tiger");
        assertFalse(testList.removeEndangeredAnimal("Cat"));
        assertTrue(testList.removeEndangeredAnimal("Tiger"));
    }

    @Test
    public void testExtinctList() {
        testList.addEndangeredAnimal(a1);
        testList.addEndangeredAnimal(a2);
        testList.addEndangeredAnimal(a3);
        testList.addEndangeredAnimal(a4);
        testList.addEndangeredAnimal(a5);
        testList.addEndangeredAnimal(a6);
        LinkedList<EndangeredAnimal> testExtinctList = new LinkedList<>();
        testExtinctList.addLast(a3);
        testExtinctList.addLast(a6);
        assertEquals(testList.extinctList(), testExtinctList);
    }

    @Test
    public void testAlreadyInList() {
        testList.addEndangeredAnimal(a1);
        testList.addEndangeredAnimal(a2);
        testList.addEndangeredAnimal(a3);
        testList.addEndangeredAnimal(a4);
        assertTrue(testList.alreadyInList("Tiger"));
        assertFalse(testList.alreadyInList("Dog"));
    }
}