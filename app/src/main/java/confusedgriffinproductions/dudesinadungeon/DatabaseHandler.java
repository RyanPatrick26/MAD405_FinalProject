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
    private static final String TABLE_ITEMS = "trips";
    private static final String TABLE_SPELLS = "spells";
    private static final String TABLE_PORTRAITS = "portraits";
    private static final String TABLE_CHARACTER_PORTRAIT = "character_portrait";
    private static final String TABLE_ITEM_PORTRAIT = "item_portraits";

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
    private static final String COLUMN_ITEMS = "items";
    private static final String COLUMN_SPELLS = "spells";

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
    private static final String COLUMN_COMPONENTS = "components";
    private static final String COLUMN_EFFECTS = "effects";

    /**
     * Portrait Table Column Names
     */
    private static final String COLUMN_RESOURCE = "resource";

    /**
     * Character Portrait Table & Item Portrait Table Column Names
     */
    private static final String COLUMN_PORTRAIT = "id_portrait";
    private static final String COLUMN_CHAR_ID = "id_character";
    private static final String COLUMN_ITEM_ID = "id_item";

    /**
     * CREATE Statements for all the tables
     */
    private static final String CREATE_CHARACTERS_TABLE = "CREATE TABLE " + TABLE_CHARACTERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_RACE + " TEXT,"
            + COLUMN_CHAR_CLASS + " TEXT,"
            + COLUMN_STRENGTH + " INTEGER,"
            + COLUMN_AGILITY + " INTEGER,"
            + COLUMN_RESILIENCE + " INTEGER,"
            + COLUMN_LUCK + " INTEGER,"
            + COLUMN_INTELLIGENCE + " INTEGER,"
            + COLUMN_FIGHTING + " INTEGER,"
            + COLUMN_GAMBLING + " INTEGER,"
            + COLUMN_SHOOTING + " INTEGER,"
            + COLUMN_LYING + " INTEGER,"
            + COLUMN_CASTING + " INTEGER,"
            + COLUMN_ACROBATICS + " INTEGER,"
            + COLUMN_SNEAKING + " INTEGER,"
            + COLUMN_CRAFTING + " INTEGER,"
            + COLUMN_SURVIVAL + " INTEGER,"
            + COLUMN_ITEMS + " TEXT,"
            + COLUMN_SPELLS + " TEXT" + ")";

    private static final String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PRICE + " NUMERIC(15,0),"
            + COLUMN_TYPE + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_DMG_DEF + " INTEGER" + ")";

    private static final String CREATE_SPELLS_TABLE = "CREATE TABLE " + TABLE_SPELLS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_SPELLTYPE + " TEXT,"
            + COLUMN_COMPONENTS + " TEXT,"
            + COLUMN_EFFECTS + " TEXT,"
            + COLUMN_DMG_HEAL + " INTEGER";

    private static final String CREATE_PORTRAITS_TABLE = "CREATE TABLE " + TABLE_PORTRAITS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_RESOURCE + " TEXT" + ")";

    private static final String CREATE_CHARACTER_PORTRAITS_TABLE = "CREATE TABLE " + TABLE_CHARACTER_PORTRAIT + "("
            + COLUMN_CHAR_ID + " INTEGER REFRENCES " + TABLE_CHARACTERS + "("+COLUMN_ID+"),"
            + COLUMN_PORTRAIT + " INTEGER REFRENCES " + TABLE_PORTRAITS + "("+COLUMN_RESOURCE+")";

    private static final String CREATE_ITEM_PORTRAITS_TABLE = "CREATE TABLE " + TABLE_ITEM_PORTRAIT + "("
            + COLUMN_ITEM_ID + " INTEGER REFRENCES " + TABLE_CHARACTERS + "("+COLUMN_ID+"),"
            + COLUMN_PORTRAIT + " INTEGER REFRENCES " + TABLE_PORTRAITS + "("+COLUMN_RESOURCE+")";

    







}