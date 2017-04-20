package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharacterEditorSecondPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharacterEditorSecondPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterEditorSecondPage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Create a variable to store the TextView
     */
    TextView characterNameTextView;

    /**
     * Create variables to store the ListViews
     */
    ListView itemsListView;
    ListView spellsListView;

    /**
     * Create variables to store the buttons
     */
    Button addItemButton;
    Button addSpellButton;
    Button resetButton;
    Button confirmButton;

    /**
     * Create other variables
     */
    Character tempCharacter;
    static ArrayList<Item> itemsList;
    static ArrayList<Item> addedItemList;
    static ArrayList<Item> removedItemList;
    static ArrayList<Spell> spellsList;
    static ArrayList<Spell> addedSpellList;
    static ArrayList<Spell> removedSpellList;
    static ItemListAdapter itemListAdapter;
    static SpellListAdapter spellListAdapter;
    addItemsArrayAdapter addItemsAdapter;
    addSpellsArrayAdapter addSpellsAdapter;

    private Character character;

    private OnFragmentInteractionListener mListener;

    public CharacterEditorSecondPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CharacterEditorSecondPage.
     */
    // TODO: Rename and change types and number of parameters
    public static CharacterEditorSecondPage newInstance(String param1, String param2) {
        CharacterEditorSecondPage fragment = new CharacterEditorSecondPage();
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
        View view = inflater.inflate(R.layout.fragment_character_editor_second_page, container, false);
        tempCharacter = CharacterEditorFragment.character;
        itemsList = tempCharacter.getItems();
        spellsList = tempCharacter.getSpells();

        addedItemList = new ArrayList<>();
        addedSpellList = new ArrayList<>();

        removedItemList = new ArrayList<>();
        removedSpellList = new ArrayList<>();

        characterNameTextView = (TextView)view.findViewById(R.id.character_name);
        characterNameTextView.setText(tempCharacter.getName());

        itemsListView = (ListView)view.findViewById(R.id.item_list_view);
        spellsListView = (ListView)view.findViewById(R.id.spell_list_view);

        itemListAdapter = new ItemListAdapter(getActivity(), itemsList);
        spellListAdapter = new SpellListAdapter(getActivity(), spellsList);

        itemsListView.setAdapter(itemListAdapter);
        spellsListView.setAdapter(spellListAdapter);

        itemsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removedItemList.add(itemsList.get(position));
                itemsList.remove(position);
                itemListAdapter.notifyDataSetChanged();
                return false;
            }
        });

        spellsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removedSpellList.add(spellsList.get(position));
                spellsList.remove(position);
                spellListAdapter.notifyDataSetChanged();
                return false;
            }
        });

        resetButton = (Button)view.findViewById(R.id.reset_button);
        confirmButton = (Button)view.findViewById(R.id.confirm_button);
        addItemButton = (Button)view.findViewById(R.id.add_items_button);
        addSpellButton = (Button)view.findViewById(R.id.add_spells_button);

        addItemButton.setOnClickListener(new View.OnClickListener() {
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

                ListView listView = (ListView)convertView.findViewById(R.id.item_list);
                addItemsAdapter = new addItemsArrayAdapter(getContext(), tempItemsList);
                listView.setAdapter(addItemsAdapter);

                addItemsDialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemListAdapter.notifyDataSetChanged();
                    }
                });
                addItemsDialog.show();
            }
        });

        addSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder addSpellsDialog = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View convertView = inflater.inflate(R.layout.item_list, null);
                addSpellsDialog.setView(convertView);
                addSpellsDialog.setTitle(getContext().getResources().getString(R.string.add_spells));

                DatabaseHandler db = new DatabaseHandler(getContext());
                ArrayList<Spell> tempSpellList = db.getAllSpellsByClass(tempCharacter.getCharClass());
                db.closeDB();

                ListView listView = (ListView)convertView.findViewById(R.id.item_list);
                addSpellsAdapter = new addSpellsArrayAdapter(getContext(), tempSpellList);
                listView.setAdapter(addSpellsAdapter);

                addSpellsDialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        spellListAdapter.notifyDataSetChanged();
                    }
                });
                addSpellsDialog.show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCharacter = CharacterEditorFragment.character;
                CharacterEditorFirstPage.setTextViewsText();
                if(CharacterEditorFirstPage.characterPortrait != null){
                    CharacterEditorFirstPage.characterImage.setImageResource(R.drawable.ic_menu_camera);
                }

                if(removedItemList.size() > 0){
                    for(int i = 0; i < removedItemList.size(); i++){
                        itemsList.add(removedItemList.get(i));
                    }
                }
                for(int i = 0; i < addedItemList.size(); i++){
                    itemsList.remove(addedItemList.get(i));
                }
                addedItemList.clear();
                for(int i = 0; i < removedItemList.size(); i++){
                    removedItemList.remove(i);
                }
                Collections.sort(itemsList);
                itemListAdapter.notifyDataSetChanged();

                if(removedSpellList.size() > 0){
                    for(int i = 0; i < removedSpellList.size(); i++){
                        spellsList.add(removedSpellList.get(i));
                    }
                }
                for(int i = 0; i < addedSpellList.size(); i++){
                    spellsList.remove(addedSpellList.get(i));
                }

                addedSpellList.clear();
                for(int i = 0; i < removedSpellList.size(); i++){
                    removedSpellList.remove(i);
                }
                Collections.sort(spellsList);
                spellListAdapter.notifyDataSetChanged();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCharacter.setItems(itemsList);
                tempCharacter.setSpells(spellsList);

                tempCharacter.setStrength(Integer.parseInt(CharacterEditorFirstPage.attributeTextViews[0].getText().toString().trim()));
                tempCharacter.setAgility(Integer.parseInt(CharacterEditorFirstPage.attributeTextViews[1].getText().toString().trim()));
                tempCharacter.setResilience(Integer.parseInt(CharacterEditorFirstPage.attributeTextViews[2].getText().toString().trim()));
                tempCharacter.setLuck(Integer.parseInt(CharacterEditorFirstPage.attributeTextViews[3].getText().toString().trim()));
                tempCharacter.setIntelligence(Integer.parseInt(CharacterEditorFirstPage.attributeTextViews[4].getText().toString().trim()));

                tempCharacter.setFighting(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[0].getText().toString().trim()));
                tempCharacter.setShooting(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[1].getText().toString().trim()));
                tempCharacter.setCasting(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[2].getText().toString().trim()));
                tempCharacter.setAcrobatics(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[3].getText().toString().trim()));
                tempCharacter.setCrafting(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[4].getText().toString().trim()));
                tempCharacter.setGambling(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[5].getText().toString().trim()));
                tempCharacter.setLying(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[6].getText().toString().trim()));
                tempCharacter.setPersuasion(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[7].getText().toString().trim()));
                tempCharacter.setSneaking(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[8].getText().toString().trim()));
                tempCharacter.setSurvival(Integer.parseInt(CharacterEditorFirstPage.skillTextViews[9].getText().toString().trim()));

                DatabaseHandler db = new DatabaseHandler(getContext());
                if(CharacterEditorFirstPage.tempPortrait != null){
                    CharacterEditorFirstPage.characterPortrait = CharacterEditorFirstPage.tempPortrait;
                    int imgId = db.addPortrait(CharacterEditorFirstPage.characterPortrait);
                    db.addCharacterPortrait(imgId, tempCharacter.getId());
                }
                db.updateCharacter(tempCharacter);
                db.closeDB();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        return view;
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
                            addedItemList.add(tempItemsList.get(position));
                        }
                    }
                    else{
                        itemsList.remove(tempItemsList.get(position));
                        addedItemList.remove(tempItemsList.get(position));
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

            for(int i = 0; i < spellsList.size(); i++){
                if(spellsList.get(i).getId() == tempSpellList.get(position).getId()){
                    checkBox.setChecked(true);
                }
            }

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        if(!spellsList.contains(tempSpellList.get(position))){
                            spellsList.add(tempSpellList.get(position));
                            addedSpellList.add(tempSpellList.get(position));
                        }
                    }
                    else{
                        itemsList.remove(tempSpellList.get(position));
                        addedSpellList.remove(tempSpellList.get(position));
                    }
                    Collections.sort(spellsList);
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
