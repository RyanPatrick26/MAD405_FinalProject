package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharacterListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharacterListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Create arraylist to store the characters
    ArrayList<Character> characterList;

    //create a variable to store the button
    Button characterCreatorButton;

    //create a variable to store the list view
    ListView characterListView;

    public CharacterListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CharacterListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CharacterListFragment newInstance(String param1, String param2) {
        CharacterListFragment fragment = new CharacterListFragment();
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
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);

        characterListView = (ListView)view.findViewById(R.id.character_list);

        DatabaseHandler db = new DatabaseHandler(getContext());
        characterList = db.getAllCharacters();

        db.closeDB();

        characterListView.setAdapter(new CustomAdapter(getContext(), characterList));

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

    public class CustomAdapter extends ArrayAdapter<Character>{
        //create variables to store the textviews
        TextView characterNameTextView;
        TextView strengthTextView;
        TextView agilityTextView;
        TextView resilienceTextView;
        TextView luckTextView;
        TextView intelligenceTextView;

        //create variable to store the button
        Button deleteButton;

        //create a variable to store the image view
        ImageView characterImageView;

        public CustomAdapter(Context context, ArrayList<Character> items) {
            super(context, 0, items);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.character_list_item, parent, false);
            }

            //initialize the TextViews
            characterNameTextView = (TextView)convertView.findViewById(R.id.character_name);
            strengthTextView = (TextView)convertView.findViewById(R.id.strength_view);
            agilityTextView = (TextView)convertView.findViewById(R.id.agility_view);
            resilienceTextView = (TextView)convertView.findViewById(R.id.resilience_view);
            luckTextView = (TextView)convertView.findViewById(R.id.luck_view);
            intelligenceTextView = (TextView)convertView.findViewById(R.id.intelligence_view);

            //initialize the Delete Button
            deleteButton = (Button)convertView.findViewById(R.id.delete_character_button);

            //initialize the ImageView
            characterImageView = (ImageView)convertView.findViewById(R.id.character_image);

            return convertView;
        }



    }
}
