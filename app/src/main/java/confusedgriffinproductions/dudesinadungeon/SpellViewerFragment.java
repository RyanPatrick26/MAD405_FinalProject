package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpellViewerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpellViewerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpellViewerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int id;

    private OnFragmentInteractionListener mListener;

    //create variable to store the views
    TextView spellName;
    TextView spellDamageLabel;
    TextView spellDamageView;
    TextView spellEffectsView;
    TextView spellDescriptionView;

    public SpellViewerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpellViewerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SpellViewerFragment newInstance(int id) {
        SpellViewerFragment fragment = new SpellViewerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spell_viewer, container, false);

        DatabaseHandler db = new DatabaseHandler(getContext());
        Spell spell = db.getSpell(id);
        db.closeDB();

        //initialize the views
        spellName = (TextView)view.findViewById(R.id.spell_name);
        spellDamageLabel = (TextView)view.findViewById(R.id.spell_damage_label);
        spellDamageView = (TextView)view.findViewById(R.id.spell_damage);
        spellEffectsView = (TextView)view.findViewById(R.id.spell_effects);
        spellDescriptionView = (TextView)view.findViewById(R.id.spell_description);

        spellName.setText(spell.getName());
        if(spell.getDmg_heal() == null){
            spellDamageLabel.setVisibility(View.GONE);
            spellDamageView.setVisibility(View.GONE);
        }
        else{
            spellDamageView.setText(spell.getDmg_heal());
        }
        spellEffectsView.setText(spell.getEffects());
        spellDescriptionView.setText(spell.getDescription());

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
