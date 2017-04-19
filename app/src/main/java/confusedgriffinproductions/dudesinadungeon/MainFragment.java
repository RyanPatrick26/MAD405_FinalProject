package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // Fragment Manager to handle navigation to different fragments
    private FragmentManager fm;

    // Button properties
    Button createCharacterButton;
    Button viewSpellsButton;
    Button viewItemsButton;
    Button viewCharactersButton;

    // Create a view
    View view;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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

        // Check to see if the view has already been created
        if(view!=null){
            if((ViewGroup)view.getParent()!=null)
                ((ViewGroup)view.getParent()).removeView(view);
            return view;
        }

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);

        // Programmatically link the button properties to the buttons in the xml
        createCharacterButton = (Button) view.findViewById(R.id.createCharacterButton);
        viewCharactersButton = (Button) view.findViewById(R.id.viewCharactersButton);
        viewItemsButton = (Button) view.findViewById(R.id.viewItemsButton);
        viewSpellsButton = (Button) view.findViewById(R.id.viewSpellsButton);

        // Get the support fragment manager
        fm = getActivity().getSupportFragmentManager();

        // Create the event handlers for the buttons
        createCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Character Creator Fragment
                FragmentTransaction tran = fm.beginTransaction();
                tran.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
                tran.addToBackStack(null);
                tran.replace(R.id.content_main, new CharacterCreatorFragment());
                tran.commit();
            }
        });

        viewCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Character List Fragment
                FragmentTransaction tran = fm.beginTransaction();
                tran.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
                tran.addToBackStack(null);
                tran.replace(R.id.content_main, new CharacterListFragment());
                tran.commit();
            }
        });

        viewItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Item List Fragment
                FragmentTransaction tran = fm.beginTransaction();
                tran.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
                tran.addToBackStack(null);
                tran.replace(R.id.content_main, new ItemListFragment());
                tran.commit();
            }
        });

        viewSpellsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Spell List Fragment
                FragmentTransaction tran = fm.beginTransaction();
                tran.setCustomAnimations(R.anim.slide_down_in, R.anim.slide_down_out);
                tran.addToBackStack(null);
                tran.replace(R.id.content_main, new SpellListFragment());
                tran.commit();
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
