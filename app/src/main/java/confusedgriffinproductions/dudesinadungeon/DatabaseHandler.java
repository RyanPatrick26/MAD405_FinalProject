package confusedgriffinproductions.dudesinadungeon;

/**
 * DatabaseHandler to create the database and handle database interactions
 *
 * @author Nicholas Allaire
 * @version 1.0
 */
public class DatabaseHandler {

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Name of the database
    private static final String DATABASE_NAME = "dudesinadungeon";

    // Name of all the tables
    private static final String TABLE_CHARACTERS = "characters";
    private static final String TABLE_TRIPS = "trip";
    private static final String TABLE_PORTRAITS = "portrait";
    private static final String TABLE_CHARACTER_PORTRAIT = "character_portrait";
    private static final String TABLE_ITEM_PORTRAIT = "item_portrait";

    // Common Table Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";

    /**
     * Character Table Column Names
     */
    private static final String COLUMN_RACE = "race";
    private static final String COLUMN_CHAR_CLASS = "charClass";
    private static final String COLUMN_STRENGTH = "strength";
    private static final String COLUMN_AGILITY = "agility";
    private static final String COLUMN_RESILIENCE = "resilience";
    private static final String COLUMN_LUCK = "luck";
    private static final String COLUMN_INTELLIGENCE = "intelligence";
    private static final String COLUMN_FIGHTING = "fighting";
    private static final String COLUMN_GAMBLING = "gambling";
    private static final String COLUMN_SHOOTING = "shooting";
    private static final String COLUMN_LYING = "lying";
    private static final String COLUMN_CASTING = "casting";
    private static final String COLUMN_ACROBATICS = "acrobatics";
    private static final String COLUMN_SNEAKING = "sneaking";
    private static final String COLUMN_CRAFTING = "crafting";
    private static final String COLUMN_SURVIVAL = "survival";

    /**
     * Item Table Column Names
     */
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_DMG_DEF = "dmg_def";

    /**
     * Spell Table Column Names
     */
    private static final String COLUMN_SPELLTYPE = "spelltype";
    private static final String COLUMN_DMG_HEAL = "dmg_heal";



}