package confusedgriffinproductions.dudesinadungeon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DatabaseHandler to create the database and handle database interactions
 *
 * @author Nicholas Allaire
 * @version 1.0
 */
public class DatabaseHandler extends SQLiteOpenHelper {

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

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // CREATE AND UPGRADE FOR THE DATABASE
    /**
     * Method to create the tables inside of the database.
     *
     * @param db - DatabaseHandler
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CHARACTERS_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
        db.execSQL(CREATE_SPELLS_TABLE);
        db.execSQL(CREATE_PORTRAITS_TABLE);
        db.execSQL(CREATE_CHARACTER_PORTRAITS_TABLE);
        db.execSQL(CREATE_ITEM_PORTRAITS_TABLE);
    }

    /**
     * When the database upgrades, delete the old database and replace it with the upgraded one
     *
     * @param db - DatabaseHandler
     * @param oldVersion - Old version of the database
     * @param newVersion - New version of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPELLS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PORTRAITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTER_PORTRAIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM_PORTRAIT);
        onCreate(db);
    }

    // CRUD OPERATIONS FOR THE DATABASE AND FOR THE TABLES
    /**
     * Add new Objects to the database
     */
    /**
     * Adds a new character to the database
     * @param character - Character to be added to the database
     */
    public void addCharacter(Character character) {
        // Get a writable Database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a ContentValues to store values
        ContentValues values = new ContentValues();
        // Put the values for the insert command
        values.put(COLUMN_NAME, character.getName());
        values.put(COLUMN_RACE, character.getRace());
        values.put(COLUMN_CHAR_CLASS, character.getCharClass());
        values.put(COLUMN_STRENGTH, character.getStrength());
        values.put(COLUMN_AGILITY, character.getAgility());
        values.put(COLUMN_RESILIENCE, character.getResilience());
        values.put(COLUMN_LUCK, character.getLuck());
        values.put(COLUMN_INTELLIGENCE, character.getIntelligence());
        values.put(COLUMN_FIGHTING, character.getFighting());
        values.put(COLUMN_GAMBLING, character.getGambling());
        values.put(COLUMN_SHOOTING, character.getShooting());
        values.put(COLUMN_LYING, character.getLying());
        values.put(COLUMN_CASTING, character.getCasting());
        values.put(COLUMN_ACROBATICS,character.getAcrobatics());
        values.put(COLUMN_SNEAKING, character.getSneaking());
        values.put(COLUMN_CRAFTING, character.getCrafting());
        values.put(COLUMN_SURVIVAL, character.getSurvival());
        // TODO: THIS MAY NEED TO BE FIXED
        values.put(COLUMN_ITEMS, character.getItems().toString());
        values.put(COLUMN_SPELLS, character.getSpells().toString());

        // Execute the insert statement
        db.insert(TABLE_CHARACTERS, null, values);
        // Close the database
        db.close();
    }

    /**
     * Adds a new item to the database
     * @param item - item to be added
     */
    public void addItem(Item item) {
        // Get a writable Database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a ContentValues to store values
        ContentValues values = new ContentValues();
        // Put the values for the insert command
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_PRICE, item.getPrice());
        values.put(COLUMN_TYPE, item.getType());
        values.put(COLUMN_DESCRIPTION, item.getDescription());
        values.put(COLUMN_DMG_DEF, item.getDmg_def());

        // Execute the insert statement
        db.insert(TABLE_ITEMS, null, values);
        // Close the database
        db.close();
    }

    /**
     * Adds a new spell to the spell list
     * @param spell - spell to be added
     */
    public void addSpell(Spell spell) {
        // Get a writable Database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a ContentValues to store values
        ContentValues values = new ContentValues();
        // Put the values for the insert command
        values.put(COLUMN_NAME, spell.getName());
        values.put(COLUMN_DESCRIPTION, spell.getDescription());
        values.put(COLUMN_SPELLTYPE, spell.getSpellType());
        values.put(COLUMN_COMPONENTS, spell.getComponents());
        values.put(COLUMN_EFFECTS, spell.getEffects());
        values.put(COLUMN_DMG_HEAL, spell.getDmg_heal());

        // Execute the insert statement
        db.insert(TABLE_ITEMS, null, values);
        // Close the database
        db.close();
    }

    /**
     * Adds a Portrait to the database
     *
     * @param portrait - portrait to be added
     * @return integer (row location)
     */
    public int addPortrait(Portrait portrait) {
        // Get a writable Database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a ContentValues to store values
        ContentValues values = new ContentValues();
        // Put the values for the insert command
        values.put(COLUMN_RESOURCE, portrait.getResource());

        // Execute the insert statement
        db.insert(TABLE_PORTRAITS, null, values);
        // Set the database to readable
        db = this.getReadableDatabase();
        // Return the row the portrait was added to
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
        if(cursor.moveToFirst()) {
            int location = Integer.parseInt(cursor.getString(0));
            System.out.println("Record ID " + location);
            db.close();
            return location;
        }
        return -1;
    }

    /**
     * Method that will add a Character Portrait record
     *
     * @param portrait
     * @param character
     */
    public void addCharacterPortrait(int portrait, int character){
        // Get a writable Database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a ContentValues to store values
        ContentValues values = new ContentValues();
        // Put the values for the insert command
        values.put(COLUMN_CHAR_ID, character);
        values.put(COLUMN_PORTRAIT, portrait);

        // Execute the insert statement
        db.insert(TABLE_CHARACTER_PORTRAIT, null, values);
        db.close();
    }

    /**
     * Method that will add an Item Portrait record
     *
     * @param portrait
     * @param item
     */
    public void addItemPortrait(int portrait, int item){
        // Get a writable Database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a ContentValues to store values
        ContentValues values = new ContentValues();
        // Put the values for the insert command
        values.put(COLUMN_ITEM_ID, item);
        values.put(COLUMN_PORTRAIT, portrait);

        // Execute the insert statement
        db.insert(TABLE_ITEM_PORTRAIT, null, values);
        db.close();
    }








}