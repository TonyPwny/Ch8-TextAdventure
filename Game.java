/**
 * Modification of "World of Zuul" application
 * 
 * @author Anthony Tiongson
 * @version 2018.11.03
 * 
 * Original credits
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *  
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room livingRoom, diningRoom, hub, bedroom, walkInCloset, office, artStudio,
            bathroom, kitchen, pantry, foyer, vestibule;
      
        // create the rooms
        livingRoom = new Room("in half of a finished attic...\n" +
                            "It's set up as a living room");
        diningRoom = new Room("in one side of a finished attic...\n" +
                            "Seems to be the dining area");
        hub = new Room("in a central hallway...\n" +
                            "It connects all the rooms and stairways");
        bedroom = new Room("in a simple bedroom...It's pretty bare.\n" +
                            "Guess this is where magic is casted..");
        walkInCloset = new Room("in a closet within the bedroom...\n" +
                            "It's so big you can kind of walk around in it");
        office = new Room("in a personal office of some kind...\n" +
                            "Hopefully lots of business deals to be had in here");
        artStudio = new Room("surrounded by a mess of creative materials in here...\n" +
                            "A canvas and an angled desk sit around the clutter..");
        bathroom = new Room("in a very small bathroom with the basics...\n" +
                            "Next to the toilet is a large litterbox;\n" +
                            "it's a multi-species facility..");
        kitchen = new Room("in the kitchen...Some say this is the heart of a home <3..");
        pantry = new Room("in a pantry..it's pretty big");
        foyer = new Room("in the first floor at the bottom of the L-shaped stairs...\n" +
                            "It's small, and just serves as a foyer...\n" +
                            "There's a windowed door with a view out into a vestibule");
        vestibule = new Room("in the vestibule to the household...\n" +
                            "The outside world can be seen on the other side of the door\n" +
                            "opposite from the door leading into the abode");
        
        // initialise room exits
        livingRoom.setExit("southeast", diningRoom);
        diningRoom.setExit("northeast", livingRoom);
        diningRoom.setExit("northwest", hub);
        hub.setExit("west", diningRoom);
        hub.setExit("southwest", bedroom);
        bedroom.setExit("east", hub);
        bedroom.setExit("northwest", walkInCloset);
        walkInCloset.setExit("southwest", bedroom);
        hub.setExit("northwest", office);
        office.setExit("southeast", hub);
        hub.setExit("north", artStudio);
        artStudio.setExit("southwest", hub);
        hub.setExit("southeast", bathroom);
        bathroom.setExit("west", hub);
        hub.setExit("south", kitchen);
        kitchen.setExit("north", hub);
        kitchen.setExit("west", pantry);
        pantry.setExit("east", kitchen);
        hub.setExit("east", foyer);
        foyer.setExit("southeast", hub);
        foyer.setExit("north", vestibule);
        vestibule.setExit("south", foyer);

        currentRoom = livingRoom;  // start game in the living room
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for visiting...\n" +
                            "Come back soon!");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around in the apartment.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
