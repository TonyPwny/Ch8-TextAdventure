
/**
 * Write a description of class Food here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Food extends Item
{
    
    private boolean magical;

    /**
     * Constructor for objects of class Food
     */
    public Food()
    {
        // initialise instance variables
        super("chocolate", "a bar of chocolate", "Who doesn't love chocolate?!", 1);
        magical = false;
    }
    
    public Food(String id, String name, String description, int weight, boolean magical)
    {
        super(id, name, description, weight);
        
        this.magical = magical;
    }
    
    public boolean isMagical()
    {
        return magical;
    }
}
