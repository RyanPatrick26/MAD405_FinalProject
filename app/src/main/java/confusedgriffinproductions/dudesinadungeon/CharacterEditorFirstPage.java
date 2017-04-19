package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharacterEditorFirstPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharacterEditorFirstPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterEditorFirstPage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Character character;

    private OnFragmentInteractionListener mListener;
    
    /**
     * Create variables to store the TextViews
     */
    TextView characterNameTextView;
    TextView characterRaceTextView;
    TextView characterClassTextView;
    TextView[] attributeTextViews;
    TextView[] skillTextViews;
    
    /**
     * Create variables to store the buttons
     */
    Button[] attributeButtons;
    Button[] skillButtons;
    Button confirmButton;

    /**
     * Create variable to store the ImageButton
     */
    ImageButton characterImageButton;

    int index;

    public CharacterEditorFirstPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param character character being edited
     * @return A new instance of fragment CharacterEditorFirstPage.
     */
    // TODO: Rename and change types and number of parameters
    public static CharacterEditorFirstPage newInstance(Character character) {
        CharacterEditorFirstPage fragment = new CharacterEditorFirstPage();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, character);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            character = (Character)getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_editor_first_page, container, false);

        /**
         * Initialize the TextViews
         */
        characterNameTextView = (TextView)view.findViewById(R.id.character_name);
        characterRaceTextView = (TextView)view.findViewById(R.id.character_race_value);
        characterClassTextView = (TextView)view.findViewById(R.id.character_class_value);
        attributeTextViews = new TextView[]{
                (TextView) view.findViewById(R.id.strength_value),
                (TextView) view.findViewById(R.id.agility_value),
                (TextView) view.findViewById(R.id.resilience_value),
                (TextView) view.findViewById(R.id.luck_value),
                (TextView) view.findViewById(R.id.intelligence_value)
        };
        
        skillTextViews = new TextView[]{
                (TextView)view.findViewById(R.id.fighting_value),
                (TextView)view.findViewById(R.id.shooting_value),
                (TextView)view.findViewById(R.id.casting_value),
                (TextView)view.findViewById(R.id.acrobatics_value),
                (TextView)view.findViewById(R.id.crafting_value),
                (TextView)view.findViewById(R.id.gambling_value),
                (TextView)view.findViewById(R.id.lying_value),
                (TextView)view.findViewById(R.id.persuasion_value),
                (TextView)view.findViewById(R.id.sneaking_value),
                (TextView)view.findViewById(R.id.survival_value)
        };

        characterNameTextView.setText(character.getName());
        characterRaceTextView.setText(character.getRace());
        characterClassTextView.setText(character.getCharClass());

        attributeTextViews[0].setText(character.getStrength() + "  ");
        attributeTextViews[1].setText(character.getAgility() + "  ");
        attributeTextViews[2].setText(character.getResilience() + "  ");
        attributeTextViews[3].setText(character.getLuck() + "  ");
        attributeTextViews[4].setText(character.getIntelligence() + "  ");

        skillTextViews[0].setText(character.getFighting() + "  ");
        skillTextViews[1].setText(character.getShooting() + "  ");
        skillTextViews[2].setText(character.getCasting() + "  ");
        skillTextViews[3].setText(character.getAcrobatics() + "  ");
        skillTextViews[4].setText(character.getCrafting() + "  ");
        skillTextViews[5].setText(character.getGambling() + "  ");
        skillTextViews[6].setText(character.getLying() + "  ");
        skillTextViews[7].setText(character.getPersuasion() + "  ");
        skillTextViews[8].setText(character.getSneaking() + "  ");
        skillTextViews[9].setText(character.getSurvival() + "  ");

        /**
         * Initialize the buttons
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
                (Button)view.findViewById(R.id.add_int_button),
                (Button)view.findViewById(R.id.subtract_int_button)
        };

        for(index = 0; index < attributeButtons.length; index++){
            final Button button = attributeButtons[index];
            button.setTag(index);
            if(index%2 == 0){
                attributeButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int attributeIndex = ((Integer)button.getTag()/2);
                        if(!attributeTextViews[attributeIndex].getText().toString().equals("25")){
                            addValue(attributeTextViews[attributeIndex]);
                        }
                    }
                });
            }
            else{
                attributeButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int attributeIndex = ((Integer)button.getTag()/2);
                        if(!attributeTextViews[attributeIndex].getText().toString().equals("1")){
                            subtractValue(attributeTextViews[attributeIndex]);
                        }
                    }
                });
            }
        }

        skillButtons = new Button[]{
                
        };


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

    public void addValue(TextView startTextView){
        String stringValue = startTextView.getText().toString();
        int value = Integer.parseInt(stringValue);
        value++;
        startTextView.setText(value + "  ");
    }

    public void subtractValue(TextView startTextView){
        String stringValue = startTextView.getText().toString();
        int value = Integer.parseInt(stringValue);
        value--;
        startTextView.setText(value + "  ");
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
