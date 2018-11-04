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
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits and items together.
     */
    private void createRooms()
    {
        Room livingRoom, diningRoom, hub, bedroom, walkInCloset, office, artStudio,
            bathroom, kitchen, pantry, foyer, vestibule;
      
        // create the rooms
        livingRoom = new Room("in half of a finished attic...\n" +
                            "It's set up as a living room.  To your north is a TV.\n" +
                            "You can see stairs to the southwest behind a railing\n" +
                            "attached to a wall in the south.  To the southeast is\n" +
                            "an opening to the other side of the attic");
        diningRoom = new Room("in one side of a finished attic...\n" +
                            "Seems to be the dining area; there is a dining table\n" +
                            "and chairs in the middle of the room.  To your north\n" +
                            "is a wall with a clock hanging on it.  A railing is\n" +
                            "attached to it to the northwest leading to stairs\n" +
                            "going down to the floor below.  On the west wall are\n" +
                            "two small food bowls and one larger water bowl");
        hub = new Room("in a central hallway...\n" +
                            "It connects all the rooms and stairways.  There are\n" +
                            "doorways to the northwest, north, southeast, and\n" +
                            "southwest of you.  To your west is a narrow\n" +
                            "stairway headed upwards, and to your east is an\n" +
                            "L-shaped stairyway headed downwards.  Towards the\n" +
                            "south is an open entryway to another room");
        bedroom = new Room("in a simple bedroom...It's pretty bare.\n" +
                            "Guess this is where magic is casted...\n" +
                            "To your northwest is a door and to your northeast\n" +
                            "is the doorway to the central hallway");
        walkInCloset = new Room("in a closet within the bedroom...\n" +
                            "It's so big you can kind of walk around in it...\n" +
                            "to your southwest is the door back into the\n" +
                            "bedroom..");
        office = new Room("in a personal office of some kind...\n" +
                            "Hopefully lots of business deals to be had in here.\n" +
                            "The exit back into the central hallway is to your\n" +
                            "southeast.");
        artStudio = new Room("surrounded by a mess of creative materials in here...\n" +
                            "A canvas and an angled desk sit around all the clutter\n" +
                            "against the northern wall.  The exit back into the\n" +
                            "central hallway is to your southwest");
        bathroom = new Room("in a very small bathroom with the basics...\n" +
                            "Next to the toilet is a large litterbox;\n" +
                            "seems to be a multi-species facility...\n" +
                            "On the western side is the doorway you came from");
        kitchen = new Room("in the kitchen...Some say this is the heart of a home <3\n" +
                            "There's a door on the west wall, and to the northeast\n" +
                            "is the open entryway to back the central hallway");
        pantry = new Room("in a pantry..it's pretty big.  The doorway back to the\n" +
                            "kitchen is to the east");
        foyer = new Room("in the first floor at the bottom of the L-shaped stairs...\n" +
                            "It's small, and just serves as a foyer...\n" +
                            "There's a windowed door with a view out into a vestibule\n" +
                            "to the north with stairs back to the second floor in the\n" +
                            "southeast");
        vestibule = new Room("in the vestibule to the household...\n" +
                            "The outside world can be seen on the other side of the door\n" +
                            "opposite from the door leading into the abode on the\n" +
                            "southern wall");
        
        // initialise room exits
        livingRoom.setExit("southeast", diningRoom);
        diningRoom.setExit("northeast", livingRoom);
        diningRoom.setExit("northwest", hub);
        hub.setExit("west", diningRoom);
        hub.setExit("southwest", bedroom);
        bedroom.setExit("northeast", hub);
        bedroom.setExit("northwest", walkInCloset);
        walkInCloset.setExit("southwest", bedroom);
        hub.setExit("northwest", office);
        office.setExit("southeast", hub);
        hub.setExit("north", artStudio);
        artStudio.setExit("southwest", hub);
        hub.setExit("southeast", bathroom);
        bathroom.setExit("west", hub);
        hub.setExit("south", kitchen);
        kitchen.setExit("northwest", hub);
        kitchen.setExit("west", pantry);
        pantry.setExit("east", kitchen);
        hub.setExit("east", foyer);
        foyer.setExit("southeast", hub);
        foyer.setExit("north", vestibule);
        vestibule.setExit("south", foyer);
        
        // create items
        
        Item dust, pabloBowl, willyBowl, waterBowl, television, macBookAir, sharpie,
                keys, iPhone, watch;
        
        dust = new Item();
        pabloBowl = new Item("Pablo's bowl", "Pablo's food bowl; it typically is placed to\n" +
                                "the left of the water bowl.");
        willyBowl = new Item("Willy's bowl", "Wilhemina's food bowl; its typically empty and\n" +
                                "on the right side of the water bowl.");
        waterBowl = new Item("Water bowl", "A shared water bowl for the kitties.  It's much\n" +
                                "larger than their food bowls and sits between them.");
        television = new Item("Samsung 4K TV", "A curved Samsung 4K TV bought from\n" +
                                "Costco Online.  It's bright and beautiful...\n" +
                                "LET THE BINGING BEGIN!");
        macBookAir = new Item("MacBook Air", "An 11-inch i7 MacBook Air from 2015; the last\n" +
                                "of its kind.  It's internal SSD has been upgraded from\n" +
                                "120GB to 512GB...");
        sharpie = new Item("Sharpie", "A black Sharpie permanent marker.");
        keys = new Item("Keychain", "A keychain with a FOB to a Toyota.");
        iPhone = new Item("iPhone", "An old 64GB iPhone 6s...It still works really well...");
        watch = new Item("Apple Watch", "A 42mm series 3 Apple Watch.");
        
        // put items into rooms
        
        hub.setItem(dust);
        diningRoom.setItem(pabloBowl);
        diningRoom.setItem(willyBowl);
        diningRoom.setItem(waterBowl);
        livingRoom.setItem(television);
        office.setItem(macBookAir);
        artStudio.setItem(sharpie);
        bedroom.setItem(keys);
        bedroom.setItem(iPhone);
        bedroom.setItem(watch);
        
        player = new Player("player", livingRoom);  // start game in the living room
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
        System.out.println("You've overslept and you have work today!");
        System.out.println("Can you find your keys and your way out of\n" +
                            "the apartment before it's too late?!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
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
            
            // 8.15 (p 303) - add another command, ie eat with a simple text response 
            case PABLO:
                System.out.println("You call out to Pablo...\n" +
                                    "You wonder where that feisty feline is...");
                break;
                
            case WILLY:
                System.out.println("You sing a high pitched singsong for Willy...\n" +
                                    "You wonder where she's napping the day away...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case EXIT:
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
        // 8.16 (p 305) - Streamline printing of available commands
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    // 8.14 - add the look command to your game (p 303)
    /**
     * A look command to reiterate your current location.
     */
    private void look()
    {
        System.out.println(player.getRoom().getLongDescription());
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
        Room nextRoom = player.getRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
        }
    }

    /** 
     * "Quit" or "exit" was entered. Check the rest of the command to see
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
