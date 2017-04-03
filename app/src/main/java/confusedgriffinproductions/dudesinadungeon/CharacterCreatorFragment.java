package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


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
    Button randomizeNameButton;
    Button[] attributeButtons;
    Button randomizeAttsButton;
    Button[] skillButtons;
    Button resetSkillsButton;
    Button confirmButton;

    /**
     * Create variables to store the ListViews
     */
    ListView itemListView;
    ListView spellListView;

    int index;

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

        /**
         * Instantiate the EditTexts
         */
        characterNameField = (EditText)view.findViewById(R.id.name_field);

        /**
         * Instantiate the Spinners
         */
        raceSpinner = (Spinner)view.findViewById(R.id.race_spinner);
        classSpinner = (Spinner)view.findViewById(R.id.class_spinner);

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
        randomizeNameButton = (Button)view.findViewById(R.id.random_name_button);
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

        /**
         * Add functionality to the attribute buttons
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
                            addValue(attributeFields[attributeIndex]);
                        }
                    }
                });
            }
            else{
                attributeButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int attributeIndex = ((Integer)button.getTag()/2);
                        if(!attributeFields[attributeIndex].getText().toString().equals("0")){
                            subtractValue(attributeFields[attributeIndex]);
                        }
                    }
                });
            }
        }

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

        confirmButton = (Button)view.findViewById(R.id.confirm_button);

        /**
         * Instantiate the ListViews
         */
        itemListView = (ListView)view.findViewById(R.id.item_list);
        spellListView = (ListView)view.findViewById(R.id.spell_list);

        return view;
    }

    public void addValue(TextView textView){
        String stringValue = textView.getText().toString();
        int value = Integer.parseInt(stringValue);
        value++;
        textView.setText(String.valueOf(value));

        stringValue = remainingAttsView.getText().toString();
        value = Integer.parseInt(stringValue);
        value--;
        remainingAttsView.setText(String.valueOf(value));
    }

    public void subtractValue(TextView textView){
        String stringValue = textView.getText().toString();
        int value = Integer.parseInt(stringValue);
        value--;
        textView.setText(String.valueOf(value));

        stringValue = remainingAttsView.getText().toString();
        value = Integer.parseInt(stringValue);
        value++;
        remainingAttsView.setText(String.valueOf(value));
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
