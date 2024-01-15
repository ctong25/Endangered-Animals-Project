package ui;

import model.EndangeredAnimal;
import model.EndangeredAnimalList;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

//Endangered Animals' Graphical User Interface
//This [GraphicalUserInterface] references code from:
//Link: https://www.youtube.com/watch?v=b3uLbrSuZaA
public class GraphicalUserInterface extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/endangeredAnimalList.json";
    private JTextField tfAnimalName;
    private JTextField tfStatus;
    private JTextField tfPopulation;
    private JTextField tfDescription;
    private JTextField tfSearchRemove;

    private JLabel lblAnimalName;
    private JLabel lblStatus;
    private JLabel lblPopulation;
    private JLabel lblDescription;
    private JLabel lblSearch;

    private JButton btnSave;
    private JButton btnRemove;
    private JButton btnAdd;
    private JButton btnSearch;
    private JButton btnDisplay;
    private JButton btnLoad;
    private JButton btnQuit;

    private JTextArea taDisplay;

    private JPanel panelBtn;
    private JPanel panelDetail;
    private JPanel panelDisplay;
    private JScrollPane scroll;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private EndangeredAnimalList list;

    //EFFECTS: constructs the graphical user interface
    public GraphicalUserInterface() {
        super("Animal Profiles");

        list = new EndangeredAnimalList("My animal profiles");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setLayout(new BorderLayout());

        panelDetail = new JPanel();
        panelDetail.setLayout(new GridLayout(4, 3, 1, 1));

        panelDisplay = new JPanel();
        panelDisplay.setLayout(new FlowLayout());

        panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout());

        setUpLabel();

        setUpText();

        setUpButton();

        setUpPanel();

        btnSave.addActionListener(this);
        btnLoad.addActionListener(this);
        btnSearch.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnQuit.addActionListener(this);

        setVisible(true);
    }

    //EFFECTS: Part of the graphical user interface constructor (Creates the Panels)
    private void setUpPanel() {
        panelDetail.add(lblAnimalName);
        panelDetail.add(tfAnimalName);
        panelDetail.add(lblStatus);
        panelDetail.add(tfStatus);
        panelDetail.add(lblPopulation);
        panelDetail.add(tfPopulation);
        panelDetail.add(lblDescription);
        panelDetail.add(tfDescription);

        panelDisplay.add(lblSearch);
        panelDisplay.add(tfSearchRemove);
        panelDisplay.add(scroll);

        try {
            //panelDetail.add(displayImage());
            panelDisplay.add(displayImage());
        } catch (IOException exc) {
            taDisplay.setText("Error displaying image.");
        }

        add(panelBtn, BorderLayout.SOUTH);
        add(panelDetail, BorderLayout.CENTER);
        add(panelDisplay, BorderLayout.EAST);
    }

    //EFFECTS: Part of the graphical user interface constructor (Sets up the Text Fields)
    private void setUpText() {
        tfAnimalName = new JTextField();
        tfAnimalName.setColumns(10);
        tfStatus = new JTextField();
        tfStatus.setColumns(10);
        tfPopulation = new JTextField();
        tfPopulation.setColumns(10);
        tfDescription = new JTextField();
        tfDescription.setColumns(10);
        tfSearchRemove = new JTextField(10);

        taDisplay = new JTextArea(10,20);
        taDisplay.setSize(20, 50);
        scroll = new JScrollPane(taDisplay);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    //EFFECTS: Part of the graphical user interface constructor (Creates the Labels)
    private void setUpLabel() {
        lblAnimalName = new JLabel("Animal Name:");
        lblStatus = new JLabel("Conservation Status:");
        lblPopulation = new JLabel("Population:");
        lblDescription = new JLabel("Description");
        lblSearch = new JLabel("Enter Animal Name:");
    }

    //EFFECTS: Part of the graphical user interface constructor (Creates the Buttons)
    private void setUpButton() {
        btnSave = new JButton("Save");
        btnLoad = new JButton("Load");
        btnAdd = new JButton("Add");
        btnDisplay = new JButton("Display");
        btnSearch = new JButton("Search");
        btnRemove = new JButton("Remove");
        btnQuit = new JButton("Quit");

        panelBtn.add(btnSave);
        panelBtn.add(btnLoad);
        panelBtn.add(btnAdd);
        panelBtn.add(btnRemove);
        panelBtn.add(btnDisplay);
        panelBtn.add(btnSearch);
        panelBtn.add(btnQuit);
    }

    //EFFECTS: perform a specific action based on which button is clicked (process user action)
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            userAddAnimal();
        } else if (e.getSource() == btnDisplay) {
            String animalNames = "";
            List<EndangeredAnimal> animals = list.getAnimals();
            for (EndangeredAnimal ea : animals) {
                animalNames = animalNames + ea.getName() + "\n";
            }
            taDisplay.setText(animalNames);
        } else if (e.getSource() == btnSearch) {
            alreadyInList();
        } else if (e.getSource() == btnRemove) {
            userRemoveAnimal();
        } else if (e.getSource() == btnSave) {
            writeJson();
        } else if (e.getSource() == btnLoad) {
            readJson();
        } else if (e.getSource() == btnQuit) {
            printLog(EventLog.getInstance());
            System.exit(0);
        }
    }

    // EFFECTS: prints each event in the EventLog to console
    public void printLog(EventLog el) {
        for (model.Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: if the animal is in the list, remove the given animal profile from the list
    private void userRemoveAnimal() {
        String text = tfSearchRemove.getText();
        List<EndangeredAnimal> animals = list.getAnimals();
        for (EndangeredAnimal ea: animals) {
            if (text.equals(ea.getName())) {
                taDisplay.setText(text + " has been removed from the list.");
                list.removeEndangeredAnimal(text);
            } else {
                taDisplay.setText("Can't remove animal from list.");
            }
        }
    }

    //EFFECTS: determine if the given animal is in the list
    private void alreadyInList() {
        String text = tfSearchRemove.getText();
        boolean found = list.alreadyInList(text);
        if (found) {
            taDisplay.setText(text + " is already in the list.");
        } else {
            taDisplay.setText("Not found in list.");
        }
    }

    //MODIFIES: this
    //EFFECTS: have the user input info about an animal and add that animal to their list of endangered animals
    private void userAddAnimal() {
        String name = tfAnimalName.getText();
        String status = tfStatus.getText();
        int population = Integer.parseInt(tfPopulation.getText());
        String description = tfDescription.getText();
        list.addEndangeredAnimal(new EndangeredAnimal(name, status, population, description));
        tfAnimalName.setText("");
        tfStatus.setText("");
        tfPopulation.setText("");
        tfDescription.setText("");
        taDisplay.setText(name + " has been added to the list.");
    }

    // MODIFIES: this
    // EFFECTS: loads endangered animal list from file
    private void readJson() {
        try {
            list = jsonReader.read();
        } catch (IOException exc) {
            taDisplay.setText("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: saves the endangered animal list to file
    private void writeJson() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
        } catch (FileNotFoundException exc) {
            taDisplay.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    //This [displayImage] references code from:
    //Link: https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    //MODIFIES: this
    //EFFECTS: display an image and scale it to a smaller size
    public JLabel displayImage() throws IOException {
        Image myPicture = ImageIO.read(new File("./data/tobs.jpg")).getScaledInstance(450, 300,
                Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        return picLabel;
    }
}