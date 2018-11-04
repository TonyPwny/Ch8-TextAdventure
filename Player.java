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
    private static final int STARTING_MAX_BURDEN = 10;
    private String name;
    private Room currentRoom;
    private Item item;
    private ArrayList<Item> inventory;      // stores items player inventory
    private int equipBurden, maxBurden;     // weight of items in player inventory,
                                            // maximum weight of items a player can carry

    /**
     * No-Arg Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        name = "Nameless";
        maxBurden = STARTING_MAX_BURDEN;
        equipBurden = 0;
        inventory = new ArrayList<Item>();
    }
    
    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        maxBurden = STARTING_MAX_BURDEN;
        equipBurden = 0;
        inventory = new ArrayList<Item>();
    }
    
    public void setRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    public Room getRoom() {
        return currentRoom;
    }
    
    public void takeItem(String itemID)
    {
        if (currentRoom.hasItem(itemID)) {
            
            Item item = currentRoom.getItem(itemID);
            if((item.getWeight() + equipBurden) < maxBurden)
            {
                currentRoom.removeItem(item);
                inventory.add(item);
                equipBurden += item.getWeight();
                System.out.println("You picked up " + item.getName() + ".\n" +
                                    item.getDescription());
            }
            else
            {
                System.out.println("You are unable to carry " + item.getName() +
                                    " in your inventory.");
            }
        }
        else {
            System.out.println("There is no " + itemID);
        }
    }
    
    public void dropItem(String itemID)
    {
        if(hasItem(itemID))
        {
            
            Item item = getItem(itemID);
            
            inventory.remove(item);
            equipBurden -= item.getWeight();
            currentRoom.setItem(item);
            System.out.println("You dropped " + item.getName() + ".");
        }
        else
        {
            System.out.println("You do not have " + itemID + " in your inventory.");
        }
    }
    
    public boolean hasItem(String itemID)
    {
        boolean itemTest = false;
        for(Item item : inventory) {
            if(itemID.equals(item.getID())) {
                itemTest = true;
            }
        }
        return itemTest;
    }
    
    public Item getItem(String itemID)
    {
        Item returnItem = null;
        for(Item item : inventory) {
            if(itemID.equals(item.getID())) {
                returnItem = item;
            }
        }
        return returnItem;
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
