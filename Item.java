
/**
 * Item class to simply manage simple items found in rooms.
 *
 * @author Anthony Tiongson
 * @version 2018.11.03
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;

    /**
     * No-arg Constructor for objects of class Item.
     */
    public Item()
    {
        // initialise instance variables.
        name = "Dirt";
        description = "Dust and cat hair.";
    }
    
    /**
     * Constructor for objects of class Item.
     * @param String description of object
     * @param int weight of object
     */
    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    
    /**
     * Get an item's name.
     * 
     * @return String name of an item
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get an item's description.
     * 
     * @return String description of an item.
     */
    public String getDescription()
    {
        return description;
    }
}
