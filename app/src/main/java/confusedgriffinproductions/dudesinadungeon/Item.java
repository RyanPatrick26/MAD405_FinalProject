package confusedgriffinproductions.dudesinadungeon;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Item Class
 * Items for player Characters to acquire and use.
 *
 * @author Nicholas Allaire
 * @version 1.0
 */
public class Item implements Comparable<Item>{

    // Declare Item properties
    private int id;
    private String name;
    private double price;
    private String type;
    private String description;
    private int dmg_def;
    private int range;
    private int imageId;



    // Constructors
    // Empty constructor
    public Item() {}

    // Constructor for weapons/shields
    public Item(int id, String name, double price, String type, String description, int dmg_def, int range, int imageId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.dmg_def = dmg_def;
        this.range = range;
        this.imageId = imageId;
    }

    // Constructor for non-weapons/shields
    public Item(int id, String name, double price, String type, String description, int imageId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.imageId = imageId;

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

    public int getRange() { return range; }

    public void setRange(int range) {
        this.range = range;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(Item o) {
        return o.getType().compareTo(this.getType());
    }
}
