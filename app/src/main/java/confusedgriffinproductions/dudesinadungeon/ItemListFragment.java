package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    //create variables to store the ListViews
    ListView allItemsListView;
    ListView weaponsListView;
    ListView armorListView;
    ListView equipmentListView;

    //create arraylists for the items
    ArrayList<Item> allItemsList;
    ArrayList<Item> weaponsList;
    ArrayList<Item> armorList;
    ArrayList<Item> equipmentList;

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
        final View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        //instantiate at start the TabHost
        tabHost = (TabHost) view.findViewById(R.id.items_tab_host);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // Colour for the unselected tabs
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i)
                            .setBackgroundResource(R.color.colorDivider);
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    tv.setTextColor(getResources().getColor(R.color.colorAccent, null));
                }
                // Colour for the selected tab
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab())
                        .setBackgroundResource(R.color.colorAccent);
                TextView tv = (TextView) tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(android.R.id.title);
                tv.setTextColor(getResources().getColor(R.color.colorPrimary, null));
            }
        });

        tabHost.setup();

        //instantiate the TabWidget
        tabWidget = tabHost.getTabWidget();

        DatabaseHandler db = new DatabaseHandler(getContext());

        allItemsList = db.getAllItems();
        weaponsList = db.getAllItems("Weapon");
        armorList = db.getAllItems("Armor");
        equipmentList = db.getAllItems("Equipment");
        int[] portraitsList = new int[]{
                R.drawable.sword,
                R.drawable.axe,
                R.drawable.spear,
                R.drawable.bow,
                R.drawable.crossbow,
                R.drawable.leather_armor,
                R.drawable.chain_mail,
                R.drawable.plate_mail,
                R.drawable.shield,
                R.drawable.backpack,
                R.drawable.canteen,
                R.drawable.tinder_box,
                R.drawable.tent,
                R.drawable.sleeping_bag,
                R.drawable.rations
        };

        Log.d("allItemsSize", "" + allItemsList.size());

        TextView allItemsTab = (TextView) view.findViewById(R.id.all_items_tab);
        TextView weaponsTab = (TextView) view.findViewById(R.id.weapons_tab);
        TextView armorTab = (TextView) view.findViewById(R.id.armor_tab);
        TextView equipmentTab = (TextView) view.findViewById(R.id.other_items_tab);

        //instantiate the listviews
        allItemsListView = (ListView) view.findViewById(R.id.all_items_list);
        weaponsListView = (ListView) view.findViewById(R.id.weapons_list);
        armorListView = (ListView) view.findViewById(R.id.armor_list);
        equipmentListView = (ListView) view.findViewById(R.id.other_items_list);

        allItemsListView.setAdapter(new ItemListAdapter(getActivity(), allItemsList));
        weaponsListView.setAdapter(new ItemListAdapter(getActivity(), weaponsList));
        armorListView.setAdapter(new ItemListAdapter(getActivity(), armorList));
        equipmentListView.setAdapter(new ItemListAdapter(getActivity(), equipmentList));

        // add views to tab host
        TabSpec tabSpec1 = tabHost.newTabSpec((String) allItemsTab.getTag());
        tabSpec1.setContent(new TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return allItemsListView;
            }
        });
        tabSpec1.setIndicator(allItemsTab.getText());

        TabSpec tabSpec2 = tabHost.newTabSpec((String) weaponsTab.getTag());
        tabSpec2.setContent(new TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return weaponsListView;
            }
        });
        tabSpec2.setIndicator(weaponsTab.getText());

        TabSpec tabSpec3 = tabHost.newTabSpec((String) armorTab.getTag());
        tabSpec3.setContent(new TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return armorListView;
            }
        });
        tabSpec3.setIndicator(armorTab.getText());

        TabSpec tabSpec4 = tabHost.newTabSpec((String) equipmentTab.getTag());
        tabSpec4.setContent(new TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return equipmentListView;
            }
        });
        tabSpec4.setIndicator(equipmentTab.getText());

        tabWidget.removeAllViews();

        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);
        tabHost.addTab(tabSpec4);


        for (int i = 0; i < tabWidget.getTabCount(); i++) {
            View tabView = tabHost.getTabWidget().getChildTabViewAt(i);
            tabView.setPadding(0, 0, 0, 0);
            TextView tv = (TextView) tabView.findViewById(android.R.id.title);
            tv.setGravity(Gravity.CENTER);
            tabHost.setCurrentTab(i);
        }
        tabHost.setCurrentTab(0);

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
