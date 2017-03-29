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
    
}
