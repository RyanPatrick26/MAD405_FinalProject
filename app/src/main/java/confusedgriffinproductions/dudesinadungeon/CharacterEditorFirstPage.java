package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        /**
         * Initialize the TextViews and set their text
         */
        characterNameTextView = (TextView)view.findViewById(R.id.character_name);
        characterClassTextView = (TextView)view.findViewById(R.id.character_class_value);
        characterRaceTextView = (TextView)view.findViewById(R.id.character_race_value);

        characterNameTextView.setText(CharacterEditorFragment.tempCharacter.getName());
        characterClassTextView.setText(CharacterEditorFragment.tempCharacter.getCharClass());
        characterRaceTextView.setText(CharacterEditorFragment.tempCharacter.getRace());

        attributeTextViews = new TextView[]{
                (TextView)view.findViewById(R.id.strength_value),
                (TextView)view.findViewById(R.id.agility_value),
                (TextView)view.findViewById(R.id.resilience_value),
                (TextView)view.findViewById(R.id.luck_value),
                (TextView)view.findViewById(R.id.intelligence_value)
        };

        attributeTextViews[0].setText(CharacterEditorFragment.tempCharacter.getStrength() + "  ");
        if(CharacterEditorFragment.tempCharacter.getStrength() < 10){
            attributeTextViews[0].append(" ");
        }
        attributeTextViews[1].setText(CharacterEditorFragment.tempCharacter.getAgility() + "  ");
        if(CharacterEditorFragment.tempCharacter.getAgility() < 10){
            attributeTextViews[1].append(" ");
        }
        attributeTextViews[2].setText(CharacterEditorFragment.tempCharacter.getResilience() + "  ");
        if(CharacterEditorFragment.tempCharacter.getResilience() < 10){
            attributeTextViews[2].append(" ");
        }
        attributeTextViews[3].setText(CharacterEditorFragment.tempCharacter.getLuck() + "  ");
        if(CharacterEditorFragment.tempCharacter.getLuck() < 10){
            attributeTextViews[3].append(" ");
        }
        attributeTextViews[4].setText(CharacterEditorFragment.tempCharacter.getIntelligence() + "  ");
        if(CharacterEditorFragment.tempCharacter.getIntelligence() < 10){
            attributeTextViews[4].append(" ");
        }

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

        skillTextViews[0].setText(CharacterEditorFragment.tempCharacter.getFighting() + "  ");
        if(CharacterEditorFragment.tempCharacter.getFighting() < 10){
            skillTextViews[0].append(" ");
        }
        skillTextViews[1].setText(CharacterEditorFragment.tempCharacter.getShooting() + "  ");
        if(CharacterEditorFragment.tempCharacter.getShooting() < 10){
            skillTextViews[1].append(" ");
        }
        skillTextViews[2].setText(CharacterEditorFragment.tempCharacter.getCasting() + "  ");
        if(CharacterEditorFragment.tempCharacter.getCasting() < 10){
            skillTextViews[2].append(" ");
        }
        skillTextViews[3].setText(CharacterEditorFragment.tempCharacter.getAcrobatics() + "  ");
        .if(CharacterEditorFragment.tempCharacter.getAcrobatics() < 10){
            skillTextViews[3].append(" ");
        }
        skillTextViews[4].setText(CharacterEditorFragment.tempCharacter.getCrafting() + "  ");
        if(CharacterEditorFragment.tempCharacter.getCrafting() < 10){
            skillTextViews[4].append(" ");
        }
        skillTextViews[5].setText(CharacterEditorFragment.tempCharacter.getGambling() +"  ");
        if(CharacterEditorFragment.tempCharacter.getGambling() < 10){
            skillTextViews[5].append(" ");
        }
        skillTextViews[6].setText(CharacterEditorFragment.tempCharacter.getLying() + "  ");
        if(CharacterEditorFragment.tempCharacter.getLying() < 10){
            skillTextViews[6].append(" ");
        }
        skillTextViews[7].setText(CharacterEditorFragment.tempCharacter.getPersuasion() + "  ");
        if(CharacterEditorFragment.tempCharacter.getPersuasion() < 10){
            skillTextViews[7].append(" ");
        }
        skillTextViews[8].setText(CharacterEditorFragment.tempCharacter.getSneaking() + "  ");
        if(CharacterEditorFragment.tempCharacter.getSneaking() < 10){
            skillTextViews[8].append(" ");
        }
        skillTextViews[9].setText(CharacterEditorFragment.tempCharacter.getSurvival() + "  ");
        if(CharacterEditorFragment.tempCharacter.getSurvival() < 10){
            skillTextViews[9].append(" ");
        }


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
