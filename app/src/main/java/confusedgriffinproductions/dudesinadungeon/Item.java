package confusedgriffinproductions.dudesinadungeon;

/**
 * Item Class
 * Items for player Characters to acquire and use.
 *
 * @author Nicholas Allaire
 * @version 1.0
 */
public class Item {

    // Declare Item properties
    private int id;
    private String name;
    private double price;
    private String type;
    private String description;
    private int dmg_def;

    // Constructors
    // Empty constructor
    public Item() {}

    // Constructor for weapons/shields
    public Item(int id, String name, double price, String type, String description, int dmg_def) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.dmg_def = dmg_def;
    }

    // Constructor for non-weapons/shields
    public Item(int id, String name, double price, String type, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;

    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDmg_def() {
        return dmg_def;
    }

    public void setDmg_def(int dmg_def) {
        this.dmg_def = dmg_def;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return getName();
    }
}
