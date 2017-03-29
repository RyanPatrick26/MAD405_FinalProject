package confusedgriffinproductions.dudesinadungeon;

import java.util.ArrayList;

/**
 * Character Class
 * User-created characters for the Dudes In A Dungeon tabletop RPG
 * @author Nicholas Allaire
 */
public class Character {

    // Declare properties for Characters
    private int id;
    private String name;
    private String race;
    private String charClass;
    // Stats
    private int strength;
    private int agility;
    private int resilience;
    private int luck;
    private int intelligence;
    // Skills
    private int fighting;
    private int gambling;
    private int shooting;
    private int lying;
    private int casting;
    private int acrobatics;
    private int sneaking;
    private int crafting;
    private int survival;
    // Items and Spells
    private ArrayList<Item> items;
    private ArrayList<Spell> spells;

    // Constructors
    // Empty Constructor
    public Character() {}

    // Constructor w/everything
    public Character(int id, String name, String race, String charClass, int strength,
                     int agility, int resilience, int luck, int intelligence, int fighting,
                     int gambling, int shooting, int lying, int casting, int acrobatics,
                     int sneaking, int crafting, int survival, ArrayList<Item> items,
                     ArrayList<Spell> spells) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.charClass = charClass;
        this.strength = strength;
        this.agility = agility;
        this.resilience = resilience;
        this.luck = luck;
        this.intelligence = intelligence;
        this.fighting = fighting;
        this.gambling = gambling;
        this.shooting = shooting;
        this.lying = lying;
        this.casting = casting;
        this.acrobatics = acrobatics;
        this.sneaking = sneaking;
        this.crafting = crafting;
        this.survival = survival;
        this.items = items;
        this.spells = spells;
    }

    // Constructor w.o/ id
    public Character(String name, String race, String charClass, int strength,
                     int agility, int resilience, int luck, int intelligence, int fighting,
                     int gambling, int shooting, int lying, int casting, int acrobatics, int sneaking,
                     int crafting, int survival, ArrayList<Item> items, ArrayList<Spell> spells) {
        this.name = name;
        this.race = race;
        this.charClass = charClass;
        this.strength = strength;
        this.agility = agility;
        this.resilience = resilience;
        this.luck = luck;
        this.intelligence = intelligence;
        this.fighting = fighting;
        this.gambling = gambling;
        this.shooting = shooting;
        this.lying = lying;
        this.casting = casting;
        this.acrobatics = acrobatics;
        this.sneaking = sneaking;
        this.crafting = crafting;
        this.survival = survival;
        this.items = items;
        this.spells = spells;
    }

    // Constructor w.o/ spells
    public Character(int id, String name, String race, String charClass, int strength,
                     int agility, int resilience, int luck, int intelligence, int fighting,
                     int gambling, int shooting, int lying, int casting, int acrobatics,
                     int sneaking, int crafting, int survival, ArrayList<Item> items) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.charClass = charClass;
        this.strength = strength;
        this.agility = agility;
        this.resilience = resilience;
        this.luck = luck;
        this.intelligence = intelligence;
        this.fighting = fighting;
        this.gambling = gambling;
        this.shooting = shooting;
        this.lying = lying;
        this.casting = casting;
        this.acrobatics = acrobatics;
        this.sneaking = sneaking;
        this.crafting = crafting;
        this.survival = survival;
        this.items = items;
    }

    // Constructor w.o/ spells or id
    public Character(String name, String race, String charClass, int strength,
                     int agility, int resilience, int luck, int intelligence, int fighting,
                     int gambling, int shooting, int lying, int casting, int acrobatics,
                     int sneaking, int crafting, int survival, ArrayList<Item> items) {
        this.name = name;
        this.race = race;
        this.charClass = charClass;
        this.strength = strength;
        this.agility = agility;
        this.resilience = resilience;
        this.luck = luck;
        this.intelligence = intelligence;
        this.fighting = fighting;
        this.gambling = gambling;
        this.shooting = shooting;
        this.lying = lying;
        this.casting = casting;
        this.acrobatics = acrobatics;
        this.sneaking = sneaking;
        this.crafting = crafting;
        this.survival = survival;
        this.items = items;
    }

    // Setters & Getters
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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getResilience() {
        return resilience;
    }

    public void setResilience(int resilience) {
        this.resilience = resilience;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getFighting() {
        return fighting;
    }

    public void setFighting(int fighting) {
        this.fighting = fighting;
    }

    public int getGambling() {
        return gambling;
    }

    public void setGambling(int gambling) {
        this.gambling = gambling;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    public int getLying() {
        return lying;
    }

    public void setLying(int lying) {
        this.lying = lying;
    }

    public int getCasting() {
        return casting;
    }

    public void setCasting(int casting) {
        this.casting = casting;
    }

    public int getAcrobatics() {
        return acrobatics;
    }

    public void setAcrobatics(int acrobatics) {
        this.acrobatics = acrobatics;
    }

    public int getSneaking() {
        return sneaking;
    }

    public void setSneaking(int sneaking) {
        this.sneaking = sneaking;
    }

    public int getCrafting() {
        return crafting;
    }

    public void setCrafting(int crafting) {
        this.crafting = crafting;
    }

    public int getSurvival() {
        return survival;
    }

    public void setSurvival(int survival) {
        this.survival = survival;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }
}
