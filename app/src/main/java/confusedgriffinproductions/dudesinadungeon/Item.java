package confusedgriffinproductions.dudesinadungeon;

public class Item {

    // Declare Item properties
    private int id;
    private String name;
    private int price;
    private String type;
    private int dmg_def;

    // Constructors
    // Empty constructor
    public Item() {}

    // Constructor for weapons/shields
    public Item(int id, String name, int price, String type, int dmg_def) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.dmg_def = dmg_def;
    }

    // Constructor for non-weapons/shields
    public Item(int id, String name, int price, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
}
