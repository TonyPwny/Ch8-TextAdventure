import java.util.ArrayList;

/**
 * Player class to manage current room location, player inventory, and player name.
 *
 * @author Anthony Tiongson
 * @version 2018.11.4
 */
public class Player
{
    // instance variables
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
    
    /**
     * Constructor for objects of class Player
     * 
     * @param String name to name Player
     * @param Room currentRoom to set starting room
     */
    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        maxBurden = STARTING_MAX_BURDEN;
        equipBurden = 0;
        inventory = new ArrayList<Item>();
    }
    
    /**
     * Method to set the player's current room
     * 
     * @param Room currentRoom of player
     */
    public void setRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    /**
     * Method to return the player's current room
     * 
     * @return Room currentRoom of player
     */
    public Room getRoom() {
        return currentRoom;
    }
    
    /**
     * Method to take an Item object that may be in a room and place it
     * in Player inventory
     * 
     * @param String itemId of an Item object's id attempted to be taken
     */
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
                                    " (" + item.getWeight() + ")" +
                                    "\nin your inventory.  Max equip burden: " + maxBurden);
            }
        }
        else {
            System.out.println("There is no " + itemID + "...");
        }
    }
    
    /**
     * Method to drop an object Item in player inventory with a particular item id.
     * 
     * @param String itemID of an Item object's id attempted to be dropped
     */
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
    
    /**
     * Method to test if a player has an Item object with a particular id in inventory
     * 
     * @param String itemID of a particular Item object's id to test
     * @return true if an Item object with an identical id is in inventory, false otherwise
     */
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
    
    /**
     * Method to return an Item object of a particular id in Player inventory.
     * 
     * @param String itemID of a particular Item object id to test
     * @return Item object that has an identical id
     */
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
        return name;
    }
    
    /**
     * Method to return a detailed string of Player inventory of Item objects
     * Also returns information regarding a Player's equipment burden and maximum
     * alloted burden.
     * 
     * @return String details of Player inventory
     */
    public String getInventoryString()
    {
        
        String returnString = "You are carrying in your inventory:\n";
        
        for(Item item : inventory ) {
            returnString += "\t" + item.getID() + "\n\t\t- " + item.getName() +
                            "\n\t\t- weight: " + item.getWeight() + "\n";
        }
        
        returnString += "\nEquip Burden:\t" + equipBurden + "/" + maxBurden;
        
        return returnString;
    }
    
    /**
     * Method to eat special Item objects known as Food objects in Player inventory.
     * 
     * @param String id of attempted Food object's id to be eaten in inventory
     */
    public void eat(String id)
    {
        if(hasItem(id)) {
            
            Item item = getItem(id);
            Food food;
            if(item instanceof Food)
            {
                food = (Food)item;
                if(food.isMagical())
                {
                    System.out.println("You ate " + food.getName() + "!!\n" +
                                        "Somehow, you feel stronger...");
                    maxBurden = 100;
                    inventory.remove(food);
                }
                else
                {
                    System.out.println("You ate " + food.getName() + ".");
                    inventory.remove(food);
                }
            }
            else
            {
                System.out.println("You cannot eat " + item.getName() + "...\n" +
                                    "I mean maybe you can but you really shouldn't...");
            }
        
        }
        else {
            System.out.println("No " + id + " to eat in inventory.");
        }
    }
}
