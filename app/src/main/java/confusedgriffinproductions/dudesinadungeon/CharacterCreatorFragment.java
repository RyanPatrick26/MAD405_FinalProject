package confusedgriffinproductions.dudesinadungeon;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharacterCreatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharacterCreatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterCreatorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Create variables to store the EditTexts
     */
    EditText characterNameField;

    /**
     * Create variables to store the Spinners
     */
    Spinner raceSpinner;
    Spinner classSpinner;
    int currentRacePosition;
    int previousRacePosition;
    int currentClassPosition;
    int previousClassPosition;

    /**
     * Create variables to store the TextViews
     */
    TextView[] attributeFields;
    TextView[] skillFields;
    TextView remainingAttsView;
    TextView remainingSkillsView;

    /**
     * Create variables to store the buttons
     */
    Button[] attributeButtons;
    Button randomizeAttsButton;
    Button[] skillButtons;
    Button resetSkillsButton;
    Button resetCharacterButton;
    Button confirmButton;
    Button addItemsButton;
    Button addSpellsButton;

    /**
     * Create variables to store the LinearLayouts
     */
    LinearLayout itemListView;
    LinearLayout spellListView;

    /**
     * Create arrays to store the available races and classes
     */
    String[] raceArray;
    String[] classArray;

    int index;

    ArrayList<Item> itemsList;
    ArrayList<Spell> spellList;

    public CharacterCreatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CharacterCreatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CharacterCreatorFragment newInstance(String param1, String param2) {
        CharacterCreatorFragment fragment = new CharacterCreatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_creator, container, false);

        itemsList = new ArrayList<>();
        spellList = new ArrayList<>();

        /**
         * Instantiate the EditTexts
         */
        characterNameField = (EditText)view.findViewById(R.id.name_field);

        /**
         * Instantiate the Spinners
         */
        raceSpinner = (Spinner)view.findViewById(R.id.race_spinner);
        classSpinner = (Spinner)view.findViewById(R.id.class_spinner);
        currentRacePosition = -1;
        currentClassPosition = -1;

        raceArray = new String[]{
                getContext().getResources().getString(R.string.human),
                getContext().getResources().getString(R.string.elf),
                getContext().getResources().getString(R.string.dwarf)
        };
        classArray = new String[]{
                getContext().getResources().getString(R.string.warrior),
                getContext().getResources().getString(R.string.rogue),
                getContext().getResources().getString(R.string.wizard),
                getContext().getResources().getString(R.string.priest)
        };

        ArrayAdapter raceAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, raceArray);
        raceSpinner.setAdapter(raceAdapter);

        ArrayAdapter classAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, classArray);
        classSpinner.setAdapter(classAdapter);

        /**
         * Instantiate the TextViews
         */
        attributeFields = new TextView[]{
                (TextView) view.findViewById(R.id.strength_field),
                (TextView) view.findViewById(R.id.agility_field),
                (TextView) view.findViewById(R.id.resilience_field),
                (TextView) view.findViewById(R.id.luck_field),
                (TextView) view.findViewById(R.id.intelligence_field)
        };

        //set on click listener for the spinners
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                previousRacePosition = currentRacePosition;
                currentRacePosition = raceSpinner.getSelectedItemPosition();

                if(previousRacePosition == 0){
                    for(int i = 0; i < attributeFields.length; i++){
                        attributeFields[i].setText((Integer.parseInt(attributeFields[i].getText().toString()) - 1) + "");
                    }
                }
                else if(previousRacePosition == 1){
                    attributeFields[1].setText((Integer.parseInt(attributeFields[1].getText().toString()) - 2) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) + 2) + "");
                    attributeFields[4].setText((Integer.parseInt(attributeFields[4].getText().toString()) - 2) + "");
                }
                else if(previousRacePosition == 2){
                    attributeFields[0].setText((Integer.parseInt(attributeFields[0].getText().toString()) - 2) + "");
                    attributeFields[1].setText((Integer.parseInt(attributeFields[1].getText().toString()) + 2) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) - 2) + "");
                }

                if(raceSpinner.getSelectedItemPosition() == 0){
                    for(int i = 0; i < attributeFields.length; i++){
                        attributeFields[i].setText((Integer.parseInt(attributeFields[i].getText().toString())+1) + "");
                    }
                }
                else if(raceSpinner.getSelectedItemPosition() == 1){
                    attributeFields[1].setText((Integer.parseInt(attributeFields[1].getText().toString()) + 2) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) - 2) + "");
                    attributeFields[4].setText((Integer.parseInt(attributeFields[4].getText().toString()) + 2) + "");
                }
                else if(raceSpinner.getSelectedItemPosition() == 2){
                    attributeFields[0].setText((Integer.parseInt(attributeFields[0].getText().toString()) + 2) + "");
                    attributeFields[1].setText((Integer.parseInt(attributeFields[1].getText().toString()) - 2) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) + 2) + "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                previousClassPosition = currentClassPosition;
                currentClassPosition = classSpinner.getSelectedItemPosition();

                if(previousClassPosition == 0){
                    attributeFields[0].setText((Integer.parseInt(attributeFields[0].getText().toString()) - 1) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) - 1) + "");
                    attributeFields[4].setText((Integer.parseInt(attributeFields[4].getText().toString()) + 1) + "");
                }
                else if(previousClassPosition == 1){
                    attributeFields[1].setText((Integer.parseInt(attributeFields[1].getText().toString()) - 1) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) + 1) + "");
                    attributeFields[3].setText((Integer.parseInt(attributeFields[3].getText().toString()) - 1) + "");
                }
                else if(previousClassPosition == 2){
                    attributeFields[0].setText((Integer.parseInt(attributeFields[0].getText().toString()) + 1) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) - 1) + "");
                    attributeFields[4].setText((Integer.parseInt(attributeFields[4].getText().toString()) - 1) + "");
                }
                else if(previousClassPosition == 3){
                    attributeFields[0].setText((Integer.parseInt(attributeFields[0].getText().toString()) - 1) + "");
                    attributeFields[1].setText((Integer.parseInt(attributeFields[1].getText().toString()) + 1) + "");
                    attributeFields[4].setText((Integer.parseInt(attributeFields[4].getText().toString()) - 1) + "");
                }

                if(classSpinner.getSelectedItemPosition() == 0){
                    attributeFields[0].setText((Integer.parseInt(attributeFields[0].getText().toString()) + 1) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) + 1) + "");
                    attributeFields[4].setText((Integer.parseInt(attributeFields[4].getText().toString()) - 1) + "");
                }
                else if(classSpinner.getSelectedItemPosition() == 1){
                    attributeFields[1].setText((Integer.parseInt(attributeFields[1].getText().toString()) + 1) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) - 1) + "");
                    attributeFields[3].setText((Integer.parseInt(attributeFields[3].getText().toString()) + 1) + "");
                }
                else if(classSpinner.getSelectedItemPosition() == 2){
                    attributeFields[0].setText((Integer.parseInt(attributeFields[0].getText().toString()) - 1) + "");
                    attributeFields[2].setText((Integer.parseInt(attributeFields[2].getText().toString()) + 1) + "");
                    attributeFields[4].setText((Integer.parseInt(attributeFields[4].getText().toString()) + 1) + "");
                }
                else if(classSpinner.getSelectedItemPosition() == 3){
                    attributeFields[0].setText((Integer.parseInt(attributeFields[0].getText().toString()) + 1) + "");
                    attributeFields[1].setText((Integer.parseInt(attributeFields[1].getText().toString()) - 1) + "");
                    attributeFields[4].setText((Integer.parseInt(attributeFields[4].getText().toString()) + 1) + "");
                }
                spellList.clear();
                spellListView.removeAllViews();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        skillFields = new TextView[]{
                (TextView)view.findViewById(R.id.fighting_field),
                (TextView)view.findViewById(R.id.shooting_field),
                (TextView)view.findViewById(R.id.casting_field),
                (TextView)view.findViewById(R.id.acrobatics_field),
                (TextView)view.findViewById(R.id.crafting_field),
                (TextView)view.findViewById(R.id.gambling_field),
                (TextView)view.findViewById(R.id.lying_field),
                (TextView)view.findViewById(R.id.persuasion_field),
                (TextView)view.findViewById(R.id.sneaking_field),
                (TextView)view.findViewById(R.id.survival_field)
        };

        remainingAttsView = (TextView)view.findViewById(R.id.remaining_att_field);
        remainingSkillsView = (TextView)view.findViewById(R.id.remaining_skill_field);

        /**
         * Instantiate the Buttons
         */
        attributeButtons = new Button[]{
                (Button)view.findViewById(R.id.add_strength_button),
                (Button)view.findViewById(R.id.subtract_strength_button),
                (Button)view.findViewById(R.id.add_agility_button),
                (Button)view.findViewById(R.id.subtract_agility_button),
                (Button)view.findViewById(R.id.add_resilience_button),
                (Button)view.findViewById(R.id.subtract_resilience_button),
                (Button)view.findViewById(R.id.add_luck_button),
                (Button)view.findViewById(R.id.subtract_luck_button),
                (Button)view.findViewById(R.id.add_intelligence_button),
                (Button)view.findViewById(R.id.subtract_intelligence_button)
        };
        randomizeAttsButton = (Button)view.findViewById(R.id.random_atts_button);

        skillButtons = new Button[]{
                (Button)view.findViewById(R.id.add_fighting),
                (Button)view.findViewById(R.id.subtract_fighting),
                (Button)view.findViewById(R.id.add_shooting),
                (Button)view.findViewById(R.id.subtract_shooting),
                (Button)view.findViewById(R.id.add_casting),
                (Button)view.findViewById(R.id.subtract_casting),
                (Button)view.findViewById(R.id.add_acrobatics),
                (Button)view.findViewById(R.id.subtract_acrobatics),
                (Button)view.findViewById(R.id.add_crafting),
                (Button)view.findViewById(R.id.subtract_crafting),
                (Button)view.findViewById(R.id.add_gambling),
                (Button)view.findViewById(R.id.subtract_gambling),
                (Button)view.findViewById(R.id.add_lying),
                (Button)view.findViewById(R.id.subtract_lying),
                (Button)view.findViewById(R.id.add_persuasion),
                (Button)view.findViewById(R.id.subtract_persuasion),
                (Button)view.findViewById(R.id.add_sneaking),
                (Button)view.findViewById(R.id.subtract_sneaking),
                (Button)view.findViewById(R.id.add_survival),
                (Button)view.findViewById(R.id.subtract_survival)
        };
        resetSkillsButton = (Button)view.findViewById(R.id.reset_button);
        resetCharacterButton = (Button)view.findViewById(R.id.reset_character);
        confirmButton = (Button)view.findViewById(R.id.confirm_button);
        addItemsButton = (Button)view.findViewById(R.id.add_item_button);
        addSpellsButton = (Button)view.findViewById(R.id.add_spell_button);

        /**
         * Instantiate the ListViews
         */
        itemListView = (LinearLayout)view.findViewById(R.id.item_list_layout);
        spellListView = (LinearLayout)view.findViewById(R.id.spell_list_layout);

        itemListView.setPadding(15,15,15,15);


        /**
         * Add functionality to the buttons
         */
        for(index = 0; index < attributeButtons.length; index++){
            final Button button = attributeButtons[index];
            button.setTag(index);
            if(index%2 == 0){
                attributeButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!remainingAttsView.getText().toString().equals("0")){
                            int attributeIndex = ((Integer)button.getTag()/2);
                            addValue(attributeFields[attributeIndex], remainingAttsView);
                        }
                    }
                });
            }
            else{
                attributeButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int attributeIndex = ((Integer)button.getTag()/2);
                        if(!attributeFields[attributeIndex].getText().toString().equals("1")){
                            subtractValue(attributeFields[attributeIndex], remainingAttsView);
                        }
                    }
                });
            }
        }

        randomizeAttsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] randomAtts = randSum();

                for(int i = 0; i < 5; i++){
                    attributeFields[i].setText(String.valueOf(randomAtts[i]));
                }

                remainingAttsView.setText("0");
            }
        });

        for(index = 0; index < skillButtons.length; index++){
            final Button button = skillButtons[index];
            button.setTag(index);
            if(index%2 == 0){
                skillButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!remainingSkillsView.getText().toString().equals("0")){
                            int attributeIndex = ((Integer)button.getTag()/2);
                            addValue(skillFields[attributeIndex], remainingSkillsView);
                        }
                    }
                });
            }
            else{
                skillButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int attributeIndex = ((Integer)button.getTag()/2);
                        if(!skillFields[attributeIndex].getText().toString().equals("0")){
                            subtractValue(skillFields[attributeIndex], remainingSkillsView);
                        }
                    }
                });
            }
        }

        resetSkillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(index = 0; index < skillFields.length; index++){
                    skillFields[index].setText("0");
                    remainingSkillsView.setText("15");
                }
            }
        });

        resetCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterNameField.setText("");

                raceSpinner.setSelection(0);
                classSpinner.setSelection(0);

                for(int i = 0; i < 5; i++){
                    attributeFields[i].setText("6");
                }
                attributeFields[0].setText("7");
                attributeFields[2].setText("7");
                attributeFields[4].setText("5");

                remainingAttsView.setText("10");

                for(int i = 0; i < 10; i++){
                    skillFields[i].setText("0");
                }
                remainingSkillsView.setText("15");
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar;
                boolean validCharacter = true;
                for(int i = 0; i < attributeFields.length; i++){
                    if(Integer.parseInt(attributeFields[i].getText().toString()) < 1){
                        validCharacter = false;
                    }
                }

                if(remainingAttsView.getText().toString().equals("0") &&
                    remainingSkillsView.getText().toString().equals("0")
                    && !characterNameField.getText().toString().equals("")
                    && !itemsList.isEmpty() && !spellList.isEmpty() &&
                    validCharacter){
                        Character character = new Character();
                        character.setName(characterNameField.getText().toString());

                        character.setRace(raceSpinner.getSelectedItem().toString());
                        character.setCharClass(classSpinner.getSelectedItem().toString());

                        character.setStrength(Integer.parseInt(attributeFields[0].getText().toString()));
                        character.setAgility(Integer.parseInt(attributeFields[1].getText().toString()));
                        character.setResilience(Integer.parseInt(attributeFields[2].getText().toString()));
                        character.setLuck(Integer.parseInt(attributeFields[3].getText().toString()));
                        character.setIntelligence(Integer.parseInt(attributeFields[4].getText().toString()));

                        character.setFighting(Integer.parseInt(skillFields[0].getText().toString()));
                        character.setShooting(Integer.parseInt(skillFields[1].getText().toString()));
                        character.setCasting(Integer.parseInt(skillFields[2].getText().toString()));
                        character.setAcrobatics(Integer.parseInt(skillFields[3].getText().toString()));
                        character.setCrafting(Integer.parseInt(skillFields[4].getText().toString()));
                        character.setGambling(Integer.parseInt(skillFields[5].getText().toString()));
                        character.setLying(Integer.parseInt(skillFields[6].getText().toString()));
                        character.setPersuasion(Integer.parseInt(skillFields[7].getText().toString()));
                        character.setSneaking(Integer.parseInt(skillFields[8].getText().toString()));
                        character.setSurvival(Integer.parseInt(skillFields[9].getText().toString()));

                        character.setItems(itemsList);
                        character.setSpells(spellList);

                        DatabaseHandler db = new DatabaseHandler(getContext());
                        db.addCharacter(character);
                        db.closeDB();

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction trans = fm.beginTransaction();
                    trans.replace(R.id.content_main, new CharacterListFragment());
                    trans.addToBackStack(null);
                    trans.commit();
                }
                else if(!remainingAttsView.getText().equals("0")
                        && !remainingSkillsView.getText().equals("0")){
                    snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "You have unspent attribute and skill points", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(!remainingAttsView.getText().equals("0")){
                    snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "You have unspent attribute points", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(!remainingSkillsView.getText().equals("0")){
                    snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "You have unspent skill points", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(itemsList.isEmpty() && spellList.isEmpty()){
                    snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "At least 1 item and 1 spell is required", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(itemsList.isEmpty()){
                    snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "At least 1 item is required", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(spellList.isEmpty()){
                    snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "At least 1 spell is required", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(!validCharacter){
                    snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Attributes are not allowed to be below 1", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(!characterNameField.getText().equals("")){
                    snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Your character requires a name", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else{
                    snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Please fill in all required fields", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }


            }
        });

        addItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder addItemsDialog = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View convertView = inflater.inflate(R.layout.item_list, null);
                addItemsDialog.setView(convertView);
                addItemsDialog.setTitle(getContext().getResources().getString(R.string.add_items));

                DatabaseHandler db = new DatabaseHandler(getContext());
                ArrayList<Item> tempItemsList = db.getAllItems();
                db.closeDB();

                Log.d("array size", tempItemsList.size() + "");
                ListView listView = (ListView)convertView.findViewById(R.id.item_list);
                listView.setAdapter(new addItemsArrayAdapter(getContext(), tempItemsList));

                addItemsDialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemListView.removeAllViews();
                        for(index = 0; index < itemsList.size(); index++){
                            final Item item = itemsList.get(index);
                            final CardView cardView = new CardView(getContext());
                            LinearLayout layout = new LinearLayout(getContext());
                            TextView itemNameTextView = new TextView(getContext());
                            ImageView itemImageView = new ImageView(getContext());

                            cardView.setUseCompatPadding(true);
                            cardView.setId(index);

                            layout.setOnLongClickListener(new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {
                                    itemsList.remove(item);
                                    itemListView.removeView(cardView);
                                    return false;
                                }
                            });

                            itemNameTextView.setText(itemsList.get(index).getName());
                            itemNameTextView.setTextSize(20);
                            itemNameTextView.setGravity(Gravity.CENTER_VERTICAL);

                            Picasso.with(getContext()).load(itemsList.get(index).getImageId()).resize(80,80).into(itemImageView);

                            layout.addView(itemImageView);
                            layout.addView(itemNameTextView);
                            layout.setPadding(20,20,20,20);

                            cardView.addView(layout);

                            itemListView.addView(cardView);
                        }
                    }
                });

                addItemsDialog.show();
            }
        });

        addSpellsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder addSpellDialog = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View convertView = inflater.inflate(R.layout.item_list, null);
                addSpellDialog.setView(convertView);
                addSpellDialog.setTitle(getContext().getResources().getString(R.string.add_spells));

                DatabaseHandler db = new DatabaseHandler(getContext());
                ArrayList<Spell> tempSpellList = db.getAllSpellsByClass(classArray[classSpinner.getSelectedItemPosition()]);
                db.closeDB();

                Log.d("array size", tempSpellList.size() + "");
                ListView listView = (ListView)convertView.findViewById(R.id.item_list);
                listView.setAdapter(new addSpellsArrayAdapter(getContext(), tempSpellList));

                addSpellDialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemListView.removeAllViews();
                        for(index = 0; index < spellList.size(); index++){
                            final Spell spell = spellList.get(index);
                            final CardView cardView = new CardView(getContext());
                            LinearLayout layout = new LinearLayout(getContext());
                            TextView itemNameTextView = new TextView(getContext());

                            cardView.setUseCompatPadding(true);

                            layout.setOnLongClickListener(new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {
                                    itemsList.remove(spell);
                                    spellListView.removeView(cardView);
                                    return false;
                                }
                            });

                            itemNameTextView.setText(spellList.get(index).getName());
                            itemNameTextView.setTextSize(20);
                            itemNameTextView.setGravity(Gravity.CENTER_VERTICAL);
                            layout.addView(itemNameTextView);
                            layout.setPadding(20,20,20,20);

                            cardView.addView(layout);

                            spellListView.addView(cardView);
                        }
                    }
                });

                addSpellDialog.show();
            }
        });
        return view;
    }

    public void addValue(TextView startTextView, TextView remainingTextView){
        String stringValue = startTextView.getText().toString();
        int value = Integer.parseInt(stringValue);
        value++;
        startTextView.setText(String.valueOf(value));

        stringValue = remainingTextView.getText().toString();
        value = Integer.parseInt(stringValue);
        value--;
        remainingTextView.setText(String.valueOf(value));
    }

    public void subtractValue(TextView startTextView, TextView remainingTextView){
        String stringValue = startTextView.getText().toString();
        int value = Integer.parseInt(stringValue);
        value--;
        startTextView.setText(String.valueOf(value));

        stringValue = remainingTextView.getText().toString();
        value = Integer.parseInt(stringValue);
        value++;
        remainingTextView.setText(String.valueOf(value));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Custom function to randomize the characters attributes
     * takes no paramaters
     * randomly creates an array of integers whose elements
     * add up to be the maximum attribute total at character
     * creation(35)
     * @return and array of integers
     */
    private int[] randSum(){
        //create a new random object and array of integers
        Random r = new Random();
        int[] nums = new int[5];
        if(raceSpinner.getSelectedItem().toString().equals(getContext().getResources().getString(R.string.human))){
            Arrays.fill(nums, 4);
        }
        else{
            Arrays.fill(nums, 3);
        }

        //create integer variables to store the maximum total attribute points a character has
        //and to store the next random attribute
        int max = 20;
        int diff;
        for(int i = 0; i < 4 && max> 0; i++){
            if(max > 11){
                diff = r.nextInt(max-11);
            }
            else{
                diff = r.nextInt(max);
            }
            nums[i] += diff;
            max -= diff;
        }

        nums[4] += max;

        //randomize the ordering of the array
        for(int i = 4; i > 0; i--){
            diff = r.nextInt(5);
            int temp = nums[diff];
            nums[diff] = nums[0];
            nums[0] = temp;
        }

        if(raceSpinner.getSelectedItemPosition() == 1){
            nums[1] += 2;
            nums[2] -= 2;
            nums[4] += 2;
        }
        else if(raceSpinner.getSelectedItemPosition() == 2){
            nums[0] += 2;
            nums[1] -= 2;
            nums[2] += 2;
        }


        if(classSpinner.getSelectedItemPosition() == 0){
            nums[0] += 1;
            nums[2] += 1;
            nums[4] -= 1;
        }
        else if(classSpinner.getSelectedItemPosition() == 1){
            nums[1] += 1;
            nums[2] -= 1;
            nums[3] += 1;
        }
        else if(classSpinner.getSelectedItemPosition() == 2){
            nums[0] -= 1;
            nums[2] += 1;
            nums[4] += 1;
        }
        else{
            nums[0] += 1;
            nums[1] -= 1;
            nums[4] += 1;
        }

        return nums;
    }

    private class addItemsArrayAdapter extends ArrayAdapter {
        ArrayList<Item> tempItemsList;
        public addItemsArrayAdapter(Context context, ArrayList<Item> tempItemsList) {
            super(context, 0, tempItemsList);
            this.tempItemsList = tempItemsList;
        }
        public View getView(final int position, View convertView, ViewGroup parent){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_check_box, parent, false);

            CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.check_box);
            checkBox.setText(tempItemsList.get(position).getName());

            for(int i = 0; i < itemsList.size(); i++){
                if(itemsList.get(i).getId() == tempItemsList.get(position).getId()){
                    checkBox.setChecked(true);
                }
            }

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        if(!itemsList.contains(tempItemsList.get(position))){
                            itemsList.add(tempItemsList.get(position));
                        }
                    }
                    else{
                        itemsList.remove(tempItemsList.get(position));
                    }
                    Collections.sort(itemsList);
                }
            });

            return convertView;
        }
    }

    private class addSpellsArrayAdapter extends ArrayAdapter {
        ArrayList<Spell> tempSpellList;
        public addSpellsArrayAdapter(Context context, ArrayList<Spell> tempSpellList) {
            super(context, 0, tempSpellList);
            this.tempSpellList = tempSpellList;
        }
        public View getView(final int position, View convertView, ViewGroup parent){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_check_box, parent, false);

            CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.check_box);
            checkBox.setText(tempSpellList.get(position).getName());

            for(int i = 0; i < spellList.size(); i++){
                if(itemsList.get(i).getId() == tempSpellList.get(position).getId()){
                    checkBox.setChecked(true);
                }
            }

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        if(!spellList.contains(tempSpellList.get(position))){
                            spellList.add(tempSpellList.get(position));
                        }
                    }
                    else{
                        itemsList.remove(tempSpellList.get(position));
                    }
                    Collections.sort(spellList);
                }
            });

            return convertView;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
