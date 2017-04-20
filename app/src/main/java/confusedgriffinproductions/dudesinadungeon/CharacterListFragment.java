package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
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

    //Create a fragment manager
    FragmentManager fm;

    CustomAdapter adapter;

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

        fm = getActivity().getSupportFragmentManager();

        //initialize the ListView
        characterListView = (ListView)view.findViewById(R.id.character_list);

        //initialize the Character Creator Button
        characterCreatorButton = (Button)view.findViewById(R.id.create_a_character);
        characterCreatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = fm.beginTransaction();
                trans.addToBackStack(null);
                trans.replace(R.id.content_main, new CharacterCreatorFragment());
                trans.commit();
            }
        });

        DatabaseHandler db = new DatabaseHandler(getContext());
        characterList = db.getAllCharacters();
        Log.d("array size", "" + characterList.size());

        db.closeDB();

        adapter = new CustomAdapter(getContext(), characterList);
        characterListView.setAdapter(adapter);

        characterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                trans.addToBackStack(null);
                trans.replace(R.id.content_main, CharacterEditorFragment.newInstance(characterList.get(position).getId()));
                trans.commit();
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

        public View getView(final int position, View convertView, ViewGroup parent){
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

            characterNameTextView.setText(characterList.get(position).getName());
            strengthTextView.setText(characterList.get(position).getStrength() + "");
            agilityTextView.setText(characterList.get(position).getAgility() + "");
            resilienceTextView.setText(characterList.get(position).getResilience() + "");
            luckTextView.setText(characterList.get(position).getLuck() + "");
            intelligenceTextView.setText(characterList.get(position).getIntelligence() + "");

            //initialize the Delete Button
            deleteButton = (Button)convertView.findViewById(R.id.delete_character_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getContext()).
                            setMessage(getContext().getResources().getString(R.string.delete_confirmation)).
                            setCancelable(false).
                            setPositiveButton(getContext().getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatabaseHandler db = new DatabaseHandler(getContext());
                                    db.deleteCharacter(characterList.get(position).getId());
                                    db.closeDB();
                                    characterList.remove(position);
                                    adapter.notifyDataSetChanged();
                                }
                            }).
                            setNegativeButton(getContext().getResources().getString(R.string.no), null).show();
                }
            });

            //initialize the ImageView
            characterImageView = (ImageView)convertView.findViewById(R.id.character_image);
            DatabaseHandler db = new DatabaseHandler(getContext());
            ArrayList<Portrait> characterPortraitList = db.getAllCharacterPortraits(characterList.get(position).getId());
            Portrait characterPortrait = characterPortraitList.get(0);
            Picasso.with(getContext()).load(new File(characterPortrait.getResource()))
                    .resize(100,100).centerCrop().into(characterImageView);
            db.closeDB();

            return convertView;
        }



    }
}
