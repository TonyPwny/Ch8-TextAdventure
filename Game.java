/**
 * Modification of "World of Zuul" application
 * 
 * Modified to simulate waking up in your apartment late for work.
 * You must find your keys and your way out to your car.
 * If you don't have your keys upon leaving the apartment, you
 * may get yourself locked out and with no way to get to work.
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
 * @originalAuthor  Michael Kölling and David J. Barnes
 * @originalVersion 2011.08.10
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Room beforeLocked, lockedRoom, beforeFinal, finalRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createLevel();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * Create all the items and place them into rooms.
     */
    private void createLevel()
    {
        Room livingRoom, diningRoom, hub, bedroom, walkInCloset, office, artStudio,
            bathroom, kitchen, pantry, foyer, vestibule, outside, prius;
      
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
                            "There's a windowed door that automatically locks the\n" +
                            "outer handleset with a view out into a vestibule to the\n" +
                            "north and the stairs back up to the second floor are\n" +
                            "to the southeast");
        vestibule = new Room("in the vestibule to the household...\n" +
                            "The outside world can be seen on the other side of the door\n" +
                            "opposite from the door leading into the abode on the\n" +
                            "southern wall");
        outside = new Room("outside...\n" +
                            "Your Prius is parked in the driveway to the right/east\n" +
                            "of you.  The entrance back into the apartment is south");
        prius = new Room("finally at your car...\n" +
                            "Time to get to work");
        beforeLocked = vestibule;
        lockedRoom = foyer;
        beforeFinal = outside;
        finalRoom = prius;  // end game in Prius
        
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
        vestibule.setExit("north", outside);
        outside.setExit("south", vestibule);
        outside.setExit("east", prius);
        
        // create items
        Item dust, pabloBowl, willyBowl, waterBowl, television, macBookAir, sharpie,
                keys, iPhone, watch;
        Food chocolate, magicCookie;
        
        dust = new Item();
        pabloBowl = new Item("pablosbowl", "Pablo's food bowl",
                                "It typically goes to the left of the water bowl.", 2);
        willyBowl = new Item("willysbowl", "Wilhemina's food bowl",
                                "It's usually empty and to the right of the water bowl.", 2);
        waterBowl = new Item("waterbowl", "a shared water bowl for the kitties",
                                "It's bigger than the food bowls.", 3);
        television = new Item("tv", "a curved Samsung 4K TV",
                                "Bought from Costco Online...\nIt's bright and beauitful...\nLET THE BINGING BEGIN!!", 60);
        macBookAir = new Item("macbook", "an 11-inch i7 MacBook Air from 2015",
                                "It's amazing and the last of its kind...\nIt's SSD has been upgraded to 512GB!", 4);
        sharpie = new Item("sharpie", "a black Sharpie permanent marker",
                                "Very useful.", 1);
        keys = new Item("keys", "a keychain with a FOB for a Toyota",
                                "The FOB is covered with a yellow plastic protector...", 1);
        iPhone = new Item("iphone", "an old 64GB iPhone 6s",
                                "It runs really well still...technology!!", 2);
        watch = new Item("watch", "a 42mm series 3 Apple Watch",
                                "It's an excellent activity monitor.", 1);
        chocolate = new Food();
        magicCookie = new Food("cookie", "a magical cookie",
                                "It's a cookie...and it's magical!", 1, true);
        
        // put items into rooms
        
        hub.setItem(dust);
        diningRoom.setItem(pabloBowl);
        diningRoom.setItem(willyBowl);
        diningRoom.setItem(waterBowl);
        livingRoom.setItem(television);
        office.setItem(macBookAir);
        artStudio.setItem(sharpie);
        artStudio.setItem(dust);
        bedroom.setItem(keys);
        bedroom.setItem(iPhone);
        bedroom.setItem(watch);
        kitchen.setItem(chocolate);
        kitchen.setItem(magicCookie);
        foyer.setItem(dust);
        
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
            
            if(player.getRoom() == finalRoom && player.hasItem("keys")) {
                finished = true;
                System.out.println("The car unlocks as you touch the door handle.\n" +
                                    "You start your car and head onto work.");
            }
            else if(player.getRoom() == finalRoom)
            {
                System.out.println("You realize you don't have your keys though...\n" +
                                    "You can't get in! *sad trombone*\n" +
                                    "You are still outside...");
                player.setRoom(beforeFinal);
            }
            else{
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
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
                System.out.println("You sing a high pitched singsong for Wilhemina...\n" +
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
                
            case TAKE:
                take(command);
                break;
                
            case DROP:
                drop(command);
                break;
                
            case EAT:
                eat(command);
                break;
                
            case ITEM:
                printInventory();
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
        System.out.println("You are sleepy. You are cranky. You wander");
        System.out.println("around in the apartment not wanting to go to work...");
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
    
    private void printInventory()
    {
        System.out.println(player.getInventoryString());
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
            System.out.println("There is no door, entryway, or stairs!");
        }
        else if (player.getRoom() == beforeLocked && nextRoom == lockedRoom && !player.hasItem("keys"))
        {
            System.out.println("Oh wow you locked yourself out!\n" +
                                "Game over, man...");
        }
        else if (player.getRoom() == beforeLocked && nextRoom == lockedRoom && player.hasItem("keys"))
        {
            System.out.println("Luckily you remembered your keys because this\n" +
                                "door automatically locks...");
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
        }
        else {
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
        }
    }
    
    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return;
        }

        String itemName = command.getSecondWord();

        // Try to take item

        player.takeItem(itemName);
    }
    
    private void drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }

        String itemName = command.getSecondWord();

        // Try to take item

        player.dropItem(itemName);
    }
    
    private void eat(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Eat what?");
            return;
        }

        String food = command.getSecondWord();

        // Try to take item

        player.eat(food);
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
