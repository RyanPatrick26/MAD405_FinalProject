package confusedgriffinproductions.dudesinadungeon;

/**
 * Spell Class
 * Spells for player Characters to cast against enemies or to help allies, etc.
 *
 * @author Nicholas Allaire
 * @version 1.0
 */
public class Spell {

    // Declare Spell properties
    private int id;
    private String name;
    private String description;
    private String spellType;
    private String components;
    private String effects;
    // Damage or healing
    private int dmg_heal;

    // Constructors
    // Empty constructor
    public Spell() {}

    // Constructor for spells that damage or heal
    public Spell(int id, String name, String description, String spellType,
                 String components, String effects, int dmg_heal) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.spellType = spellType;
        this.components = components;
        this.effects = effects;
        this.dmg_heal = dmg_heal;
    }

    // Constructor for non-healing, non-damage spells
    public Spell(int id, String name, String description, String spellType,
                 String components, String effects) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.spellType = spellType;
        this.components = components;
        this.effects = effects;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpellType() {
        return spellType;
    }

    public void setSpellType(String spellType) {
        this.spellType = spellType;
    }

    public int getDmg_heal() {
        return dmg_heal;
    }

    public void setDmg_heal(int dmg_heal) {
        this.dmg_heal = dmg_heal;
    }

    public String toString() {
        return getName();
    }
}
