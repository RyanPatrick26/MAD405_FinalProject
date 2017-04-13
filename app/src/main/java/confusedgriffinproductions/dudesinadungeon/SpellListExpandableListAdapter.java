package confusedgriffinproductions.dudesinadungeon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Ryan Patrick on 4/12/2017.
 */

public class SpellListExpandableListAdapter extends BaseExpandableListAdapter {
    private String groups[];
    private ArrayList<ArrayList<Spell>> children;
    private Context context;

    public SpellListExpandableListAdapter(Context context, ArrayList<Spell> spellList, String condition){
        children = new ArrayList<>();
        this.context = context;
        if(condition.equals("type")){
            this.groups = new String[]{
                    context.getResources().getString(R.string.offensive),
                    context.getResources().getString(R.string.buff),
                    context.getResources().getString(R.string.debuff),
                    context.getResources().getString(R.string.healing),
                    context.getResources().getString(R.string.support)
            };
            for(int i = 0; i < this.groups.length; i++){
                children.add(new ArrayList<Spell>());
                for(int j = 0; j < spellList.size(); j++){
                    if(spellList.get(j).getSpellType().equals(groups[i])){
                        children.get(i).add(spellList.get(j));
                    }
                }
            }
        }
        else if(condition.equals("class")){
            this.groups = new String[]{
                    context.getResources().getString(R.string.warrior),
                    context.getResources().getString(R.string.rogue),
                    context.getResources().getString(R.string.wizard),
                    context.getResources().getString(R.string.priest)
            };
            for(int i = 0; i < this.groups.length; i++){
                children.add(new ArrayList<Spell>());
                for(int j = 0; j < spellList.size(); j++){
                    if(spellList.get(j).getSpellClass().equals(groups[i])){
                        children.get(i).add(spellList.get(j));
                    }
                }
            }
        }
    }

    @Override
    public int getGroupCount() {
        return this.groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spell_list_headers, parent, false);

        TextView headerText = (TextView)convertView.findViewById(R.id.header_text);
        headerText.setText(getGroup(groupPosition).toString());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spell_list_sub_menu, parent, false);

        TextView spellName = (TextView)convertView.findViewById(R.id.spell_name);
        TextView spellDescription = (TextView)convertView.findViewById(R.id.spell_description);

        spellName.setText(children.get(groupPosition).get(childPosition).getName());
        spellDescription.setText(children.get(groupPosition).get(childPosition).getDescription());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
