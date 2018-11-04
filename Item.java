
/**
 * Item class to simply manage simple items found in rooms.
 *
 * @author Anthony Tiongson
 * @version 2018.11.03
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String id, name, description;
    private int weight;

    /**
     * No-arg Constructor for objects of class Item.
     */
    public Item()
    {
        // initialise instance variables.
        id = "dirt";
        name = "dust and cat hair";
        description = "This stuff is everywhere!";
        weight = 0;
    }
    
    /**
     * Constructor for objects of class Item.
     * @param String description of object
     * @param int weight of object
     */
    public Item(String id, String name, String description, int weight)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Get an item's id.
     * 
     * @return String id of an item
     */
    public String getID()
    {
        return id;
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
    
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Get an item's weight.
     * 
     * @return integer weight of an item
     */
    public int getWeight()
    {
        return weight;
    }
}
