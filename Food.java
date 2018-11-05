
/**
 * Food class is a subclass of Item to recognize special items that can be eaten.
 * It has an additional field to signify if the food is magical or not.
 *
 * @author Anthony Tiongson
 * @version 2018.11.04
 */
public class Food extends Item
{
    
    private boolean magical;

    /**
     * No-Arg Constructor for objects of class Food
     * It makes a non-magical bar of chocolate.
     */
    public Food()
    {
        // initialise instance variables
        super("chocolate", "a bar of chocolate", "Who doesn't love chocolate?!", 1);
        magical = false;
    }
    
    /**
     * Constructor for objects of class Food.
     * @param String id of food
     * @param String name of food
     * @param String description of food
     * @param integer weight of food
     * @param boolean magical to signify if food is magical or not
     */
    public Food(String id, String name, String description, int weight, boolean magical)
    {
        super(id, name, description, weight);
        
        this.magical = magical;
    }
    
    /**
     * Method to test if a food object is magical or not.
     * 
     * @return boolean true if food is magical false otherwise
     */
    public boolean isMagical()
    {
        return magical;
    }
}
