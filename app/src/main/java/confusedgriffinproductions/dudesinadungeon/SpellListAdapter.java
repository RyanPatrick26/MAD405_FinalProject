package confusedgriffinproductions.dudesinadungeon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Ryan Patrick on 4/11/2017.
 */

public class SpellListAdapter extends ArrayAdapter<Spell>{
    private final Activity context;
    private final ArrayList<Spell> spellList;

    public SpellListAdapter(Activity context, ArrayList<Spell> spellList){
        super(context, 0, spellList);
        this.context = context;
        this.spellList = spellList;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater layoutInflater = context.getLayoutInflater();

        view = layoutInflater.inflate(R.layout.spell_list_item, null, true);

        TextView itemName = (TextView)view.findViewById(R.id.spell_name);
        TextView itemDescription = (TextView)view.findViewById(R.id.spell_description);

        itemName.setText(spellList.get(position).getName());
        itemDescription.setText(spellList.get(position).getDescription());

        return view;
    }
}
