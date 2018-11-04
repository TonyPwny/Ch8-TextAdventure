import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private Item item;
    private ArrayList<Item> itemsInRoom;    // stores items in this room

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        itemsInRoom = new ArrayList<Item>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    public void setItem(Item item)
    {
        itemsInRoom.add(item);
    }
    
    public void removeItem(Item item)
    {
        itemsInRoom.remove(item);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getItemsString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:\t";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += exit + " ";
        }
        return returnString;
    }
    
    private String getItemsString()
    {
        String returnString = "Items:\t";
        for(Item item : itemsInRoom ) {
            returnString += item.getID() + "\n\t\t- " + item.getName() +
                            "\n\t\t- weight: " + item.getWeight() + "\n\t";
        }
        return returnString;
    }
    
    public boolean hasItem(String itemID)
    {
        boolean itemTest = false;
        for(Item item : itemsInRoom) {
            if(itemID.equals(item.getID())) {
                itemTest = true;
            }
        }
        return itemTest;
    }
    
    public Item getItem(String itemID)
    {
        Item returnItem = null;
        for(Item item : itemsInRoom) {
            if(itemID.equals(item.getID())) {
                returnItem = item;
            }
        }
        return returnItem;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

