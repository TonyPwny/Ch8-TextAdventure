/**
 * 
 * Modification of "World of Zuul" application
 * 
 * @author Anthony Tiongson
 * @version 2018.11.04
 * 
 * Original credits
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @originalAuthor  Michael Kölling and David J. Barnes
 * @originalVersion 2011.08.10
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    PABLO("pablo"), WILLY("willy"), EXIT("exit"), GO("go"), LOOK("look"),
    TAKE("take"), DROP("drop"), EAT("eat"), ITEM("item"), QUIT("quit"),
    HELP("help"), UNKNOWN("?");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
