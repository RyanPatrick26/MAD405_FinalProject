package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //create variables to store the views
    TabHost tabHost;
    TabWidget tabWidget;
    TextView[] tabs;
    FrameLayout tabContent;

    //create variables to store the ListViews
    ListView allItemsListView;
    ListView weaponsListView;
    ListView armorListView;
    ListView equipmentListView;

    public ItemListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemListFragment newInstance(String param1, String param2) {
        ItemListFragment fragment = new ItemListFragment();
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
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        //instantiate at start the TabHost
        tabHost = (TabHost)view.findViewById(R.id.items_tab_host);
        tabHost.setup();

        //instantiate the TabWidget
        tabWidget = tabHost.getTabWidget();
        tabContent = tabHost.getTabContentView();

        //instantiate the TextViews
        tabs = new TextView[tabWidget.getTabCount()];
        for (int index = 0; index < tabs.length; index++) {
            tabs[index] = (TextView)tabWidget.getChildTabViewAt(index);
        }
        tabWidget.removeAllViews();

        // Ensure that all tab content childs are not visible at startup.
        for (int index = 0; index < tabs.length; index++) {
            tabContent.getChildAt(index).setVisibility(View.GONE);
        }

        // Create the TabSpec based on the TextViews
        for (int index = 0; index < tabs.length; index++) {
            TextView tabWidgetTextView = tabs[index];
            final View tabContentView = tabContent.getChildAt(index);
            TabSpec tabSpec = tabHost.newTabSpec((String) tabWidgetTextView.getTag());
            tabSpec.setContent(new TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return tabContentView;
                }
            });
            if (tabWidgetTextView.getBackground() == null) {
                tabSpec.setIndicator(tabWidgetTextView.getText());
            } else {
                tabSpec.setIndicator(tabWidgetTextView.getText(), tabWidgetTextView.getBackground());
            }
            tabHost.addTab(tabSpec);
        }

        //prevent the textviews from being clipped
        for(int index = 0; index < tabs.length; index++){
            View tabView = tabHost.getTabWidget().getChildTabViewAt(index);
            tabView.setPadding(0,0,0,0);
            TextView tv = (TextView)tabHost.getTabWidget().getChildAt(index).findViewById(android.R.id.title);
            tv.setTextSize(12);
            tv.setGravity(Gravity.CENTER);
        }

        //instantiate the listviews
        allItemsListView = (ListView)view.findViewById(R.id.all_items_list);
        weaponsListView = (ListView)view.findViewById(R.id.weapons_list);
        armorListView = (ListView)view.findViewById(R.id.armor_list);
        equipmentListView = (ListView)view.findViewById(R.id.other_items_list);

        DatabaseHandler db = new DatabaseHandler(getContext());

        ArrayList<Item> allItemsList = db.getAllItems();

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
