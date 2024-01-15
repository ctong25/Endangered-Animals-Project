package ui;

import model.EndangeredAnimal;
import model.EndangeredAnimalList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//Endangered Animals' application
//This [EndangeredAnimalApp] references code from the [TellerApp]
//Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp
//This [JsonReaderTest] references code from the [JsonSerializationDemo]
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class EndangeredAnimalApp {
    private static final String JSON_STORE = "./data/endangeredAnimalList.json";
    private Scanner input;
    private EndangeredAnimalList endangeredAnimalList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs endangered animal list and runs application
    public EndangeredAnimalApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        endangeredAnimalList = new EndangeredAnimalList("My animal profiles");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    private void runApp() {
        boolean continueTask = true;
        String command = null;

        init();

        while (continueTask) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                continueTask = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nDone");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            inputAnimal();
        } else if (command.equals("r")) {
            removeAnimal();
        } else if (command.equals("e")) {
            extinctList();
        } else if (command.equals("d")) {
            found();
        } else if (command.equals("p")) {
            printAnimalNames();
        } else if (command.equals("s")) {
            saveEndangeredAnimalList();
        } else if (command.equals("l")) {
            loadEndangeredAnimalList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void init() {
        input = new Scanner(System.in);
        endangeredAnimalList = new EndangeredAnimalList("My animal profiles");
    }

    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nChoose from:");
        System.out.println("\ta -> add a new animal profile to the list");
        System.out.println("\tr -> remove an animal profile from the list");
        System.out.println("\te -> create a separate list of animals that have a status of Extinct");
        System.out.println("\td -> determine if an animal is in the list");
        System.out.println("\tp -> print the names of the animals in the list");
        System.out.println("\ts -> save animal profiles to file");
        System.out.println("\tl -> load animal profiles from file");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: ask the user for info about the animal and add that animal to their list
    private void inputAnimal() {
        System.out.println("Name?");
        String name = input.next();
        System.out.println("Conservation Status?");
        String status = input.next();
        System.out.println("Population?");
        String population = input.next();
        int pop = Integer.parseInt(population);
        System.out.println("Description?");
        String description = input.next();
        EndangeredAnimal a1 = new EndangeredAnimal(name, status, pop, description);
        System.out.println("You inputted: \nName: " + a1.getName()
                + "\nConservation Status: " + a1.getConservationStatus()
                + "\nPopulation: " + a1.getPopulation()
                + "\nDescription: " + a1.getDescription());
        endangeredAnimalList.addEndangeredAnimal(a1);
        System.out.println("The animal profile " + a1.getName() + " has been added to your list.");
    }

    //MODIFIES: this
    //EFFECTS: remove the given animal profile from the list
    private void removeAnimal() {
        System.out.println("\nWhat is the name of the animal you want to remove?");
        String remove = input.next();
        endangeredAnimalList.removeEndangeredAnimal(remove);
        System.out.println("The animal profile " + remove + " has been removed from your list.");
    }

    //MODIFIES: this
    //EFFECTS: find the extinct animals in your list and put them in a new list
    private void extinctList() {
        LinkedList<EndangeredAnimal> printList = endangeredAnimalList.extinctList();
        for (EndangeredAnimal e: printList) {
            System.out.println(e.getName());
        }
    }

    //EFFECTS: determine if the given animal is in the list
    private void found() {
        System.out.println("\nWhat is the name of the animal you want to find?");
        String find = input.next();

        if (endangeredAnimalList.alreadyInList(find)) {
            System.out.println("The animal " + find + " is in the list");
        } else {
            System.out.println("The animal " + find + " is not in the list.");
        }
    }

    // EFFECTS: prints all the animal names in the endangered animal list to the console
    private void printAnimalNames() {
        List<EndangeredAnimal> animals = endangeredAnimalList.getAnimals();
        for (EndangeredAnimal e : animals) {
            System.out.println(e.getName());
        }
    }

    // EFFECTS: saves the endangered animal list to file
    private void saveEndangeredAnimalList() {
        try {
            jsonWriter.open();
            jsonWriter.write(endangeredAnimalList);
            jsonWriter.close();
            System.out.println("Saved " + endangeredAnimalList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads endangered animal list from file
    private void loadEndangeredAnimalList() {
        try {
            endangeredAnimalList = jsonReader.read();
            System.out.println("Loaded " + endangeredAnimalList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

