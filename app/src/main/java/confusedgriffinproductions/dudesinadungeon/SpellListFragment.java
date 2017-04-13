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
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpellListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpellListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpellListFragment extends Fragment {
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
    ListView allSpellsListView;
    ExpandableListView spellsByTypeListView;
    ExpandableListView spellsByClassListView;

    //create arraylists for the items
    ArrayList<Spell> allSpellsList;
    ArrayList<Spell> spellsByTypeList;
    ArrayList<Spell> spellsByClassList;

    public SpellListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpellListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SpellListFragment newInstance(String param1, String param2) {
        SpellListFragment fragment = new SpellListFragment();
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
        View view = inflater.inflate(R.layout.fragment_spell_list, container, false);

        //instantiate at start the TabHost
        tabHost = (TabHost) view.findViewById(R.id.items_tab_host);
        tabHost.setup();

        //instantiate the TabWidget
        tabWidget = tabHost.getTabWidget();

        DatabaseHandler db = new DatabaseHandler(getContext());

        allSpellsList = db.getAllSpells();
        Log.d("arrayList size", allSpellsList.size()+"");

        TextView allSpellsTab = (TextView) view.findViewById(R.id.all_spells_tab);
        TextView spellsByTypeTab = (TextView) view.findViewById(R.id.spells_by_type_tab);
        TextView spellsByClassTab = (TextView) view.findViewById(R.id.spells_by_class_tab);

        //instantiate the listviews
        allSpellsListView = (ListView)view.findViewById(R.id.all_spells_list);
        spellsByTypeListView = (ExpandableListView)view.findViewById(R.id.spells_by_type_list);
        spellsByClassListView = (ExpandableListView)view.findViewById(R.id.spells_by_class_list);

        allSpellsListView.setAdapter(new SpellListAdapter(getActivity(), allSpellsList));
        spellsByTypeListView.setAdapter(new SpellListExpandableListAdapter(getContext(), allSpellsList, "type"));
        spellsByClassListView.setAdapter(new SpellListExpandableListAdapter(getContext(), allSpellsList, "class"));

        // add views to tab host
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec((String) allSpellsTab.getTag());
        tabSpec1.setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return allSpellsListView;
            }
        });
        tabSpec1.setIndicator(allSpellsTab.getText());

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec((String) spellsByTypeTab.getTag());
        tabSpec2.setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return spellsByTypeListView;
            }
        });
        tabSpec2.setIndicator(spellsByTypeTab.getText());

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec((String) spellsByClassTab.getTag());
        tabSpec3.setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return spellsByClassListView;
            }
        });
        tabSpec3.setIndicator(spellsByClassTab.getText());

        tabWidget.removeAllViews();

        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);


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
