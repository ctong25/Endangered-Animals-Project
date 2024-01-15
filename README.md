# Endangered Animals Project

### An application to further your knowledge about endangered animals.

This application will allow a user to create a *new animal profile* by filling in its information and 
be able to use these animal profiles to create a list of endangered animals they are interested 
in following & learning more about.

Each animal profile includes:
- The animal's name
- How many are left in the wild
- Their conservation status
- Places in the world that they inhabit

The people who will use it include: 
- Animal conservationists & environmentalists (ecologists)
- Students
- The general public interested in animal conservation

This project interests me because I've always loved animals growing up. However, 
the concept of **endangered animals** hadn't occurred to me until technology became more prevalent in my life, 
which was around high school. Because of this, I want to help increase awareness about **endangered animals** 
to the bigger community through technology in an organization application for users to keep track of the 
**endangered animals** they know about. Being able to pursue a future career related to 
animal conservation and technology would be an incredible opportunity for me to do something
I'm passionate about so *I hope this project allows me to learn and gain more experience about this topic.*


## User Stories
As a user I want to create an animal profile

As a user I want to add an animal profile to my list

As a user I want to be able to determine if an animal is already in my list

As a user I want to be able to create a separate list that stores only Extinct animals

As a user I want to be able to delete an animal profile from my list

As a user I want to be able to save my Endangered Animal list to file

As a user I want to be able to load my Endangered Animal list from file

## Phase 4: Task 2
Wed Nov 24 20:55:22 PST 2021

Animal profile has been added to the Endangered Animal List.


Wed Nov 24 20:55:22 PST 2021

Animal profile has been added to the Endangered Animal List.


Wed Nov 24 20:55:27 PST 2021

Animal profile has been removed from the Endangered Animal List.


Wed Nov 24 20:55:44 PST 2021

Animal profile has been added to the Endangered Animal List.

## Phase 4: Task 3

In my UML Class Design Diagram, my EndangeredAnimalList class has a list of endangered animals where each animal 
object is from the EndangeredAnimal class. 
They both implement the interface Writable which returns a JSONObject. 
The GraphicalUserInterface class has a single field of the class EndangeredAnimalList, the class JSONReader, and 
the class JSONWriter. 
The EventLog class which is floating apart from the rest of the diagram has a collection of events where each event 
is from the Event class.
- In terms of refactoring if I had more time, I would work on the EndangeredAnimalApp or GraphicalUserInterface class 
because some of the code in one method could be moved to a helper method to clearly differentiate the purpose of what 
the method should be doing.
- This would make the code look cleaner and also easier to follow if someone else was to look at my code.