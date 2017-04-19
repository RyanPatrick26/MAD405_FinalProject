package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
     * Create variables to store the Buttons
     */
    Button[] attributeButtons;
    Button[] skillButtons;
    Button resetButton;
    Button confirmButton;

    /**
     * Create variable to store the ImageView
     */
    ImageView characterImage;

    int index;
    Character tempCharacter;

    public CharacterEditorFirstPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CharacterEditorFirstPage.
     */
    // TODO: Rename and change types and number of parameters
    public static CharacterEditorFirstPage newInstance(String param1, String param2) {
        CharacterEditorFirstPage fragment = new CharacterEditorFirstPage();
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
        View view = inflater.inflate(R.layout.fragment_character_editor_first_page, container, false);
        tempCharacter = CharacterEditorFragment.character;

        /**
         * Initialize the TextViews and set their text
         */
        characterNameTextView = (TextView)view.findViewById(R.id.character_name);
        characterClassTextView = (TextView)view.findViewById(R.id.character_class_value);
        characterRaceTextView = (TextView)view.findViewById(R.id.character_race_value);

        characterNameTextView.setText(tempCharacter.getName());
        characterClassTextView.setText(tempCharacter.getCharClass());
        characterRaceTextView.setText(tempCharacter.getRace());

        attributeTextViews = new TextView[]{
                (TextView)view.findViewById(R.id.strength_value),
                (TextView)view.findViewById(R.id.agility_value),
                (TextView)view.findViewById(R.id.resilience_value),
                (TextView)view.findViewById(R.id.luck_value),
                (TextView)view.findViewById(R.id.intelligence_value)
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

        setTextViewsText();


        /**
         * Initialize the Buttons and set their functionality
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

        for(index = 0; index < attributeButtons.length; index++){
            final Button button = attributeButtons[index];
            button.setTag(index);
            if(index%2 == 0){
                attributeButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int attributeIndex = ((Integer)button.getTag()/2);
                        if(!attributeTextViews[attributeIndex].getText().toString().trim().equals("25")){
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
                        if(!attributeTextViews[attributeIndex].getText().toString().trim().equals("1")){
                            subtractValue(attributeTextViews[attributeIndex]);
                        }
                    }
                });
            }
        }

        skillButtons = new Button[]{
                (Button)view.findViewById(R.id.add_fighting_button),
                (Button)view.findViewById(R.id.subtract_fighting_button),
                (Button)view.findViewById(R.id.add_shooting_button),
                (Button)view.findViewById(R.id.subtract_shooting_button),
                (Button)view.findViewById(R.id.add_casting_button),
                (Button)view.findViewById(R.id.subtract_casting_button),
                (Button)view.findViewById(R.id.add_acrobatics_button),
                (Button)view.findViewById(R.id.subtract_acrobatics_button),
                (Button)view.findViewById(R.id.add_crafting_button),
                (Button)view.findViewById(R.id.subtract_crafting_button),
                (Button)view.findViewById(R.id.add_gambling_button),
                (Button)view.findViewById(R.id.subtract_gambling_button),
                (Button)view.findViewById(R.id.add_lying_button),
                (Button)view.findViewById(R.id.subtract_lying_button),
                (Button)view.findViewById(R.id.add_persuasion_button),
                (Button)view.findViewById(R.id.subtract_persuasion_button),
                (Button)view.findViewById(R.id.add_sneaking_button),
                (Button)view.findViewById(R.id.subtract_sneaking_button),
                (Button)view.findViewById(R.id.add_survival_button),
                (Button)view.findViewById(R.id.subtract_survival_button)
        };

        for(index = 0; index < skillButtons.length; index++){
            final Button button = skillButtons[index];
            button.setTag(index);
            if(index%2 == 0){
                skillButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int attributeIndex = ((Integer)button.getTag()/2);
                        if(!skillTextViews[attributeIndex].getText().toString().trim().equals("20")){
                            addValue(skillTextViews[attributeIndex]);
                        }
                    }
                });
            }
            else{
                skillButtons[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int attributeIndex = ((Integer)button.getTag()/2);
                        if(!skillTextViews[attributeIndex].getText().toString().trim().equals("0")){
                            subtractValue(skillTextViews[attributeIndex]);
                        }
                    }
                });
            }
        }

        resetButton = (Button)view.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCharacter = CharacterEditorFragment.character;
                setTextViewsText();
            }
        });

        confirmButton = (Button)view.findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCharacter.setStrength(Integer.parseInt(attributeTextViews[0].getText().toString().trim()));
                tempCharacter.setAgility(Integer.parseInt(attributeTextViews[1].getText().toString().trim()));
                tempCharacter.setResilience(Integer.parseInt(attributeTextViews[2].getText().toString().trim()));
                tempCharacter.setLuck(Integer.parseInt(attributeTextViews[3].getText().toString().trim()));
                tempCharacter.setIntelligence(Integer.parseInt(attributeTextViews[4].getText().toString().trim()));

                tempCharacter.setFighting(Integer.parseInt(skillTextViews[0].getText().toString().trim()));
                tempCharacter.setShooting(Integer.parseInt(skillTextViews[1].getText().toString().trim()));
                tempCharacter.setCasting(Integer.parseInt(skillTextViews[2].getText().toString().trim()));
                tempCharacter.setAcrobatics(Integer.parseInt(skillTextViews[3].getText().toString().trim()));
                tempCharacter.setCrafting(Integer.parseInt(skillTextViews[4].getText().toString().trim()));
                tempCharacter.setGambling(Integer.parseInt(skillTextViews[5].getText().toString().trim()));
                tempCharacter.setLying(Integer.parseInt(skillTextViews[6].getText().toString().trim()));
                tempCharacter.setPersuasion(Integer.parseInt(skillTextViews[7].getText().toString().trim()));
                tempCharacter.setSneaking(Integer.parseInt(skillTextViews[8].getText().toString().trim()));
                tempCharacter.setSurvival(Integer.parseInt(skillTextViews[9].getText().toString().trim()));

                DatabaseHandler db = new DatabaseHandler(getContext());
                db.updateCharacter(tempCharacter);
                db.closeDB();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });


        return view;
    }

    public void setTextViewsText(){
        attributeTextViews[0].setText(tempCharacter.getStrength() + "  ");
        if(tempCharacter.getStrength() < 10){
            attributeTextViews[0].append(" ");
        }
        attributeTextViews[1].setText(tempCharacter.getAgility() + "  ");
        if(tempCharacter.getAgility() < 10){
            attributeTextViews[1].append(" ");
        }
        attributeTextViews[2].setText(tempCharacter.getResilience() + "  ");
        if(tempCharacter.getResilience() < 10){
            attributeTextViews[2].append(" ");
        }
        attributeTextViews[3].setText(tempCharacter.getLuck() + "  ");
        if(tempCharacter.getLuck() < 10){
            attributeTextViews[3].append(" ");
        }
        attributeTextViews[4].setText(tempCharacter.getIntelligence() + "  ");
        if(tempCharacter.getIntelligence() < 10){
            attributeTextViews[4].append(" ");
        }

        skillTextViews[0].setText(tempCharacter.getFighting() + "  ");
        if(tempCharacter.getFighting() < 10){
            skillTextViews[0].append(" ");
        }
        skillTextViews[1].setText(tempCharacter.getShooting() + "  ");
        if(tempCharacter.getShooting() < 10){
            skillTextViews[1].append(" ");
        }
        skillTextViews[2].setText(tempCharacter.getCasting() + "  ");
        if(tempCharacter.getCasting() < 10){
            skillTextViews[2].append(" ");
        }
        skillTextViews[3].setText(tempCharacter.getAcrobatics() + "  ");
        if(tempCharacter.getAcrobatics() < 10){
            skillTextViews[3].append(" ");
        }
        skillTextViews[4].setText(tempCharacter.getCrafting() + "  ");
        if(tempCharacter.getCrafting() < 10){
            skillTextViews[4].append(" ");
        }
        skillTextViews[5].setText(tempCharacter.getGambling() +"  ");
        if(tempCharacter.getGambling() < 10){
            skillTextViews[5].append(" ");
        }
        skillTextViews[6].setText(tempCharacter.getLying() + "  ");
        if(tempCharacter.getLying() < 10){
            skillTextViews[6].append(" ");
        }
        skillTextViews[7].setText(tempCharacter.getPersuasion() + "  ");
        if(tempCharacter.getPersuasion() < 10){
            skillTextViews[7].append(" ");
        }
        skillTextViews[8].setText(tempCharacter.getSneaking() + "  ");
        if(tempCharacter.getSneaking() < 10){
            skillTextViews[8].append(" ");
        }
        skillTextViews[9].setText(tempCharacter.getSurvival() + "  ");
        if(tempCharacter.getSurvival() < 10){
            skillTextViews[9].append(" ");
        }
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
        String stringValue = startTextView.getText().toString().trim();
        int value = Integer.parseInt(stringValue);
        value++;
        startTextView.setText(value + "  ");
        if(value < 10){
            startTextView.append(" ");
        }
    }

    public void subtractValue(TextView startTextView){
        String stringValue = startTextView.getText().toString().trim();
        int value = Integer.parseInt(stringValue);
        value--;

        startTextView.setText(value + "  ");
        if(value < 10){
            startTextView.append(" ");
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
