package confusedgriffinproductions.dudesinadungeon;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ryan Patrick on 4/10/2017.
 */

public class ItemListAdapter extends ArrayAdapter<Item> {
    private final Activity context;
    private final ArrayList<Item> itemsList;
    private final int[] portraits;

    public ItemListAdapter(Activity context, ArrayList<Item> itemsList, int[] portraits){
        super(context, 0, itemsList);
        this.context = context;
        this.itemsList = itemsList;
        this.portraits = portraits;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater layoutInflater = context.getLayoutInflater();

        Log.d("position", "" + position);

        view = layoutInflater.inflate(R.layout.item_list_item, null, true);
        TextView itemName = (TextView)view.findViewById(R.id.item_name);
        TextView itemDescription = (TextView)view.findViewById(R.id.item_description);
        ImageView itemImage = (ImageView)view.findViewById(R.id.item_image);

        //Log.d("item", itemsList.get(position).getName() + " is item number " + position);

        itemName.setText(itemsList.get(position).getName());
        itemDescription.setText(itemsList.get(position).getDescription());

        if(itemsList.get(position).getId() == portraits[position]){
            itemImage.setImageResource(portraits[position]);
        }

        return view;
    }
}
