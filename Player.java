
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

    /**
     * No-Arg Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        name = "no name";
    }
    
    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
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
