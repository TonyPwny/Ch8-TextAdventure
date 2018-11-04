import java.util.ArrayList;

/**
 * Write a description of class Player here.
 *
 * @author Anthony Tiongson
 * @version 2018.11.4
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private Room currentRoom;
    private Item item;
    private ArrayList<Item> inventory;      // stores items player inventory
    private int equipBurden;                // maximum weight of allowed player inventory
    

    /**
     * No-Arg Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        name = "Nameless";
        inventory = new ArrayList<Item>();
    }
    
    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        inventory = new ArrayList<Item>();
    }
    
    public void setRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    public Room getRoom() {
        return currentRoom;
    }

    /**
     * Method to retrieve a player's name
     *
     * @return String name of player
     */
    public String getName()
    {
        // put your code here
        return name;
    }
}
