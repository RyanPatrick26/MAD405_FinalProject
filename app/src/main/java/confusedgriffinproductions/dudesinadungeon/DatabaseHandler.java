package confusedgriffinproductions.dudesinadungeon;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * DatabaseHandler to create the database and handle database interactions
 * Contains CREATE, READ, UPDATE, DELETE, CLOSE Database methods.
 *
 * @author Nicholas Allaire
 * @version 1.0
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private Context context;

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Name of the database
    private static final String DATABASE_NAME = "dudesinadungeon";

    // Name of all the tables
    private static final String TABLE_CHARACTERS = "characters";
    private static final String TABLE_ITEMS = "items";
    private static final String TABLE_SPELLS = "spells";
    private static final String TABLE_PORTRAITS = "portraits";
    private static final String TABLE_CHARACTER_PORTRAIT = "character_portrait";

    // Common Table Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";

    /**
     * Character Table Column Names
     */
    private static final String COLUMN_RACE = "race";
    private static final String COLUMN_CHAR_CLASS = "class";
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
    private static final String COLUMN_RANGE = "range";
    private static final String COLUMN_PORTRAIT_ID = "portrait_id";

    /**
     * Spell Table Column Names
     */
    private static final String COLUMN_SPELLTYPE = "spelltype";
    private static final String COLUMN_CLASS = "class";
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

    /**
     * CREATE Statements for all the tables
     */
    private static final String CREATE_CHARACTERS_TABLE = "CREATE TABLE " + TABLE_CHARACTERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_RACE + " TEXT,"
            + COLUMN_CHAR_CLASS + " TEXT,"
            + COLUMN_STRENGTH + " TEXT,"
            + COLUMN_AGILITY + " TEXT,"
            + COLUMN_RESILIENCE + " TEXT,"
            + COLUMN_LUCK + " TEXT,"
            + COLUMN_INTELLIGENCE + " TEXT,"
            + COLUMN_FIGHTING + " TEXT,"
            + COLUMN_GAMBLING + " TEXT,"
            + COLUMN_SHOOTING + " TEXT,"
            + COLUMN_LYING + " TEXT,"
            + COLUMN_CASTING + " TEXT,"
            + COLUMN_ACROBATICS + " TEXT,"
            + COLUMN_SNEAKING + " TEXT,"
            + COLUMN_CRAFTING + " TEXT,"
            + COLUMN_SURVIVAL + " TEXT" + ")";

    private static final String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PRICE + " TEXT,"
            + COLUMN_TYPE + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_DMG_DEF + " TEXT,"
            + COLUMN_RANGE + " TEXT,"
            + COLUMN_PORTRAIT_ID + " INTEGER)";

    private static final String CREATE_SPELLS_TABLE = "CREATE TABLE " + TABLE_SPELLS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_SPELLTYPE + " TEXT,"
            + COLUMN_CLASS + " TEXT,"
            + COLUMN_COMPONENTS + " TEXT,"
            + COLUMN_EFFECTS + " TEXT,"
            + COLUMN_DMG_HEAL + " TEXT" + ")";

    private static final String CREATE_PORTRAITS_TABLE = "CREATE TABLE " + TABLE_PORTRAITS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_RESOURCE + " TEXT" + ")";

    private static final String CREATE_CHARACTER_PORTRAITS_TABLE = "CREATE TABLE " + TABLE_CHARACTER_PORTRAIT + "("
            + COLUMN_CHAR_ID + " INTEGER REFERENCES "
            + TABLE_CHARACTERS + "("+COLUMN_ID+"),"
            + COLUMN_PORTRAIT + " INTEGER REFERENCES "
            + TABLE_PORTRAITS + "("+COLUMN_ID+")" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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

        this.initializeItemsTable(db);
        //initializeSpellsTable();
    }

    /**
     * Create a function to initialize the items table
     */
    public void initializeItemsTable(SQLiteDatabase db){
        Item sword = new Item();
        sword.setName(context.getResources().getString(R.string.sword));
        sword.setPrice(15);
        sword.setType("Weapon");
        sword.setDmg_def(4);
        sword.setDescription(context.getResources().getString(R.string.sword_description));
        sword.setImageId(R.drawable.sword);

        Item axe = new Item();
        axe.setName(context.getResources().getString(R.string.axe));
        axe.setPrice(17);
        axe.setType("Weapon");
        axe.setDmg_def(5);
        axe.setDescription(context.getResources().getString(R.string.axe_description));
        axe.setImageId(R.drawable.axe);

        Item spear = new Item();
        spear.setName(context.getResources().getString(R.string.spear));
        spear.setPrice(10);
        spear.setType("Weapon");
        spear.setDmg_def(3);
        spear.setDescription(context.getResources().getString(R.string.spear_description));
        spear.setImageId(R.drawable.spear);

        Item bow = new Item();
        bow.setName(context.getResources().getString(R.string.bow));
        bow.setPrice(15);
        bow.setType("Weapon");
        bow.setDmg_def(3);
        bow.setRange(50);
        bow.setDescription(context.getResources().getString(R.string.bow_description));
        bow.setImageId(R.drawable.bow);

        Item crossbow = new Item();
        crossbow.setName(context.getResources().getString(R.string.crossbow));
        crossbow.setPrice(18);
        crossbow.setType("Weapon");
        crossbow.setDmg_def(4);
        crossbow.setRange(35);
        crossbow.setDescription(context.getResources().getString(R.string.crossbow_description));
        crossbow.setImageId(R.drawable.crossbow);

        Item leatherArmor = new Item();
        leatherArmor.setName(context.getResources().getString(R.string.leather_armor));
        leatherArmor.setPrice(10);
        leatherArmor.setType("Armor");
        leatherArmor.setDmg_def(-3);
        leatherArmor.setDescription(context.getResources().getString(R.string.leather_armor_description));
        leatherArmor.setImageId(R.drawable.leather_armor);

        Item chainMail = new Item();
        chainMail.setName(context.getResources().getString(R.string.chain_mail));
        chainMail.setPrice(25);
        chainMail.setType("Armor");
        chainMail.setDmg_def(-8);
        chainMail.setDescription(context.getResources().getString(R.string.chain_mail_description));
        chainMail.setImageId(R.drawable.chain_mail);

        Item plateMail = new Item();
        plateMail.setName(context.getResources().getString(R.string.plate_mail));
        plateMail.setPrice(100);
        plateMail.setType("Armor");
        plateMail.setDmg_def(-15);
        plateMail.setDescription(context.getResources().getString(R.string.plate_mail_description));
        plateMail.setImageId(R.drawable.plate_mail);

        Item shield = new Item();
        shield.setName(context.getResources().getString(R.string.shield));
        shield.setPrice(3);
        shield.setType("Armor");
        shield.setDmg_def(-1);
        shield.setDescription(context.getResources().getString(R.string.shield_description));
        shield.setImageId(R.drawable.shield);

        Item backpack = new Item();
        backpack.setName(context.getResources().getString(R.string.backpack));
        backpack.setPrice(3);
        backpack.setType("Equipment");
        backpack.setDescription(context.getResources().getString(R.string.backpack_description));
        backpack.setImageId(R.drawable.backpack);

        Item canteen = new Item();
        canteen.setName(context.getResources().getString(R.string.canteen));
        canteen.setPrice(2);
        canteen.setType("Equipment");
        canteen.setDescription(context.getResources().getString(R.string.canteen_description));
        canteen.setImageId(R.drawable.canteen);

        Item tinderBox = new Item();
        tinderBox.setName(context.getResources().getString(R.string.tinder_box));
        tinderBox.setPrice(4);
        tinderBox.setType("Equipment");
        tinderBox.setDescription(context.getResources().getString(R.string.tinder_box_description));
        tinderBox.setImageId(R.drawable.tinder_box);

        Item tent = new Item();
        tent.setName(context.getResources().getString(R.string.tent));
        tent.setPrice(12);
        tent.setType("Equipment");
        tent.setDescription(context.getResources().getString(R.string.tent_description));
        tent.setImageId(R.drawable.tent);

        Item sleepingBag = new Item();
        sleepingBag.setName(context.getResources().getString(R.string.sleeping_bag));
        sleepingBag.setPrice(4);
        sleepingBag.setType("Equipment");
        sleepingBag.setDescription(context.getResources().getString(R.string.sleeping_bag_description));
        sleepingBag.setImageId(R.drawable.sleeping_bag);

        Item rations = new Item();
        rations.setName(context.getResources().getString(R.string.rations));
        rations.setPrice(2);
        rations.setType("Equipment");
        rations.setDescription(context.getResources().getString(R.string.rations_description));
        rations.setImageId(R.drawable.rations);

        ArrayList<Item> items = new ArrayList<>();
        items.add(sword);
        items.add(axe);
        items.add(spear);
        items.add(bow);
        items.add(crossbow);
        items.add(leatherArmor);
        items.add(chainMail);
        items.add(plateMail);
        items.add(shield);
        items.add(backpack);
        items.add(canteen);
        items.add(tinderBox);
        items.add(tent);
        items.add(sleepingBag);
        items.add(rations);

        ContentValues values = new ContentValues();

        for(int i = 0; i < items.size(); i++){
            values.put(COLUMN_NAME, items.get(i).getName());
            values.put(COLUMN_PRICE, items.get(i).getPrice());
            values.put(COLUMN_TYPE, items.get(i).getType());
            values.put(COLUMN_DESCRIPTION, items.get(i).getDescription());
            values.put(COLUMN_DMG_DEF, items.get(i).getDmg_def());
            values.put(COLUMN_RANGE, items.get(i).getRange());
            values.put(COLUMN_PORTRAIT_ID, items.get(i).getImageId());

            db.insert(TABLE_ITEMS, null, values);
        }
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
        onCreate(db);
    }

    // CRUD OPERATIONS FOR THE DATABASE AND FOR THE TABLES
    /**
     * CREATE OBJECTS AND STORE THEM IN THE DATABASE
     */
    /**
     * Method that will CREATE a CHARACTER record in the database
     * @param character object
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

        // Execute the insert statement
        db.insert(TABLE_CHARACTERS, null, values);
        // Close the database
        db.close();
    }

    /**
     * Method that will CREATE an ITEM record in the database
     * @param item object
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
        values.put(COLUMN_RANGE, item.getRange());

        // Execute the insert statement
        db.insert(TABLE_ITEMS, null, values);
        // Close the database
        db.close();
    }

    /**
     * Method that will CREATE a SPELL record in the database
     * @param spell object
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
     * Method that will CREATE a PORTRAIT record in the database
     * @param portrait object
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
     * Method that will CREATE a CHARACTER_PORTRAIT record in the database
     *
     * @param portrait object
     * @param character object
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
     * READ OBJECTS FROM THE DATABASE
     */
    /**
     * Method to READ a CHARACTER from the database
     */
    public Character getCharater(int id) {
        // Get a readable database
        SQLiteDatabase db = this.getReadableDatabase();
        // Create a cursor to store all the values
        Cursor cursor = db.query(TABLE_CHARACTERS,
                new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_RACE, COLUMN_CHAR_CLASS, COLUMN_STRENGTH,
                        COLUMN_AGILITY, COLUMN_RESILIENCE, COLUMN_LUCK, COLUMN_INTELLIGENCE, COLUMN_FIGHTING,
                        COLUMN_GAMBLING, COLUMN_SHOOTING, COLUMN_LYING, COLUMN_CASTING, COLUMN_ACROBATICS,
                        COLUMN_SNEAKING, COLUMN_CRAFTING, COLUMN_SURVIVAL},
                        COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        // Create a new character
        Character character = new Character(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),
                cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)),
                Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)), Integer.parseInt(cursor.getString(11)),
                Integer.parseInt(cursor.getString(12)), Integer.parseInt(cursor.getString(13)), Integer.parseInt(cursor.getString(14)),
                Integer.parseInt(cursor.getString(15)), Integer.parseInt(cursor.getString(16)), Integer.parseInt(cursor.getString(17)),
                Integer.parseInt(cursor.getString(18)));
        // Return the new character
        return character;
    }

    /**
     * Method to READ ALL CHARACTERS from the Database
     */
    public ArrayList<Character> getAllCharacters() {
        // Create an ArrayList of characters
        ArrayList<Character> characterList = new ArrayList<Character>();
        // Create a sql query string to get all the characters
        String selectQuery = "SELECT  * FROM " + TABLE_CHARACTERS;
        // Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a cursor to store all the values
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                // Create each character and set all their properties
                Character character = new Character();
                character.setId(Integer.parseInt(cursor.getString(0)));
                character.setName(cursor.getString(1));
                character.setRace(cursor.getString(2));
                character.setCharClass(cursor.getString(3));
                character.setStrength(Integer.parseInt(cursor.getString(4)));
                character.setAgility(Integer.parseInt(cursor.getString(5)));
                character.setResilience(Integer.parseInt(cursor.getString(6)));
                character.setLuck(Integer.parseInt(cursor.getString(7)));
                character.setIntelligence(Integer.parseInt(cursor.getString(8)));
                character.setFighting(Integer.parseInt(cursor.getString(9)));
                character.setGambling(Integer.parseInt(cursor.getString(10)));
                character.setShooting(Integer.parseInt(cursor.getString(11)));
                character.setLying(Integer.parseInt(cursor.getString(12)));
                character.setCasting(Integer.parseInt(cursor.getString(13)));
                character.setAcrobatics(Integer.parseInt(cursor.getString(14)));
                character.setSneaking(Integer.parseInt(cursor.getString(15)));
                character.setCrafting(Integer.parseInt(cursor.getString(16)));
                character.setSurvival(Integer.parseInt(cursor.getString(17)));
            } while (cursor.moveToNext());
        }
        // Return the list of characters
        return characterList;
    }

    /**
     * Method to READ an ITEM from the database
     */
    public Item getItem(int id) {
        // Get a readable database
        SQLiteDatabase db = this.getReadableDatabase();
        // Create a cursor to store all the values
        Cursor cursor = db.query(TABLE_ITEMS,
                new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_PRICE, COLUMN_TYPE,
                        COLUMN_DESCRIPTION, COLUMN_DMG_DEF, COLUMN_RANGE, COLUMN_PORTRAIT_ID},
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        // Create a new item
        Item item = new Item(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                Double.parseDouble(cursor.getString(2)), cursor.getString(3), cursor.getString(4),
                Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), cursor.getInt(7));
        // Return the new item
        return item;
    }

    /**
     * Method to READ ALL ITEMS from the database
     */
    public ArrayList<Item> getAllItems() {
        // Create an ArrayList of items
        ArrayList<Item> itemList = new ArrayList<Item>();
        // Create a sql query string to get all the items
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;
        // Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a cursor to store all the values
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                // Create each item and set all their properties
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setPrice(Double.parseDouble(cursor.getString(2)));
                item.setType(cursor.getString(3));
                item.setDescription(cursor.getString(4));
                item.setDmg_def(Integer.parseInt(cursor.getString(5)));
                item.setRange(Integer.parseInt(cursor.getString(6)));
                item.setImageId(cursor.getInt(7));

                itemList.add(item);
            } while (cursor.moveToNext());
        }
        // Return the list of items
        return itemList;
    }

    /**
     * Method to grab all items of a specific type
     * @return
     */
    public ArrayList<Item> getAllItems(String type) {
        // Create an ArrayList of items
        ArrayList<Item> itemList = new ArrayList<Item>();
        // Create a sql query string to get all the items
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS + " WHERE " + COLUMN_TYPE + " = '" + type + "'";
        // Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a cursor to store all the values
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                // Create each item and set all their properties
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setPrice(Double.parseDouble(cursor.getString(2)));
                item.setType(cursor.getString(3));
                item.setDescription(cursor.getString(4));
                item.setDmg_def(Integer.parseInt(cursor.getString(5)));
                item.setRange(Integer.parseInt(cursor.getString(6)));
                item.setImageId(cursor.getInt(7));

                itemList.add(item);
            } while (cursor.moveToNext());
        }
        // Return the list of items
        return itemList;
    }

    /**
     * Method to READ a SPELL from the database
     */
    public Spell getSpell(int id) {
        // Get a readable database
        SQLiteDatabase db = this.getReadableDatabase();
        // Create a cursor to store all the values
        Cursor cursor = db.query(TABLE_SPELLS,
                new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_SPELLTYPE,
                        COLUMN_COMPONENTS, COLUMN_EFFECTS, COLUMN_DMG_HEAL},
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        // Create a new spell
        Spell spell = new Spell(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.parseInt(cursor.getString(7)));
        // Return the new spell
        return spell;
    }

    /**
     * Method to READ ALL SPELLS from the database
     */
    public ArrayList<Spell> getAllSpells() {
        // Create an ArrayList of spells
        ArrayList<Spell> spellList = new ArrayList<Spell>();
        // Create a sql query string to get all the spells
        String selectQuery = "SELECT  * FROM " + TABLE_SPELLS;
        // Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a cursor to store all the values
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                // Create each spell and set all their properties
                Spell spell = new Spell();
                spell.setId(Integer.parseInt(cursor.getString(0)));
                spell.setName(cursor.getString(1));
                spell.setDescription(cursor.getString(2));
                spell.setSpellType(cursor.getString(3));
                spell.setSpellClass(cursor.getString(4));
                spell.setComponents(cursor.getString(5));
                spell.setEffects(cursor.getString(6));
                spell.setDmg_heal(Integer.parseInt(cursor.getString(7)));

                spellList.add(spell);
            } while (cursor.moveToNext());
        }
        // Return the list of spells
        return spellList;
    }


    /**
     * Method to get a PORTRAIT from the database
     */
    public Portrait getPortrait(int id) {
        // Get a readable database
        SQLiteDatabase db = this.getReadableDatabase();
        // Create a cursor to store all the values
        Cursor cursor = db.query(TABLE_PORTRAITS, new String[] {COLUMN_ID, COLUMN_RESOURCE}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        // Create a new portrait
        Portrait portrait = new Portrait(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // Return the new portrait
        return portrait;
    }

    /**
     * Method to get ALL PORTRAITS from the database
     */
    public ArrayList<Portrait> getAllPortraits() {
        // Create an arrayList of portraits
        ArrayList<Portrait> portraitList = new ArrayList<Portrait>();
        // Create a SQL string query to get all records from the portraits table
        String selectQuery = "SELECT  * FROM " + TABLE_PORTRAITS;
        // Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a cursor to store the SQL string
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                // Create a new portrait and set all its properties
                Portrait portrait = new Portrait();
                portrait.setId(Integer.parseInt(cursor.getString(0)));
                portrait.setResource(cursor.getString(1));
                portraitList.add(portrait);
            } while (cursor.moveToNext());
        }
        // Return the new portrait list
        return portraitList;
    }

    /**
     * Method to get ALL PORTRAITS associated with CHARACTERS from the database
     */
    public ArrayList<Portrait> getAllCharacterPortraits(int character) {
        // Create an array of portraits
        ArrayList<Portrait> portraitList = new ArrayList<Portrait>();
        // Create a SQL string query to get all records from the Character Portrait Table
        //  where the Character ID is equal to character
        String selectQuery = "SELECT  * FROM " + TABLE_CHARACTER_PORTRAIT + " WHERE " + COLUMN_CHAR_ID + " = " + character;
        // Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a cursor to store the selectQuery
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                // Create a string query to select all Portraits in the Portraits where the ID is equal
                //  to the outer portrait ID
                String innerQuery = "SELECT * FROM " + TABLE_PORTRAITS + " WHERE " + COLUMN_ID + "=" + cursor.getInt(1);
                // store the results inside of an inner cursor
                Cursor innerCursor = db.rawQuery(innerQuery, null);
                if (innerCursor.moveToFirst()) {
                    do {
                        // Create a new portrait and set its values
                        Portrait portrait = new Portrait();
                        portrait.setId(Integer.parseInt(innerCursor.getString(0)));
                        portrait.setResource(innerCursor.getString(1));
                        // add it to the portrait list
                        portraitList.add(portrait);
                    } while (innerCursor.moveToNext());
                }
            }while (cursor.moveToNext());
        }
        // return the portrait list
        return portraitList;
    }

    /**
     * UPDATE RECORDS IN THE DATABASE
     */
    /**
     * Method to UPDATE a CHARACTER in the database
     */
    public int updateCharacter(Character character) {
        // Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a contentValues to store all the values
        ContentValues values = new ContentValues();
        // Put all properties into values
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
        values.put(COLUMN_ACROBATICS, character.getAcrobatics());
        values.put(COLUMN_SNEAKING, character.getSneaking());
        values.put(COLUMN_CRAFTING, character.getCrafting());
        values.put(COLUMN_SURVIVAL, character.getSurvival());
        // Update the database
        return db.update(TABLE_CHARACTERS, values, COLUMN_ID + " = ?", new String[] { String.valueOf(character.getId()) });
    }

    /**
     * Method to UPDATE an ITEM in the database
     */
    public int updateItem(Item item) {
        // Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a contentValues to store all the values
        ContentValues values = new ContentValues();
        // Put all properties into values
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_PRICE, item.getPrice());
        values.put(COLUMN_TYPE, item.getType());
        values.put(COLUMN_DESCRIPTION, item.getDescription());
        values.put(COLUMN_DMG_DEF, item.getDmg_def());
        values.put(COLUMN_RANGE, item.getRange());

        // Update the database
        return db.update(TABLE_ITEMS, values, COLUMN_ID + " = ?", new String[] { String.valueOf(item.getId()) });
    }

    /**
     * Method to UPDATE a SPELL in the database
     */
    public int updateSpell(Spell spell) {
        // Get a writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a contentValues to store all the values
        ContentValues values = new ContentValues();
        // Put all properties into values
        values.put(COLUMN_NAME, spell.getName());
        values.put(COLUMN_DESCRIPTION, spell.getDescription());
        values.put(COLUMN_SPELLTYPE, spell.getSpellType());
        values.put(COLUMN_COMPONENTS, spell.getComponents());
        values.put(COLUMN_EFFECTS, spell.getEffects());
        values.put(COLUMN_DMG_HEAL, spell.getDmg_heal());

        // Update the database
        return db.update(TABLE_ITEMS, values, COLUMN_ID + " = ?", new String[] { String.valueOf(spell.getId()) });
    }

    /**
     * Method to UPDATE a PORTRAIT in the database
     */
    public int updatePortrait(Portrait portrait) {
        // Get the writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create and set the content values
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESOURCE, portrait.getResource());
        // Update the database
        return db.update(TABLE_PORTRAITS, values, COLUMN_ID + " = ?", new String[] { String.valueOf(portrait.getId()) });
    }

    /**
     * DELETE RECORDS FROM THE DATABASE
     */
    /**
     * Method to DELETE a CHARACTER from the database
     */
    public void deleteCharacter(long character_id) {
        // Get the writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete the record from the database
        db.delete(TABLE_CHARACTERS, COLUMN_ID + " = ?",
                new String[] { String.valueOf(character_id) });
    }

    /**
     * Method to DELETE an ITEM from the database
     */
    public void deleteItem(long item_id) {
        // Get the writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete the record from the database
        db.delete(TABLE_ITEMS, COLUMN_ID + " = ?",
                new String[] { String.valueOf(item_id) });
    }

    /**
     * Method to DELETE a SPELL from the database
     */
    public void deleteSpell(long spell_id) {
        // Get the writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete the record from the database
        db.delete(TABLE_SPELLS, COLUMN_ID + " = ?",
                new String[] { String.valueOf(spell_id) });
    }

    /**
     * Method to DELETE a PORTRAIT from the database
     */
    public void deletePortrait(long portrait_id) {
        // Get the writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete the record from the database
        db.delete(TABLE_PORTRAITS, COLUMN_ID + " = ?",
                new String[] { String.valueOf(portrait_id) });
    }

    /**
     * Method to CLOSE the database connection
     */
    public void closeDB() {
        // Get the readable database
        SQLiteDatabase db = this.getReadableDatabase();
        // If the database exists and is open, close it
        if (db != null && db.isOpen())
            db.close();
    }

}