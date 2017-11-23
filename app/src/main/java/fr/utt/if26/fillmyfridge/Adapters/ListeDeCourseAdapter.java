package fr.utt.if26.fillmyfridge.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import fr.utt.if26.fillmyfridge.Objects.Ingredient;
import fr.utt.if26.fillmyfridge.Objects.ItemListeDeCourse;
import fr.utt.if26.fillmyfridge.Objects.ListeMenus;
import fr.utt.if26.fillmyfridge.Objects.Menu;
import fr.utt.if26.fillmyfridge.Objects.Repas;
import fr.utt.if26.fillmyfridge.R;

/**
 * Created by valygar on 21/11/2017.
 */

public class ListeDeCourseAdapter extends ArrayAdapter<ItemListeDeCourse> {

    private ArrayList<ItemListeDeCourse> listeDeCourses;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView ingredientRow;
    }

    public ListeDeCourseAdapter(ListeMenus data, Context context) {
        super(context, R.layout.row_liste_course, data.getIngredients());
        this.listeDeCourses = data.getIngredients();
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_liste_course, parent, false);
            viewHolder.ingredientRow = (TextView) convertView.findViewById(R.id.TV_ingredient_row);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = new ViewHolder();
            viewHolder.ingredientRow = (TextView) convertView.findViewById(R.id.TV_ingredient_row);
            result=convertView;
        }
        viewHolder.ingredientRow.setText(listeDeCourses.get(position).toString());
        // Return the completed view to render on screen
        return convertView;
    }
}

