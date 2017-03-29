package confusedgriffinproductions.dudesinadungeon;

/**
 * Portrait Class
 * Users will be able to take a picture of themselves or anything to represent
 * themselves in the game
 *
 * @author Nicholas Allaire
 * @version 1.0
 */
public class Portrait {

    // Declare Portrait properties
    private int id;
    private String resource;

    // Constructors
    // Empty constructor
    public Portrait() {}

    // Constructor w/everything
    public Portrait(int id, String resource){
        this.id = id;
        this.resource = resource;
    }

    // Constructor w.o/ id
    public Portrait(String resource){
        this.resource = resource;
    }

    // Getters & Setters
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
