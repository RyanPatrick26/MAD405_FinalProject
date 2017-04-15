package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.transition.Visibility;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemViewerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemViewerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemViewerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    int id;

    private OnFragmentInteractionListener mListener;

    //Create variables to hold all the views
    TextView itemName;
    TextView itemType;
    TextView itemDmg_Def;
    TextView itemDmg_defLabel;
    TextView itemRangeLabel;
    TextView itemRange;
    TextView itemPrice;
    TextView itemDescription;

    ImageView itemImage;

    //Create an item object to store the current item
    Item item;


    public ItemViewerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemViewerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemViewerFragment newInstance(int id) {
        ItemViewerFragment fragment = new ItemViewerFragment();
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
        View view = inflater.inflate(R.layout.fragment_item_viewer, container, false);

        //initialize the views
        itemName = (TextView)view.findViewById(R.id.item_name);
        itemType = (TextView)view.findViewById(R.id.item_type);
        itemDmg_defLabel = (TextView)view.findViewById(R.id.dmg_def_label);
        itemDmg_Def = (TextView)view.findViewById(R.id.item_dmg_def);
        itemRangeLabel = (TextView)view.findViewById(R.id.range_label);
        itemRange = (TextView)view.findViewById(R.id.item_range);
        itemPrice = (TextView)view.findViewById(R.id.item_price);
        itemDescription = (TextView)view.findViewById(R.id.item_description);

        itemImage = (ImageView)view.findViewById(R.id.item_image);

        DatabaseHandler db = new DatabaseHandler(getContext());
        item = db.getItem(id);
        db.closeDB();

        itemName.setText(item.getName());
        itemType.setText(item.getType());
        if(item.getDmg_def() == 0){
            itemDmg_defLabel.setVisibility(View.GONE);
            itemDmg_Def.setVisibility(View.GONE);
        }
        else{
            itemDmg_Def.setText(item.getDmg_def() + "");
        }
        itemPrice.setText(item.getPrice() + "");
        if(item.getRange() == 0){
            itemRangeLabel.setVisibility(View.GONE);
            itemRange.setVisibility(View.GONE);
        }
        else{
            itemRange.setText(item.getRange() + "");
        }
        itemDescription.setText(item.getDescription());

        Picasso.with(getContext()).load(item.getImageId()).resize(120, 120).into(itemImage);

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
